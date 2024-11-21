package com.javaacademy.insurance.insuranceService;

import com.javaacademy.insurance.Archive;
import com.javaacademy.insurance.InsuranceContract;
import com.javaacademy.insurance.NumberGenerator;
import com.javaacademy.insurance.enums.TypeOfInsurance;
import com.javaacademy.insurance.insuranceCalcService.InsuranceCalcService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.javaacademy.insurance.enums.StatusOfContract.PAID;
import static com.javaacademy.insurance.enums.StatusOfContract.UNPAID;

@Component
@Profile("japan")
@RequiredArgsConstructor
@Slf4j
public class InsuranceServiceJapan implements InsuranceService {
    @Value("${app.country}")
    private String country;
    @Value("${app.currency}")
    private String currency;
    @NonNull
    private InsuranceCalcService insuranceCalcService;
    @NonNull
    private Archive archive;

    @Override
    public InsuranceContract giveOffer(@NonNull BigDecimal coverageAmount, @NonNull String clientsFullName,
                                       @NonNull TypeOfInsurance type) {
        String numberContract = NumberGenerator.generateNumber();
        try {
            if (archive.getArchive().containsKey(numberContract)) {
                giveOffer(coverageAmount, clientsFullName, type);
            }
        } catch (StackOverflowError error) {
            throw new RuntimeException("Лимит данной серии договоров исчерпан. Требуется создание новой серии.");
        }
        BigDecimal price = insuranceCalcService.priceInsuranceContract(coverageAmount, type);
        InsuranceContract contract = new InsuranceContract(numberContract, price, coverageAmount,
                currency, clientsFullName, country, type, UNPAID);
        archive.getArchive().put(numberContract, contract);
        return contract;
    }

    @Override
    public InsuranceContract payContract(@NonNull String numberContract) {
        if (archive.findContract(numberContract) == null) {
            throw new RuntimeException("Такого договора не существует.");
        }
        archive.findContract(numberContract).setStatusOfContract(PAID);
        return archive.getArchive().get(numberContract);
    }
}
