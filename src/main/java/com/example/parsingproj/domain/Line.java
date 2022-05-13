package com.example.parsingproj.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Line {

    private final String content;
    private final long lineNumber;

    @Override
    public String toString() {
        return "Line{" +
                "content='" + content + '\'' +
                ", lineNumber=" + lineNumber +
                '}';
    }
}
