package com.javaacademy.insurance.insuranceService;

import com.javaacademy.insurance.Archive;
import com.javaacademy.insurance.InsuranceContract;
import com.javaacademy.insurance.NumberGenerator;
import com.javaacademy.insurance.enums.TypeOfInsurance;
import com.javaacademy.insurance.insuranceCalcService.InsuranceCalcBrazilService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.javaacademy.insurance.enums.StatusOfContract.PAID;
import static com.javaacademy.insurance.enums.StatusOfContract.UNPAID;

@Component
@Profile("brazil")
@RequiredArgsConstructor
public class InsuranceServiceBrazil implements InsuranceService {
    @Value("${app.country}")
    private String country;
    @Value("${app.currency}")
    private String currency;
    @Setter
    @NonNull
    private InsuranceCalcBrazilService insuranceCalcBrazilService;
    @Setter
    @NonNull
    private Archive archive;

    @Override
    public InsuranceContract giveOffer(BigDecimal coverageAmount, String clientsFullName, TypeOfInsurance type) {
        String numberContract = NumberGenerator.generateNumber();
        if (archive.getArchive().containsKey(numberContract)) {
            giveOffer(coverageAmount, clientsFullName, type);
        }
        BigDecimal price = insuranceCalcBrazilService.priceInsurance(coverageAmount, type);
        InsuranceContract contract = new InsuranceContract(numberContract, price, coverageAmount,
                currency, clientsFullName, country, type, UNPAID);
        archive.getArchive().put(numberContract, contract);
        return contract;
    }

    @Override
    public InsuranceContract payContract(String numberContract) {
        archive.findContract(numberContract).setStatusOfContract(PAID);
        return archive.getArchive().get(numberContract);
    }
}
