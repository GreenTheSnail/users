# Users application
Project to create, read, update and delete users (test for MoroSystems).



## Tech Stack

**Operation system:** any 

**Backend:** Kotlin, Spring

**Security:** Spring Security

**Application server:** Tomcat(Spring framework)

**Database:** PostgreSql (Docker)

**Build:** Gradle

## Deployment

To deploy this project, run

```bash
  docker compose up -d
```
It will create a PostgreSql database container.
Then you can use the GET endpoint /users/test_data_init to create test users.

Only the PUT and DELETE endpoints require username and password authentication, so the user can only modify or delete himself. 
One of the next steps in application development is to create a user with the role ADMIN and give him rights to all endpoints and secure not secured endpoints.

## P.S.
Thank you in advance for your time and code review.

## Authors

- [@vladislav_soshko](https://github.com/GreenTheSnail)
