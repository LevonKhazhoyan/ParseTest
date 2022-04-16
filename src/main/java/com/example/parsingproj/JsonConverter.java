package com.example.parsingproj;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class JsonConverter {

    public static void convert(String file, List<String> out) {
        var data = new LinkedBlockingQueue<String>();
        var line = new AtomicInteger(1);
        ReadService.readFile("src/main/resources/" + file, data);
        ObjectMapper mapper = new ObjectMapper();
        data.parallelStream().forEachOrdered(s -> {
            try {
                Order order = mapper.readValue(s, Order.class);
                order.setResult("OK");
                order.setFileName(file);
                order.setLine(line.toString());
                out.add(order.toString());
                line.addAndGet(1);
            } catch (InvalidFormatException e) {
                out.add(s + ",\"filename\":\"" + file + "\",\"line\":\"" + line + "\",\"result\":\"Invalid Json Format for Order Construct\"");
                line.addAndGet(1);
            } catch (NoSuchElementException e) { line.addAndGet(1);}
            catch (IOException e) { e.printStackTrace(); }
        });
    }
}
