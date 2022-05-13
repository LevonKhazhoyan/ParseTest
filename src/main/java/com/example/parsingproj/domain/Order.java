package com.example.parsingproj.domain;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Currency;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Order {

    @NotNull
    @Positive
    private int orderId;
    @NotNull
    @Positive
    private double amount;
    @NotNull
    private Currency currency;
    @NotNull
    private String comment;
    private String fileName;
    private long line;
    private String result;

    @Override
    public String toString() {
        return "\"orderId\"=" + orderId +
                ",\"amount\"=" + amount +
                ",\"currency\"=\"" + currency + "\"" +
                ",\"comment\"=\"" + comment + "\"" +
                ",\"fileName\"=\"" + fileName + "\"" +
                ",\"line\"=\"" + line + "\"" +
                ",\"result\"=\"" + result + "\"" ;
    }
}
