package com.sven.variousviews;


import com.sven.variousviews.bean.FruitBean;
import com.sven.variousviews.bean.ImageUrls;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    private static String[] data = {"Apple", "Banana", "Orange", "Cherry", "Strawberry",
            "Apple", "Banana", "Orange", "Cherry", "Strawberry",
            "Apple", "Banana", "Orange", "Cherry", "Strawberry",
            "Apple", "Banana", "Orange", "Cherry", "Strawberry"
    };

    public static List<FruitBean> initData(){
        ArrayList<FruitBean> fruitBeanLists = new ArrayList<>();
        int len = data.length > ImageUrls.imageUrls.length ? ImageUrls.imageUrls.length : data.length;
        for(int i = 0; i<len; i++){
            fruitBeanLists.add(new FruitBean(data[i], i+"",ImageUrls.imageUrls[i]));
        }
        return  fruitBeanLists;
    }

    public static List<FruitBean> initData2(){
        ArrayList<FruitBean> fruitBeanLists = new ArrayList<>();
        int len = data.length > ImageUrls.imageUrls.length ? ImageUrls.imageUrls.length : data.length;
        for(int i = 0; i<len; i++){
            fruitBeanLists.add(new FruitBean(getRandomLenName(data[i]), i+"",ImageUrls.imageUrls[i]));
        }


        return  fruitBeanLists;
    }

    private static String getRandomLenName(String name){
        Random random = new Random();
        int length = random.nextInt(10)+1;
        StringBuilder builder = new StringBuilder();
        for(int i =0 ;i < length;i++){
            builder.append(name);
        }
        return builder.toString();
    }
}
