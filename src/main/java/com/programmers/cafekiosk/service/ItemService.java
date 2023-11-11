package com.programmers.cafekiosk.service;

import com.programmers.cafekiosk.dto.item.request.CreateItemRequest;
import com.programmers.cafekiosk.dto.item.request.GetItemsRequest;
import com.programmers.cafekiosk.dto.item.response.ItemResponse;
import com.programmers.cafekiosk.dto.item.request.UpdateItemRequest;
import com.programmers.cafekiosk.entity.item.Item;
import com.programmers.cafekiosk.entity.item.ItemStatus;
import com.programmers.cafekiosk.exception.NotFoundException;
import com.programmers.cafekiosk.repository.item.ItemQuerydslRepository;
import com.programmers.cafekiosk.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemQuerydslRepository itemQuerydslRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemQuerydslRepository itemQuerydslRepository) {
        this.itemRepository = itemRepository;
        this.itemQuerydslRepository = itemQuerydslRepository;
    }

    public Long createItem(CreateItemRequest request) {
        Item item = new Item(
                request.type(),
                ItemStatus.AVAILABLE,
                request.name(),
                request.price(),
                request.description()
        );

        return itemRepository.save(item).getId();
    }

    @Transactional(readOnly = true)
    public ItemResponse getItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 상품입니다."));
        return ItemResponse.from(
                item.getId(),
                item.getType(),
                item.getStatus(),
                item.getName(),
                item.getPrice(),
                item.getDescription(),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> getItems(GetItemsRequest request) {
        return itemQuerydslRepository.findAll(request).stream()
                .map(item -> ItemResponse.from(
                        item.getId(),
                        item.getType(),
                        item.getStatus(),
                        item.getName(),
                        item.getPrice(),
                        item.getDescription(),
                        item.getCreatedAt(),
                        item.getUpdatedAt()
                ))
                .toList();
    }

    public void updateItem(Long id, UpdateItemRequest request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 상품입니다."));
        item.update(request);
    }

    public void deleteItem(Long id) {
        itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 상품입니다."));
        itemRepository.deleteById(id);
    }
}
