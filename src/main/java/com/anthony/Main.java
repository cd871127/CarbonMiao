package com.anthony;

import com.anthony.config.ConfigParameter;
import com.anthony.config.InitParameter;
import com.anthony.manager.DownloadManager;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by chend on 2017/6/15.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println();
        System.out.println("Fuuuuuuuuuuuuuuuuuck U, Jian Jian Miao!\n");
        System.out.println("Notice:");
        System.out.println("    This program is free for Jian Jian Miao\n5$ per month for others.");
        System.out.println("PayPal: cdistc@163.com");
        System.out.println("Alipay: cdistc@163.com\n");


        InitParameter initParameter = new InitParameter();
        initParameter.init();

        int i = 0;
        for (Map.Entry<String, Map.Entry<String, String>> e : ConfigParameter.marketList) {
            System.out.println("    " + (i++) + " " + e.getKey());
        }

        System.out.println();
        System.out.println("    " + (ConfigParameter.marketList.size()) + " All Market\n");
        System.out.println("Input a number to select a market: (input q to quit)");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        if (userInput.equals("q"))
            return;

        boolean isCorrect = userInput.matches("[0-9]+");

        if (isCorrect) {
            int index = Integer.valueOf(userInput);
            if (index <= ConfigParameter.marketList.size() || index >= 0) {
                DownloadManager dm = new DownloadManager();
                dm.download(index);
                return;
            }

        }
        System.out.println("Incorrect input");
    }
}
