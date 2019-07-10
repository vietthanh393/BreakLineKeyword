package com.vietthanh.tikidisplaykeyword.common.util;

import android.graphics.Color;

import java.util.Random;

public class ColorUtil {
    public static int generateColor() {
        /*
        Do not get value 255 because Color.rgb(255, 255, 255) is White.
        We are using White for textView color already.
         */
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);

        if (r == 255 && g == 255 && b == 255) {
            return Color.rgb(0, 0, 0); // Black
        }

        return Color.rgb(r, g, b);
    }
}
