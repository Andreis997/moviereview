Create database movie_review;

use movie_review;

create table users(
    id int NOT NULL AUTO_INCREMENT,
    email varchar(50),
    username varchar(50),
    password varchar(50),
    status varchar(50),
    PRIMARY KEY (id)
);

select * from users;

ALTER TABLE users
MODIFY COLUMN password varchar(256);
