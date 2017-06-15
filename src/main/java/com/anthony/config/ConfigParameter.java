package com.anthony.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chend on 2017/6/15.
 */
public class ConfigParameter {
    public static List<Map.Entry<String, Map.Entry<String, String>>> marketList = new ArrayList<>();
    public static boolean proxy;
    public static boolean fillNoVolume;
}
