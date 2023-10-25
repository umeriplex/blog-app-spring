package tecqasr.blog.app.blogguist.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private int id;
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    private String content;
    private String image;
    private String tag;
    private Date createdAt;
    private UserDto user;
    private CategoryDto category;
    private List<CommentDto> comments = new ArrayList<>();
}
