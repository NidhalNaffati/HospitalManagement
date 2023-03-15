create table doctor
(
    speciality varchar(255),
    id         bigint not null,
    primary key (id)
);

create table medical_record
(
    id               bigint    not null auto_increment,
    blood_pressure   float(23) not null,
    blood_type       varchar(255),
    heart_rate       float(23) not null,
    height           float(23) not null,
    respiratory_rate float(23) not null,
    sugar_level      float(23) not null,
    surgeries        smallint  not null,
    weight           float(23) not null,
    patient_id       bigint,
    primary key (id)
);

create table patient
(
    family_email varchar(255),
    id           bigint not null,
    primary key (id)
);

create table person (
       person_type varchar(31) not null,
        id bigint not null,
        address varchar(255),
        created_at datetime(6) not null,
        day_of_birth datetime(6),
        email varchar(255),
        first_name varchar(255),
        gender varchar(255),
        last_name varchar(255),
        last_update_at datetime(6),
        primary key (id)
    );

create table person_sequence
(
    next_val bigint
);

  create table medical_record_sequence
(
    next_val bigint
);