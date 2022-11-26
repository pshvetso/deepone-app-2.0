package com.deepone.deeponeapplication.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_like", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "post_id"})
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
