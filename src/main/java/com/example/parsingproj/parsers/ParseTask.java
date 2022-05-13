package com.example.parsingproj.parsers;

import com.example.parsingproj.domain.Line;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicLong;

@AllArgsConstructor
public class ParseTask implements Runnable {

    private final Line line;
    private final String fileName;
    private final AtomicLong count;
    private final Parser parser;

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("{\"id\"=\"" + count.incrementAndGet() + "\"," + parser.parse(line.getContent(), fileName, line.getLineNumber()) + "}");
    }
}
