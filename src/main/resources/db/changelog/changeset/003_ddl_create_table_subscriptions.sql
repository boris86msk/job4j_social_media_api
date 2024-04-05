create table subscriptions
(
    id      serial primary key,
    subscriber int not null references users(id),
    subscribed int not null references users(id),
    is_mutual  boolean default false,
    CHECK (subscriber != subscribed)
);