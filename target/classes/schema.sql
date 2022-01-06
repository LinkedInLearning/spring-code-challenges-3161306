DROP TABLE IF EXISTS catering_job;
CREATE TYPE STATUS as enum('NOT_STARTED',
    'COMPLETED',
    'CANCELED',
    'IN_PROGRESS');

CREATE TABLE catering_job (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name  VARCHAR(250) NOT NULL,
    phone_number VARCHAR(10),
    email VARCHAR(250),
    menu JSON,
    no_of_guests INT,
    status STATUS
);

INSERT INTO catering_job (id, customer_name, phone_number, email, no_of_guests, menu, status) VALUES
  (1, 'Susan Bob', '1234567890', 'susan.bob@test.com', 15, '{"spaghetti and meatballs":"fresh pasta with homemade meatballs"}' FORMAT JSON, 'NOT_STARTED');

INSERT INTO catering_job (id, customer_name, phone_number, email, no_of_guests, menu, status) VALUES
  (2, 'Rudy John', '234567891', 'rudy@mail.com', 35, '{"chicken parmesan":"fresh pasta with fried chicken thighs, marinara sauce, and parmesan cheese"}' FORMAT JSON, 'CANCELED');
