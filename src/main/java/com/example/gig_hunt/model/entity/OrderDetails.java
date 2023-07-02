package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class OrderDetails {

    private static Log log = LogFactory.getLog(OrderDetails.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long orderId;

    @Column(name = "date", columnDefinition = "date", nullable = false)
    @ToString.Include
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ToString.Include
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ToString.Include
    private Goods goods;

    @Column(name = "quantity", columnDefinition = "number default = 1", nullable = false)
    @ToString.Include
    private int quantity;

    @Transient
    private Double cost;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    @PrePersist
    public void logNewOrderDetailsAttempt() {
        log.info("Attempting to add new order with id: " + orderId);
    }

    @PostPersist
    public void logNewOrderDetailsAdd() {
        log.info("Added order with ID: " + orderId);
    }

    @PostLoad
    public void countCost() {
        Master master = goods.getMaster();
        log.info(master.getNickname());
        cost = quantity * goods.getPrice();
        log.info("COST = " + cost);
    }

}
