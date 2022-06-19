package com.example.spring_boot_rest_api.goods;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Good {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    @NotNull
    private Long amount;

    public Good() {
    }

    public Good(String id, String name, Long amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
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

    @Override
    public String toString() {
        return "Good{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
