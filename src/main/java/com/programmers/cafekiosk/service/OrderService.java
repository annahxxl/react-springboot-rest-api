package com.programmers.cafekiosk.service;

import com.programmers.cafekiosk.dto.CreateOrderItemRequest;
import com.programmers.cafekiosk.dto.CreateOrderRequest;
import com.programmers.cafekiosk.dto.OrderItemResponse;
import com.programmers.cafekiosk.dto.OrderResponse;
import com.programmers.cafekiosk.entity.Item;
import com.programmers.cafekiosk.entity.Order;
import com.programmers.cafekiosk.entity.OrderItem;
import com.programmers.cafekiosk.repository.ItemRepository;
import com.programmers.cafekiosk.repository.OrderRepository;
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
    private final ItemRepository itemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));
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

    public Long createOrder(CreateOrderRequest request) {
        Set<Long> itemIds = request.orderitems().stream()
                .map(CreateOrderItemRequest::id)
                .collect(Collectors.toSet());

        List<Item> items = itemRepository.findAllById(itemIds);

        Map<Long, Item> itemMap = items.stream()
                .collect(Collectors.toMap(Item::getId, item -> item));

        if (itemMap.size() != itemIds.size()) {
            throw new IllegalArgumentException("존재하지 않는 상품이 포함되어 있습니다.");
        }

        Order order = new Order();
        long totalPrice = 0L;

        for (CreateOrderItemRequest orderItemRequest : request.orderitems()) {
            Item item = itemMap.get(orderItemRequest.id());

            totalPrice += (long) item.getPrice() * orderItemRequest.quantity();

            OrderItem orderItem = new OrderItem(
                    item.getType(),
                    item.getName(),
                    (long) item.getPrice(),
                    orderItemRequest.quantity(),
                    item,
                    order
            );

            order.addOrderItem(orderItem);
        }

        order.updateTotalPrice(totalPrice);

        return orderRepository.save(order).getId();
    }
}
