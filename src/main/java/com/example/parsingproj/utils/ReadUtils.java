package com.example.parsingproj.utils;

import com.example.parsingproj.domain.Line;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadUtils {

    public static List<Line> numerateFileLines(List<String> fileLines) {
        final Long[] line = {1L};
        return fileLines.stream().map(str -> new Line(str, line[0]++))
                .filter(s -> !s.getContent().isEmpty()).collect(Collectors.toList());
    }
}
