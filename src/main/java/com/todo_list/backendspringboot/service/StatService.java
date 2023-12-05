package com.todo_list.backendspringboot.service;

import com.todo_list.backendspringboot.entity.Stat;
import com.todo_list.backendspringboot.repository.StatRepository;
import org.springframework.stereotype.Service;

@Service
public class StatService {
    private final StatRepository statRepository;

    public StatService(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    //TODO add Exception
    public Stat findById(Long id) {
        return statRepository.findById(id).get();
    }
}
