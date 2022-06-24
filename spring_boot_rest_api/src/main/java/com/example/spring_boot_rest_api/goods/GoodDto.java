package com.example.spring_boot_rest_api.goods;

import com.example.spring_boot_rest_api.validator.constraint.EnumConstraint;
import com.example.spring_boot_rest_api.validator.constraint.PriceAndTypeMatchConstraint;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@PriceAndTypeMatchConstraint()
public class GoodDto {

    private String id;

    private String name;

    @NotNull
    private Long amount;

    @EnumConstraint(targetClassType = Type.class)
    @NotNull
    private String type;

    @Digits(integer = 19, fraction = 0)
    private Double price;

    private String parentId;

    public GoodDto() {
    }

    public GoodDto(String id, String name, Long amount, String type, Double price, String parentId) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.price = price;
        this.parentId = parentId;
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

    public Long getPrice() {
        return price.longValue();
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", parentId='" + parentId + '\'' +
                '}';
    }

}
