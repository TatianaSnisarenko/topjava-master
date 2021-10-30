DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES ((SELECT user_id FROM user_roles where role='USER'), '2020-01-30 10:00:00', 'Breakfast', 500),
       ((SELECT user_id FROM user_roles where role='USER'), '2020-01-30 13:00:00', 'Lunch', 1000),
       ((SELECT user_id FROM user_roles where role='USER'), '2020-01-30 20:00:00', 'Diner', 500),
       ((SELECT user_id FROM user_roles where role='USER'), '2020-01-31 00:00:00', 'Border meal', 100),
       ((SELECT user_id FROM user_roles where role='USER'), '2020-01-31 10:00:00', 'Breakfast', 1000),
       ((SELECT user_id FROM user_roles where role='USER'), '2020-01-31 13:00:00', 'Lunch', 500),
       ((SELECT user_id FROM user_roles where role='USER'), '2020-01-31 20:00:00', 'Diner', 410),
       ((SELECT user_id FROM user_roles where role='ADMIN'), '2020-01-31 14:00:00', 'Admin lunch', 510),
       ((SELECT user_id FROM user_roles where role='ADMIN'), '2020-01-31 21:00:00', 'Admin diner', 1500);

