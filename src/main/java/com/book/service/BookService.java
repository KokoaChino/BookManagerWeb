package com.book.service;

import java.util.List;
import java.util.Map;
import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;

public interface BookService {
    List<Borrow> getBorrowList();
    List<Book> getActiveBookList();
    List<Student> getStudentList();
    Map<Book, Boolean> getBookList();
    void returnBook(String id);
    void deleteBook(int bid);
    void addBorrow(int sid, int bid);
    void addBook(String title, String introduction, double price);
}