package com.javaacademy.insurance;

import com.javaacademy.insurance.enums.StatusOfContract;
import com.javaacademy.insurance.enums.TypeOfInsurance;
import com.javaacademy.insurance.insuranceCalcService.InsuranceCalcJapanService;
import com.javaacademy.insurance.insuranceService.InsuranceServiceJapan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    private static final String clientsFullName = "Иванов Иван Иванович";
    private static final String CURRENCY = "yen";
    private static final String COUNTRY = "Japan";

    private static final BigDecimal COVERAGE = BigDecimal.valueOf(1_000_000);
    private static final BigDecimal PRICE = BigDecimal.valueOf(20_000);
    private static final TypeOfInsurance TYPE = ROBBERY_PROTECTION;

    private static final BigDecimal COVERAGE2 = BigDecimal.valueOf(10_000_000);
    private static final BigDecimal PRICE2 = BigDecimal.valueOf(162_000);
    private static final TypeOfInsurance TYPE2 = MEDICAL;

    @Autowired
    private InsuranceServiceJapan insuranceServiceJapan;
    private InsuranceCalcJapanService mockCalc = Mockito.mock(InsuranceCalcJapanService.class);
    private Archive archive = Mockito.mock(Archive.class);

    @BeforeEach
    public void mockOther() {
        insuranceServiceJapan.setInsuranceCalcJapanService(mockCalc);
        insuranceServiceJapan.setArchive(archive);
    }

    @Test
    @DisplayName("1 Предложение по страховке - успешное получение")
    public void successTakeOffer1() {
        Mockito.when(mockCalc.priceInsurance(COVERAGE, TYPE)).thenReturn(PRICE);
        MockedStatic<NumberGenerator> mocked = Mockito.mockStatic(NumberGenerator.class);
            mocked.when(NumberGenerator::generateNumber).thenReturn(CONTRACT_NUMBER);

        InsuranceContract result = insuranceServiceJapan.giveOffer(COVERAGE, clientsFullName, TYPE);
        InsuranceContract result2 = insuranceServiceJapan.giveOffer(COVERAGE, clientsFullName, TYPE);
        System.out.println(archive.getArchive().size());
        InsuranceContract expected = new InsuranceContract(CONTRACT_NUMBER, PRICE, COVERAGE, CURRENCY, clientsFullName,
                COUNTRY, ROBBERY_PROTECTION, UNPAID);
        Assertions.assertEquals(expected, result);
        mocked.close();
    }

    @Test
    @DisplayName("2 Предложение по страховке - успешное получение")
    public void successTakeOffer2() {
        Mockito.when(mockCalc.priceInsurance(COVERAGE2, TYPE2)).thenReturn(PRICE2);
        MockedStatic<NumberGenerator> mocked = Mockito.mockStatic(NumberGenerator.class);
        mocked.when(NumberGenerator::generateNumber).thenReturn(CONTRACT_NUMBER);

        InsuranceContract result = insuranceServiceJapan.giveOffer(COVERAGE2, clientsFullName, TYPE2);
        InsuranceContract expected = new InsuranceContract(CONTRACT_NUMBER, PRICE2, COVERAGE2, CURRENCY,
                clientsFullName, COUNTRY, MEDICAL, UNPAID);
        Assertions.assertEquals(expected, result);
        mocked.close();
    }

    @Test
    @DisplayName("Успешная оплата страховки")
    public void successPay() {
        Mockito.when(mockCalc.priceInsurance(COVERAGE2, TYPE2)).thenReturn(PRICE2);
        MockedStatic<NumberGenerator> mocked = Mockito.mockStatic(NumberGenerator.class);
        mocked.when(NumberGenerator::generateNumber).thenReturn(CONTRACT_NUMBER);

        InsuranceContract contract = insuranceServiceJapan.giveOffer(COVERAGE2, clientsFullName, TYPE2);
        Mockito.when(archive.findContract(CONTRACT_NUMBER)).thenReturn(contract);
        InsuranceContract paidContract = insuranceServiceJapan.payContract(CONTRACT_NUMBER);

        StatusOfContract result = contract.getStatusOfContract();
        Assertions.assertEquals(PAID, result);
        mocked.close();

    }

}
