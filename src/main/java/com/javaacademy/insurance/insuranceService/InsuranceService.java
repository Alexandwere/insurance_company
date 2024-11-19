package com.javaacademy.insurance.insuranceService;

import com.javaacademy.insurance.InsuranceContract;
import com.javaacademy.insurance.enums.TypeOfInsurance;

import java.math.BigDecimal;

public interface InsuranceService {
    InsuranceContract giveOffer(BigDecimal coverageAmount, String clientsFullName, TypeOfInsurance type);

    InsuranceContract payContract(String numberContract);
}
