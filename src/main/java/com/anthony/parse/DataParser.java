package com.anthony.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anthony.common.Pair;
import com.anthony.config.ConfigParameter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by chend on 2017/6/15.
 */
public class DataParser {
    public List<Pair<Pair<String, String>, Pair<String, String>>> parse(String priceData, String volData) {
        return mergeList(toList(priceData), toList(volData));
    }

    private List<Pair<String, String>> toList(String data) {
        data = data.substring(data.indexOf("(") + 1, data.indexOf(")"));
        List<String> list = new ArrayList<>();
        List<Pair<String, String>> res = new ArrayList<>();
        if (data.contains(":")) {
            JSONObject jsonObject = JSON.parseObject(data);
            ArrayList<Integer> sortKeyList = new ArrayList<>();
            for (String key : jsonObject.keySet()) {
                int intKey = Integer.valueOf(key);
                sortKeyList.add(intKey);
            }
            Collections.sort(sortKeyList);

            for (Integer key : sortKeyList) {
                JSONArray jsonArray = jsonObject.getJSONArray(String.valueOf(key));
                list.addAll(jsonArray.toJavaList(String.class));
            }
        } else {
            list.addAll(JSON.parseArray(data).toJavaList(String.class));
        }


        for (String str : list) {
            str = str.replace("[", " ");
            str = str.replace("]", " ");
            str = str.trim();
            String[] values = str.split(",");
            if (values[1].equals("null"))
                continue;

            Pair<String, String> pair = new Pair<>();
            pair.put(values[0], values[1]);

            res.add(pair);
        }

        return res;
    }

    private List<Pair<Pair<String, String>, Pair<String, String>>> mergeList(List<Pair<String, String>> priceList, List<Pair<String, String>> volList) {
        List<Pair<Pair<String, String>, Pair<String, String>>> res = new ArrayList<>();

        SimpleDateFormat dfWeek = new SimpleDateFormat("EEEE");
        SimpleDateFormat dfDate = new SimpleDateFormat("yyyy/MM/dd");

        int i = 0, j = 0;
        while (i != priceList.size() && j != volList.size()) {
            long pKey = Long.valueOf(priceList.get(i).getKey());
            long vKey = Long.valueOf(volList.get(j).getKey());

            Pair<Pair<String, String>, Pair<String, String>> item = new Pair<>();
            Pair<String, String> values = new Pair<>();
            Pair<String, String> datePair = new Pair<>();
            Date date = null;

            if (pKey == vKey) {
                values.put(priceList.get(i).getValue(), volList.get(j).getValue());
                date = new Date(pKey);
                ++i;
                ++j;
            } else if (pKey < vKey) {
                if (ConfigParameter.fillNoVolume && j > 0)
                    values.put(priceList.get(i).getValue(), volList.get(j - 1).getValue());
                else
                    values.put(priceList.get(i).getValue(), "no volume");
                date = new Date(pKey);
                ++i;
            } else if (pKey > vKey) {
                if (ConfigParameter.fillNoVolume && i > 0)
                    values.put(priceList.get(i - 1).getValue(), volList.get(j).getValue());
                else
                    values.put("no price", volList.get(j).getValue());
                date = new Date(vKey);
                ++j;
            }
            datePair.put(dfDate.format(date), dfWeek.format(date));
            item.put(datePair, values);
            res.add(item);
        }
        return res;
    }


}
