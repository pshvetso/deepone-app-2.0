package com.deepone.deeponeapplication.controller;

import com.deepone.deeponeapplication.dto.PostDataDto;
import com.deepone.deeponeapplication.model.PostView;
import com.deepone.deeponeapplication.service.PostViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostViewController {
    private final PostViewService postService;

    //curl -v http://localhost:8080/api/feed?postId=
    @GetMapping("/feed")
    public List<PostView> getFeed(@RequestParam(required = false) Long postId) {
        return postService.getFeed(postId);
    }

    @GetMapping("/feedData")
    public List<PostDataDto> getFeedData(@RequestParam(required = false) Long postId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return postService.getFeedData(postId);
    }
}
