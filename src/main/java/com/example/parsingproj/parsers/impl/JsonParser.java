package com.example.parsingproj.parsers.impl;

import com.example.parsingproj.domain.Order;
import com.example.parsingproj.parsers.Parser;
import com.example.parsingproj.validator.OrderValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonParser extends Parser {

    private final ObjectMapper objectMapper;
    @Autowired
    private final OrderValidator orderValidator;

    @Override
    public String transform(Long line, String strToParse, String fileName) throws JsonProcessingException {
        val order = objectMapper.readValue(strToParse, Order.class);
        orderValidator.validateBean(order);
        return validOut(
                order,
                fileName,
                line);
    }
}
