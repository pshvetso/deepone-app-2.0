package com.deepone.deeponeapplication.service;

import com.deepone.deeponeapplication.dto.PostDataDto;
import com.deepone.deeponeapplication.model.PostView;
import com.deepone.deeponeapplication.model.User;
import com.deepone.deeponeapplication.repository.PostDataRepository;
import com.deepone.deeponeapplication.repository.PostViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostViewService {
    private final PostViewRepository postViewRepository;
    private final PostDataRepository postDataRepository;

    // TODO set current user
    private final User loggedInUser = User.builder().id(3L).build();

    public List<PostView> getFeed(Long postId) {
        List<PostView> result;
        if (postId == null) {
            result = postViewRepository.findTop10ByOrderByIdDesc();
        } else {
            result = postViewRepository.findFirst10ByIdLessThanOrderByIdDesc(postId);
        }
        return result;
    }

    public List<PostView> getUserWall(long userId, Long postId) {
        List<PostView> result;
        if (postId == null) {
            result = postViewRepository.findFirst10ByUserIdOrderByIdDesc(userId);
        } else {
            result = postViewRepository.findFirst10ByUserIdAndIdLessThanOrderByIdDesc(userId, postId);
        }
        return result;
    }

    public List<PostDataDto> getFeedData(Long postId) {
        return postDataRepository.getFeedViewedAndLikedPosts(loggedInUser.getId(), postId);
    }
}