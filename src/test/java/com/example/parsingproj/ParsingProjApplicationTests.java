package com.example.parsingproj;

import com.example.parsingproj.parsers.impl.CsvParser;
import com.example.parsingproj.parsers.impl.JsonParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ParsingProjApplicationTests {

    @Autowired
    JsonParser jsonConverter;

    @Autowired
    CsvParser csvConverter;

    @Test
    void displaysHumanReadableErrorWhenJsonValidationIsIncorrect() throws IOException {
        String actual = jsonConverter.parse("{\"orderId\":-5,\"amount\":1000.0,\"currency\":\"USD\",\"comment\":\"оплата заказа\"}", "TestFiles/ErroredFile.json",3L);
        String expected = "\"content\"={\"orderId\":-5,\"amount\":1000.0,\"currency\":\"USD\",\"comment\":\"оплата заказа\"},\"fileName\"=\"TestFiles/ErroredFile.json\",\"line\"=\"3\",\"result\"=\"orderId must be greater than 0\"";
        assertEquals(expected, actual);
    }

    @Test
    void displaysHumanReadableErrorWhenJsonParsingIsIncorrect() throws IOException {
        String actual = jsonConverter.parse("{\"orderId\":String,\"amount\":1000.0,\"currency\":\"USD\",\"comment\":\"оплата заказа\"}", "TestFiles/ErroredFile.json",3L);
        String expected = "\"content\"={\"orderId\":String,\"amount\":1000.0,\"currency\":\"USD\",\"comment\":\"оплата заказа\"},\"fileName\"=\"TestFiles/ErroredFile.json\",\"line\"=\"3\",\"result\"=\"Unrecognized token 'String': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')\n" +
                " at [Source: (String)\"{\"orderId\":String,\"amount\":1000.0,\"currency\":\"USD\",\"comment\":\"оплата заказа\"}\"; line: 1, column: 18]\"";
        assertEquals(expected, actual);
    }

    @Test
    void parsesCorrectJson() throws IOException {
        String actual = jsonConverter.parse("{\"orderId\":3,\"amount\":1000.0,\"currency\":\"USD\",\"comment\":\"оплата заказа\"}", "TestFiles/ErroredFile.json",3L);
        String expected = "\"orderId\"=3,\"amount\"=1000.0,\"currency\"=\"USD\",\"comment\"=\"оплата заказа\",\"fileName\"=\"TestFiles/ErroredFile.json\",\"line\"=\"3\",\"result\"=\"OK\"";
        assertEquals(expected, actual);
    }

    @Test
    void displaysHumanReadableErrorWhenCsvValidationIsIncorrect() throws IOException {
        String actual = csvConverter.parse("-31,100,USD,оплата заказа", "TestFiles/ErroredFile.csv", 3L);
        String expected = "\"content\"=-31,100,USD,оплата заказа,\"fileName\"=\"TestFiles/ErroredFile.csv\",\"line\"=\"3\",\"result\"=\"orderId must be greater than 0\"";
        assertEquals(expected, actual);
    }

    @Test
    void displaysHumanReadableErrorWhenCsvParsingIsIncorrect() throws IOException {
        String actual = csvConverter.parse("String,1000,USD,оплата заказа", "TestFiles/ErroredFile.csv", 3L);
        String expected = "\"content\"=String,1000,USD,оплата заказа,\"fileName\"=\"TestFiles/ErroredFile.csv\",\"line\"=\"3\",\"result\"=\"Cannot deserialize value of type `int` from String \"String\": not a valid `int` value\n" +
                " at [Source: (StringReader); line: 1, column: 1] (through reference chain: com.example.parsingproj.domain.Order[\"orderId\"])\"";
        assertEquals(expected, actual);
    }

    @Test
    void convertsCorrectCsv() throws IOException {
        String actual = csvConverter.parse("1,100,USD,оплата заказа", "TestFiles/ErroredFile.csv", 3L);
        String expected = "\"orderId\"=1,\"amount\"=100.0,\"currency\"=\"USD\",\"comment\"=\"оплата заказа\",\"fileName\"=\"TestFiles/ErroredFile.csv\",\"line\"=\"3\",\"result\"=\"OK\"";
        assertEquals(expected, actual);
    }
}
