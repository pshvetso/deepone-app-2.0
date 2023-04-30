package com.deepone.deeponeapplication.repository;

import com.deepone.deeponeapplication.model.PostView;
import com.deepone.deeponeapplication.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // To load test:application.yaml
class PostViewRepositoryTest {
    @Autowired
    private PostViewRepository postViewRepository;

    private final User loggedInUser = User.builder().id(3L).build();

    @Test
    void findFirst10ByUserIdAndIdLessThanOrderByIdDesc_shouldFindPosts() {
        List<PostView> foundPosts = postViewRepository.findFirst10ByUserIdAndIdLessThanOrderByIdDesc(loggedInUser.getId(), 5L);

        assertThat(foundPosts)
                .hasSize(2)
                .extracting(PostView::getId)
                .contains(3L, 4L);

        assertThat(foundPosts)
                .extracting(PostView::getUsername)
                .contains("salmahayek", "salmahayek");
    }

    @Test
    void findFirst10ByUserIdOrderByIdDesc_shouldFindPosts() {
        List<PostView> foundPosts = postViewRepository.findFirst10ByUserIdOrderByIdDesc(loggedInUser.getId());

        assertThat(foundPosts)
                .hasSize(10)
                .extracting(PostView::getId)
                .contains(14L, 13L, 12L, 11L, 10L, 9L, 8L, 7L, 6L, 5L);

        assertThat(foundPosts)
                .extracting(PostView::getUsername)
                .contains("salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek");
    }

    @Test
    void findFirst10ByIdLessThanOrderByIdDesc_shouldFindPosts() {
        List<PostView> foundPosts = postViewRepository.findFirst10ByIdLessThanOrderByIdDesc(5L);

        assertThat(foundPosts)
                .hasSize(4)
                .extracting(PostView::getId)
                .contains(4L, 3L, 2L, 1L);

        assertThat(foundPosts)
                .extracting(PostView::getUsername)
                .contains("salmahayek", "salmahayek", "elizabethhurley", "britneyspears");
    }

    @Test
    void findTop10ByOrderByIdDesc_shouldFindPosts() {
        List<PostView> foundPosts = postViewRepository.findTop10ByOrderByIdDesc();

        assertThat(foundPosts)
                .hasSize(10)
                .extracting(PostView::getId)
                .contains(14L, 13L, 12L, 11L, 10L, 9L, 8L, 7L, 6L, 5L);

        assertThat(foundPosts)
                .extracting(PostView::getUsername)
                .contains("salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek");
    }
}
