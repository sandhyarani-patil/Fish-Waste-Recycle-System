package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.impl;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.OrderDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.OrderRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.Order;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.Requirement;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.Seller;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.WasteListing;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.FishWasteStatus;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.OrderStatus;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.RequirementStatus;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.OrderRepository;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.RequirementRepository;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.SellerRepository;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.WasteListingRepository;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RequirementRepository requirementRepository;
    private final WasteListingRepository wasteListingRepository;
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<OrderDto> getAllOrders()
    {
         return orderRepository.findAll()
                 .stream()
                 .map(order-> {
                     OrderDto dto = modelMapper.map(order, OrderDto.class);
                     dto.setWasteListingId(order.getWasteListing().getWasteListingId());
                     dto.setRequirementId(order.getRequirement().getRequirementId());
                     return dto;
                 }).toList();
    }

    @Override
    public OrderDto getOrderById(Long orderId){
            Order order=orderRepository.findById(orderId).
                    orElseThrow(()->new IllegalArgumentException("Order not found with id"+orderId));
            OrderDto dto=modelMapper.map(order,OrderDto.class);
            dto.setWasteListingId(order.getWasteListing().getWasteListingId());
            dto.setRequirementId(order.getRequirement().getRequirementId());

            return  dto;
    }

    @Override
    @Transactional
    public OrderDto createNewOrder(OrderRequestDto orderRequestDto)
    {
        WasteListing listing=wasteListingRepository.findById(orderRequestDto.getListingId()).
                orElseThrow(()-> new IllegalArgumentException("Listing not found"));

        Requirement requirement=requirementRepository.findById(orderRequestDto.getRequirementId()).
                orElseThrow(()->new IllegalArgumentException("Requirement not found"));

        if (orderRequestDto.getOrderQuantity().compareTo(listing.getQuantity()) > 0) {
            throw new IllegalArgumentException("Ordered quantity exceeds available quantity");
        }


        Order neworder=new Order();
        neworder.setOrderQuantity(orderRequestDto.getOrderQuantity());
        neworder.setTotalAmount(
                listing.getPricePerKg().multiply(orderRequestDto.getOrderQuantity())
        );
        neworder.setPickupDate(orderRequestDto.getPickupDate());
        neworder.setStatus(OrderStatus.PENDING);
        neworder.setWasteListing(listing);
        neworder.setRequirement(requirement);

        Order savedOrder=orderRepository.save(neworder);
        // =========================
        // 1. Update Waste Listing
        // =========================
        listing.setQuantity(
                listing.getQuantity().subtract(orderRequestDto.getOrderQuantity())
        );

        if (listing.getQuantity().compareTo(BigDecimal.ZERO) == 0) {
            listing.setStatus(FishWasteStatus.SOLD);
        } else {
            listing.setStatus(FishWasteStatus.AVAILABLE);
        }

        wasteListingRepository.save(listing);

        // =========================
        // 2. Update Requirement
        // =========================
        requirement.setQuantity(
                requirement.getQuantity() - orderRequestDto.getOrderQuantity().doubleValue()
        );

        if (requirement.getQuantity() <= 0) {
            requirement.setQuantity(0.0);
            requirement.setStatus(RequirementStatus.FULFILLED);
        } else {
            requirement.setStatus(RequirementStatus.OPEN);
        }

        requirementRepository.save(requirement);

        // =========================
        // 3. Update Seller
        // =========================
        Seller seller = listing.getSeller();

        seller.setAvailableFishWasteKg(
                seller.getAvailableFishWasteKg()
                        - orderRequestDto.getOrderQuantity().intValue()
        );

        sellerRepository.save(seller);
        OrderDto dto=modelMapper.map(savedOrder,OrderDto.class);
        dto.setWasteListingId(savedOrder.getWasteListing().getWasteListingId());
        dto.setRequirementId(savedOrder.getRequirement().getRequirementId());
        return  dto;
    }


    @Override
    public void deleteOrderById(Long orderId){

        if (!orderRepository.existsById(orderId)) {
            throw new IllegalArgumentException("Order does not exist with id: " + orderId);
        }

      orderRepository.deleteById(orderId);

    }

    @Override
    public OrderDto updateOrder(Long orderId,OrderRequestDto orderRequestDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id " + orderId));

        WasteListing listing = wasteListingRepository.findById(orderRequestDto.getListingId()).
                orElseThrow(() -> new IllegalArgumentException("Listing not found"));

        Requirement requirement = requirementRepository.findById(orderRequestDto.getRequirementId()).
                orElseThrow(() -> new IllegalArgumentException("Requirement not found"));

        if (orderRequestDto.getOrderQuantity().compareTo(listing.getQuantity()) > 0) {
            throw new IllegalArgumentException("Ordered quantity exceeds available quantity");
        }


        order.setOrderQuantity(orderRequestDto.getOrderQuantity());
        order.setTotalAmount(
                listing.getPricePerKg().multiply(orderRequestDto.getOrderQuantity())
        );
        order.setPickupDate(orderRequestDto.getPickupDate());

        order.setWasteListing(listing);
        order.setRequirement(requirement);

        Order savedOrder = orderRepository.save(order);
        OrderDto dto = modelMapper.map(savedOrder, OrderDto.class);
        dto.setWasteListingId(savedOrder.getWasteListing().getWasteListingId());
        dto.setRequirementId(savedOrder.getRequirement().getRequirementId());
        return dto;

    }



    @Override
    public OrderDto updatePartialOrder(Long orderId, Map<String, Object> updates) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Order not found with id " + orderId));

        updates.forEach((field, value) -> {

            switch (field) {

                case "orderQuantity":

                    BigDecimal quantity = new BigDecimal(value.toString());

                    WasteListing listing = order.getWasteListing();

                    if (quantity.compareTo(listing.getQuantity()) > 0) {
                        throw new IllegalArgumentException("Ordered quantity exceeds available quantity");
                    }

                    order.setOrderQuantity(quantity);

                    order.setTotalAmount(
                            listing.getPricePerKg().multiply(quantity)
                    );
                    break;

                case "pickupDate":
                    order.setPickupDate(LocalDate.parse(value.toString()));
                    break;

                case "status":
                    order.setStatus(OrderStatus.valueOf(value.toString()));
                    break;

                case "listingId":

                    WasteListing wasteListing = wasteListingRepository
                            .findById(Long.valueOf(value.toString()))
                            .orElseThrow(() ->
                                    new IllegalArgumentException("Listing not found"));

                    order.setWasteListing(wasteListing);

                    order.setTotalAmount(
                            wasteListing.getPricePerKg()
                                    .multiply(order.getOrderQuantity())
                    );
                    break;

                case "requirementId":

                    Requirement requirement = requirementRepository
                            .findById(Long.valueOf(value.toString()))
                            .orElseThrow(() ->
                                    new IllegalArgumentException("Requirement not found"));

                    order.setRequirement(requirement);
                    break;

                default:
                    throw new IllegalArgumentException("Invalid field : " + field);
            }
        });

        Order savedOrder = orderRepository.save(order);

        OrderDto dto = modelMapper.map(savedOrder, OrderDto.class);

        dto.setWasteListingId(savedOrder.getWasteListing().getWasteListingId());
        dto.setRequirementId(savedOrder.getRequirement().getRequirementId());

        return dto;
    }

    @Override
    public OrderDto cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Order not found"));

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new IllegalArgumentException("Order is already cancelled");
        }

        WasteListing listing = order.getWasteListing();
        Requirement requirement = order.getRequirement();
        Seller seller = listing.getSeller();

        // Restore Listing Quantity
        listing.setQuantity(
                listing.getQuantity().add(order.getOrderQuantity())
        );

        // Restore Listing Status
        if (listing.getQuantity().compareTo(BigDecimal.ZERO) > 0) {
            listing.setStatus(FishWasteStatus.AVAILABLE);
        }

        // Restore Seller Available Waste
        seller.setAvailableFishWasteKg(
                seller.getAvailableFishWasteKg()
                        + order.getOrderQuantity().intValue()
        );

        // Restore Requirement Quantity
        requirement.setQuantity(
                requirement.getQuantity()
                        + order.getOrderQuantity().doubleValue()
        );

        // Restore Requirement Status
        if (requirement.getQuantity() > 0) {
            requirement.setStatus(RequirementStatus.OPEN);
        }

        // Cancel Order
        order.setStatus(OrderStatus.CANCELLED);

        wasteListingRepository.save(listing);
        requirementRepository.save(requirement);
        orderRepository.save(order);

        OrderDto dto = modelMapper.map(order, OrderDto.class);
        dto.setWasteListingId(listing.getWasteListingId());
        dto.setRequirementId(requirement.getRequirementId());

        return dto;
    }
}
