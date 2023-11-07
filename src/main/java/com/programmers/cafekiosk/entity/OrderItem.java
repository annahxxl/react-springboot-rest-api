package com.programmers.cafekiosk.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    private String name;

    private long price;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "item_id") //! FK
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id") //! FK
    private Order order;
}
