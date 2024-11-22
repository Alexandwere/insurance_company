package com.javaacademy.insurance;

import com.javaacademy.insurance.enums.StatusOfContract;
import com.javaacademy.insurance.enums.TypeOfInsurance;
import com.javaacademy.insurance.insurance_calc_service.InsuranceCalcService;
import com.javaacademy.insurance.insurance_service.InsuranceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static com.javaacademy.insurance.enums.StatusOfContract.PAID;
import static com.javaacademy.insurance.enums.StatusOfContract.UNPAID;
import static com.javaacademy.insurance.enums.TypeOfInsurance.MEDICAL;
import static com.javaacademy.insurance.enums.TypeOfInsurance.ROBBERY_PROTECTION;

@SpringBootTest
@ActiveProfiles("japan")
@DisplayName("Успешная выдача предложений сервисом Японии")
public class InsuranceServiceJapanTest {
    private static final String CONTRACT_NUMBER = "001";
    private static final String CLIENTS_FULL_NAME = "Иванов Иван Иванович";
    private static final String CURRENCY = "yen";
    private static final String COUNTRY = "Japan";

    private static final BigDecimal COVERAGE1 = BigDecimal.valueOf(1_000_000);
    private static final BigDecimal PRICE1 = BigDecimal.valueOf(20_000);
    private static final TypeOfInsurance TYPE1 = ROBBERY_PROTECTION;

    private static final BigDecimal COVERAGE2 = BigDecimal.valueOf(10_000_000);
    private static final BigDecimal PRICE2 = BigDecimal.valueOf(162_000);
    private static final TypeOfInsurance TYPE2 = MEDICAL;

    @MockBean
    private InsuranceCalcService mockCalc;
    @MockBean
    private Archive archive;
    @Autowired
    private InsuranceService insuranceService;

    @Test
    @DisplayName("1 Предложение по страховке - успешное получение")
    public void successTakeOffer1() {
        Mockito.when(mockCalc.priceInsuranceContract(COVERAGE1, TYPE1)).thenReturn(PRICE1);
        MockedStatic<NumberGenerator> mocked = Mockito.mockStatic(NumberGenerator.class);
            mocked.when(NumberGenerator::generateNumber).thenReturn(CONTRACT_NUMBER);

        InsuranceContract result = insuranceService.giveOffer(COVERAGE1, CLIENTS_FULL_NAME, TYPE1);
        InsuranceContract expected = new InsuranceContract(CONTRACT_NUMBER, PRICE1, COVERAGE1, CURRENCY,
                CLIENTS_FULL_NAME, COUNTRY, ROBBERY_PROTECTION, UNPAID);
        Assertions.assertEquals(expected, result);
        mocked.close();
    }

    @Test
    @DisplayName("2 Предложение по страховке - успешное получение")
    public void successTakeOffer2() {
        Mockito.when(mockCalc.priceInsuranceContract(COVERAGE2, TYPE2)).thenReturn(PRICE2);
        MockedStatic<NumberGenerator> mocked = Mockito.mockStatic(NumberGenerator.class);
        mocked.when(NumberGenerator::generateNumber).thenReturn(CONTRACT_NUMBER);

        InsuranceContract result = insuranceService.giveOffer(COVERAGE2, CLIENTS_FULL_NAME, TYPE2);
        InsuranceContract expected = new InsuranceContract(CONTRACT_NUMBER, PRICE2, COVERAGE2, CURRENCY,
                CLIENTS_FULL_NAME, COUNTRY, MEDICAL, UNPAID);
        Assertions.assertEquals(expected, result);
        mocked.close();
    }

    @Test
    @DisplayName("Успешная оплата страховки")
    public void successPay() {
        Mockito.when(mockCalc.priceInsuranceContract(COVERAGE2, TYPE2)).thenReturn(PRICE2);
        MockedStatic<NumberGenerator> mocked = Mockito.mockStatic(NumberGenerator.class);
        mocked.when(NumberGenerator::generateNumber).thenReturn(CONTRACT_NUMBER);

        InsuranceContract contract = insuranceService.giveOffer(COVERAGE2, CLIENTS_FULL_NAME, TYPE2);
        Mockito.when(archive.findContract(CONTRACT_NUMBER)).thenReturn(contract);
        InsuranceContract paidContract = insuranceService.payContract(CONTRACT_NUMBER);

        StatusOfContract result = contract.getStatusOfContract();
        Assertions.assertEquals(PAID, result);
        mocked.close();
    }

}
