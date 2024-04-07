package com.zack.bookserver.entity.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.zack.bookserver.entity.dto.BookDTO;
import com.zack.bookserver.entity.po.Book;
import lombok.Data;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class BookVO {

    private String bookId;

    private String bookName;

    private String authorName;

    private String bookDesc;
//    @JsonFormat(pattern = DateTimeUtils.FORMAT_STRING_DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishTime;

    private String status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    public static BookVO from(Book dto){
        BookVO vo = new BookVO();
        BeanUtils.copyProperties(dto,vo);
        return vo;
    }

//    public Date getPublishTime() {
//        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtils.FORMAT_STRING_DATE);
//        try {
//            return sdf.parse(publishTime.toString());
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    public void setPublishTime(Date publishTime) {
//        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtils.FORMAT_STRING_DATE);
//        try {
//            Date parse = sdf.parse(publishTime.toString());
//            this.publishTime = parse;
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
