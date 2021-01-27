package com.example.news.model;

import com.example.news.constants.EnumCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListCategories {
    private static final List<Category> list = new ArrayList<>(Arrays.asList(
        new Category(EnumCategory.GENERAL),
        new Category(EnumCategory.ENTERTAINMENT),
        new Category(EnumCategory.SPORTS),
        new Category(EnumCategory.BUSINESS),
        new Category(EnumCategory.SCIENCE),
        new Category(EnumCategory.TECHNOLOGY)));

    public static List<Category> getAllCategories(){
        return  new ArrayList<>(list);
    }
}
