# Deepone application 2.0

Social network Spring Boot backend application.

## Technologies

- Java 11
- Spring Boot 2.7.6
- Hibernate
- MySQL

## Architecture

Web Server - Application - ORM - Database

**Modules**

*Post module:*

Post data.

- model/Post.java

*User module:*

User data.

- model/User.java

*View module:*

Information about post views.

- model/View.java

*Like module:*

Information about liked posts.

- model/Like.java

*PostView module:*

View with post and user data.

- PostView.java
- PostViewRepository.java
- PostViewService.java
- PostViewController.java

*PostData module:*

Information about viewed and liked posts.

- PostDataDto.java
- PostDataRepository.java

## Data Schema

![Data schema](doc/data_schema.PNG)

## API

**Post module**

Get current user feed:

- GET /api/feed(postId)

postId - feed 1st post id (int).

Get current user feed data:

- GET /api/feedData(postId)

postId - feed 1st post id (int).

## Screenshots