package com.company;

import com.company.controller.WidgetController;
import com.company.enums.Color;
import com.company.objects.Widget;

import java.util.ArrayList;
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

        /** Match */
        WidgetController matchTestControl = WidgetController.get();

        Collection<Widget> mixColorWidgets = new ArrayList<>();
        mixColorWidgets.add(matchTestControl.getColorWidget(Color.RED));
        mixColorWidgets.add(matchTestControl.getColorWidget(Color.BLUE));
        mixColorWidgets.add(matchTestControl.getColorWidget(Color.YELLOW));

        Collection<Widget> redColorWidgets = new ArrayList<>();
        redColorWidgets.add(matchTestControl.getColorWidget(Color.RED));
        redColorWidgets.add(matchTestControl.getColorWidget(Color.RED));
        redColorWidgets.add(matchTestControl.getColorWidget(Color.RED));

        /** All Match */
        Boolean isAllRedInMix = mixColorWidgets.stream().allMatch(widget -> widget.getColor().equals(Color.RED));
        Boolean isAllRed = redColorWidgets.stream().anyMatch(widget -> widget.getColor().equals(Color.RED));

        System.out.println("-- All Match Red Color --");
        System.out.println("mixColorWidgets : " + isAllRedInMix);
        System.out.println("redColorWidgets : " + isAllRed);

        /** Any Match */
        Boolean isAnyRedInMix = mixColorWidgets.stream().anyMatch(widget -> widget.getColor().equals(Color.RED));
        Boolean isAnyRed = redColorWidgets.stream().anyMatch(widget -> widget.getColor().equals(Color.RED));

        Boolean isAnyBlueInMix = mixColorWidgets.stream().anyMatch(widget -> widget.getColor().equals(Color.BLUE));
        Boolean isAnyBlue = redColorWidgets.stream().anyMatch(widget -> widget.getColor().equals(Color.BLUE));

        System.out.println("-- Any Match Red Color --");
        System.out.println("mixColorWidgets : " + isAnyRedInMix);
        System.out.println("redColorWidgets : " + isAnyRed);

        System.out.println("-- Any Match Blue Color --");
        System.out.println("mixColorWidgets : " + isAnyBlueInMix);
        System.out.println("redColorWidgets : " + isAnyBlue);

        /** None Match */
        Boolean haveNotBlueInMix = mixColorWidgets.stream().noneMatch(widget -> widget.getColor().equals(Color.BLUE));
        Boolean haveNotBlue = redColorWidgets.stream().noneMatch(widget -> widget.getColor().equals(Color.BLUE));

        Boolean haveNotGreenInMix = mixColorWidgets.stream().noneMatch(widget -> widget.getColor().equals(Color.GREEN));
        Boolean haveNotGreen = redColorWidgets.stream().noneMatch(widget -> widget.getColor().equals(Color.GREEN));

        System.out.println("-- None Match Blue Color --");
        System.out.println("mixColorWidgets : " + haveNotBlueInMix);
        System.out.println("redColorWidgets : " + haveNotBlue);

        System.out.println("-- Any Match Green Color --");
        System.out.println("mixColorWidgets : " + haveNotGreenInMix);
        System.out.println("redColorWidgets : " + haveNotGreen);

    }
}
