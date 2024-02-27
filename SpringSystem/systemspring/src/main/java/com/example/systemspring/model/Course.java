package com.example.systemspring.model;


public class Course {
    private String name;
    private Double mark;

    public Course(String name, Double mark) {
        this.name = name;
        this.mark = mark;
    }

    public Course() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMark() {
        return this.mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public String toString() {
        return "Mark in " + this.name + " = " + this.mark;
    }
}
