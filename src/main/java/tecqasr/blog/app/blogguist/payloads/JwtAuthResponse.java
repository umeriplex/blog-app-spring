package tecqasr.blog.app.blogguist.payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String accessToken;
}
