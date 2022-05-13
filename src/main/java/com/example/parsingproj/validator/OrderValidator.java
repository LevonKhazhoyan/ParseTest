package com.example.parsingproj.validator;

import com.example.parsingproj.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    private final Validator validator;

    public void validateBean(Order order) {
        val validatorMessage = new StringBuilder();
        for (ConstraintViolation<Order> violation : validator.validate(order)) {
            validatorMessage.append(violation.getPropertyPath()).append(" ").append(violation.getMessage()).append("; ");
        }
        val result = validatorMessage.toString();
        if (!result.isEmpty()) {
            throw new IllegalArgumentException(result.substring(0, result.length() - 2));
        }
    }
}
