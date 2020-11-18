package com.book.web;

import com.book.pojo.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends BaseServlet {
    private final UserService userService =  new UserServiceImpl();
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(username, password);
        if (user==null){
            System.out.println("用户名或密码错误!");
            request.setAttribute("msg", "用户名或密码错误!");
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }else{
            System.out.println("登录成功");
            System.out.println(user);
//            request.setAttribute("key", "value1");
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/regist");
//            requestDispatcher.forward(request, response);
            request.getRequestDispatcher("http://localhost:8080/bookstore/pages/loginSuccess.jsp").forward(request, response);
        }
    }
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//        User user = new User(null, username, password, email);

        User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());
        if(userService.existsUsername(user.getUsername())){
            System.out.println("用户名已存在");
            request.setAttribute("msg", "用户已存在!");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
        } else{
            System.out.println("用户不存在, 执行注册");
            userService.register(user);
        }
    }


}
