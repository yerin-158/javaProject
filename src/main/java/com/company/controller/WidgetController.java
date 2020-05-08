package com.company.controller;

import com.company.enums.Color;
import com.company.factory.RandomController;
import com.company.objects.Widget;

import java.util.ArrayList;
import java.util.Collection;

public class WidgetController implements BaseController<Widget> {

    public static WidgetController get() {
        return new WidgetController();
    }

    @Override
    public Widget random() {
        return Widget.builder().color(Color.randomColor()).weight(RandomController.randomInt(1, 100)).build();
    }

    @Override
    public Collection<Widget> randomList(int size) {
        Collection<Widget> widgets = new ArrayList<Widget>();
        while (size-- != 0) {
            widgets.add(random());
        }
        return widgets;
    }


}
