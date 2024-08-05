package com.example.capstone1.Controller;
import com.example.capstone1.Api.ApiResponse;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    //Get all categories
    @GetMapping("/categories")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }

    //Add category
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String msg =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(201).body(new ApiResponse("Category added successfully"));
    }

    //Update category
    @PutMapping("/update/{categoryId}")
    public ResponseEntity updateCategory(@PathVariable int categoryId, @Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String msg =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        boolean isCategoryUpdated = categoryService.updateCategory(categoryId, category);
        if (isCategoryUpdated) {
            return ResponseEntity.status(201).body(new ApiResponse("Category updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Category not found"));
    }

    //Delete category
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable int categoryId) {
        boolean isCategoryDeleted = categoryService.deleteCategory(categoryId);
        if (isCategoryDeleted) {
            return ResponseEntity.status(201).body(new ApiResponse("Category deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Category not found"));
    }
}
