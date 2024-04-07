package com.zack.bookserver.entity.dto;

import com.zack.bookserver.entity.po.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BookDTO {

    private String bookId;
    @NotBlank
    private String bookName;
    @NotBlank
    private String authorName;
    @NotBlank
    private String bookDesc;
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtils.FORMAT_STRING_DATE)
    private Date publishTime;

    private String status;

    private boolean isDeleted;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    public static Book to(BookDTO dto){
        Book po = new Book();
        BeanUtils.copyProperties(dto,po);
        return po;
    }
}
