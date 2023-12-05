package com.todo_list.backendspringboot.service;

import com.todo_list.backendspringboot.entity.Category;
import com.todo_list.backendspringboot.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    //TODO add Exception

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findAllByOrderByTitleAsc() {
        return categoryRepository.findAllByOrderByTitleAsc();
    }

    public List<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
