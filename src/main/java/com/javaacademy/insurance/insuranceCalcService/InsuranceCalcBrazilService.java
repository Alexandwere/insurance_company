package com.javaacademy.insurance.insuranceCalcService;

import com.javaacademy.insurance.enums.TypeOfInsurance;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.javaacademy.insurance.enums.TypeOfInsurance.MEDICAL;
import static com.javaacademy.insurance.enums.TypeOfInsurance.ROBBERY_PROTECTION;

@Component
@Profile("brazil")
public class InsuranceCalcBrazilService implements InsuranceCalcService {
    @Value("${app.robbery-coefficient}")
    private BigDecimal robberyCoefficient;
    @Value("${app.robbery-add-money}")
    private BigDecimal robberyAddMoney;
    @Value("${app.medical-coefficient}")
    private BigDecimal medicalCoefficient;
    @Value("${app.medical-add-money}")
    private BigDecimal medicalAddMoney;

    @Override
    public BigDecimal priceInsuranceContract(@NonNull BigDecimal coverageAmount, @NonNull TypeOfInsurance type) {
        if (type == ROBBERY_PROTECTION) {
            return coverageAmount.multiply(robberyCoefficient).add(robberyAddMoney);
        }
        if (type == MEDICAL) {
            return coverageAmount.multiply(medicalCoefficient).add(medicalAddMoney);
        } else {
            throw new RuntimeException("Нет искомого типа страховки!");
        }
    }
}
