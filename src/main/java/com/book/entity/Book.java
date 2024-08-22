package com.book.entity;

import lombok.Data;


@Data
public class Book {
    int id;
    String title;
    String introduction;
    double price;
}