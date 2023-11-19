package com.programmers.cafekiosk.service;

import com.programmers.cafekiosk.dto.item.request.GetItemsRequest;
import com.programmers.cafekiosk.dto.order.CreateOrderRequest;
import com.programmers.cafekiosk.dto.order.OrderResponse;
import com.programmers.cafekiosk.dto.orderItem.CreateOrderItemRequest;
import com.programmers.cafekiosk.dto.orderItem.OrderItemResponse;
import com.programmers.cafekiosk.entity.Order;
import com.programmers.cafekiosk.entity.OrderItem;
import com.programmers.cafekiosk.entity.item.Item;
import com.programmers.cafekiosk.entity.item.ItemStatus;
import com.programmers.cafekiosk.exception.NotFoundException;
import com.programmers.cafekiosk.repository.OrderItemRepository;
import com.programmers.cafekiosk.repository.OrderRepository;
import com.programmers.cafekiosk.repository.item.ItemQuerydslRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemQuerydslRepository itemQuerydslRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemQuerydslRepository itemQuerydslRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.itemQuerydslRepository = itemQuerydslRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 주문입니다."));
        return OrderResponse.from(
                order.getId(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getOrderItems().stream()
                        .map(orderItem -> OrderItemResponse.from(
                                orderItem.getId(),
                                orderItem.getType(),
                                orderItem.getItem().getName(),
                                orderItem.getPrice(),
                                orderItem.getQuantity()
                        ))
                        .collect(Collectors.toList())
        );
    }

    private Map<Long, Item> getAvailableItemsMap(List<CreateOrderItemRequest> request) {
        Set<Long> ids = request.stream()
                .map(CreateOrderItemRequest::id)
                .collect(Collectors.toSet());

        Map<Long, Item> itemsMap = itemQuerydslRepository.findAll(new GetItemsRequest(null, ItemStatus.AVAILABLE, ids)).stream()
                .collect(Collectors.toMap(Item::getId, item -> item));

        if (itemsMap.size() != ids.size()) {
            throw new NotFoundException("존재하지 않는 상품이 포함되어 있습니다.");
        }

        return itemsMap;
    }

    private Long calculateTotalPrice(List<CreateOrderItemRequest> request, Map<Long, Item> itemsMap) {
        return request.stream()
                .mapToLong(createOrderItemRequest -> itemsMap.get(createOrderItemRequest.id()).getPrice() * createOrderItemRequest.quantity())
                .sum();
    }

    public Long createOrder(CreateOrderRequest request) {
        Map<Long, Item> itemsMap = getAvailableItemsMap(request.orderitems());
        Order order = new Order(calculateTotalPrice(request.orderitems(), itemsMap));

        for (CreateOrderItemRequest createOrderItemRequest : request.orderitems()) {
            Item item = itemsMap.get(createOrderItemRequest.id());

            OrderItem orderItem = new OrderItem(
                    item.getType(),
                    item.getName(),
                    item.getPrice(),
                    createOrderItemRequest.quantity(),
                    item,
                    order
            );

            order.addOrderItem(orderItem);
        }

        return orderRepository.save(order).getId();
    }
}
