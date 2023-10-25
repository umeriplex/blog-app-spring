package tecqasr.blog.app.blogguist.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tecqasr.blog.app.blogguist.payloads.ApiResponse;
import tecqasr.blog.app.blogguist.payloads.CategoryDto;
import tecqasr.blog.app.blogguist.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService service;

    // save category
    @PostMapping("/")
    public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = service.saveCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // update category
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @Valid
            @RequestBody CategoryDto categoryDto,
            @PathVariable("categoryId") int categoryId
            ){
        CategoryDto updatedCategory = service.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.CREATED);
    }

    // show category by id
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> showCategoryById(@PathVariable("categoryId") int categoryId){
        CategoryDto user = service.showCategoryById(categoryId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // show all categories
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> showAllCategories(){
        List<CategoryDto> categoryDtos = service.showAllCategory();
        return new ResponseEntity<>(categoryDtos,HttpStatus.OK);
    }

    // delete category

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") int categoryId){
        service.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted",true),HttpStatus.OK);
    }


}
