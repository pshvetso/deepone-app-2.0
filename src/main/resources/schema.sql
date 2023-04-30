CREATE TABLE IF NOT EXISTS `tbl_user`
(
    `user_id`         bigint(20) NOT NULL AUTO_INCREMENT,
    `hash`            int(11)     DEFAULT NULL,
    `username`        varchar(16) DEFAULT NULL,
    `first_name`      varchar(16) DEFAULT NULL,
    `last_name`       varchar(16) DEFAULT NULL,
    `avatar_id`       tinyint(4)  DEFAULT NULL,
    `reg_date`        datetime,
    `last_visit_date` datetime    DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `UK_8xjvj8wb0q08uh6l7h7hj29a5` (`hash`),
    KEY `IDX_USERNAME` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `post`
(
    `post_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `date`    datetime     DEFAULT NULL,
    `title`   varchar(255) DEFAULT NULL,
    `user_id` bigint(20) NOT NULL,
    PRIMARY KEY (`post_id`),
    KEY `FK5or1f9qa18oxf39kdilu7np7g` (`user_id`),
    CONSTRAINT `FK5or1f9qa18oxf39kdilu7np7g` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `tbl_like`
(
    `like_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `post_id` bigint(20) NOT NULL,
    `user_id` bigint(20) NOT NULL,
    PRIMARY KEY (`like_id`),
    UNIQUE KEY `UKthcdc49pxxjwdvnpgi2e90101` (`user_id`, `post_id`),
    KEY `FKss0frg3i9teuwtrhwm15vy1os` (`post_id`),
    CONSTRAINT `FK6p9i7gj6frx1tpqses1bdufvm` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`),
    CONSTRAINT `FKss0frg3i9teuwtrhwm15vy1os` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `view`
(
    `view_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `post_id` bigint(20) NOT NULL,
    `user_id` bigint(20) NOT NULL,
    PRIMARY KEY (`view_id`),
    UNIQUE KEY `UKsfufpvry4867vf8oo2u4nwxh0` (`user_id`, `post_id`),
    KEY `FKdjs52rs6rvnt6cyl8de7hgero` (`post_id`),
    CONSTRAINT `FK6h2o1bipyh3dt6wgbsaeevja8` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`),
    CONSTRAINT `FKdjs52rs6rvnt6cyl8de7hgero` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4;

CREATE VIEW IF NOT EXISTS `post_view` AS
SELECT p.`post_id`,
       u.`user_id`,
       p.`date`,
       p.`title`,
       u.`username`,
       u.`first_name`,
       u.`last_name`,
       u.`avatar_id`,
       u.`last_visit_date`,
       COUNT(v.`view_id`) AS `views`,
       COUNT(l.`like_id`) AS `likes`
FROM `post` p
         JOIN `tbl_user` u
              ON p.`user_id` = u.`user_id`
         LEFT JOIN `tbl_like` l
                   ON p.`post_id` = l.`post_id`
         LEFT JOIN `view` v
                   ON p.`post_id` = v.`post_id`
GROUP BY p.`post_id`;
