package com.example.springbootjpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    @Rollback(value = false)
    public void crudRepsitory () {
        // Given
        Post post = new Post();
        post.setTitle("hello spring boot");
        assertThat(post.getId()).isNull();

        // When
        Post newPost = postRepository.save(post);
        // Then
        assertThat(newPost.getId()).isNotNull();

        // When
        List<Post> posts = postRepository.findAll(); // jpa기능
        // Then
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts).contains(newPost);

        // When
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));
        // Then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0); // 현재 페이지 넘버(0부터시작)
        assertThat(page.getSize()).isEqualTo(10); // 요청한 사이즈
        assertThat(page.getNumberOfElements()).isEqualTo(1); // 현재 페이지에 들어올 수 있는 갯수

        // When
        postRepository.findByTitleContains("Spring", PageRequest.of(0, 10)); // 페이징 처리
        // Then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0); // 현재 페이지 넘버(0부터시작)
        assertThat(page.getSize()).isEqualTo(10); // 요청한 사이즈
        assertThat(page.getNumberOfElements()).isEqualTo(1); // 현재 페이지에 들어올 수 있는 갯수

        // When
        long spring = postRepository.countByTitleContains("spring");
        // Then
        assertThat(spring).isEqualTo(1);

    }
}