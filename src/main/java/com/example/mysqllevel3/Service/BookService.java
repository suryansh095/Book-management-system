package com.example.mysqllevel3.Service;

import java.util.List;
import com.example.mysqllevel3.Model.Book;

public interface BookService {
    List<Book> getBooks();
    Book getBookById(int id);
    Book saveBook(Book book);
    String deleteBook(int id);
    Book updateMyBook(int id, Book book);
}
