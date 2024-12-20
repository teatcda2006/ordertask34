package com.baga.zoo.dto;

import com.baga.zoo.entity.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderStatusRequest {
    private Long orderId;
    private OrderStatus newStatus;
}
