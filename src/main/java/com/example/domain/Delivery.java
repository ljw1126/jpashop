package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.Objects;

import static jakarta.persistence.FetchType.*;

@Entity
public class Delivery extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    private String city;
    private String street;
    private String zipcode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Delivery delivery = (Delivery) other;
        return Objects.equals(id, delivery.id) && Objects.equals(city, delivery.city) && Objects.equals(street, delivery.street) && Objects.equals(zipcode, delivery.zipcode) && status == delivery.status && Objects.equals(order, delivery.order) && Objects.equals(address, delivery.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, zipcode, status, order, address);
    }
}
