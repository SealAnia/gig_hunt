package com.example.gig_hunt.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long companyId;

    @Column(name = "name", columnDefinition = "varchar(225)", nullable = false)
    @ToString.Include
    private String name;

    @Column(name = "registration_number", columnDefinition = "bigint", nullable = false, unique = true, length = 10)
    @ToString.Include
    private Long registrationNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ToString.Include
    private Master user;

}
