package com.book.service.realize;

import com.book.dao.BookMapper;
import com.book.dao.StudentMapper;
import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.Student;
import com.book.service.BookService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import java.util.*;
import java.util.stream.Collectors;


public class BookServiceRealize implements BookService {
    public static final BookServiceRealize bookService = new BookServiceRealize();

    @Override
    public List<Borrow> getBorrowList() {
        try(SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper bookMapper = sqlSession.getMapper((BookMapper.class));
            return bookMapper.getBorrowList();
        }
    }

    @Override
    public List<Book> getActiveBookList() {
        Set<Integer> s = new HashSet<>();
        this.getBorrowList().forEach(borrow -> s.add(borrow.getBook_id()));
        try(SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper bookMapper = sqlSession.getMapper((BookMapper.class));
            return bookMapper.getBookList()
                    .stream()
                    .filter(book -> !s.contains(book.getId()))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<Student> getStudentList() {
        try(SqlSession sqlSession = MybatisUtil.getSession()) {
            StudentMapper bookMapper = sqlSession.getMapper((StudentMapper.class));
            return bookMapper.getStudentList();
        }
    }

    @Override
    public Map<Book, Boolean> getBookList() {
        Set<Integer> s = new HashSet<>();
        this.getBorrowList().forEach(borrow -> s.add(borrow.getBook_id()));
        try (SqlSession sqlSession = MybatisUtil.getSession()){
            Map<Book, Boolean> map = new LinkedHashMap<>();
            BookMapper mapper = sqlSession.getMapper(BookMapper.class);
            mapper.getBookList().forEach(book -> map.put(book, s.contains(book.getId())));
            return map;
        }
    }

    @Override
    public void returnBook(String id) {
        try(SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper bookMapper = sqlSession.getMapper((BookMapper.class));
            bookMapper.deleteBorrow(id);
        }
    }

    @Override
    public void deleteBook(int bid) {
        try(SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper bookMapper = sqlSession.getMapper((BookMapper.class));
            bookMapper.deleteBook(bid);
        }
    }

    @Override
    public void addBorrow(int sid, int bid) {
        try(SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper bookMapper = sqlSession.getMapper((BookMapper.class));
            bookMapper.addBorrow(sid, bid);
        }
    }

    @Override
    public void addBook(String title, String introduction, double price) {
        try(SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper bookMapper = sqlSession.getMapper((BookMapper.class));
            bookMapper.addBook(title, introduction, price);
        }
    }
}