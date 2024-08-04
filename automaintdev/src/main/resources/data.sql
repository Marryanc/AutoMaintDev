INSERT INTO vehicle (make, model, vyear, vin) VALUES 
('Toyota', 'Camry', 2015, '1HGCM82633A123456'),
('Honda', 'Civic', 2018, '2HGFA16578H123456'),
('Ford', 'Mustang', 2020, '1FA6P8TH4L5101234');

INSERT INTO maintenance_record (vehicle_id, date, description, mileage) VALUES
(1, '2023-01-10', 'Oil Change', 45000),
(1, '2023-06-15', 'Tire Rotation', 47000),
(2, '2023-02-20', 'Brake Pads Replacement', 30000),
(3, '2023-05-25', 'Battery Replacement', 15000);
