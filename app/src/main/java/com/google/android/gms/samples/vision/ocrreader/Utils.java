package com.google.android.gms.samples.vision.ocrreader;

import com.google.android.gms.vision.text.TextBlock;

import java.util.ArrayList;

public  class Utils {
    public static boolean checkText(String item) {
        ArrayList<String> values = new ArrayList<>();
        values.add("E111");
        values.add("E123");
        values.add("E126");
        values.add("E143");
        values.add("E103");
        values.add("E105");
        values.add("E107");
        values.add("E110");
        values.add("E122");
        values.add("E124");
        values.add("E131");
        values.add("E211");
        values.add("E216");
        values.add("E217");
        values.add("E222");
        values.add("E320");
        values.add("E952");

        boolean contains = false;
        for (String value : values) {
            if(item.contains(value)){
                contains = true;
                break;
            }
        }
        return contains;
    }

    }

