package com.book.entity;

import lombok.Data;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
public class Borrow {
    int id;
    int book_id;
    String book_name;
    Date time;
    String student_name;
    int student_id;

    public String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }
}