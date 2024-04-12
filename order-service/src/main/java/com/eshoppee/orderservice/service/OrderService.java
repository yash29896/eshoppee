package com.eshoppee.orderservice.service;

import com.eshoppee.orderservice.dto.OrderLineItemDto;
import com.eshoppee.orderservice.dto.OrderRequest;
import com.eshoppee.orderservice.model.Order;
import com.eshoppee.orderservice.model.OrderLineItem;
import com.eshoppee.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest)
    {
        List<OrderLineItem> orderLineItemList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToOrderLineItems)
                .toList();

        Order order = Order.builder()
                .orderLineItemsList(orderLineItemList)
                .orderNumber(UUID.randomUUID().toString())
                .build();

        orderRepository.save(order);
    }

    private OrderLineItem mapToOrderLineItems(OrderLineItemDto orderLineItemDto) {
        return OrderLineItem.builder()
                .price(orderLineItemDto.getPrice())
                .quantity(orderLineItemDto.getQuantity())
                .skuCode(orderLineItemDto.getSkuCode())
                .build();
    }
}
