package com.todo_list.backendspringboot.controller;

import com.todo_list.backendspringboot.entity.Priority;
import com.todo_list.backendspringboot.repository.PriorityRepository;
import com.todo_list.backendspringboot.search.PrioritySearchValues;
import com.todo_list.backendspringboot.service.PriorityService;
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
    private final PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping("/all")
    public List<Priority> findAll() {
        return priorityService.findAllByOrderByIdAsc();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id) {
        Optional<Priority> priority = priorityService.findById(id);

        return ResponseEntity.ok(priority.get());
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValues priorityValues) {
        return ResponseEntity.ok(priorityService.findByTitle(priorityValues.getText()));
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
        return ResponseEntity.ok(priorityService.save(priority));
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

        priorityService.save(priority);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try{
            priorityService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity("Priority with id=" + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
