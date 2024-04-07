package com.zack.bookserver.controller;

import com.zack.bookserver.entity.dto.BookDTO;
import com.zack.bookserver.entity.po.Book;
import com.zack.bookserver.entity.vo.BookVO;
import com.zack.bookserver.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("findAll")
    public ResponseEntity findAll(){

        return ResponseEntity.ok(bookService.findAll().stream().map(e->BookVO.from(e)).collect(Collectors.toList()));
    }

    @GetMapping("findById/{bookId}")
    public ResponseEntity findById(@PathVariable String bookId){
        Optional<Book> byId = bookService.findById(bookId);
        if(byId.isEmpty()){
            throw new RuntimeException("WRONG BOOK ID");
        }
        BookVO from = BookVO.from(byId.get());
        return ResponseEntity.ok( from );
    }

    @PostMapping("save")
    public ResponseEntity save(@RequestBody @Valid BookDTO dto){

        return ResponseEntity.ok(bookService.save(dto));
    }


    @DeleteMapping("delete/{bookId}")
    public ResponseEntity delete(@PathVariable String bookId){
        bookService.delete(bookId);
        return ResponseEntity.ok().build();
    }
}
