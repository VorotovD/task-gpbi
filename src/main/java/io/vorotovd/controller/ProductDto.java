package io.vorotovd.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductDto {

    private String name;
    private String description;
    private int kcal;
    private long prodListId;

    public long getProdListId() {
        return prodListId;
    }
}
