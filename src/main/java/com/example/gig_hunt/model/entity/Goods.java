package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "goods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long goodsId;

    @ManyToOne
    @JoinColumn(name = "master_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ToString.Include
    private Master master;

    @Column(name = "price", columnDefinition = "double", nullable = false)
    @ToString.Include
    private Double price;

    @Column(name = "description", columnDefinition = "varchar(225)", nullable = false)
    @ToString.Include
    private String description;

    @ManyToMany(mappedBy = "basket", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Customer> customers;

}
