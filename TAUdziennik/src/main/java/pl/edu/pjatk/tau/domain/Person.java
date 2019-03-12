package pl.edu.pjwstk.tau.domain;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private long id;
    private String name;
    private List<Double> grades;

    public void setId(long i) {
        id  = i;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrades(List<Double> grades) {
        this.grades = grades;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public List<Double> getGrades() {
        return grades;
    }
}