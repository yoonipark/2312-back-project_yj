package com.github.backproject.respository.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.*;

@NoRepositoryBean
public interface CommentRepositoryCustom<S> {
    List<S> findByPostId(Long postId);
}

