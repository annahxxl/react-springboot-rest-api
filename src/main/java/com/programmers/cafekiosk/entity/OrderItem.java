package com.programmers.cafekiosk.entity;

import com.programmers.cafekiosk.entity.item.Item;
import com.programmers.cafekiosk.entity.item.ItemType;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "order_items")
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false) //! FK
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false) //! FK
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
