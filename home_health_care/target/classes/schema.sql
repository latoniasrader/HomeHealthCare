DROP TABLE IF EXISTS home_health_care_client;
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS home_health_care;


CREATE TABLE home_health_care(
home_health_care_id int NOT NULL AUTO_INCREMENT,
home_health_care_name varchar(256) NOT NULL,
home_health_care_address varchar(130) NOT NULL,
home_health_care_city varchar(60),
home_health_care_state varchar(50),
home_health_care_zip varchar(20),
home_health_care_phone varchar(30),
home_health_care_email varchar(40),
PRIMARY KEY (home_health_care_id)
);

CREATE TABLE client(
client_id int NOT NULL AUTO_INCREMENT,
home_health_care_id int NOT NULL,
client_first_name varchar(70) NOT NULL,
client_last_name varchar(70)NOT NULL,
client_address varchar(130)NOT NULL,
client_city varchar(60),
client_state varchar(70),
client_zip varchar(20),
client_phone varchar(30),
client_care_package varchar(500),
PRIMARY KEY(client_id),
FOREIGN KEY (home_health_care_id)REFERENCES home_health_care(home_health_care_id)ON DELETE CASCADE
);

CREATE TABLE staff(
staff_id int NOT NULL AUTO_INCREMENT,
home_health_care_id int NOT NULL,
staff_first_name varchar(70) NOT NULL,
staff_last_name varchar(70) NOT NULL,
staff_phone varchar(30),
staff_email varchar(40),
staff_job_title varchar(70),
staff_shift_hours varchar(20),
PRIMARY KEY(staff_id),
FOREIGN KEY (home_health_care_id)REFERENCES home_health_care(home_health_care_id)ON DELETE CASCADE
);

CREATE TABLE home_health_care_client(
home_health_care_id int NOT NULL,
client_id int NOT NULL,
FOREIGN KEY(home_health_care_id)REFERENCES home_health_care(home_health_care_id)ON DELETE CASCADE,
FOREIGN KEY (client_id)REFERENCES client (client_id)ON DELETE CASCADE
);




