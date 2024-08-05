INSERT INTO vehicle (make, model, vyear, vin) VALUES 
('Toyota', 'Camry', 2015, '1HGCM82633A123456'),
('Honda', 'Civic', 2018, '2HGFA16578H123456'),
('Ford', 'Mustang', 2020, '1FA6P8TH4L5101234');

INSERT INTO maintenance_record (vehicle_id, date, description, mileage) VALUES
(1, '2023-01-10', 'Oil Change', 45000),
(1, '2023-06-15', 'Tire Rotation', 47000),
(2, '2023-02-20', 'Brake Pads Replacement', 30000),
(3, '2023-05-25', 'Battery Replacement', 15000);

-- Insert roles
INSERT INTO sec_role (roleName) VALUES ('ROLE_USER');
INSERT INTO sec_role (roleName) VALUES ('ROLE_ADMIN');

-- Insert a test user
INSERT INTO sec_user (email, encryptedPassword, enabled) VALUES ('admin@admin.com', '$2a$12$EKj5ZjubTLzBvw2VMcXtqu27x3/r.9cf9K8KGzKaESZxxH4tQ51ZK', 1);
INSERT INTO sec_user (email, encryptedPassword, enabled) VALUES ('user@user.com', '$2a$12$OA2X5uJCVweMSFIRr8uYM.373m9LkDatCJGZcQeB18qGUPpKUFxwi', 2);
-- Note: The password 'password' is encrypted using BCrypt with a strength of 10. You can use any BCrypt library to generate your own.

-- Insert user-role associations
INSERT INTO user_role (userId, roleId) VALUES (1, 1); -- Assuming the userId of the inserted user is 1 and roleId for ROLE_USER is 1
INSERT INTO user_role (userId, roleId) VALUES (1, 2); -- Assuming roleId for ROLE_ADMIN is 2
INSERT INTO user_role (userId, roleId) VALUES (2, 1); -- Assuming the userId of the inserted user is 1 and roleId for ROLE_USER is 1
