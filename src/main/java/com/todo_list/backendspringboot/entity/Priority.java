package com.todo_list.backendspringboot.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Priority {
    private Long id;
    private String title;
    private String color;
    private Collection<Task> taskById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }
}
