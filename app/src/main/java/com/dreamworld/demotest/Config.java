package com.dreamworld.demotest;

import android.graphics.Bitmap;

/**
 * Created by faizan on 31/07/2017.
 */

public class Config {
    public static String[] names;
    public static String[] urls;
    public static int[] viewtype;
    public static final String TAG_JSON_ARRAY="result";

    public Config(int i){
        names = new String[i];
        urls = new String[i];
        viewtype = new int[i];
    }
}
