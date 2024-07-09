
alter table medical_doctors add active tinyInt;
update medical_doctors set active = 1;