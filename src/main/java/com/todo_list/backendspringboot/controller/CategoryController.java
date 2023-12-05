package com.todo_list.backendspringboot.controller;

import com.todo_list.backendspringboot.entity.Category;
import com.todo_list.backendspringboot.repository.CategoryRepository;
import com.todo_list.backendspringboot.search.CategorySearchValues;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    //TODO add logger for controller
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/all")
    public List<Category> findAll() {
        return categoryRepository.findAllByOrderByTitleAsc();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()) {
            return new ResponseEntity("Category with id=" + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category.get());
    }

    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categoryValues) {
        return ResponseEntity.ok(categoryRepository.findByTitle(categoryValues.getText()));
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        if(category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("Redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if(category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {
        if(category.getId() == null && category.getId() == 0) {
            return new ResponseEntity("Missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if(category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        categoryRepository.save(category);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try{
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity("Priority with id=" + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
