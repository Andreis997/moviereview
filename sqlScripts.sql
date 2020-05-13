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

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `movie_id_api` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;
  
ALTER TABLE `reviews`
	ADD CONSTRAINT `review_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
	
INSERT INTO reviews (movie_id_api, user_id, content) VALUES (545609, 4, "Foarte bun") 

ALTER TABLE users ADD role varchar(255);