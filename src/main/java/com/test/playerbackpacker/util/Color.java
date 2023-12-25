package com.test.playerbackpacker.util;

import java.util.ArrayList;
import java.util.List;

public class Color {
    public static List<String> listColorTranslate(List<String> stringList){
        List<String> a = new ArrayList<>();
        for(String s:stringList){
            a.add(s.replace("&","§"));
        }
        return a;

    }
    public static String stringColorTranslate(String string) {


        return string.replace("&","§");
    }
}
