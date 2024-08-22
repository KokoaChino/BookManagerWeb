package com.book.dao;

import java.util.List;
import com.book.entity.Student;
import org.apache.ibatis.annotations.Select;


public interface StudentMapper {
    @Select("select * from student")
    List<Student> getStudentList();
}