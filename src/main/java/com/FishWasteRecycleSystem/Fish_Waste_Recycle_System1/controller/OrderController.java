package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.controller;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.OrderDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.OrderRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllorders()
    {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable  Long orderId){

        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PostMapping
    public ResponseEntity<OrderDto> createNewOrder(
            @Valid @RequestBody OrderRequestDto orderRequestDto) {

        OrderDto order = orderService.createNewOrder(orderRequestDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(
            @PathVariable Long orderId,
            @Valid @RequestBody OrderRequestDto orderRequestDto) {

        return ResponseEntity.ok(
               orderService.updateOrder(
                       orderId,
                        orderRequestDto));
    }

    // Partial Update
    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderDto> updatePartialOrder(
            @PathVariable Long orderId,
            @RequestBody Map<String, Object> updates) {

        return ResponseEntity.ok(
                orderService.updatePartialOrder(
                        orderId,
                        updates));
    }

    // Delete Listing
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(
            @PathVariable Long orderId) {

        orderService.deleteOrderById(orderId);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable Long orderId) {

        return ResponseEntity.ok(
                orderService.cancelOrder(orderId)
        );
    }
}
