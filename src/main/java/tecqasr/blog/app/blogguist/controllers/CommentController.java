package tecqasr.blog.app.blogguist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tecqasr.blog.app.blogguist.payloads.ApiResponse;
import tecqasr.blog.app.blogguist.payloads.CommentDto;
import tecqasr.blog.app.blogguist.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService service;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> saveComment (@RequestBody CommentDto commentDto, @PathVariable int postId){
        CommentDto save = service.save(commentDto, postId);
        return new  ResponseEntity<CommentDto>(save, HttpStatus.CREATED);
    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment (@PathVariable int commentId){
        service.delete(commentId);
        return new  ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted!",true), HttpStatus.CREATED);
    }
}