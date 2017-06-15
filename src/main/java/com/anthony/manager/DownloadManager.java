package com.anthony.manager;

import com.anthony.common.Pair;
import com.anthony.config.ConfigParameter;
import com.anthony.file.ResultWriter;
import com.anthony.net.DefaultHttpGet;
import com.anthony.parse.DataParser;

import java.util.List;

/**
 * Created by chend on 2017/6/15.
 */
public class DownloadManager {
    private DefaultHttpGet httpGet = new DefaultHttpGet();
    private DataParser parser = new DataParser();
    private ResultWriter rw = new ResultWriter();

    public void download(int index) {
        if (index == ConfigParameter.marketList.size()) {
            for (int i = 0; i != index; ++i)
                doDownload(i);
        } else
            doDownload(index);
        rw.save();
    }

    private void doDownload(int index) {
        System.out.println("================================================");
        System.out.println("Start get data of " + ConfigParameter.marketList.get(index).getKey());
        String priceUrl = ConfigParameter.marketList.get(index).getValue().getKey();
        String priceData = httpGet.get(priceUrl);

        String volUrl = ConfigParameter.marketList.get(index).getValue().getValue();
        String volData = httpGet.get(volUrl);

        if (priceData == null || volData == null) {
            System.out.println(ConfigParameter.marketList.get(index).getValue() + " get data failed");
            return;
        }
        List<Pair<Pair<String, String>, Pair<String, String>>> resList = parser.parse(priceData, volData);
        rw.buildExcel(resList, ConfigParameter.marketList.get(index).getKey());
        System.out.println("Finish get data of " + ConfigParameter.marketList.get(index).getKey());
        System.out.println("================================================");
    }


}
