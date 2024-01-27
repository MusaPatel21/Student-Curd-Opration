use student
create table students(fname varchar(20), lname varchar(20), contact int, address varchar(200), clas int ); 

desc students

ALTER TABLE students
MODIFY COLUMN contact VARCHAR(20);

