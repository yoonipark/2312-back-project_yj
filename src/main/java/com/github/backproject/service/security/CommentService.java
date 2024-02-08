package com.github.backproject.service.security;


import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import com.github.backproject.respository.Post.Post;
import com.github.backproject.respository.Post.PostRepository;
import com.github.backproject.respository.Comment.Comment;
import com.github.backproject.respository.Comment.CommentRepository;
import com.github.backproject.web.dto.auth.CommentDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;



@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository; //

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    //댓글 조회 요청
    public List<CommentDto> comments(Long postId){
        //1. 댓글 조회
        List<Comment> comments = commentRepository.findByPostId(postId);

        //2. 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i < comments.size(); i++) { //조회 댓글 엔티티 수만큼 반복
                Comment c = comments.get(i); //조회한 댓글 하나씩 가져오기
                CommentDto dto= CommentDto.createCommentDto(c); //엔티티 dto로 변환
                dtos.add(dto); //dtos 리스트에 삽입
        }
        //3. 결과 반환
//        return dtos;
//            작동 잘 안되는 부분
            return commentRepository.findAllByPostId(postId)
                    .stream()
                    .map(comment -> CommentDto.createCommentDto(comment))
                    .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long postId, CommentDto dto){
            //  1.게시글 조회 및 예외 발생
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패!" +
                        "대상 게시글이 없습니다."));
            //  2. 댓글 엔티티 생성
        Comment comment = Comment.creatComment(dto, post);
            //  3. 댓글 엔티티 db 저장
        Comment created = commentRepository.save(comment);
            //  4.DTO 로 변환해서 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //1.댓글 조회 및 예외발생
        Comment target = commentRepository.findById(id)//수정할 댓글
                .orElseThrow(()-> new IllegalArgumentException("댓글 수정 실패!"+
                        "대상 댓글이 없습니다."));
        //2. 댓글 수정
        target.patch(dto);
        //3. DB로 갱신
        Comment updated = commentRepository.save(target);
        //4. 댓글 엔티티를 DTO 로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 1. 댓글 조회 및 예외발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("댓글 삭제 실패!"+
                        "대상이 없습니다"));
        // 2. 댓글 삭제
        commentRepository.delete(target);
        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }
}
