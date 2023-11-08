package com.programmers.cafekiosk.repository;

import com.programmers.cafekiosk.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
