package tecqasr.blog.app.blogguist.services.implimentations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tecqasr.blog.app.blogguist.entities.Category;
import tecqasr.blog.app.blogguist.exceptions.ResourceNotFoundException;
import tecqasr.blog.app.blogguist.payloads.CategoryDto;
import tecqasr.blog.app.blogguist.repositories.CategoryRepository;

import tecqasr.blog.app.blogguist.services.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto,Category.class);
        Category savedCategory = repository.save(category);
        return mapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
        Category oldCat = repository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        oldCat.setName(categoryDto.getName());
        oldCat.setDescription(categoryDto.getDescription());
        oldCat.setImage(categoryDto.getImage());
        Category updatedCat = repository.save(oldCat);
        return mapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto showCategoryById(int categoryId) {
        Category category = repository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        return mapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> showAllCategory() {
        List<Category> categories = repository.findAll();
        return categories.stream().map(category -> mapper.map(category, CategoryDto.class)).toList();
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = repository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        repository.delete(category);
    }
}
