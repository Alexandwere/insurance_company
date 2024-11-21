package com.javaacademy.insurance.calcService;

import com.javaacademy.insurance.insuranceCalcService.InsuranceCalcService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static com.javaacademy.insurance.enums.TypeOfInsurance.MEDICAL;
import static com.javaacademy.insurance.enums.TypeOfInsurance.ROBBERY_PROTECTION;

@SpringBootTest
@ActiveProfiles("japan")
@DisplayName("Успешный расчёт стоимости, Япония")
public class InsuranceCalcJapanServiceTest {
    private static final BigDecimal ROBBERY_COVERAGE = BigDecimal.valueOf(1_000_000);
    private static final BigDecimal EXP_ROB_COV = BigDecimal.valueOf(20_000);
    private static final BigDecimal MEDICAL_COVERAGE = BigDecimal.valueOf(10_000_000);
    private static final BigDecimal EXP_MED_COV = BigDecimal.valueOf(162_000);

    @Autowired
    private InsuranceCalcService insuranceCalcService;

    @Test
    @DisplayName("Страховка от ограбления - успешный расчёт")
    public void successCountRobbery() {
        CalcServiceUtil.countPrice(insuranceCalcService, ROBBERY_COVERAGE, EXP_ROB_COV, ROBBERY_PROTECTION);
    }

    @Test
    @DisplayName("Медицинская страховка - успешный расчёт")
    public void successCountMedical() {
        CalcServiceUtil.countPrice(insuranceCalcService, MEDICAL_COVERAGE, EXP_MED_COV, MEDICAL);
    }

}
