create table account (
    id integer generated by default as identity,
    document_number varchar(30) not null,

    primary key (id)
);