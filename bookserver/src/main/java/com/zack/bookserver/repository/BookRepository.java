package com.zack.bookserver.repository;


import com.zack.bookserver.entity.po.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {


}
