package com.app.post.responses;




import com.app.post.entities.Likes;
import com.app.post.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class PostResponse {
    Long id;
    Long userId;
    String userName;
    String text;
    String title;
    List<Likes> postLikes;




    public PostResponse(Post entity, List<Likes> likes) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.postLikes=likes;

    }
}


