package com.programmers.cafekiosk.repository.item;

import com.programmers.cafekiosk.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
