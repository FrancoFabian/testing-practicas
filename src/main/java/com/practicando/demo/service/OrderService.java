package com.practicando.demo.service;

import com.practicando.demo.dto.OrderRequestDTO;
import com.practicando.demo.entity.Order;

public interface OrderService {
    Order placeOrder(OrderRequestDTO request);
}
