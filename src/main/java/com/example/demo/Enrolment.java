 package com.example.demo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import javax.persistence.ForeignKey;

/*this enrolment table , the link table is what spring boot did for us
 * now we are creating it oursleves
 */

@Entity(name="Enrolment")
@Table(name = "enrolment")
public class Enrolment {
    
    @EmbeddedId
    private EnrolmentId enrolmentId;  //this is composite key

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(
        name = "student_id",
        foreignKey = @ForeignKey(
            name = "enrolment_student_id_fk"
        )
        )  
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(
        name = "course_id",
        foreignKey = @ForeignKey(
            name = "enrolment_course_id_fk"
        )
        )
    private Course course;

    @Column(
        name = "created_at",
        nullable = false,
        columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;


    public Enrolment() {
    }

    public Enrolment(EnrolmentId enrolmentId, 
                Student student, Course course, LocalDateTime createdAt) {
        this.enrolmentId = enrolmentId;
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }

    public Enrolment(Student student, Course course, LocalDateTime createdAt) {
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }

    public EnrolmentId getEnrolmentId() {
        return enrolmentId;
    }

    public void setEnrolmentId(EnrolmentId enrolmentId) {
        this.enrolmentId = enrolmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    

    
}
