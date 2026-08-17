package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.OrderDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.OrderRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.OrderStatus;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long orderId);

    OrderDto createNewOrder(OrderRequestDto orderRequestDto);

    void deleteOrderById(Long orderId);

    OrderDto updateOrder(Long orderId,OrderRequestDto orderRequestDto);

    OrderDto cancelOrder(Long orderId);

    OrderDto updatePartialOrder(Long orderId,Map<String,Object> updates);


    OrderDto updateOrderStatus(Long orderId, OrderStatus status);

    OrderDto acceptOrder(Long orderId);
}
