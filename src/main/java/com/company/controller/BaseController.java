package com.company.controller;

import java.util.Collection;

public interface BaseController<T> {

    T random();
    Collection<T> randomList(int size);

}
