package com.javaacademy.insurance.calcService;

import com.javaacademy.insurance.insuranceCalcService.InsuranceCalcJapanService;
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
public class InsuranceCalcJapanServiceTest {

    @Autowired
    private InsuranceCalcJapanService insuranceCalcJapanService;

    @Test
    @DisplayName("Страховка от ограбления - успешный расчёт")
    public void successCountRobbery() {
        BigDecimal coverage = BigDecimal.valueOf(1_000_000);
        BigDecimal expected = BigDecimal.valueOf(20_000);
        CalcServiceUtil.countPrice(insuranceCalcJapanService, coverage, expected, ROBBERY_PROTECTION);
    }

    @Test
    @DisplayName("Медицинская страховка - успешный расчёт")
    public void successCountMedical() {
        BigDecimal coverage = BigDecimal.valueOf(10_000_000);
        BigDecimal expected = BigDecimal.valueOf(162_000);
        CalcServiceUtil.countPrice(insuranceCalcJapanService, coverage, expected, MEDICAL);
    }

}
