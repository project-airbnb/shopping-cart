package com.project.shopcart.controller;

import com.project.shopcart.model.Product;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class GetUrlProduct {
    public List<String> covertStringToURL(List<Product> products) {
        List<String> strs = new ArrayList<String>();
        try {
            for (int i = 0; i < products.size(); i++) {
                String temp = Normalizer.normalize(products.get(i).getName(), Normalizer.Form.NFD);
                Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
                strs.add(pattern.matcher(temp).replaceAll("").toLowerCase()
                        .replaceAll(" ", "-").replaceAll("đ", "d"));
            }
            return strs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String covertStringToURL(String str) {
        try {
            String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
