## RestApi with SpringBoot Java + MySQL

### Pre-requests:
1. Pull docker image for MySQL database from DockerHub

```
docker pull mysql
```

2. Start container

```
docker run -d --name mysql --user root -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql
```

### Create application 

- Spring Initializr
- IntellijIdea
- @GetMapping
- Class
- API Layer
- @PostMapping
- Connect MySQL
- @Configuration
- Extra Fields
- @DeleteMapping
- @PutMapping
- Handle Exceptions
- Refactor Response