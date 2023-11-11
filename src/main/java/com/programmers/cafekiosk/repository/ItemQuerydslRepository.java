package com.programmers.cafekiosk.repository;

import com.programmers.cafekiosk.dto.GetItemsRequest;
import com.programmers.cafekiosk.entity.Item;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.programmers.cafekiosk.entity.QItem.item;

@Component
public class ItemQuerydslRepository {

    private final JPAQueryFactory queryFactory;

    public ItemQuerydslRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Item> findAll(GetItemsRequest request) {
        BooleanBuilder builder = new BooleanBuilder();

        if (request.type() != null) {
            builder.and(item.type.eq(request.type()));
        }

        if (request.status() != null) {
            builder.and(item.status.eq(request.status()));
        }

        if (request.ids() != null) {
            builder.and(item.id.in(request.ids()));
        }

        return queryFactory
                .selectFrom(item)
                .where(builder)
                .fetch();
    }
}
