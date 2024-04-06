create table subscriptions
(
    id            serial primary key,
    user_id       int not null,
    subscribe_id  int not null,
    is_mutual     boolean default false,
    CHECK (user_id != subscribe_id),
    UNIQUE (user_id, subscribe_id)
);