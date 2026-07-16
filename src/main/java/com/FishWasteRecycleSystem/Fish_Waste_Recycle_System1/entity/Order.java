package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="listing_id")
    private WasteListing wasteListing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="requirement_id")
    private Requirement requirement;

    private BigDecimal orderQuantity;

    private BigDecimal totalAmount;

    private LocalDate pickupDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @CreationTimestamp
    private LocalDateTime orderedAt;
}
