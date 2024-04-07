package com.zack.bookserver.entity.po;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="book")
@Data
public class Book {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "book_id")
    private String bookId;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "book_desc")
    private String bookDesc;
    @Column(name = "publish_time")
    private Date publishTime;
    @Column(name = "status")
    private String status;
    @Column(name = "is_deleted")
    private String isDeleted;
    @Column(name = "create_by")
    private String createBy;
    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_by")
    private String updateBy;
    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;
}
