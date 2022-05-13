package com.example.parsingproj.parsers;

import com.example.parsingproj.domain.InvalidOrder;
import com.example.parsingproj.domain.Order;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

import static com.example.parsingproj.utils.Constants.*;

public abstract class Parser {

    public abstract String transform(Long line, String strToParse, String fileName) throws IOException;

    public String parse(String strToParse, String fileName, Long line) throws IOException {
        if (strToParse.split(",").length != RECORD_SIZE) {
             return invalidOut(strToParse, fileName, line, RECORD_SIZE_ERROR_MESSAGE);
        }
        else {
            try {
                return transform(line, strToParse, fileName);
            } catch (JsonProcessingException | IllegalArgumentException e) {
                return invalidOut(
                        strToParse,
                        fileName,
                        line,
                        e.getLocalizedMessage());
            }
        }
    }

    public String validOut(Order order, String fileName, Long line) {
        order.setLine(line);
        order.setFileName(fileName);
        order.setResult(RESULT_OK);
        return order.toString();
    }

    public String invalidOut(String strToParse, String fileName, Long line, String errorMessage) {
        return InvalidOrder.builder()
                .content(strToParse)
                .fileName(fileName)
                .line(line)
                .result(errorMessage).build().toString();
    }
}
