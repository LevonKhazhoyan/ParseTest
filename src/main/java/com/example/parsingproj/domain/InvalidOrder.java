package com.example.parsingproj.domain;

import lombok.*;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvalidOrder {

    @NonNull
    private String fileName;
    @NonNull
    private long line;
    @NonNull
    private String result;
    @NonNull
    private String content;

    @Override
    public String toString() {
        return  "\"content\"=" + content +
                ",\"fileName\"=\"" + fileName + "\"" +
                ",\"line\"=\"" + line + "\"" +
                ",\"result\"=\"" + result + "\"" ;
    }
}