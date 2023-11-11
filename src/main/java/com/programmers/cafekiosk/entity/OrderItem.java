package com.programmers.cafekiosk.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "order_items")
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    private String name;

    private Long price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "item_id") //! FK
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id") //! FK
    private Order order;

    protected OrderItem() {
    }

    public OrderItem(ItemType type, String name, Long price, Integer quantity, Item item, Order order) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.item = item;
        this.order = order;
        order.getOrderItems().add(this);
    }
}
