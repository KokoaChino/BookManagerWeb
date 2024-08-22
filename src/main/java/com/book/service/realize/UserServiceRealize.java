package com.book.service.realize;

import com.book.dao.BookMapper;
import com.book.dao.UserMapper;
import com.book.entity.User;
import com.book.service.UserService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import javax.servlet.http.HttpSession;


public class UserServiceRealize implements UserService {
    @Override
    public boolean check(String username, String password, HttpSession httpSession) {
        try(SqlSession sqlSession = MybatisUtil.getSession()) {
            UserMapper userMapper = sqlSession.getMapper((UserMapper.class));
            User user = userMapper.getUser(username, password);
            if (user == null) return false;
            httpSession.setAttribute("user", user);
            return true;
        }
    }

    @Override
    public void addUser(String username, String nickname, String password) {
        try(SqlSession sqlSession = MybatisUtil.getSession()) {
            UserMapper userMapper = sqlSession.getMapper((UserMapper.class));
            userMapper.addUser(username, nickname, password);
        }
    }
}