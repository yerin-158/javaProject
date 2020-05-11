package com.company.objects;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@NoArgsConstructor
public class SoundCard {

    @Setter
    private Optional<USB> usb;

    @Builder
    public SoundCard (USB usb){
        this.usb = Optional.ofNullable(usb);
    }
}
