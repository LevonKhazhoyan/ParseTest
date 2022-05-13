package com.example.parsingproj;

import com.example.parsingproj.parsers.ParserFabric;
import com.example.parsingproj.parsers.ParseTask;
import com.example.parsingproj.parsers.impl.CsvParser;
import com.example.parsingproj.parsers.impl.JsonParser;
import com.example.parsingproj.utils.ReadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

import static com.example.parsingproj.utils.Constants.RESOURCE_PATH;

@SpringBootApplication
public class OrdersParser implements CommandLineRunner {

    @Autowired
    private CsvParser csvParser;
    @Autowired
    private JsonParser jsonParser;
    @Autowired
    private ParserFabric parserFabric;

    public static void main(String[] args) {
        SpringApplication.run(OrdersParser.class, args);
    }

    @Override
    public void run(String[] args) throws IOException {
        AtomicLong count = new AtomicLong(0);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (String arg : args)
            try {
                ReadUtils.numerateFileLines(Files.readAllLines(Paths.get((RESOURCE_PATH + arg)))).forEach(s -> {
                    try {
                        executorService.execute(new ParseTask(s, arg, count, parserFabric.getParser(arg)));
                    } catch (IllegalArgumentException ignored) {
                        System.err.printf("Expected json or csv file instead of %s%n", arg);
                    }});
            } catch (NoSuchFileException ignored) {
                System.err.printf("No access to file %s%n", arg);
            }
        executorService.shutdown();
    }
}
