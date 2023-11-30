package com.todo_list.backendspringboot.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Stat {
    private Long id;
    private Long completedTotal;
    private Long uncompletedTotal;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "completed_total")
    public Long getCompletedTotal() {
        return completedTotal;
    }

    @Basic
    @Column(name = "uncompleted_total")
    public Long getUncompletedTotal() {
        return uncompletedTotal;
    }
}
