package com.javaacademy.insurance.calcService;

import com.javaacademy.insurance.insuranceCalcService.InsuranceCalcBrazilService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static com.javaacademy.insurance.enums.TypeOfInsurance.MEDICAL;
import static com.javaacademy.insurance.enums.TypeOfInsurance.ROBBERY_PROTECTION;

@SpringBootTest
@ActiveProfiles("brazil")
public class InsuranceCalcBrazilServiceTest {

    @Autowired
    private InsuranceCalcBrazilService insuranceCalcBrazilService;

    @Test
    @DisplayName("Страховка от ограбления - успешный расчёт")
    public void successCountRobbery() {
        BigDecimal coverage = BigDecimal.valueOf(50_000);
        BigDecimal expected = BigDecimal.valueOf(2_800);
        CalcServiceUtil.countPrice(insuranceCalcBrazilService, coverage, expected, ROBBERY_PROTECTION);
    }

    @Test
    @DisplayName("Медицинская страховка - успешный расчёт")
    public void successCountMedical() {
        BigDecimal coverage = BigDecimal.valueOf(200_000);
        BigDecimal expected = BigDecimal.valueOf(6_800);
        CalcServiceUtil.countPrice(insuranceCalcBrazilService, coverage, expected, MEDICAL);
    }

}
