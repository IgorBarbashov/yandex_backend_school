package com.example.spring_boot_rest_api.goods;

import com.example.spring_boot_rest_api.validator.constraint.EnumConstraint;
import com.example.spring_boot_rest_api.validator.constraint.PriceAndTypeMatchConstraint;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@PriceAndTypeMatchConstraint()
public class Good {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    @NotNull
    private Long amount;

    @Column(name = "type")
    @EnumConstraint(targetClassType = Type.class)
    @NotNull
    private String type;

    @Column(name = "price")
    @Digits(integer = 19, fraction = 0)
    private Double price;

    public Good() {
    }

    public Good(String id, String name, Long amount, String type, Double price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
