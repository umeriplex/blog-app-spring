package tecqasr.blog.app.blogguist.services.implimentations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tecqasr.blog.app.blogguist.entities.Category;
import tecqasr.blog.app.blogguist.entities.Post;
import tecqasr.blog.app.blogguist.entities.User;
import tecqasr.blog.app.blogguist.exceptions.ResourceNotFoundException;
import tecqasr.blog.app.blogguist.payloads.PostDto;
import tecqasr.blog.app.blogguist.payloads.PostResponse;
import tecqasr.blog.app.blogguist.repositories.CategoryRepository;
import tecqasr.blog.app.blogguist.repositories.CommentRepository;
import tecqasr.blog.app.blogguist.repositories.PostRepository;
import tecqasr.blog.app.blogguist.repositories.UserRepository;
import tecqasr.blog.app.blogguist.services.PostService;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommentRepository commentRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public PostDto savePost(PostDto postDto, int userId, int categoryId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        Post post = mapper.map(postDto, Post.class);
        post.setCreatedAt(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = repo.save(post);
        return mapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        Post post = repo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImage(postDto.getImage());
        post.setTag(postDto.getTag());
        Post updatedPost = repo.save(post);
        return mapper.map(updatedPost, PostDto.class);
    }

    @Override
    public PostDto showPostById(int postId) {
        Post post = repo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));

        // print all comments in post
        System.out.println("Comments in post: " + post.getComments().size());

        return mapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse showAllPosts(int pageNo, int pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Post> pagePosts = repo.findAll(pageable);
        List<Post> posts = pagePosts.getContent();

        PostResponse response = new PostResponse();

        response.setPosts(posts.stream().map(post -> mapper.map(post,PostDto.class)).toList());
        response.setCurrentPage(pagePosts.getNumber());
        response.setTotalPages(pagePosts.getTotalPages());
        response.setTotalPosts(pagePosts.getTotalElements());
        response.setPageSize(pagePosts.getSize());
        response.setLast(pagePosts.isLast());


        return response;
    }

    @Override
    public PostResponse searchPosts(String keyword) {
        // search by keyword in title, content, tag
        List<Post> searedPosts = repo.findByTitleContaining(keyword);
        PostResponse response = new PostResponse();
        response.setPosts(searedPosts.stream().map(post -> mapper.map(post,PostDto.class)).toList());
        return response;
    }

    @Override
    public List<PostDto> getPostsByUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        List<Post> posts = repo.findByUser(user);
        return posts.stream().map(post -> mapper.map(post,PostDto.class)).toList();
    }

    @Override
    public List<PostDto> getPostsByCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        List<Post> posts = repo.findByCategory(category);
        return posts.stream().map(post -> mapper.map(post,PostDto.class)).toList();
    }

    @Override
    public void deletePost(int postId) {
        Post post = repo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        repo.delete(post);
    }
}
