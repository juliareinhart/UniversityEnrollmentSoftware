### 📘 University Enrollment Software
A JavaFX desktop application backed by a MySQL database.
Easily manage university data: departments, professors, courses, students, and enrollments.

### 🚀 Features
-JavaFX GUI with TableView for clean visual management

-MySQL database integration using JDBC

-Stored procedures (e.g., add_department)

-Modular architecture with MVC-style organization

-Sample generic LinkedList implementation included

-Input validation and error handling

-Modern, clean CSS styling

### 🛠️ Requirements
-Java 17+

-JavaFX SDK

-MySQL

J-DBC Driver

-(Optional) Eclipse or IntelliJ

### 📂 Folder Structure

```bash
university-enrollment-software/
├── src/
│   └── application/
│       ├── university-enrollment-software.java         # Main application file
│       ├── LinkedList.java                             # Custom generic linked list
│       └── application.css                             # CSS styling
├── app.config                                          # Database connection string config
├── build.fxbuild                                       # JavaFX build configuration
├── gson_lib/                                           # Gson library for JSON parsing
├── JRE System Library/                                 # Java runtime environment (JavaSE-21)
├── JavaFX/                                             # JavaFX SDK libraries
├── university_schema.sql                               # SQL script
└── README.md                                           # Project documentation
```

### 🧪 How to Run

**1. Set up your MySQL database:**

- Run the `university_schema.sql` file in MySQL Workbench or another MySQL client.
- This will create all 5 tables and the stored procedures.

**2. Configure database connection:**

- Edit the connection string in your Java class or `app.config`.

```plaintext
jdbc:mysql://localhost:3306/universityenrollmentsoftware?user=root&password=yourpassword
```
**3.Run the program:**

-Open in your preferred IDE

-Run Main.java from the src/ directory

-Use the GUI to view, add, and manage university records

**📄 SQL Script**
All table creation statements and stored procedures are in:
university_schema.sql

**🔗 Generic Linked List**
This project includes a custom implementation of a generic LinkedList class, used for earlier data management before full DB integration:
LinkedList.java

🙋 About Me
👩‍💻 Built with 💖 by Julia Reinhart —
Math graduate, self-taught MERN + Java developer, and passionate about beautiful accessible software.

🔗 Connect on LinkedIn

📄 License
This project is licensed under the MIT License.
See the LICENSE file for more info.

