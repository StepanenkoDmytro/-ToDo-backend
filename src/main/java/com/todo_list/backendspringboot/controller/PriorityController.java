package com.todo_list.backendspringboot.controller;

import com.todo_list.backendspringboot.entity.Priority;
import com.todo_list.backendspringboot.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/test")
    public List<Priority> test() {
        List<Priority> priorities = priorityRepository.findAll();
        return priorities;
    }
}
