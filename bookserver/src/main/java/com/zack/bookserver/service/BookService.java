package com.zack.bookserver.service;


import com.zack.bookserver.entity.dto.BookDTO;
import com.zack.bookserver.entity.po.Book;
import com.zack.bookserver.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll(Sort.by("createTime").descending());
    }


    public Page<Book> findAllPage(PageRequest pageable){
        pageable.withSort(Sort.by("createTime").descending());
        return bookRepository.findAll(pageable);
    }

    public Optional<Book> findById(String bookId) {
        return bookRepository.findById(bookId);
    }


    @Transactional
    public Object save(BookDTO dto) {
        Book book = BookDTO.to(dto);
        Book save = bookRepository.save(book);
        return save;
    }

    public void delete(String bookId) {
        bookRepository.deleteById(bookId);
    }

}
