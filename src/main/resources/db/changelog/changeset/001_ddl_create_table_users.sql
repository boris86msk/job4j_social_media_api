create table users
(
    id       serial   primary key,
    login    varchar  not null  unique,
    email    varchar not null unique,
    password varchar  not null
);