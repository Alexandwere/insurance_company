package com.javaacademy.insurance;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class Archive {
    private final Map<String, InsuranceContract> archive = new HashMap<>();

    public InsuranceContract findContract(String number) {
        return archive.get(number);
    }
}
