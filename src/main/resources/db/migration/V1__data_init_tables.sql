create table if not exists person
(
    person_type  varchar(50) not null,
    id           bigint      not null,
    address      varchar(50),
    day_of_birth datetime(6),
    email        varchar(50),
    first_name   varchar(50),
    gender       varchar(50),
    last_name    varchar(50),
    primary key (id)
);


create table if not exists doctor
(
    speciality varchar(50),
    id         bigint not null,
    primary key (id)
);


create table if not exists patient
(
    family_email        varchar(50),
    family_phone_number integer not null,
    id                  bigint  not null,
    primary key (id)
);

create table if not exists medical_record
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



create table if not exists person_seq
(
    next_val bigint
);


alter table doctor
    add constraint FKop6cku1inyqn8son2ki4cgqdh
        foreign key (id)
            references person (id);


alter table medical_record
    add constraint FKt0lf3feuiurr73bpln2n6x0v
        foreign key (patient_id)
            references patient (id);


alter table patient
    add constraint FKaxjru7sr936i3y7dy396vnon8
        foreign key (id)
            references person (id);


-- doctor insertion.
INSERT INTO HospitalManagementDB.person (id, person_type,
                                         first_name, last_name, email,
                                         address, gender, day_of_birth)
VALUES (1, 'doctor', 'John', 'Doe', 'johndoe@example.com', '123 Main St', 'MALE', '2023-03-24'),
       (2, 'doctor', 'Jane', 'Doe', 'janedoe@example.com', '456 Oak St', 'FEMALE', '2023-03-24'),
       (3, 'doctor', 'Bob', 'Smith', 'bobsmith@example.com', '789 Elm St', 'MALE', '2023-03-24'),
       (4, 'doctor', 'Alice', 'Johnson', 'alicejohnson@example.com', '1011 Pine St', 'FEMALE', '2023-03-24'),
       (5, 'doctor', 'David', 'Brown', 'davidbrown@example.com', '1213 Maple St', 'MALE', '2023-03-24'),
       (6, 'doctor', 'Emily', 'Davis', 'emilydavis@example.com', '1415 Oak St', 'FEMALE', '2023-03-24'),
       (7, 'doctor', 'Michael', 'Garcia', 'michaelgarcia@example.com', '1617 Elm St', 'MALE', '2023-03-24'),
       (8, 'doctor', 'Emma', 'Martinez', 'emmamartinez@example.com', '1819 Pine St', 'FEMALE', '2023-03-24'),
       (9, 'doctor', 'William', 'Hernandez', 'williamhernandez@example.com', '2021 Maple St', 'MALE', '2023-03-24'),
       (10, 'doctor', 'Olivia', 'Lee', 'oliviale@example.com', '2223 Oak St', 'FEMALE', '2023-03-24'),
       (11, 'doctor', 'James', 'Walker', 'jameswalker@example.com', '2425 Elm St', 'MALE', '2023-03-24'),
       (12, 'doctor', 'Sophia', 'Hall', 'sophiahall@example.com', '2627 Pine St', 'FEMALE', '2023-03-24'),
       (13, 'doctor', 'Benjamin', 'Baker', 'benjaminbaker@example.com', '2829 Maple St', 'MALE', '2023-03-24'),
       (14, 'doctor', 'Isabella', 'Gonzalez', 'isabellagonzalez@example.com', '3031 Oak St', 'MALE', '2023-03-24'),
       (15, 'doctor', 'Ethan', 'Nelson', 'ethannelson@example.com', '3233 Elm St', 'MALE', '2023-03-24'),
       (16, 'doctor', 'Mia', 'Carter', 'miacarter@example.com', '3435 Pine St', 'FEMALE', '2023-03-24'),
       (17, 'doctor', 'Alexander', 'Mitchell', 'alexandermitchell@example.com', '3637 Maple St', 'MALE',
        '2023-03-24'),
       (18, 'doctor', 'Abigail', 'Perez', 'abigailperez@example.com', '3839 Oak St', 'FEMALE', '2023-03-24'),
       (19, 'doctor', 'Daniel', 'Roberts', 'danielroberts@example.com', '4041 Elm St', 'MALE', '2023-03-24'),
       (20, 'doctor', 'Madison', 'Turner', 'madisonturner@example.com', '4243 Pine St', 'FEMALE', '2023-03-24');

INSERT INTO HospitalManagementDB.doctor (id, speciality)
VALUES (1, 'PSYCHIATRY'),
       (2, 'CARDIOLOGY'),
       (3, 'DERMATOLOGY'),
       (4, 'ENDOCRINOLOGY'),
       (5, 'HEMATOLOGY'),
       (6, 'NEPHROLOGY'),
       (7, 'NEUROLOGY'),
       (8, 'ONCOLOGY'),
       (9, 'UROLOGY'),
       (10, 'PSYCHIATRY'),
       (11, 'UROLOGY'),
       (12, 'DERMATOLOGY'),
       (13, 'ENDOCRINOLOGY'),
       (14, 'HEMATOLOGY'),
       (15, 'NEPHROLOGY'),
       (16, 'NEUROLOGY'),
       (17, 'ONCOLOGY'),
       (18, 'UROLOGY'),
       (19, 'PSYCHIATRY'),
       (20, 'CARDIOLOGY');

INSERT INTO HospitalManagementDB.person (id, person_type,
                                         first_name, last_name, email,
                                         address, gender, day_of_birth)
VALUES (21, 'patient', 'Emily', 'Johnson', 'emilyjohnson@example.com', '123 Main St', 'FEMALE', '2023-03-24'),
       (22, 'patient', 'David', 'Smith', 'davidsmith@example.com', '456 Oak St', 'MALE', '2023-03-24'),
       (23, 'patient', 'Emma', 'Brown', 'emmabrown@example.com', '789 Elm St', 'FEMALE', '2023-03-24'),
       (24, 'patient', 'William', 'Davis', 'williamdavis@example.com', '1011 Pine St', 'MALE', '2023-03-24'),
       (25, 'patient', 'Olivia', 'Garcia', 'oliviagarcia@example.com', '1213 Maple St', 'FEMALE', '2023-03-24'),
       (26, 'patient', 'James', 'Martinez', 'jamesmartinez@example.com', '1415 Oak St', 'MALE', '2023-03-24'),
       (27, 'patient', 'Sophia', 'Hernandez', 'sophiahernandez@example.com', '1617 Elm St', 'FEMALE', '2023-03-24'),
       (28, 'patient', 'Benjamin', 'Lee', 'benjaminlee@example.com', '1819 Pine St', 'MALE', '2023-03-24'),
       (29, 'patient', 'Isabella', 'Walker', 'isabellawalker@example.com', '2021 Maple St', 'FEMALE', '2023-03-24'),
       (30, 'patient', 'Ethan', 'Hall', 'ethanhall@example.com', '2223 Oak St', 'MALE', '2023-03-24'),
       (31, 'patient', 'Mia', 'Baker', 'miabaker@example.com', '2425 Elm St', 'FEMALE', '2023-03-24'),
       (32, 'patient', 'Alexander', 'Gonzalez', 'alexandergonzalez@example.com', '2627 Pine St', 'MALE',
        '2023-03-24'),
       (33, 'patient', 'Abigail', 'Nelson', 'abigailnelson@example.com', '2829 Maple St', 'FEMALE', '2023-03-24'),
       (34, 'patient', 'Daniel', 'Carter', 'danielcarter@example.com', '3031 Oak St', 'MALE', '2023-03-24'),
       (35, 'patient', 'Madison', 'Mitchell', 'madisonmitchell@example.com', '3233 Elm St', 'FEMALE', '2023-03-24'),
       (36, 'patient', 'Monica', 'Geller', 'monicageller@example.com', '456 Oak St', 'FEMALE', '2023-03-24'),
       (37, 'patient', 'Ross', 'Geller', 'rossgeller@example.com', '789 Elm St', 'MALE', '2023-03-24'),
       (38, 'patient', 'Chandler', 'Bing', 'chandlerbing@example.com', '1011 Pine St', 'MALE', '2023-03-24'),
       (39, 'patient', 'Joey', 'Tribbiani', 'joeytribbiani@example.com', '1213 Maple St', 'MALE', '2023-03-24'),
       (40, 'patient', 'Phoebe', 'Buffay', 'phoebebuffay@example.com', '1415 Oak St', 'FEMALE', '2023-03-24'),
       (41, 'patient', 'Gunther', 'CentralPerk', 'gunthercentralperk@example.com', '1617 Elm St', 'MALE',
        '2023-03-24'),
       (42, 'patient', 'Janice', 'Hosenstein', 'janicehosenstein@example.com', '1819 Pine St', 'FEMALE',
        '2023-03-24'),
       (43, 'patient', 'Estelle', 'Leonard', 'estelleleonard@example.com', '2021 Maple St', 'FEMALE', '2023-03-24'),
       (44, 'patient', 'Frank', 'BuffaySr', 'frankbuffaysr@example.com', '2223 Oak St', 'MALE', '2023-03-24'),
       (45, 'patient', 'Judy', 'Geller', 'judygeller@example.com', '2425 Elm St', 'FEMALE', '2023-03-24'),
       (46, 'patient', 'Jack', 'Geller', 'jackgeller@example.com', '2627 Pine St', 'MALE', '2023-03-24'),
       (47, 'patient', 'Ursula', 'Buffay', 'ursulabuffay@example.com', '2829 Maple St', 'FEMALE', '2023-03-24'),
       (48, 'patient', 'Leonard', 'Green', 'leonardgreen@example.com', '3031 Oak St', 'MALE', '2023-03-24'),
       (49, 'patient', 'Christina', 'Applegate', 'christinaapplegate@example.com', '3233 Elm St', 'FEMALE',
        '2023-03-24'),
       (50, 'patient', 'Rachel', 'Green', 'rachelgreen@example.com', '123 Main St', 'FEMALE', '2023-03-24'),
       (51, 'patient', 'John', 'Doe', 'johndoe@example.com', '123 Main St', 'MALE', '2023-03-24'),
       (52, 'patient', 'Jane', 'Doe', 'janedoe@example.com', '456 Oak St', 'FEMALE', '2023-03-24'),
       (53, 'patient', 'Bob', 'Smith', 'bobsmith@example.com', '789 Elm St', 'MALE', '2023-03-24'),
       (54, 'patient', 'Alice', 'Jones', 'alicejones@example.com', '1011 Pine St', 'FEMALE', '2023-03-24'),
       (55, 'patient', 'Tom', 'Wilson', 'tomwilson@example.com', '1213 Maple St', 'MALE', '2023-03-24'),
       (56, 'patient', 'Sara', 'Brown', 'sarabrown@example.com', '1415 Oak St', 'FEMALE', '2023-03-24'),
       (57, 'patient', 'Mike', 'Taylor', 'miketaylor@example.com', '1617 Elm St', 'MALE', '2023-03-24'),
       (58, 'patient', 'Lisa', 'Anderson', 'lisaanderson@example.com', '1819 Pine St', 'FEMALE', '2023-03-24'),
       (59, 'patient', 'David', 'Thomas', 'davidthomas@example.com', '2021 Maple St', 'MALE', '2023-03-24'),
       (60, 'patient', 'Karen', 'Clark', 'karenclark@example.com', '2223 Oak St', 'FEMALE', '2023-03-24'),
       (61, 'patient', 'Daniel', 'Walker', 'danielwalker@example.com', '2425 Elm St', 'MALE', '2023-03-24'),
       (62, 'patient', 'Michelle', 'Lewis', 'michellelewis@example.com', '2627 Pine St', 'FEMALE', '2023-03-24'),
       (63, 'patient', 'Kevin', 'Allen', 'kevinallen@example.com', '2829 Maple St', 'MALE', '2023-03-24'),
       (64, 'patient', 'Amanda', 'Young', 'amandayoung@example.com', '3031 Oak St', 'FEMALE', '2023-03-24'),
       (65, 'patient', 'Robert', 'Scott', 'robertscott@example.com', '3233 Elm St', 'MALE', '2023-03-24'),
       (66, 'patient', 'Emily', 'Kim', 'emilykim@example.com', '456 Oak St', 'FEMALE', '2023-03-24'),
       (67, 'patient', 'David', 'Smith', 'davidsmith@example.com', '456 Oak St', 'MALE', '2023-03-24'),
       (68, 'patient', 'Sophia', 'Williams', 'sophiawilliams@example.com', '789 Maple Ave', 'FEMALE', '2023-03-24'),
       (69, 'patient', 'William', 'Jones', 'williamjones@example.com', '321 Elm St', 'MALE', '2023-03-24'),
       (70, 'patient', 'Emily', 'Johnson', 'emilyjohnson@example.com', '123 Main St', 'FEMALE', '2023-03-24');

INSERT INTO HospitalManagementDB.patient (id, family_email, family_phone_number)
VALUES (21, 'familyemail0@email.com', 1234567890),
       (22, 'familyemail0@email.com', 1234567890),
       (23, 'familyemail0@email.com', 1234567890),
       (24, 'familyemail0@email.com', 1234567890),
       (25, 'familyemail0@email.com', 1234567890),
       (26, 'familyemail0@email.com', 1234567890),
       (27, 'familyemail0@email.com', 1234567890),
       (28, 'familyemail0@email.com', 1234567890),
       (29, 'familyemail0@email.com', 1234567890),
       (30, 'familyemail0@email.com', 1234567890),
       (31, 'familyemail0@email.com', 1234567890),
       (32, 'familyemail0@email.com', 1234567890),
       (33, 'familyemail0@email.com', 1234567890),
       (34, 'familyemail0@email.com', 1234567890),
       (35, 'familyemail0@email.com', 1234567890),
       (36, 'familyemail0@email.com', 1234567890),
       (37, 'familyemail0@email.com', 1234567890),
       (38, 'familyemail0@email.com', 1234567890),
       (39, 'familyemail0@email.com', 1234567890),
       (40, 'familyemail0@email.com', 1234567890),
       (41, 'familyemail0@email.com', 1234567890),
       (42, 'familyemail0@email.com', 1234567890),
       (43, 'familyemail0@email.com', 1234567890),
       (44, 'familyemail0@email.com', 1234567890),
       (45, 'familyemail0@email.com', 1234567890),
       (46, 'familyemail0@email.com', 1234567890),
       (47, 'familyemail0@email.com', 1234567890),
       (48, 'familyemail0@email.com', 1234567890),
       (49, 'familyemail0@email.com', 1234567890),
       (50, 'familyemail0@email.com', 1234567890),
       (51, 'familyemail0@email.com', 1234567890),
       (52, 'familyemail0@email.com', 1234567890),
       (53, 'familyemail0@email.com', 1234567890),
       (54, 'familyemail0@email.com', 1234567890),
       (55, 'familyemail0@email.com', 1234567890),
       (56, 'familyemail0@email.com', 1234567890),
       (57, 'familyemail0@email.com', 1234567890),
       (58, 'familyemail0@email.com', 1234567890),
       (59, 'familyemail0@email.com', 1234567890),
       (60, 'familyemail0@email.com', 1234567890),
       (61, 'familyemail0@email.com', 1234567890),
       (62, 'familyemail0@email.com', 1234567890),
       (63, 'familyemail0@email.com', 1234567890),
       (64, 'familyemail0@email.com', 1234567890),
       (65, 'familyemail0@email.com', 1234567890),
       (66, 'familyemail0@email.com', 1234567890),
       (67, 'familyemail0@email.com', 1234567890),
       (68, 'familyemail0@email.com', 1234567890),
       (69, 'familyemail0@email.com', 1234567890),
       (70, 'familyemail0@email.com', 1234567890);

INSERT INTO HospitalManagementDB.medical_record (id, patient_id,
                                                 blood_type, heart_rate, respiratory_rate, sugar_level,
                                                 blood_pressure, weight, height, surgeries)
VALUES (1, 21, 'A_POSITIVE', 80.0, 18.0, 100.0, 120.0, 70.0, 180.0, 0),
       (2, 22, 'A_NEGATIVE', 70.0, 16.0, 90.0, 110.0, 65.0, 170.0, 1),
       (3, 23, 'B_POSITIVE', 75.0, 20.0, 110.0, 130.0, 75.0, 185.0, 2),
       (4, 24, 'B_NEGATIVE', 65.0, 14.0, 95.0, 125.0, 60.0, 160.0, 0),
       (5, 25, 'AB_POSITIVE', 85.0, 22.0, 120.0, 140.0, 80.0, 190.0, 1),
       (6, 26, 'AB_NEGATIVE', 90.0, 19.0, 120.0, 115.0, 75.0, 175.0, 2),
       (7, 27, 'O_POSITIVE', 80.0, 18.0, 100.0, 120.0, 70.0, 180.0, 0),
       (8, 28, 'O_NEGATIVE', 70.0, 16.0, 90.0, 110.0, 65.0, 170.0, 1),
       (9, 29, 'A_POSITIVE', 75.0, 20.0, 110.0, 130.0, 75.0, 185.0, 2),
       (10, 30, 'A_NEGATIVE', 65.0, 14.0, 95.0, 125.0, 60.0, 160.0, 0),
       (11, 31, 'B_POSITIVE', 85.0, 22.0, 120.0, 140.0, 80.0, 190.0, 1),
       (12, 32, 'B_NEGATIVE', 90.0, 19.0, 120.0, 115.0, 75.0, 175.0, 2),
       (13, 33, 'AB_POSITIVE', 80.0, 18.0, 100.0, 120.0, 70.0, 180.0, 0),
       (14, 34, 'AB_NEGATIVE', 70.0, 16.0, 90.0, 110.0, 65.0, 170.0, 1),
       (15, 35, 'O_POSITIVE', 75.0, 20.0, 110.0, 130.0, 75.0, 185.0, 2),
       (16, 36, 'O_NEGATIVE', 65.0, 14.0, 95.0, 125.0, 60.0, 160.0, 0),
       (17, 37, 'A_POSITIVE', 85.0, 22.0, 120.0, 140.0, 80.0, 190.0, 1),
       (18, 38, 'A_NEGATIVE', 90.0, 19.0, 120.0, 115.0, 75.0, 175.0, 2),
       (19, 39, 'B_POSITIVE', 80.0, 18.0, 100.0, 120.0, 70.0, 180.0, 0),
       (20, 40, 'O_POSITIVE', 80.0, 18.0, 100.0, 120.0, 70.0, 180.0, 0),
       (21, 41, 'O_NEGATIVE', 70.0, 16.0, 90.0, 110.0, 65.0, 170.0, 1),
       (22, 42, 'AB_POSITIVE', 75.0, 20.0, 110.0, 130.0, 75.0, 185.0, 2),
       (23, 43, 'AB_NEGATIVE', 65.0, 14.0, 95.0, 125.0, 60.0, 160.0, 0),
       (24, 44, 'A_POSITIVE', 85.0, 22.0, 120.0, 140.0, 80.0, 190.0, 1),
       (25, 45, 'A_NEGATIVE', 90.0, 19.0, 120.0, 115.0, 75.0, 175.0, 2),
       (26, 46, 'B_POSITIVE', 80.0, 18.0, 100.0, 120.0, 70.0, 180.0, 0),
       (27, 47, 'B_NEGATIVE', 70.0, 16.0, 90.0, 110.0, 65.0, 170.0, 1),
       (28, 48, 'AB_POSITIVE', 75.0, 20.0, 110.0, 130.0, 75.0, 185.0, 2),
       (29, 49, 'AB_NEGATIVE', 65.0, 14.0, 95.0, 125.0, 60.0, 160.0, 0),
       (30, 50, 'O_POSITIVE', 85.0, 22.0, 120.0, 140.0, 80.0, 190.0, 1),
       (31, 51, 'O_NEGATIVE', 90.0, 19.0, 120.0, 115.0, 75.0, 175.0, 2),
       (32, 52, 'A_POSITIVE', 80.0, 18.0, 100.0, 120.0, 70.0, 180.0, 0),
       (33, 53, 'A_NEGATIVE', 70.0, 16.0, 90.0, 110.0, 65.0, 170.0, 1),
       (34, 54, 'B_POSITIVE', 75.0, 20.0, 110.0, 130.0, 75.0, 185.0, 2),
       (35, 55, 'B_NEGATIVE', 65.0, 14.0, 95.0, 125.0, 60.0, 160.0, 0),
       (36, 56, 'AB_POSITIVE', 85.0, 22.0, 120.0, 140.0, 80.0, 190.0, 1),
       (37, 57, 'AB_NEGATIVE', 90.0, 19.0, 120.0, 115.0, 75.0, 175.0, 2),
       (38, 58, 'O_POSITIVE', 80.0, 18.0, 100.0, 120.0, 70.0, 180.0, 0),
       (39, 59, 'O_NEGATIVE', 70.0, 16.0, 90.0, 110.0, 65.0, 170.0, 1),
       (40, 60, 'A_POSITIVE', 75.0, 20.0, 110.0, 130.0, 75.0, 185.0, 2),
       (41, 61, 'A_NEGATIVE', 65.0, 14.0, 95.0, 125.0, 60.0, 160.0, 0),
       (42, 62, 'B_POSITIVE', 85.0, 22.0, 120.0, 140.0, 80.0, 190.0, 1),
       (43, 63, 'AB_NEGATIVE', 90.0, 19.0, 120.0, 115.0, 75.0, 175.0, 2),
       (44, 64, 'AB_POSITIVE', 80.0, 18.0, 100.0, 120.0, 70.0, 180.0, 0),
       (45, 65, 'B_NEGATIVE', 70.0, 16.0, 90.0, 110.0, 65.0, 170.0, 1),
       (46, 66, 'O_POSITIVE', 75.0, 20.0, 110.0, 130.0, 75.0, 185.0, 2),
       (47, 67, 'O_NEGATIVE', 65.0, 14.0, 95.0, 125.0, 60.0, 160.0, 0),
       (48, 68, 'A_POSITIVE', 85.0, 22.0, 120.0, 140.0, 80.0, 190.0, 1),
       (49, 69, 'A_NEGATIVE', 90.0, 19.0, 120.0, 115.0, 75.0, 175.0, 2),
       (50, 70, 'B_POSITIVE', 80.0, 18.0, 100.0, 120.0, 70.0, 180.0, 0);

commit;