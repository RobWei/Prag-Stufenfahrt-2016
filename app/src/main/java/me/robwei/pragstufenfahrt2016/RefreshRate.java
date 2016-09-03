package me.robwei.pragstufenfahrt2016;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by RobWei on 28.08.2016.
 */
public class RefreshRate {
    public static String refreshRate()
    {
        InputStream io = null;
        try {
            io = new URL("http://api.fixer.io/latest?symbols=CZK").openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(io);
        JSONArray arr = null;
        JSONObject Object = null;
        try {
            Object = new JSONObject(sc.nextLine());
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        String[] splitResult = null;
        try {
            splitResult = Object.getString("rates").split(":");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return removeLastChar(splitResult[1]);
    }
    private static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }
    public static boolean isOnline()
    {
        InputStream io = null;
        try {
            io = new URL("http://api.fixer.io/latest?symbols=CZK").openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return io != null;


    }
}
