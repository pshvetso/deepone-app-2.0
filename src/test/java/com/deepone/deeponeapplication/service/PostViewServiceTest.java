package com.deepone.deeponeapplication.service;

import com.deepone.deeponeapplication.dto.PostDataDto;
import com.deepone.deeponeapplication.helper.DataHelper;
import com.deepone.deeponeapplication.model.PostView;
import com.deepone.deeponeapplication.repository.PostDataRepository;
import com.deepone.deeponeapplication.repository.PostViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostViewServiceTest {
    @MockBean
    private PostViewRepository postViewRepository;

    @MockBean
    private PostDataRepository postDataRepository;

    @Autowired
    private PostViewService postViewService;

    @Test
    void getFeedShouldReturnLatestPosts() {
        long postId = 5L;
        List<PostView> expectedPosts = DataHelper.resourceToObject("response_postview_feed.json", new TypeReference<>() {
        });
        when(postViewRepository.findFirst10ByIdLessThanOrderByIdDesc(anyLong())).thenReturn(expectedPosts);

        List<PostView> posts = postViewService.getFeed(postId);
        // could use assertThat().extracting()
        assertThat(posts).isEqualTo(expectedPosts);
    }

    @Test
    void getFeedDataShouldReturnLatestPostData() {
        long postId = 5L;
        List<PostDataDto> expectedPostsData = DataHelper.resourceToObject("response_postdata_feed.json", new TypeReference<>() {
        });
        when(postDataRepository.getFeedViewedAndLikedPosts(anyLong(), anyLong())).thenReturn(expectedPostsData);

        List<PostDataDto> postsData = postViewService.getFeedData(postId);
        assertThat(postsData).isEqualTo(expectedPostsData);
    }
}
