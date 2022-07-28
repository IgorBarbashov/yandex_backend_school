package com.example.spring_boot_rest_api.validator.constraint;

import com.example.spring_boot_rest_api.goods.Type;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceAndTypeMatchValidator implements ConstraintValidator<PriceAndTypeMatchConstraint, Object> {

    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Double priceValue = (Double) new BeanWrapperImpl(value).getPropertyValue("price");
        String typeValue = (String) new BeanWrapperImpl(value).getPropertyValue("type");

        if (typeValue.equals(Type.CATEGORY.name())) {
            return priceValue == null;
        } else if (typeValue.equals(Type.OFFER.name())) {
            return priceValue != null && priceValue >= 0 && priceValue <= Long.MAX_VALUE;
        }

        return false;
    }
}
