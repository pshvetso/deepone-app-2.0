package com.deepone.deeponeapplication.repository;

import com.deepone.deeponeapplication.dto.PostDataDto;
import com.deepone.deeponeapplication.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostDataRepositoryTest {
    @Autowired
    private PostDataRepository postDataRepository;

    private final User loggedInUser = User.builder().id(3L).build();

    @Test
    void getFeedViewedAndLikedPosts_shouldFindPosts() {
        List<PostDataDto> foundPosts = postDataRepository.getFeedViewedAndLikedPosts(loggedInUser.getId(), 5L);

        assertThat(foundPosts)
                .hasSize(4)
                .extracting(PostDataDto::getId)
                .contains(1L, 2L, 3L, 4L);
    }

    @Test
    void getUserWallViewedAndLikedPosts_shouldFindPosts() {
        List<PostDataDto> foundPosts = postDataRepository.getUserWallViewedAndLikedPosts(2L, loggedInUser.getId(), 5L);

        assertThat(foundPosts)
                .hasSize(1)
                .extracting(PostDataDto::getId)
                .contains(2L);
    }
}
