package com.example.mysqllevel3.Reposistory;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mysqllevel3.Entity.BookEntity;
public interface BookReposistory extends JpaRepository<BookEntity,Integer> {
    
}
