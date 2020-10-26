package com.example.springbootjpa;

import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

// 임의로 작성한 Repository
//@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommentRepository extends MyRepository<Comment, Long> {

//    Comment save(Comment comment);
//    List<Comment> findAll();
}
