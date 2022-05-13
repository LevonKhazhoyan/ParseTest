package com.example.parsingproj.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FileType {

    JSON,
    CSV;

    public static FileType fromValue(String textValue) {
        for (FileType fileType: FileType.values()) {
            if (fileType.name().equalsIgnoreCase(textValue)) {
                return fileType;
            }
        }
        throw new IllegalArgumentException();
    }
}
