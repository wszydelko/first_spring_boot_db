/*
INSERT INTO project(name) VALUES('Projekt 1');
INSERT INTO project(name) VALUES('Projekt 2');

Insert into task(name, description, budget, done, project_id) values('Task 1', 'Description of task 1', 100.00, 1, SELECT id FROM project WHERE name = 'Projekt 1');
Insert into task(name, description, budget, done, project_id) values('Task 2', 'Description of task 2 Do', 100.00, 1, SELECT id FROM project WHERE name = 'Projekt 1');
Insert into task(name, description, budget, done, project_id) values('Task 3', 'Description of task 3 Do', 50.00, 0, SELECT id FROM project WHERE name = 'Projekt 1');
Insert into task(name, description, budget, done, project_id) values('Task 4', 'Description of task 3 Do', 50.00, 0, select id FROM  project WHERE name = 'Projekt 1');

Insert into task(name, description, budget, done, project_id) values('Task 1', 'Description of task 1', 100.00, 1, SELECT id FROM project WHERE name = 'Projekt 2');
Insert into task(name, description, budget, done, project_id) values('Task 2', 'Description of task 2 Do', 100.00, 1, SELECT id FROM project WHERE name = 'Projekt 2');
Insert into task(name, description, budget, done, project_id) values('Task 3', 'Description of task 3 Do', 50.00, 0, SELECT id FROM project WHERE name = 'Projekt 2');

INSERT INTO project_Details(description, project_id) VALUES('init test', SELECT id FROM project WHERE name = 'Projekt 1');
INSERT INTO project_Details(description, project_id) VALUES('init test', SELECT id FROM project WHERE name = 'Projekt 2');
*/