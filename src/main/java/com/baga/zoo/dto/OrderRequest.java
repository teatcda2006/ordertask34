package com.baga.zoo.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private String customerName;
    private Double totalAmount;
}
