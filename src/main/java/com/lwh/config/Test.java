package com.lwh.config;

import java.util.HashMap;
import java.util.Map;

public class Test {

    static void Resize(){
        Map<String,String> hashMap = new HashMap<>(20);
        for (int i=0;i<20;i++){
            hashMap.put(String.valueOf(i),"AAA");
        }
    }

    public static void main(String[] args) {
        System.out.println();
    }

}
