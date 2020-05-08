package com.company.objects;

import com.company.controller.WidgetController;
import com.company.enums.Color;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;

class WidgetTest {

    private WidgetController matchTestControl;
    private Collection<Widget> mixColorWidgets;
    private Collection<Widget> redColorWidgets;

    @BeforeEach
    void widgetListInit() {
        matchTestControl = WidgetController.get();
        mixColorWidgets = new ArrayList<>();
        mixColorWidgets.add(matchTestControl.getColorWidget(Color.RED));
        mixColorWidgets.add(matchTestControl.getColorWidget(Color.BLUE));
        mixColorWidgets.add(matchTestControl.getColorWidget(Color.YELLOW));

        redColorWidgets = new ArrayList<>();
        redColorWidgets.add(matchTestControl.getColorWidget(Color.RED));
        redColorWidgets.add(matchTestControl.getColorWidget(Color.RED));
        redColorWidgets.add(matchTestControl.getColorWidget(Color.RED));
    }

    @Test
    void allMatchRedTest(){
        Boolean isAllRedInMix = mixColorWidgets.stream().allMatch(widget -> widget.getColor().equals(Color.RED));
        Boolean isAllRed = redColorWidgets.stream().anyMatch(widget -> widget.getColor().equals(Color.RED));

        org.assertj.core.api.Assertions.assertThat(isAllRedInMix).isEqualTo(false);
        org.assertj.core.api.Assertions.assertThat(isAllRed).isEqualTo(true);
    }

    @Test
    void anyMatchRedTest(){
        Boolean isAnyRedInMix = mixColorWidgets.stream().anyMatch(widget -> widget.getColor().equals(Color.RED));
        Boolean isAnyRed = redColorWidgets.stream().anyMatch(widget -> widget.getColor().equals(Color.RED));

        assertThat(isAnyRedInMix, Is.is(true));
        assertThat(isAnyRed, Is.is(true));

    }


    @Test
    void anyMatchBlueTest(){
        Boolean isAnyBlueInMix = mixColorWidgets.stream().anyMatch(widget -> widget.getColor().equals(Color.BLUE));
        Boolean isAnyBlue = redColorWidgets.stream().anyMatch(widget -> widget.getColor().equals(Color.BLUE));

        assertThat(isAnyBlueInMix, Is.is(true));
        assertThat(isAnyBlue, Is.is(false));
    }

    @Test
    void noneMatchBlueTest(){
        Boolean haveNotBlueInMix = mixColorWidgets.stream().noneMatch(widget -> widget.getColor().equals(Color.BLUE));
        Boolean haveNotBlue = redColorWidgets.stream().noneMatch(widget -> widget.getColor().equals(Color.BLUE));

        assertThat(haveNotBlueInMix, Is.is(false));
        assertThat(haveNotBlue, Is.is(true));
    }

    @Test
    void noneMatchGreenTest(){
        Boolean haveNotGreenInMix = mixColorWidgets.stream().noneMatch(widget -> widget.getColor().equals(Color.GREEN));
        Boolean haveNotGreen = redColorWidgets.stream().noneMatch(widget -> widget.getColor().equals(Color.GREEN));

        assertThat(haveNotGreenInMix, Is.is(true));
        assertThat(haveNotGreen, Is.is(true));
    }
}
