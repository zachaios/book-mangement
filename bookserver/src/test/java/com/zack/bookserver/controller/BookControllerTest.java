//package com.zack.bookserver.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.zack.bookserver.entity.dto.BookDTO;
//import com.zack.bookserver.entity.po.Book;
//import com.zack.bookserver.service.BookService;
//import jakarta.annotation.Resource;
//import jakarta.servlet.ServletException;
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.Date;
//import java.util.Optional;
//
//
//
//@WebMvcTest(BookController.class)
//public class BookControllerTest {
//
//    @Resource
//    private MockMvc mvc;
//
//    @MockBean
//    private BookService bookService;
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Disabled
//    @SneakyThrows
//    @Test
//    public void findAllTest(){
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/findAll")).andReturn();
//        String contentAsString = mvcResult.getResponse().getContentAsString();
//        System.out.println(contentAsString);
//
//    }
//    @Disabled
//    @SneakyThrows
//    @Test
//    public void findByIdTest(){
//        Assertions.assertThrows(ServletException.class,()->mvc.perform(MockMvcRequestBuilders.get("/findById/1")));
//    }
//    @Disabled
//    @SneakyThrows
//    @Test
//    public void findByIdTest2(){
//        Mockito.when(bookService.findById("1")).thenReturn(Optional.of(new Book()));
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/findById/1")).andReturn();
//        String contentAsString = mvcResult.getResponse().getContentAsString();
//        Assertions.assertNotNull(contentAsString);
//    }
//
//    @Disabled
//    @SneakyThrows
//    @Test
//    public void saveTest(){
//        BookDTO dto = new BookDTO();
//        dto.setBookId("1");
//        dto.setBookName("Alive");
//        dto.setBookDesc("description");
//        dto.setPublishTime(new Date());
//        dto.setAuthorName("Yu Hua");
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/save").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto))).andReturn();
//        String contentAsString = mvcResult.getResponse().getContentAsString();
//        System.out.println(contentAsString);
//    }
//    @Disabled
//    @SneakyThrows
//    @Test
//    public void deleteTest(){
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete("/delete/1")).andReturn();
//        String contentAsString = mvcResult.getResponse().getContentAsString();
//        System.out.println(contentAsString);
//    }
//}
