package tecqasr.blog.app.blogguist.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Role {

    @Id
    private int id;
    private String name;
}
