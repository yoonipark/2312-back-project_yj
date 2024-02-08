package com.github.backproject.respository.Comment;

import com.github.backproject.respository.Post.Post;
import com.github.backproject.web.dto.auth.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//   Todo : post 부분 확인 및 추가
    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name="post_id")
    private Post post;
    @Column
    private String nickname;//댓글 단 사람
    @Column
    private String body; //댓글 본문
    public static Comment createComment(CommentDto dto, Post post) {
        //예외 발생
        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다");
        if(dto.getPostId() != post.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");

        //엔티티 생성 및 반환
        return new Comment(
                dto.getId(),// 댓글아이디
                post, // 부모 게시글
                dto.getNickname(), // 댓글 작성자 닉네임
                dto.getBody() // 댓글 본문
        );
    }

    public void patch(CommentDto dto){
        //예외발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입려됐습니다.");

        //객체 갱신
        if(dto.getNickname() !=null) //수정할 닉네임 데이터 있다면
            this.nickname = dto.getNickname();//내용 반영
        if(dto.getBody() != null) //수정할 데이터 있다면
            this.body=dto.getBody();//내용반영
    }

}