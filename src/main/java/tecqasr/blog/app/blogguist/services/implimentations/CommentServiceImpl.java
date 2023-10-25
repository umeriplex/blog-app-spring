package tecqasr.blog.app.blogguist.services.implimentations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tecqasr.blog.app.blogguist.entities.Comment;
import tecqasr.blog.app.blogguist.entities.Post;
import tecqasr.blog.app.blogguist.exceptions.ResourceNotFoundException;
import tecqasr.blog.app.blogguist.payloads.CommentDto;
import tecqasr.blog.app.blogguist.repositories.CommentRepository;
import tecqasr.blog.app.blogguist.repositories.PostRepository;
import tecqasr.blog.app.blogguist.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    private final ModelMapper mapper = new ModelMapper();



    @Override
    public CommentDto save(CommentDto commentDto, int postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        Comment comment = mapper.map(commentDto, Comment.class);

        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        return mapper.map(newComment, CommentDto.class);
    }

    @Override
    public CommentDto update(CommentDto commentDto, int commentId, int postId) {
        return null;
    }

    @Override
    public void delete(int commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
        commentRepository.delete(comment);
    }
}
