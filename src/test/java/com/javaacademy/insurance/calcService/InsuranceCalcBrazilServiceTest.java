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
@DisplayName("Успешный расчёт стоимости, Бразилия")
public class InsuranceCalcBrazilServiceTest {
    private static final BigDecimal ROBBERY_COVERAGE = BigDecimal.valueOf(50_000);
    private static final BigDecimal EXP_ROB_COV = BigDecimal.valueOf(2_800);
    private static final BigDecimal MEDICAL_COVERAGE = BigDecimal.valueOf(200_000);
    private static final BigDecimal EXP_MED_COV = BigDecimal.valueOf(6_800);


    @Autowired
    private InsuranceCalcBrazilService insuranceCalcBrazilService;

    @Test
    @DisplayName("Страховка от ограбления - успешный расчёт")
    public void successCountRobbery() {
        CalcServiceUtil.countPrice(insuranceCalcBrazilService, ROBBERY_COVERAGE, EXP_ROB_COV, ROBBERY_PROTECTION);
    }

    @Test
    @DisplayName("Медицинская страховка - успешный расчёт")
    public void successCountMedical() {
        CalcServiceUtil.countPrice(insuranceCalcBrazilService, MEDICAL_COVERAGE, EXP_MED_COV, MEDICAL);
    }

}
