# Appointment and Customer Management System

**Purpose:**
This application is designed to manage appointments and customers. It allows users to add, modify, and delete appointments and customer records. The system also checks for upcoming appointments and provides alerts. Additionally, it includes functionality to generate various reports, such as appointments by contact or customer. This application is built using JavaFX and MySQL.

---

**Author:**
- **Name**: Chris Schmitz
- **Email**: 96Schmitzc@gmail.com
- **Student Application Version**: v1.0
- **Date**: 10/7/2024

---

**IDE and Environment:**
- **IDE**: IntelliJ IDEA Community Edition 2023.2.2
- **JDK**: Java SE 17.0.6
- **JavaFX SDK**: JavaFX-SDK-17.0.1 (compatible with JDK 17)
- **MySQL Connector Driver**: mysql-connector-java-8.0.26

---

**How to Run the Program:**
1. Ensure Java 17 and JavaFX SDK 17 are properly installed and configured.
2. Clone or download the project files.
3. Import the project into your preferred IDE (e.g., IntelliJ IDEA).
4. Configure the project dependencies:
    - Add the `JavaFX-SDK` to the project.
    - Add the `mysql-connector-java-8.X.X.jar` to the classpath.
5. Set up a MySQL database and import the provided database schema.
6. Update the JDBC connection settings in the `JDBC.java` helper class with your MySQL credentials.
7. Run the `MainApplication.java` file to launch the application.

---

**Usage:**
- **Login Credentials**:
    - **Username**: `test`
    - **Password**: `test`

- **Adding Appointments**:
    - To add an appointment, navigate to the "Add Appointment" screen, fill out the required fields (title, description, contact, type, start time, end time, etc.), and click "Save."

- **Modifying Appointments**:
    - To modify an appointment, select an appointment from the table, click the "Modify Appointment" button, update the desired fields, and click "Save."

- **Adding Customers**:
    - To add a customer, go to the "Add Customer" page, enter the customer's information (name, address, phone number, etc.), and click "Save."

- **Modifying Customers**:
    - To modify a customer's details, select the customer from the customer table, click "Modify Customer," update the necessary information, and click "Save."

---






