package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity(name="Student")
@Table(
    name = "Student",
    uniqueConstraints = {
        @UniqueConstraint(name="student_email_unique", columnNames = "email")
    }
)
public class Student {
    
    @Id
    @SequenceGenerator(
        name = "studnet_sequence",
        sequenceName = "studnet_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "studnet_sequence"
    )
    @Column (
       name="id",
       updatable = false
    )
    private Long id;
    
    @Column(
        name="first_name",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String firstName;
    
    @Column(
        name="last_name",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
        name="email",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String email;

    @Column(
        name="age",
        nullable = false
        )
    private Integer age;

    @OneToOne(
        mappedBy = "student", //  this mapped by creates bidirectional
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        orphanRemoval = true
    ) 
    private StudentIdCard studentIdCard;

    @OneToMany(
        mappedBy = "student",
        orphanRemoval = true,
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        fetch = FetchType.LAZY  //better to make lazy fro oneToMany
    )
    private List<Book> books = new ArrayList<>();

    @OneToMany(
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        mappedBy = "student"
    )
    private List<Enrolment> enrolments = new ArrayList<>(); 

    public Student(){}

    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public void addBook(Book book){
        if(!this.books.contains(book)){
            this.books.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book){
        if(this.books.contains(book)){
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public List<Book> getBooks(){
        return this.books;
    }

    public List<Enrolment> getEnrolments() {
        return enrolments;
    }

    public void addEnrolment(Enrolment enrolment){
        if(!enrolments.contains(enrolment)){
            enrolments.add(enrolment);
        }
    } 

    public void removeEnrolment(Enrolment enrolment){
        enrolments.remove(enrolment);
    }
    
    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", age=" + age + "]";
    }
    
    
}
