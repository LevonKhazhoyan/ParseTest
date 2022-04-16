package com.example.parsingproj;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class CsvConverter {



    public static void convert(String file, List<String> out) {
        var data = new LinkedBlockingQueue<String>();
        AtomicInteger line = new AtomicInteger(0);
        ReadService.readFile("src/main/resources/" + file, data);

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.builder()
                .addColumn("orderId")
                .addColumn("amount")
                .addColumn("currency")
                .addColumn("comment")
                .build();

        data.parallelStream().forEachOrdered(s -> {
            try {
                Order order = (Order) mapper
                        .readerFor(Order.class)
                        .with(schema)
                        .readValues(s).nextValue();
                line.addAndGet(1);
                order.setResult("OK");
                order.setFileName(file);
                order.setLine(line.toString());
                out.add(order.toString());
            } catch (InvalidFormatException e) {
                try { out.add(mapper.reader().with(schema).readValue(s)); }
                    catch (JsonProcessingException ex) { System.out.println("Invalid format for Order schema"); }
            } catch (NoSuchElementException e) { line.addAndGet(1);}
              catch (IOException e) { e.printStackTrace(); }
        });
    }
}
