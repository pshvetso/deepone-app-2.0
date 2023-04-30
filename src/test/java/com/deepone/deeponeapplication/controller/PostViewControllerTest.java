package com.deepone.deeponeapplication.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/* Integration tests should be as similar as possible to the production environment. Considering this, using
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) would be a better choice. This approach is closer to test
the real application. You can see whether the whole system is going to work as expected.
When you use @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) you test with a real http server. */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PostViewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenReadingFeed_thenReturnFeedForUser() throws Exception {
        mockMvc.perform(get("/api/v1/feed")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[*].id", contains(14, 13, 12, 11, 10, 9, 8, 7, 6, 5)))
                .andExpect(jsonPath("$[*].username", contains("salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek", "salmahayek")));
    }

    @Test
    void whenReadingFeedData_thenReturnFeedDataForUser() throws Exception {
        mockMvc.perform(get("/api/v1/feedData")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[*].id", contains(14, 13, 12, 11, 10, 9, 8, 7, 6, 5)))
                .andExpect(jsonPath("$[*].liked", contains(null, null, null, null, null, null, null, null, null, null)));
    }

}
