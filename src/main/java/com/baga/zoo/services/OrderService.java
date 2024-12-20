package com.baga.zoo.services;

import com.baga.zoo.dto.OrderRequest;
import com.baga.zoo.dto.OrderDTO;
import com.baga.zoo.dto.OrderStatusRequest;
import com.baga.zoo.entity.Order;
import com.baga.zoo.exception.OrderNotFoundException;
import com.baga.zoo.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDTO createOrder(OrderRequest request) {
        logger.info("Creating order for customer: {} with amount: {}", request.getCustomerName(), request.getTotalAmount());
        Order order = new Order();
        order.setTotalAmount(request.getTotalAmount());
        order.setCustomerName(request.getCustomerName());

        OrderDTO orderDTO = getOrderDTO(order);
        logger.info("Order created with ID: {}", orderDTO.getId());
        return orderDTO;
    }

    public OrderDTO getOrderById(Long id) {
        logger.info("Fetching order with ID: {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id));

        OrderDTO orderDto = new OrderDTO();
        orderDto.setId(order.getId());
        orderDto.setCustomerName(order.getCustomerName());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setStatus(order.getStatus().name());

        logger.info("Fetched order: {}", orderDto);
        return orderDto;
    }

    public OrderDTO updateOrderStatus(OrderStatusRequest request) {
        logger.info("Updating status for order ID: {} to {}", request.getOrderId(), request.getNewStatus());
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + request.getOrderId()));

        order.setStatus(request.getNewStatus());
        OrderDTO orderDTO = getOrderDTO(order);
        logger.info("Updated order ID: {} to status: {}", orderDTO.getId(), orderDTO.getStatus());
        return orderDTO;
    }

    private OrderDTO getOrderDTO(Order order) {
        logger.debug("Saving order: {}", order);
        Order savedOrder = orderRepository.save(order);

        OrderDTO orderDto = new OrderDTO();
        orderDto.setId(savedOrder.getId());
        orderDto.setCustomerName(savedOrder.getCustomerName());
        orderDto.setTotalAmount(savedOrder.getTotalAmount());
        orderDto.setStatus(savedOrder.getStatus().name());

        logger.debug("Order saved: {}", orderDto);
        return orderDto;
    }
}

