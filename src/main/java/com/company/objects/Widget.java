package com.company.objects;

import com.company.enums.Color;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Widget {

    private Color color;
    private int weight;

    @Builder
    public Widget(Color color, int weight){
        this.color = color;
        this.weight = weight;
    }
}
