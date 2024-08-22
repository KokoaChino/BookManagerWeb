package com.book.servlet.pages;

import com.book.entity.User;
import com.book.service.BookService;
import com.book.service.realize.BookServiceRealize;
import com.book.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    BookService bookService;
    @Override
    public void init() throws ServletException {
        bookService = BookServiceRealize.bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("nickname", user.getNickname());
        context.setVariable("borrow_list", bookService.getBorrowList());
        context.setVariable("book_count", bookService.getBookList().size());
        context.setVariable("student_count", bookService.getStudentList().size());
        ThymeleafUtil.process("index.html", context, resp.getWriter());
    }
}