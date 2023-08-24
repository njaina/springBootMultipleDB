create extension if not exists "uuid-ossp";

CREATE TABLE IF NOT EXISTS "cnaps_employee"
(
    id      varchar
    constraint cnaps_pk primary key default uuid_generate_v4(),
    cnaps_number  varchar not null check ( cnaps_number ~ '^[A-Za-z0-9]+$' ),
    end_to_end_id  varchar not null unique
    );