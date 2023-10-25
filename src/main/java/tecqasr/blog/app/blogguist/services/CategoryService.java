package tecqasr.blog.app.blogguist.services;
import tecqasr.blog.app.blogguist.payloads.CategoryDto;
import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
    CategoryDto showCategoryById(int categoryId);
    List<CategoryDto> showAllCategory();
    void deleteCategory(int categoryId);
}