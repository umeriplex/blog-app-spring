package tecqasr.blog.app.blogguist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tecqasr.blog.app.blogguist.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
