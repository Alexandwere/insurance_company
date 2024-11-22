package com.javaacademy.insurance.insurance_calc_service;

import com.javaacademy.insurance.enums.TypeOfInsurance;

import java.math.BigDecimal;

public interface InsuranceCalcService {
    BigDecimal priceInsuranceContract(BigDecimal coverageAmount, TypeOfInsurance type);
}
