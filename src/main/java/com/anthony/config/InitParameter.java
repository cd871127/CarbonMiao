package com.anthony.config;

import com.anthony.common.Pair;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by chend on 2017/6/15.
 */
public class InitParameter {
    private Properties properties = new Properties();

    public InitParameter() {
        final String propertiesFileName = "config.properties";
        try (FileInputStream in = new FileInputStream(propertiesFileName)) {
            properties.load(in);
        } catch (IOException e) {
            System.out.println("read config failed");
        }
    }

    private void setMarketList() {
        String[] markets = properties.getProperty("marketList").split(",");
        String priceUrlTemplate = "http://chinacarbon.net.cn/charts/jsonfiles/jsonp.php?filename=%spricedata.json";
        String volumeUrlTemplate = "http://chinacarbon.net.cn/charts/jsonfiles/jsonp.php?filename=%svolumedata.json";
        for (int i = 0; i != markets.length; ++i) {
            String str = markets[i].toLowerCase().split(" ")[0];
            String priceUrl = String.format(priceUrlTemplate, str);
            String volumeUrl = String.format(volumeUrlTemplate, str);

            Pair<String, Map.Entry<String, String>> pair = new Pair<>();
            Pair<String, String> urlPair = new Pair<>();

            urlPair.put(priceUrl, volumeUrl);
            pair.put(markets[i], urlPair);
            ConfigParameter.marketList.add(pair);
        }
    }

    private void setProxy() {
        String proxy = properties.getProperty("proxy");
        ConfigParameter.proxy = !proxy.equals("false");
    }

    private void setFillNoVolume() {
        String proxy = properties.getProperty("fillNoVolume");
        ConfigParameter.fillNoVolume = !proxy.equals("false");
    }

    public void init() {
        setMarketList();
        setProxy();
        setFillNoVolume();
    }


}
