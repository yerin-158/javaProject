package com.company.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class USB {
    private String serialNumber;

    public USB(String serialNumber){
        this.serialNumber = serialNumber;
    }
}
