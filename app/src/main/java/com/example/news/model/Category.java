package com.example.news.model;

import com.example.news.constants.EnumCategory;

public class Category {
    final EnumCategory enumCategory;

    public Category(EnumCategory general) {
        this.enumCategory = general;
    }

    public EnumCategory getEnumCategory() {
        return enumCategory;
    }


}
