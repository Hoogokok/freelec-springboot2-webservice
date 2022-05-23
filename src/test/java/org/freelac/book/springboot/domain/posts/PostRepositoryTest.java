package org.freelac.book.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @AfterEach
    public void cleanUp() {
        postRepository.deleteAll();
    }

    @Test
    public void findAll() {
        //given
        String title = "테스트 게시글";
        String content = "test 본문";

        postRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojo@gmail.com")
                .build());
        //when
        List<Posts> posts = postRepository.findAll();
        //then
        Posts post = posts.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getAuthor()).isEqualTo("jojo@gmail.com");
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("BaseTimeEntity등록")
    void createBaseTime() {
        //given
        LocalDateTime now = LocalDateTime.of(2022, 5, 14, 0, 0, 0, 0);
        postRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("haha")
                .build());
        //when
        List<Posts> postsList = postRepository.findAll();
        //then
        Posts posts = postsList.get(0);
        System.out.println(">>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}