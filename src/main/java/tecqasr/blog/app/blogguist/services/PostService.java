package tecqasr.blog.app.blogguist.services;
import tecqasr.blog.app.blogguist.payloads.PostDto;
import tecqasr.blog.app.blogguist.payloads.PostResponse;


import java.util.List;

public interface PostService {
    PostDto savePost(PostDto postDto, int userId, int categoryId);
    PostDto updatePost(PostDto postDto, int postId);

    PostDto showPostById(int postId);

    PostResponse showAllPosts(int pageNo, int pageSize, String sortBy);

    PostResponse searchPosts(String keyword);

    List<PostDto> getPostsByUser(int userId);

    List<PostDto> getPostsByCategory(int categoryId);

    void deletePost(int postId);
}
