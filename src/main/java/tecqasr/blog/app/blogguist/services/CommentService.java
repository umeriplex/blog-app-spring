package tecqasr.blog.app.blogguist.services;

import tecqasr.blog.app.blogguist.payloads.CommentDto;

public interface CommentService {
    CommentDto save(CommentDto commentDto, int postId);
    CommentDto update(CommentDto commentDto, int commentId, int postId);
    void delete(int commentId);

}
