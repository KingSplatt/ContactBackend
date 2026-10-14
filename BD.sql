CREATE TABLE type_phones(
    id serial primary key,
    tipo varchar(30) not null
);

CREATE TABLE phones(
    id serial primary key,
    phone_number varchar(100) not null,
    id_type INT not null
);

alter table phones add constraint fk_type foreign key (id_type) references type_phones(id);

CREATE TABLE contacts(
    id serial primary key,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    email varchar(100) not null UNIQUE,
    created_at timestamp not null default current_timestamp,
    status INT not null default 1
);

CREATE TABLE contact_phones(
    contact_id bigint not null,
    phone_id bigint not null,
    primary key (contact_id, phone_id)
);

alter table contact_phones add constraint fk_contact foreign key (contact_id) references contacts(id) on delete cascade;
alter table contact_phones add constraint fk_phone foreign key (phone_id) references phones(id) on delete cascade;

INSERT INTO type_phones (tipo) VALUES('casa'),('celular'),('trabajo');

INSERT INTO phones (phone_number, id_type) VALUES('4411223311', 2),('6655443322', 1);

INSERT INTO contacts(nombre, apellido, email) VALUES('Miguel', 'Lopez', 'malp@gmail.com');

INSERT INTO contact_phones(contact_id, phone_id) VALUES(1, 1),(1, 2);