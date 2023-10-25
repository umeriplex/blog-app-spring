package tecqasr.blog.app.blogguist.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tecqasr.blog.app.blogguist.configs.AppConstants;
import tecqasr.blog.app.blogguist.payloads.ApiResponse;
import tecqasr.blog.app.blogguist.payloads.PostDto;
import tecqasr.blog.app.blogguist.payloads.PostResponse;
import tecqasr.blog.app.blogguist.services.FileService;
import tecqasr.blog.app.blogguist.services.PostService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostService service;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    // save post
    @PostMapping("/user/{userId}/category/{categoryId}/post/")
    public ResponseEntity<PostDto> savePost(
            @Valid
            @RequestBody PostDto postDto,
            @PathVariable(name = "userId") int userId,
            @PathVariable(name = "categoryId") int categoryId
    ){
        PostDto savedPost = service.savePost(postDto, userId, categoryId);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    // show by category
    @GetMapping("/category/{categoryId}/post/")
    public ResponseEntity<List<PostDto>> showPostsByCategory(@PathVariable(name = "categoryId") int categoryId){
        List<PostDto> posts = service.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // show by user
    @GetMapping("/user/{userId}/post/")
    public ResponseEntity<List<PostDto>> showPostsByUser(@PathVariable(name = "userId") int userId){
        List<PostDto> posts = service.getPostsByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // update post
    @PutMapping("/post/{postId}/")
    public ResponseEntity<PostDto> updatePost(
            @Valid
            @RequestBody PostDto postDto,
            @PathVariable(name = "postId") int postId
    ){
        PostDto updatedPost = service.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.CREATED);
    }

    // show post by id
    @GetMapping("/post/{postId}/")
    public ResponseEntity<PostDto> showPostById(@PathVariable(name = "postId") int postId){
        PostDto post = service.showPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    // show all posts
    @GetMapping("/post/")
    public ResponseEntity<PostResponse> showAllPosts(
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,value = "pageNo",required = false) int pageNo,
            @RequestParam(defaultValue = AppConstants.DEFAULT_PAGE_SIZE,value = "pageSize",required = false) int pageSize,
            @RequestParam(defaultValue = AppConstants.DEFAULT_SORT_BY,value = "sortBy",required = false) String sortBy
    ){
        PostResponse posts = service.showAllPosts(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // delete post
    @DeleteMapping("/post/{postId}/")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable(name = "postId") int postId){
        service.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted!",true),HttpStatus.OK);
    }

    // search posts
    @GetMapping("/post/search/{keywords}")
    public ResponseEntity<PostResponse> searchPosts(
            @PathVariable(name = "keywords") String keywords
    ){
        PostResponse posts = service.searchPosts(keywords);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // post image upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image,@PathVariable int postId) throws IOException {
        PostDto postDto = service.showPostById(postId);
        String fileName = fileService.updateImage(path, image);
        postDto.setImage(fileName);
        PostDto updatedPost = service.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost , HttpStatus.OK);
    }

    // get image
    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException{
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }


}
