package com.todo_list.backendspringboot.service;

import com.todo_list.backendspringboot.entity.Priority;
import com.todo_list.backendspringboot.repository.PriorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriorityService {
    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }
    //TODO add Exception

    public List<Priority> findAllByOrderByIdAsc() {
        return priorityRepository.findAllByOrderByIdAsc();
    }

    public Optional<Priority> findById(Long id) {
        return priorityRepository.findById(id);
    }

    public List<Priority> findByTitle(String title) {
        return priorityRepository.findByTitle(title);
    }

    public Priority save(Priority priority) {
        return priorityRepository.save(priority);
    }

    public void deleteById(Long id) {
        priorityRepository.deleteById(id);
    }
}
