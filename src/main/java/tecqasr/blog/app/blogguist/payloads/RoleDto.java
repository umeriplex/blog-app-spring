package tecqasr.blog.app.blogguist.payloads;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RoleDto {
    private int id;
    @NotEmpty(message = "Role name cannot be empty")
    private String name;
}
