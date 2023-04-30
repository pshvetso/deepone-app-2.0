package com.deepone.deeponeapplication.repository;

import com.deepone.deeponeapplication.dto.PostDataDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@AllArgsConstructor
public class PostDataRepository {
    private final int pageSize = 10;

    private final String PostDataDtoQueryTpl =
            "SELECT new com.deepone.deeponeapplication.dto.PostDataDto(p.id, l.id AS liked) " +
                    "FROM Post p " +
                    "JOIN p.user " +
                    "LEFT JOIN p.likes l " +
                    "ON l.post = p AND l.user.id = :currentUserId";

    private final EntityManager em;

    private String getQueryString(String template, String where, List<String> order) {
        StringBuilder query = new StringBuilder(template);

        if (where != null) {
            query.append(" WHERE ").append(where);
        }

        if (order.size() > 0) {
            query.append(" ORDER BY ").append(String.join(", ", order));
        }

        return query.toString();
    }

    public List<PostDataDto> getFeedViewedAndLikedPosts(long currentUserId, Long postId) {
        String queryStr = getQueryString(
                PostDataDtoQueryTpl,
                (postId == null) ? null : "p.id < :postId",
                List.of("p.id DESC")
        );

        TypedQuery<PostDataDto> query = em.createQuery(queryStr, PostDataDto.class);

        if (postId != null) {
            query.setParameter("postId", postId);
        }

        return query
                .setParameter("currentUserId", currentUserId)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public List<PostDataDto> getUserWallViewedAndLikedPosts(long userId, long currentUserId, Long postId) {
        String queryStr = getQueryString(
                PostDataDtoQueryTpl,
                (postId == null) ? "p.id = :userId" : "p.id = :userId AND p.id < :postId",
                List.of("p.id DESC")
        );

        TypedQuery<PostDataDto> query = em.createQuery(queryStr, PostDataDto.class);

        if (postId != null) {
            query.setParameter("postId", postId);
        }

        return query
                .setParameter("currentUserId", currentUserId)
                .setParameter("userId", userId)
                .setMaxResults(pageSize)
                .getResultList();
    }
}
