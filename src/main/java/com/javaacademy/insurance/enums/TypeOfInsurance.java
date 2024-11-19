package com.javaacademy.insurance.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TypeOfInsurance {
    MEDICAL("Медицинское страхование"),
    ROBBERY_PROTECTION("Защита от грабежа");

    private final String description;
}
