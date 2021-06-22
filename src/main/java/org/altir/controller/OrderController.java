package org.altir.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.altir.dto.CreateOrderDto;
import org.altir.dto.OrderDto;
import org.altir.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    public final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody CreateOrderDto order) {
        return ResponseEntity.of(orderService.create(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> get(@PathVariable  Long id) {
        return ResponseEntity.of(orderService.get(id));
    }

}
