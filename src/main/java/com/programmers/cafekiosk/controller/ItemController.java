package com.programmers.cafekiosk.controller;

import com.programmers.cafekiosk.dto.CreateItemRequest;
import com.programmers.cafekiosk.dto.GetItemsRequest;
import com.programmers.cafekiosk.dto.ItemResponse;
import com.programmers.cafekiosk.dto.UpdateItemRequest;
import com.programmers.cafekiosk.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Long> createItem(@RequestBody CreateItemRequest request) {
        Long itemId = itemService.createItem(request);
        return ResponseEntity.created(URI.create("/items/" + itemId)).body(itemId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable Long id) {
        ItemResponse itemResponse = itemService.getItem(id);
        return ResponseEntity.ok(itemResponse);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getItems(GetItemsRequest request) {
        List<ItemResponse> items = itemService.getItems(request);
        return ResponseEntity.ok(items);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(@PathVariable Long id, @RequestBody UpdateItemRequest request) {
        itemService.updateItem(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
