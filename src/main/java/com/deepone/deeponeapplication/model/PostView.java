package com.deepone.deeponeapplication.model;


import lombok.Getter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Immutable
@Getter
public class PostView {
    @Id
    @Column(name = "post_id")
    private Long id;

    private Long userId;

    private LocalDateTime date;

    private String title;

    private String username;

    private String firstName;

    private String lastName;

    private Byte avatarId;

    private LocalDateTime lastVisitDate;

    private long views;

    private long likes;
}
