package tecqasr.blog.app.blogguist.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tecqasr.blog.app.blogguist.entities.Category;
import tecqasr.blog.app.blogguist.entities.Post;
import tecqasr.blog.app.blogguist.entities.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category user);

    List<Post> findByTitleContaining(String keyword);
}
