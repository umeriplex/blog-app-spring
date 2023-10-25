package tecqasr.blog.app.blogguist.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private int id;

    @NotEmpty(message = "Comment cannot be empty")
    private String content;
}
