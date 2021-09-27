package com.jcjz.one;

import java.util.ArrayList;

public class CommonUtils {
    private static CommonUtils instance;
    private ArrayList<Integer> cars;
    private ArrayList<Integer> selectCars;

    public CommonUtils(){
        cars=new ArrayList<>();
        selectCars =new ArrayList<>();
        cars.add(R.drawable.big_no1_img);
        cars.add(R.drawable.big_no2_img);
        cars.add(R.drawable.big_no3_img);
        cars.add(R.drawable.big_no4_img);
        cars.add(R.drawable.big_no5_hm_img);
        cars.add(R.drawable.big_no6_img);
        selectCars.add(R.drawable.no1_img);
        selectCars.add(R.drawable.no2_img);
        selectCars.add(R.drawable.no3_img);
        selectCars.add(R.drawable.no4_img);
        selectCars.add(R.drawable.no5_img);
        selectCars.add(R.drawable.no6_img);
    }
    public static CommonUtils getInstance() {
        if (instance == null) {
            synchronized (CommonUtils.class) {
                if (instance == null)
                    instance = new CommonUtils();
            }
        }
        return instance;
    }

    public ArrayList<Integer> getCars() {
        return cars;
    }

    public ArrayList<Integer> getSelectCars() {
        return selectCars;
    }
}
