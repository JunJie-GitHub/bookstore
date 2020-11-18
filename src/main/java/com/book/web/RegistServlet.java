package com.book.web;

import com.book.pojo.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 不再使用, 以合并到UserServlet
 */
public class RegistServlet extends HttpServlet {
    private final UserService userService =  new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
//        UserService userService =  new UserServiceImpl();
        User user = new User(null, username, password, email);
        System.out.println(user);
        if(userService.existsUsername(username)){
            System.out.println("用户名已存在");
            req.setAttribute("msg", "用户已存在!");
            req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
        } else{
            System.out.println("用户不存在, 执行注册");
            userService.register(user);
        }
    }
}
