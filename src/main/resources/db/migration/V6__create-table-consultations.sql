create table consultations(

id bigint not null auto_increment,
md_id bigint not null,
patient_id bigint not null,
data datetime not null,

primary key(id),

constraint fk_consultation_md_id foreign key(md_id) references medical_doctors(id),
constraint fk_consultation_patient_id foreign key(patient_id) references patients(id)



);