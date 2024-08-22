package com.book.service;

import javax.servlet.http.HttpSession;


public interface UserService {
    boolean check(String username, String password, HttpSession httpSession);
    void addUser(String username, String nickname, String password);
}