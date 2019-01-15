create table users (
  id   serial8 primary key,
  name text    not null unique
);
