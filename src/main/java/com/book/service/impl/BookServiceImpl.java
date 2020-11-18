package com.book.service.impl;

import com.book.dao.BookDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.pojo.Book;
import com.book.service.BookService;

import java.util.List;

/**
 * BookDaoImpl实现的是对数据库的操作,不能有其他代码,  BookServiceImpl可以有一些逻辑代码等
 */
public class BookServiceImpl implements BookService {
    private final BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }
}
