package com.aline.mydemo.base;

import android.util.Log;

/**
 * @author SuTs
 * @create 2020/8/13 9:44
 * @Describe
 */
public class Logger {
    private static final String TAG = "Logger";
    //设为false关闭日志
    public static final boolean IS_DEBUG = true;
    private static int LOG_MAXLENGTH = 2000;

    public static void message(String msg) {
        if (IS_DEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (IS_DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (IS_DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (IS_DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (IS_DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (IS_DEBUG) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.e(tag + "=====" + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.e(tag + "=====" + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }
}
