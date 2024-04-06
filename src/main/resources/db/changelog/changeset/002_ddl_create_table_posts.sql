create table posts
(
    id        serial   primary key,
    title     varchar  not null,
    text      text     not null,
    file_path varchar,
    created   timestamp not null,
    user_id   int references users(id)
);