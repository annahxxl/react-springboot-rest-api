package com.programmers.cafekiosk.entity;

import com.programmers.cafekiosk.dto.UpdateItemRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "items")
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    private String name;

    private Integer price;

    private String description;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Item(Long id, ItemType type, ItemStatus status, String name, Integer price, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.name = name;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Item(ItemType type, ItemStatus status, String name, Integer price, String description) {
        this.type = type;
        this.status = status;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void update(UpdateItemRequest request) {
        this.type = request.type();
        this.name = request.name();
        this.price = request.price();
        this.description = request.description();
    }
}
