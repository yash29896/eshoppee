package com.eshoppee.orderservice.controller;

import com.eshoppee.orderservice.dto.OrderRequest;
import com.eshoppee.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest)
    {
        orderService.placeOrder(orderRequest);
        return "Order placed Successfully";
    }
}
