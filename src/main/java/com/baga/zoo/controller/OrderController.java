package com.baga.zoo.controller;

import com.baga.zoo.dto.OrderRequest;
import com.baga.zoo.dto.OrderDTO;
import com.baga.zoo.dto.OrderStatusRequest;
import com.baga.zoo.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@RequestBody OrderStatusRequest request) {
        return ResponseEntity.ok(orderService.updateOrderStatus(request));
    }
}
