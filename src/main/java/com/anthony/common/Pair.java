package com.anthony.common;

import java.util.Map;

/**
 * Created by chend on 2017/6/15.
 */
public class Pair<k, v> implements Map.Entry<k, v> {

    private k key;
    private v value;

    @Override
    public k getKey() {
        return key;
    }

    @Override
    public v getValue() {
        return value;
    }

    @Override
    public v setValue(v value) {
        this.value = value;
        return value;
    }

    public k setKey(k key) {
        this.key = key;
        return key;
    }

    public void put(k key, v value) {
        setValue(value);
        setKey(key);
    }

    @Override
    public String toString() {
        return key + ": " + value;
    }

}
