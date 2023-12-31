package com.example.demo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity(name = "Book")
@Table(name = "book")
public class Book {
    
    @Id
    @SequenceGenerator(
        name = "book_sequence",
        sequenceName = "book_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "book_sequence"
    )
    @Column (
       name="id",
       updatable = false
    )
    private Long id;

    @Column (
        name="book_name",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String bookName;

    @Column(
        name="created_at",
        nullable = false,
        columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(
        name = "student_id",
        nullable = false,
        referencedColumnName = "id",
        foreignKey = @ForeignKey(
            name="student_book_fk"
        )
    )
    private Student student;

    

    public Book() {
    }

    public Book(String bookName, LocalDateTime createdAt) {
        this.bookName = bookName;
        this.createdAt = createdAt;
    }

    public Book(String bookName, Student student) {
        this.bookName = bookName;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", bookName=" + bookName + ", createdAt=" + createdAt + ", student=" + student + "]";
    }

    
}
