package com.example.parsingproj.parsers;

import com.example.parsingproj.domain.FileType;
import com.example.parsingproj.parsers.impl.CsvParser;
import com.example.parsingproj.parsers.impl.JsonParser;
import lombok.AllArgsConstructor;
import lombok.val;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class ParserFabric {

    @Autowired
    private final JsonParser jsonParser;
    @Autowired
    private final CsvParser csvParser;

    public Parser getParser(String fileName) {
        val extension = FilenameUtils.getExtension(fileName);
        val fileType = FileType.fromValue(extension);
        switch (Objects.requireNonNull(fileType)) {
            case JSON:
                return jsonParser;
            case CSV:
                return csvParser;
            default:
                throw new IllegalArgumentException();
        }
    }
}
