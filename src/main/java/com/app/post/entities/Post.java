package com.app.post.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name= "post")
public class Post {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)     // bir user silindiğinde postları silinsin
    private User user;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "text")
    private String text;

   @OneToMany(mappedBy = "post")
  private List<Likes> likes;

    @Temporal(TemporalType.TIMESTAMP)
    Date createDate;
}
