package com.company.objects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
public class Computer {

    @Setter
    private Optional<SoundCard> soundCard;

    @Builder
    public Computer (SoundCard soundCard){
        this.soundCard = Optional.ofNullable(soundCard);
    }

    public Optional<Computer> toOptional () {
        return Optional.ofNullable(this);
    }
}
