package com.aline.mydemo.base;

import android.os.Environment;

/**
 * @author SuTs
 * @create 2020/8/13 9:28
 * @Describe
 */
public class ConstantString {
    public static final long CACHE_SIZE = 1024 * 1024 * 100;

    public static final String BASE_URL = "";//TODO

    public static final String SD_PATH = Environment.getExternalStorageDirectory() + "/mydemo/http/";

    public static String CACHE_PATH = SD_PATH + "cache";
}
