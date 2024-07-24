CREATE TABLE team (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(255) NOT NULL,
      department VARCHAR(255) NOT NULL
);

CREATE TABLE person (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        cpf VARCHAR(20) NOT NULL,
        birth_date DATE NOT NULL,
        gender VARCHAR(10) NOT NULL,
        phone VARCHAR(20) NOT NULL,
        email VARCHAR(255) NOT NULL,
        team_id BIGINT,
        FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE education (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       course_name VARCHAR(255) NOT NULL,
       course_level VARCHAR(50) NOT NULL,
       completion_date DATE NOT NULL,
       institution VARCHAR(255) NOT NULL,
       person_id BIGINT,
       FOREIGN KEY (person_id) REFERENCES person(id)
);
