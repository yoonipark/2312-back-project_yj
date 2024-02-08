package com.github.backproject.web.dto.auth;

import lombok.*;
import com.github.backproject.respository.Comment.Comment;

// 다른 import 선언들...

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id; //댓글 id
    private Long postId; // 댓글의 부모 id
    private String nickname; //댓글 작성자
    @Setter
    private String body; //댓글 내용

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(), //댓글 엔티티 id
                comment.getPost().getId(), //댓글 엔티티 속한 부모 게시글 id
                comment.getNickname(),
                comment.getBody()
        );
    }


    public java.lang.String getContent() {
        return a;
    }

    public void setContent(a a) {
        this.a = a;
    }
}
