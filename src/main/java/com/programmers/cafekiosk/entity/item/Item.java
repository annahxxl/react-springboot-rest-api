package com.programmers.cafekiosk.entity.item;

import com.programmers.cafekiosk.dto.item.request.UpdateItemRequest;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "items")
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    private String description;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected Item() {
    }

    public Item(Long id, ItemType type, ItemStatus status, String name, Long price, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.name = name;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Item(ItemType type, ItemStatus status, String name, Long price, String description) {
        this.type = type;
        this.status = status;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void update(UpdateItemRequest request) {
        this.type = request.type();
        this.status = request.status();
        this.name = request.name();
        this.price = request.price();
        this.description = request.description();
        this.updatedAt = LocalDateTime.now();
    }
}
