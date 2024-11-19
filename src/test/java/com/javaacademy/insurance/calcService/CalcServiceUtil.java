package com.javaacademy.insurance.calcService;

import com.javaacademy.insurance.enums.TypeOfInsurance;
import com.javaacademy.insurance.insuranceCalcService.InsuranceCalcService;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

public class CalcServiceUtil {
    public static void countPrice(InsuranceCalcService service, BigDecimal coverage, BigDecimal expectedPrice,
                                  TypeOfInsurance type) {
        BigDecimal result = service.priceInsurance(coverage, type);
        int i = result.compareTo(expectedPrice);
        Assertions.assertEquals(0, i);
    }
}