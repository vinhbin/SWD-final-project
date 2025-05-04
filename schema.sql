
CREATE TABLE users(
  username VARCHAR(50) PRIMARY KEY,
  password_hash CHAR(64) NOT NULL,
  role ENUM('ADMIN','EMPLOYEE') NOT NULL
);
CREATE TABLE employees(
  empid INT PRIMARY KEY,
  firstName VARCHAR(50),
  lastName VARCHAR(50),
  ssn CHAR(9) UNIQUE,
  dob DATE,
  salary DECIMAL(10,2)
);
CREATE TABLE address(
  empid INT,
  street VARCHAR(100),
  city VARCHAR(50),
  state VARCHAR(50),
  zip VARCHAR(10),
  PRIMARY KEY(empid),
  FOREIGN KEY(empid) REFERENCES employees(empid)
);
CREATE TABLE division(
  divisionID INT PRIMARY KEY,
  name VARCHAR(50)
);
CREATE TABLE employee_division(
  empid INT,
  divisionID INT,
  FOREIGN KEY(empid) REFERENCES employees(empid),
  FOREIGN KEY(divisionID) REFERENCES division(divisionID)
);
CREATE TABLE job_title(
  jobTitleID INT PRIMARY KEY,
  title VARCHAR(50)
);
CREATE TABLE employee_job_title(
  empid INT,
  jobTitleID INT,
  FOREIGN KEY(empid) REFERENCES employees(empid),
  FOREIGN KEY(jobTitleID) REFERENCES job_title(jobTitleID)
);
CREATE TABLE payroll(
  payId INT PRIMARY KEY AUTO_INCREMENT,
  empid INT,
  payDate DATE,
  amount DECIMAL(10,2),
  FOREIGN KEY(empid) REFERENCES employees(empid)
);
