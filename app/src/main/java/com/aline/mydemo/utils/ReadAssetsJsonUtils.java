package com.aline.mydemo.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author SuTs
 * @create 2020/4/28 11:16
 * @Describe
 */
public class ReadAssetsJsonUtils {

    /**
     * 读取assets中的json文件
     *
     * @param context
     * @return
     */
    public String readJsonFromAsset(Context context) {
        String result = "";
        try {
            InputStream inputStream = context.getResources().getAssets().open("json.json");
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);
            String jsonLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((jsonLine = reader.readLine()) != null) {
                stringBuilder.append(jsonLine);
            }
            reader.close();
            isr.close();
            inputStream.close();
            result = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
