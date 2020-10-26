package com.example.springbootjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional
public class JpaRunner2 implements ApplicationRunner {

//    @PersistenceContext
//    EntityManager entityManager;
    @Autowired
    PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        TypedQuery<Post> query = entityManager.createQuery("select p from Post as p", Post.class);
//        List<Post> posts = query.getResultList();
        postRepository.findById(4l);
    }
}
