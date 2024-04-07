package com.zack.bookserver.service;

import com.zack.bookserver.entity.dto.BookDTO;
import com.zack.bookserver.entity.po.Book;
import com.zack.bookserver.repository.BookRepository;
import com.zack.bookserver.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testFindAll(){
        List<Book> list = new ArrayList<>();
        Book book = new Book();
        list.add(book);
        Mockito.when(bookRepository.findAll(Sort.by("createTime").descending())).thenReturn(list);
        Assertions.assertDoesNotThrow(()->{bookService.findAll();});
    }

    @Test
    public void testFindById(){
        Book book = new Book();
        Mockito.when(bookRepository.findById("1")).thenReturn(Optional.of(book));
        Assertions.assertDoesNotThrow(()->{bookService.findById("1").isPresent();});
    }

    @Test
    public void testSave(){
        Book book = new Book();
        BookDTO bookDto = new BookDTO();
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Assertions.assertDoesNotThrow(()->{bookService.save(bookDto);});
    }

    @Test
    public void testDelete(){
        Assertions.assertDoesNotThrow(()->{bookService.delete("1");});
    }
}
