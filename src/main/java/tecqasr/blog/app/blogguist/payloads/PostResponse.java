package tecqasr.blog.app.blogguist.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tecqasr.blog.app.blogguist.configs.AppConstants;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class PostResponse {
    private List<PostDto> posts;
    private int currentPage;
    private int totalPages;
    private long totalPosts;
    private int pageSize;
    private boolean last;
    private String mediaUrl = AppConstants.MEDIA_URL;

}
