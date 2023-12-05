package com.todo_list.backendspringboot.controller;

import com.todo_list.backendspringboot.entity.Priority;
import com.todo_list.backendspringboot.repository.PriorityRepository;
import com.todo_list.backendspringboot.search.PrioritySearchValues;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/priority")
public class PriorityController {
    //TODO add logger for controller
    private final PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/all")
    public List<Priority> findAll() {
        return priorityRepository.findAllByOrderByIdAsc();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id) {
        Optional<Priority> priority = priorityRepository.findById(id);
        if(priority.isEmpty()) {
            return new ResponseEntity("Priority with id=" + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priority.get());
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValues priorityValues) {
        return ResponseEntity.ok(priorityRepository.findByTitle(priorityValues.getText()));
    }

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {
        if(priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("Redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if(priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        if(priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("Missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @PutMapping("/update")
    public ResponseEntity<Priority> update(@RequestBody Priority priority) {
        if(priority.getId() == null && priority.getId() == 0) {
            return new ResponseEntity("Missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if(priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        if(priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("Missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }

        priorityRepository.save(priority);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try{
            priorityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity("Priority with id=" + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
