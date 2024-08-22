package com.book.dao;

import com.book.entity.Book;
import com.book.entity.Borrow;
import org.apache.ibatis.annotations.*;
import java.util.List;


public interface BookMapper {
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "b_id", property = "book_id"),
            @Result(column = "title", property = "book_name"),
            @Result(column = "time", property = "time"),
            @Result(column = "name", property = "student_name"),
            @Result(column = "s_id", property = "student_id")
    })
    @Select("select * from borrow, student, book where borrow.b_id=book.id and borrow.s_id=student.id")
    List<Borrow> getBorrowList();

    @Select("select * from book")
    List<Book> getBookList();

    @Delete("delete from borrow where id=#{id}")
    void deleteBorrow(String id);

    @Insert("insert into borrow(s_id, b_id, time) values(#{sid}, #{bid}, NOW())")
    void addBorrow(@Param("sid") int sid, @Param("bid") int bid);

    @Delete("delete from book where id = #{bid}")
    void deleteBook(int bid);

    @Insert("insert into book(title, introduction, price) values(#{title}, #{introduction}, #{price})")
    void addBook(@Param("title") String title, @Param("introduction") String introduction, @Param("price") double price);
}