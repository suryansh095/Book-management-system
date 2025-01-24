package com.example.mysqllevel3.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mysqllevel3.Entity.BookEntity;
import com.example.mysqllevel3.Reposistory.BookReposistory;
import com.example.mysqllevel3.Model.Book;

@Service
public class BookServiceimp implements BookService {  // Implement the BookService interface

    private final BookReposistory bookRepository;

    @Autowired
    public BookServiceimp(BookReposistory bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        List<BookEntity> bookEntities = bookRepository.findAll();
        return bookEntities.stream().map(entity -> {
            Book book = new Book();
            BeanUtils.copyProperties(entity, book);
            return book;
        }).collect(Collectors.toList());
    }

    @Override
    public Book getBookById(int id) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);
        if (optionalBookEntity.isPresent()) {
            Book book = new Book();
            BeanUtils.copyProperties(optionalBookEntity.get(), book);
            return book;
        }
        return null;
    }

    @Override
    public Book saveBook(Book book) {
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(book, bookEntity);
        bookRepository.save(bookEntity);
        return book;
    }

    @Override
    public String deleteBook(int id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return "Book deleted successfully";
        }
        return "Book not found";
    }

    @Override
    public Book updateMyBook(int id, Book book) {
        BookEntity bookEntity = bookRepository.findById(id).get();
       

        // update the  previous value 
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setTitle(book.getTitle());

        bookRepository.save(bookEntity);

        return book;
        }
        
    }

