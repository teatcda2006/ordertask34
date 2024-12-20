package com.baga.zoo.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private String customerName;
    private Double totalAmount;
    private String status;
}
