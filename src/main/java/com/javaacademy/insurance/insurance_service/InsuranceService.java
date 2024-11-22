package com.javaacademy.insurance.insurance_service;

import com.javaacademy.insurance.InsuranceContract;
import com.javaacademy.insurance.enums.TypeOfInsurance;

import java.math.BigDecimal;

public interface InsuranceService {
    InsuranceContract giveOffer(BigDecimal coverageAmount, String clientsFullName, TypeOfInsurance type);

    InsuranceContract payContract(String numberContract);
}
