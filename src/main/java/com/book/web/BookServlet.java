package com.book.web;

import com.book.pojo.Book;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    private final BookService bookService = new BookServiceImpl();
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //用封装好的BeanUtils工具类, 把返回的参数注入到Book里
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        bookService.addBook(book);
        request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request, response);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> bookList = bookService.queryBooks();
        request.setAttribute("books", bookList);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //用封装好的BeanUtils工具类, 把返回的参数注入到Book里
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        bookService.updateBook(book);
        request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request, response);
    }

    /**
     * 修改图书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = bookService.queryBookById(Integer.valueOf(request.getParameter("id")));
        request.setAttribute("book", book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }

}
