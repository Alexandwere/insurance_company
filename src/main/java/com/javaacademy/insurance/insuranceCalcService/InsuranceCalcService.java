package com.javaacademy.insurance.insuranceCalcService;

import com.javaacademy.insurance.enums.TypeOfInsurance;

import java.math.BigDecimal;

public interface InsuranceCalcService {
    BigDecimal priceInsurance(BigDecimal coverageAmount, TypeOfInsurance type);
}
