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