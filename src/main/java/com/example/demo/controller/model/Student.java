package com.example.demo.controller.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import com.example.demo.controller.model.Course;

@Entity
@Table(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "name")
  private String name;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinTable(name = "std_course", joinColumns = {@JoinColumn(name = "students_id", referencedColumnName = "id")},
  inverseJoinColumns = {@JoinColumn(name = "courses_id", referencedColumnName = "id")})
  private Course course;

  public Student() {

  }

  public Student(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String title) {
    this.name = name;
  }
}
