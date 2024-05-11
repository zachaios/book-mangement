package com.zack.bookserver.controller;

import com.zack.bookserver.cache.RedisService;
import com.zack.bookserver.entity.dto.BookDTO;
import com.zack.bookserver.entity.po.Book;
import com.zack.bookserver.entity.vo.BookVO;
import com.zack.bookserver.service.BookService;
import io.netty.util.CharsetUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RedisService redisService;

    @GetMapping("findAll")
    public ResponseEntity findAll() {
        Object redisList = redisService.getValueByKey("list");
        if(redisList != null){
            return ResponseEntity.ok(redisList);
        }
        List<BookVO> list = bookService.findAll().stream().map(BookVO::from).collect(Collectors.toList());
        redisService.setKeyValue("list",list);
        return ResponseEntity.ok(list);
    }


    @GetMapping("findAll2")
    public ResponseEntity findAll2() {
        Object list = redisService.getValueByKey("list");
        return ResponseEntity.ok(list);
    }

    @GetMapping("findAllPage")
    public Page<Book> findAllPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return bookService.findAllPage(PageRequest.of(page, size));
    }

    @GetMapping("findById/{bookId}")
    public ResponseEntity findById(@PathVariable String bookId) {
        Optional<Book> byId = bookService.findById(bookId);
        if (byId.isEmpty()) {
            throw new RuntimeException("WRONG BOOK ID");
        }
        BookVO from = BookVO.from(byId.get());
        return ResponseEntity.ok(from);
    }

    @PostMapping("save")
    public ResponseEntity save(@RequestBody @Valid BookDTO dto) {
        redisService.setKeyValue("list",null);
        return ResponseEntity.ok(bookService.save(dto));
    }


    @DeleteMapping("delete/{bookId}")
    public ResponseEntity delete(@PathVariable String bookId) {
        bookService.delete(bookId);
        return ResponseEntity.ok().build();
    }
}
