package com.company;

import com.company.controller.WidgetController;
import com.company.enums.Color;
import com.company.objects.Widget;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {
	    /**
	    * widget 클래스를 이용한 Stream 생성
	    * */

        Collection<Widget> widgets = WidgetController.get().randomList(5);
        int sum = widgets.stream()
                        .filter(widget -> widget.getColor().equals(Color.RED))
                        .mapToInt(widget -> widget.getWeight())
                        .sum();
        System.out.println(sum);
    }
}
