INSERT INTO home_health_care ( home_health_care_name, home_health_care_address, home_health_care_city, home_health_care_state,
home_health_care_zip, home_health_care_phone, home_health_care_email) VALUES ('Hill Top Home Health Care',
'127 Joyce Street', 'Fayetteville', 'Akransas', '72701', '479-751-5401', 'hilltophomehealthcare@gmail.com');
INSERT INTO home_health_care ( home_health_care_name, home_health_care_address, home_health_care_city, home_health_care_state, 
 home_health_care_zip, home_health_care_phone, home_health_care_email) VALUES ('Countryside Home Health Care',
'42 North Highway 265', 'Fayetteville', 'Akransas', '72701', '479-751-8799', 'countrysidehomehealthcare@gmail.com');
INSERT INTO client ( home_health_care_id, client_first_name, client_last_name, client_address, client_city, client_state,
client_zip, client_phone, client_care_package) VALUES(1,'Jane','Smath', '502 West Street', 'Green Forst', 'Arkansas','70745','870-445-8987',
'Had a Stroke and weak on the right side and need help with daily liveing activities and personal care.');
INSERT INTO client (home_health_care_id, client_first_name, client_last_name, client_address, client_city, client_state,
 client_zip, client_phone, client_care_package) VALUES(2,'John', 'Doe', '9423 North Highway 65', 'Berryville', 'Akransas', '72616', '870-665-5612',
 'Has alzheimers and help with personal care, light house cleaning');
INSERT INTO staff ( home_health_care_id, staff_first_name, staff_last_name, staff_phone, staff_email, staff_job_title, staff_shift_hours)
VALUES(1,'Jackie', 'Thomas', '479-445-8789', 'jackiethomas@yahoo.com','CNA', '1st shift 8am-4pm');
INSERT INTO staff (home_health_care_id, staff_first_name, staff_last_name, staff_phone, staff_email, staff_job_title, staff_shift_hours) 
VALUES(2,'Lori', 'Wills','479-571-5505', 'loriwills@gmail.com','RN', '3rd shift 7am-7pm');

