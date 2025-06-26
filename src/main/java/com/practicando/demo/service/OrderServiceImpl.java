package com.practicando.demo.service;

import com.practicando.demo.dto.OrderRequestDTO;
import com.practicando.demo.entity.*;
import com.practicando.demo.exception.InsufficientStockException;
import com.practicando.demo.repository.CustomerRepository;
import com.practicando.demo.repository.OrderRepository;
import com.practicando.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public Order placeOrder(OrderRequestDTO request) {

        // 1. Obtener cliente
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // 2. Obtener productos y mapear por id
        List<Long> productIds = request.getItems().stream()
                .map(OrderRequestDTO.ItemRequest::getProductId)
                .toList();

        Map<Long, Product> products = productRepository.findByIdIn(productIds).stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        // 3. Validar stock disponible
        for (OrderRequestDTO.ItemRequest item : request.getItems()) {
            Product p = products.get(item.getProductId());
            if (p == null)
                throw new IllegalArgumentException("Product " + item.getProductId() + " not found");

            if (p.getStock() < item.getQuantity())
                throw new InsufficientStockException(
                        "Stock insuficiente para producto " + p.getName());
        }

        // 4. Crear Order y OrderItems
        Order order = Order.builder()
                .customer(customer)
                .orderDate(Instant.now())
                .build();

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderRequestDTO.ItemRequest item : request.getItems()) {
            Product p = products.get(item.getProductId());

            // restar stock
            p.setStock(p.getStock() - item.getQuantity());

            OrderItem oi = OrderItem.builder()
                    .id(new OrderItemId(null, p.getId())) // orderId se asigna después
                    .order(order)
                    .product(p)
                    .quantity(item.getQuantity())
                    .price(p.getPrice())
                    .build();

            orderItems.add(oi);
        }
        order.setItems(orderItems);

        // 5. Guardar todo (cascade = ALL en Order.items se encarga de OrderItem)
        return orderRepository.save(order);
    }
}
