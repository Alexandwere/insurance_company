package com.javaacademy.insurance.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusOfContract {
    PAID("Оплаченный договор"),
    UNPAID("Неоплаченный договор");

    private final String description;
}
