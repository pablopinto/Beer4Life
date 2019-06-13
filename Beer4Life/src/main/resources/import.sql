INSERT INTO users (name,username,password,enabled,create_at,last_name,email) VALUES  ("admin","admin" , "$2a$10$GqrfGszeMcy12Y7dJ0noGOZmWtIHFgWcfBh4thsGs2jyVrV5PqOVW",1,"2019/06/08","admin","admin@admin.com")
insert into authorities (authority , user_id) values ("ROLE_ADMIN" , 1)
insert into authorities (authority , user_id) values ("ROLE_USER" , 1)
