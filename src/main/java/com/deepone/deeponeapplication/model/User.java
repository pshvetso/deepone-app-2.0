package com.deepone.deeponeapplication.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
// from H2 2.1.212 user is reserved so couldn't make the table
@Table(name = "tbl_user", indexes = {
        @Index(name = "IDX_USERNAME", columnList = "username")
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, length = 16)
    @NotNull
    private Integer hash;

    @Column(length = 16)
    @NotEmpty(message = "*Please provide valid username")
    private String username;

    @Column(length = 16)
    @NotEmpty(message = "*Please provide valid first name")
    private String firstName;

    @Column(length = 16)
    @NotEmpty(message = "*Please provide valid last name")
    private String lastName;

    @NotNull(message = "*Please provide valid avatar id")
    private Byte avatarId;

    @PastOrPresent
    private LocalDateTime regDate;

    @PastOrPresent
    private LocalDateTime lastVisitDate;
}
