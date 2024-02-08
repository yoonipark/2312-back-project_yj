package com.github.backproject.web.controller.auth;


import com.github.backproject.service.security.CommentService;
import com.github.backproject.web.dto.auth.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    private CommentDto dto;

    //1. 댓글 조회
    @GetMapping("/api/comments/{postId}") //댓글 조회 요청
    public ResponseEntity<List <CommentDto>> comments (@PathVariable Long postId){
        //서비스에 위임
       List<CommentDto> dtos= commentService.comments(postId);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    //2. 댓글 생성
    @PostMapping("/api/post/{postId}/comments")// 댓글 조회요청
    public ResponseEntity<CommentDto> create(@PathVariable Long postId,
                                             @RequestBody CommentDto dto){
        //서비스에 위임
        CommentDto createdDto = commentService.create(postId, dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    //3. 댓글 수정
    @PatchMapping("*/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto){
        //서비스에 위임
        CommentDto updatedDto = commentService.update(id, dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    //4. 댓글 삭제
    @DeleteMapping("/api/comments/{id}") //댓글삭제 요청
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        //서비스에 위임
        CommentDto deletedDto = commentService.delete(id);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);

    }

}
