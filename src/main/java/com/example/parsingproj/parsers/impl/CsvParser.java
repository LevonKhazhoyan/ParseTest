package com.example.parsingproj.parsers.impl;

import com.example.parsingproj.domain.Order;
import com.example.parsingproj.parsers.Parser;
import com.example.parsingproj.validator.OrderValidator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CsvParser extends Parser {

    private final CsvMapper csvMapper;
    private final CsvSchema schema;
    @Autowired
    private final OrderValidator orderValidator;

    @Override
    public String transform(Long line, String strToParse, String fileName) throws IOException {
        val order = (Order) csvMapper
                .readerFor(Order.class)
                .with(schema)
                .readValues(strToParse).nextValue();
        orderValidator.validateBean(order);
        return validOut(
                order,
                fileName,
                line);
    }
}
