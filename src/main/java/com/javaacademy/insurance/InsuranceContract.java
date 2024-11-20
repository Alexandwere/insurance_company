package com.javaacademy.insurance;

import com.javaacademy.insurance.enums.StatusOfContract;
import com.javaacademy.insurance.enums.TypeOfInsurance;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class InsuranceContract {
    private String numberContract;
    private BigDecimal price;
    private BigDecimal coverageAmount;
    private String currency;
    private String clientsFullName;
    private String country;
    private TypeOfInsurance typeOfInsurance;
    @Setter
    private StatusOfContract statusOfContract;
}
