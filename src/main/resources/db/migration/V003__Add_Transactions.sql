create table transaction (
    id int generated by default as identity,
    account_id int not null,
    operation_id int not null,
    amount decimal not null,
    event_date timestamp,

    primary key (id),
    foreign key (account_id) references account (id),
    foreign key (operation_id) references operation (id)
);