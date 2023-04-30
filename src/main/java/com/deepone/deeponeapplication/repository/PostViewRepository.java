package com.deepone.deeponeapplication.repository;

import com.deepone.deeponeapplication.model.PostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostViewRepository extends JpaRepository<PostView, Long> {
    List<PostView> findFirst10ByUserIdAndIdLessThanOrderByIdDesc(long userId, long id);

    List<PostView> findFirst10ByUserIdOrderByIdDesc(long userId);

    List<PostView> findFirst10ByIdLessThanOrderByIdDesc(long id);

    List<PostView> findTop10ByOrderByIdDesc();

}
