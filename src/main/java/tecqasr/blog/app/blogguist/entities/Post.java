package tecqasr.blog.app.blogguist.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String content;
    private String image;
    private String tag;
    private Date createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    //@JoinColumn(name = "category_id") // to change the name of the column of the foreign key
    private Category category;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();
}
