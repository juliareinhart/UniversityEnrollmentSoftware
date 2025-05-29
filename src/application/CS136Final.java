//necessary imports for the code to run and to use JavaFX additional features

package application;
//import MyGenericList.Node;

//import ArrayList.Node;

import java.io.*; // needed to write to files and to throw exceptions when reading  a file, necessary for files
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Callback;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.DialogPane;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.sql.*;
// needed for scanner objects such as file reading and reading input from the keyboard
import java.util.*;


//to be honest, i'm not quite sure what these classes do, but I know they are necessary for the table view to work properly. I believe these are used for binding/attaching the object parameters to the tableview data in that will be displayed there. I haven't learned call backs but I will and I was following an easier tutorial that didn't work for me, so I did it the old and longer way which is this way

//class used to bind student name variable in my enrollmentdatalist object to the name column in the table view, similarly for the remaining variables
class StudentNameCellValueFactory implements Callback<TableColumn.CellDataFeatures<EnrollmentTableViewData, String>, ObservableValue<String>> {
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<EnrollmentTableViewData, String> param) {
        return new SimpleStringProperty(param.getValue().getStudentName());
    }
}

class CourseNameCellValueFactory implements Callback<TableColumn.CellDataFeatures<EnrollmentTableViewData, String>, ObservableValue<String>> {
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<EnrollmentTableViewData, String> param) {
        return new SimpleStringProperty(param.getValue().getCourseName());
    }
}

class CourseNumberCellValueFactory implements Callback<TableColumn.CellDataFeatures<EnrollmentTableViewData, String>, ObservableValue<String>> {
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<EnrollmentTableViewData, String> param) {
        return new SimpleStringProperty(param.getValue().getCourseNumber());
    }
}

class CourseIdCellValueFactory implements Callback<TableColumn.CellDataFeatures<EnrollmentTableViewData, Integer>, ObservableValue<Integer>> {
    @Override
    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<EnrollmentTableViewData, Integer> param) {
        return new SimpleIntegerProperty(param.getValue().getCourseId()).asObject();
    }
}

class StudentIdCellValueFactory implements Callback<TableColumn.CellDataFeatures<EnrollmentTableViewData, Integer>, ObservableValue<Integer>> {
    @Override
    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<EnrollmentTableViewData, Integer> param) {
        return new SimpleIntegerProperty(param.getValue().getStudentId()).asObject();
    }
}

class SemesterCellValueFactory implements Callback<TableColumn.CellDataFeatures<EnrollmentTableViewData, String>, ObservableValue<String>> {
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<EnrollmentTableViewData, String> param) {
        return new SimpleStringProperty(param.getValue().getSemester());
    }
}

class YearCellValueFactory implements Callback<TableColumn.CellDataFeatures<EnrollmentTableViewData, Integer>, ObservableValue<Integer>> {
    @Override
    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<EnrollmentTableViewData, Integer> param) {
        return new SimpleIntegerProperty(param.getValue().getYear()).asObject();
    }
}

class GradeCellValueFactory implements Callback<TableColumn.CellDataFeatures<EnrollmentTableViewData, Character>, ObservableValue<Character>> {
    @Override
    public ObservableValue<Character> call(TableColumn.CellDataFeatures<EnrollmentTableViewData, Character> param) {
        return new SimpleObjectProperty<>(param.getValue().getGrade());
    }
}


class Student implements Comparable<Student>{
	private int studentId; // variables for student data in the file
	private String studentFirstName;
	private String studentLastName;
	private String studentAddress;
	private String studentCity;
	private String studentState;
	private String studentZip;
	

	
	// constructor to use when creating student objects so they all have data
	public Student(int studentId, String studentFirstName, String studentLastName, String studentAddress, String studentCity, String studentState, String studentZip) {
		this.studentId = studentId;
		this.studentFirstName = studentFirstName;
		this.studentLastName = studentLastName;
		this.studentAddress = studentAddress;
		this.studentCity = studentCity;
		this.studentState = studentState;
		this.studentZip = studentZip;
	}
	

	
	//mutator/setter functions to assign data to private member variables without corrupting it
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;	
	}
	
	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;	
	}
	
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	
	public void setStudentCity(String studentCity) {
		this.studentCity = studentCity;
	}
	
	public void setStudentState(String studentState) {
		this.studentState = studentState;
	}
	
	public void setStudentZip(String studentZip) {
		this.studentZip = studentZip;
	}
	
	//accessor/getter functions to retrieve data from private member variables without corrupting it
	public int getStudentId() {
		return studentId;
	}
	
	public String getStudentFirstName() {
		return studentFirstName;
	}
	
	public String getStudentLastName() {
		return studentLastName;
	}
	
	public String getStudentAddress() {
		return studentAddress;
	}
	
	public String getStudentCity() {
		return studentCity;
	}
	
	public String getStudentState() {
		return studentState;
	}
	
	public String getStudentZip() {
		return studentZip;
	}
	
	@Override
    public int compareTo(Student student)

    {
		return this.studentLastName.compareTo(student.studentLastName); //returns negative integer if the calling object is less than the other, positive if the calling object is greater than the other one and 0 if the calling object is equal to the other object 
    }
	
	@Override
    public String toString() {
        return "ID:" + studentId + ", Name: " + studentFirstName + " " + studentLastName + ", Location: " + studentAddress + ", " + studentCity + ", " + studentState + " " + studentZip;
    }
}

class Instructor implements Comparable<Instructor>{
	private int instructorId; // variables for student data in the file
	private String instructorName;
	private String department;
	
	

	
	// constructor to use when creating student objects so they all have data
	public Instructor(int instructorId, String instructorName, String department) {
		this.instructorId = instructorId;
		this.instructorName = instructorName;
		this.department = department;
	}
	

	
	//mutator/setter functions to assign data to private member variables without corrupting it
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
	
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;	
	}
	
	public void setInstructordepartment(String department) {
		this.department = department;
	}
	
	//accessor/getter functions to retrieve data from private member variables without corrupting it
	public int getInstructorId() {
		return instructorId;
	}
	
	public String getInstructorName() {
		return instructorName;
	}
	
	public String getInstructorDepartment() {
		return department;
	}
	
	@Override
    public int compareTo(Instructor instructor)

    {
		return this.instructorName.compareTo(instructor.instructorName); //returns negative integer if the calling object is less than the other, positive if the calling object is greater than the other one and 0 if the calling object is equal to the other object 
    }
	
	@Override
    public String toString() {
        return "ID:" + instructorId + ", Name: " + instructorName +  ", Department: " + department;
    }
}

class Department implements Comparable<Department>{
	private int departmentId; // variables for student data in the file
	private String departmentName;
	

	
	// constructor to use when creating student objects so they all have data
	public Department(int departmentId, String departmentName) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	

	
	//mutator/setter functions to assign data to private member variables without corrupting it
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;	
	}
	
	//accessor/getter functions to retrieve data from private member variables without corrupting it
	public int getDepartmentId() {
		return departmentId;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	
	@Override
    public int compareTo(Department department)

    {
		return this.departmentName.compareTo(department.departmentName); //returns negative integer if the calling object is less than the other, positive if the calling object is greater than the other one and 0 if the calling object is equal to the other object 
    }
	
	@Override
    public String toString() {
        return "ID:" + departmentId + ", Name: " + departmentName;
    }
}

class Course implements Comparable<Course>{ // course class holds private member variables, used with coursefile manager class
	private int courseId;
	private String courseName;
	private int courseNumber;
	private String department;
	private String instructor;
	
	//constructor for course objectss to initialize them with data
	// note that I used the this keyword to make it easier to see whats going on with setter and constructor
	public Course(int courseId, String courseName, int courseNumber, String department, String instructor) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseNumber = courseNumber;
		this.department = department;
		this.instructor = instructor;
	}
	
	//setter and getter functions for course objects so data stays secure with integrity
	public int getCourseId() {
		return courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public int getCourseNumber() {
		return courseNumber;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public String getInstructor() {
		return instructor;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
		}
	
	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	@Override
    public int compareTo(Course course)

    {
		return this.courseName.compareTo(course.courseName); //returns negative integer if the calling object is less than the other, positive if the calling object is greater than the other one and 0 if the calling object is equal to the other object 
    }
	
	@Override
    public String toString() {
        return "ID:" + courseId + ", Name: " + courseNumber + " " + courseName + ", Department, Instructor: " + department + ", " + instructor;
    }
}
class Enrollment implements Comparable<Enrollment>{ //enrollment class follows similar layout to course and student classes
	private int enrollmentId,
				courseId, 
				studentId, 
				year; 
	private String semester;
	private char grade;
	
	public Enrollment(int enrollmentId, int studentId, int courseId, int year, String semester, char grade) {
		this.enrollmentId = enrollmentId;
		this.courseId = courseId;
		this.studentId = studentId;
		this.year = year;
		this.semester = semester;
		this.grade = grade;
	}
	
	public int getEnrollmentId() {
		return enrollmentId;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	public int getStudentId() {
		return studentId;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getSemester() {
		return semester;
	}
	
	public char getGrade() {
		return grade;
	}
	
	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	@Override
    public int compareTo(Enrollment enrollment)

    {
		//returns negative integer if the calling object is less than the other, positive if the calling object is greater than the other one and 0 if the calling object is equal to the other object 
		if (this.year < enrollment.year) {
			return -1;
		}
		else if (this.year > enrollment.year) {
			return 1;
		}
		return 0; //only executes if the previous two conditions don't case where everything is equal
    }
	
	@Override
    public String toString() {
        return "Enr. ID, Crse. ID, St. ID:" + enrollmentId + ", " + courseId + ", " + studentId + ", Sem, Yr, Grade: " + semester + " " + year + " " + grade;
    }
	
}



//here is the class that goes with the classes that bind data in object to the table view data should have moved this but at this point I am worrying about breaking the code and getting bugs will move it later
class EnrollmentTableViewData {
    private SimpleStringProperty studentName; //simplestring property for strings working with the classes to bind data, simple integer property for ints, and an object property of character type for char data
    private SimpleStringProperty courseName;
    private SimpleIntegerProperty courseId;
    private SimpleIntegerProperty studentId;
    private SimpleStringProperty courseNumber;
    private SimpleStringProperty semester;
    private SimpleIntegerProperty year;
    private SimpleObjectProperty<Character> grade;

    // Constructor
    public EnrollmentTableViewData(
            SimpleStringProperty studentName,
            SimpleStringProperty courseName,
            SimpleIntegerProperty courseId,
            SimpleIntegerProperty studentId,
            SimpleStringProperty courseNumber,
            SimpleStringProperty semester,
            SimpleIntegerProperty year,
            SimpleObjectProperty<Character> grade) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.courseId = courseId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseNumber = courseNumber;
        this.semester = semester;
        this.year = year;
        this.grade = grade;
    }
    
 // Default Constructor
    public EnrollmentTableViewData() {
        this.studentName = new SimpleStringProperty("");
        this.courseName = new SimpleStringProperty("");
        this.courseId = new SimpleIntegerProperty(-1);
        this.studentId = new SimpleIntegerProperty(-1);
        this.courseNumber = new SimpleStringProperty("");
        this.semester = new SimpleStringProperty("");
        this.year = new SimpleIntegerProperty(-1);
        this.grade = new SimpleObjectProperty<>(' ');
    }

    // Getters and Setters
    // need three functions per variable for getters and setters, follow the pattern below to create more
    public SimpleStringProperty studentNameProperty() {
        return studentName;
    }

    public String getStudentName() {
        return studentName.get();
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }
    
    public SimpleStringProperty courseNameProperty() {
        return courseName;
    }

    public String getCourseName() {
        return courseName.get();
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    public SimpleIntegerProperty courseIdProperty() {
        return courseId;
    }

    public int getCourseId() {
        return courseId.get();
    }

    public void setCourseId(int courseId) {
        this.courseId.set(courseId);
    }
    
    public SimpleIntegerProperty studentIdProperty() {
        return studentId;
    }

    public int getStudentId() {
        return studentId.get();
    }

    public void setStudentId(int studentId) {
        this.studentId.set(studentId);
    }
    
    public SimpleStringProperty courseNumberProperty() {
        return courseNumber;
    }

    public String getCourseNumber() {
        return courseNumber.get();
    }
    
    public void setCourseNumber(String courseNumber) {
        this.courseNumber.set(courseNumber);;
    }

    public SimpleStringProperty semesterProperty() {
        return semester;
    }

    public String getSemester() {
        return semester.get();
    }

    public void setSemester(String semester) {
        this.semester.set(semester);
    }

    public SimpleIntegerProperty yearProperty() {
        return year;
    }

    public int getYear() {
        return year.get();
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public SimpleObjectProperty<Character> gradeProperty() {
        return grade;
    }

    public char getGrade() {
        return grade.get();
    }

    public void setGrade(char grade) {
        this.grade.set(grade);
    }
}

//Database connection class
class ConnectToDatabase { // we create a static method and variable in the class to have access to both of these variables without instantiating an object in the class. This way, anytime the connection can be made or closed whenever, and read data from app.config
    private static Connection conn; //NEEDS to be static so that it can be accessed by the getConnection method a static method. 

    public static Connection getConnection() throws Exception {//can just use a throws without a catch bc don't need to catch this exception if it fails to connect in this class bc every class/fn that uses it already catches this exception
        if (conn != null && !conn.isClosed()) return conn; //if we are already connected to the database, do nothing but return / keep the existing connection

        Properties props = new Properties(); // use properties to keep data secure but use properties so password and everything stays out of code. read data from the app.config, use that data to CONNECT to database
        try (FileInputStream fis = new FileInputStream("app.config")) { //read the file and load the input stream right away for app.config file
            props.load(fis); //load the file and attempt to read the data and establish and return a connection object
        }
        // variables to connect to database
        String databaseURL = props.getProperty("url");
        String databaseUserName = props.getProperty("user");
        String databasePassword = props.getProperty("password");

        conn = DriverManager.getConnection(databaseURL, databaseUserName, databasePassword); //just like we learned in the book, get the connection first with Driver Manager, and return the valid connection so it can be used in other functions
        return conn; //return the connection object, if it doesn't happen successfully, other fn/class always catch exception anyway
    }
}

//learning oop YAY! organizing into separate classes so the GUI won't be a functional mess, everything will be organized, connection class and all functions for database in one class
class DatabaseManager { //instead of separate classes for each file manager table, we use one class for organization, for all tables, all functions will come from this class and will be called from the front end
	
	// ***** DEPARTMENT FUNCTIONS :D *******
	public boolean addDepartment(String departmentName) throws SQLException{ //if we don't catch the exception ourselves, we need the throws SQL Exception, so Java will catch it for us, to be safe I caught it myself and included the throws SQLException
		//adding a department, but the id gets automatically added, we don't need to add id, bc the database takes care of that
		String callProcedure = "{CALL add_department(?)}"; //to change to a stored procedure you code the procedure in SQL workbench, then you just call it and use callable statement instead of the other statements, prepared statement switch to a stored procedure
        //String SqlStatement = "INSERT INTO department (deptname) VALUES (?)"; //SQL statement will change to stored procedure later, () in parentheses is field name in SQL table, the ?? are the parameters that come from java to put into the field in the database
        //try these steps, if it works stay in the try, if errors switch to catch but will not crash the system
        try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity
        		CallableStatement stmt = conn.prepareCall(callProcedure)) { // parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java
            stmt.setString(1, departmentName); //number 1 means it is the first ?/parameter to be inserted
            int numberOfRowsAffectedBySQLStatement = stmt.executeUpdate(); //returns the number of rows changed by SQL statement, if it's positive the insert was successful, if negative unsuccessful
            boolean insertSuccessful = false; //flag variable to return whether or not insert was successful
            if (numberOfRowsAffectedBySQLStatement > 0) {
            	insertSuccessful = true;
            }
            return insertSuccessful;
        } catch (Exception e) { //catch the exception
	        e.printStackTrace(); // to show a detailed description for debugging
	        System.err.println("Error loading departments from database."); // display necessary error for user
	        return false; //in the catch block insert will always be false
	    }
    }
	
	public int getLastDepartmentId() throws SQLException{
	    String SqlStatement = "SELECT deptid FROM department ORDER BY deptid DESC LIMIT 1"; //this gets the last department ID so I can fill the GUI on the front end, might not work bc in React, things in the Database take time to happen will look to alternative fixes if this doesn't work
	    try (Connection conn = ConnectToDatabase.getConnection();//using connect to database class for simplicity, make the connection to connect to the database
	    	// note statement instead of prepared statement is okay here bc there is NO user input, to prevent hackers, whenever there is user input in an SQL query we NEED to do PREPARED STATEMENT not statement, 
	    	//bc prepared statement does not pass whole string sql query to DB, instead the names/user input are sent separately from the whole query statement string, this way we can check before the user just automatically does SQL injection and deletes the database
	         Statement stmt = conn.createStatement(); // parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement
	         ResultSet result = stmt.executeQuery(SqlStatement)) { //get the result set like the JSON the query returns, everything the query returns

	        if (result.next()) { //if there is another line, or more rows, keep reading, if not stop at one, this should only execute once because the query specifies limit one, meaning only one row will be retrieved with the highest id
	            return result.getInt("deptid");
	        }
	        else {
	            // departments table is empty
	            System.out.println("No departments found in database.");
	            return 0; // 0 would be the last department id, bc in GUI add one to it to display which department comes next 
	        }
	    }  
	    catch (Exception e) { //catch the exception
	        e.printStackTrace(); // to show a detailed description for debugging
	        System.err.println("Error loading departments from database."); // display necessary error for user
	        return -1; // return -1, meaning problems in the try block
	    }
	}
	
	public Department getDepartment(int id) throws SQLException {//throws clause to throw SQL exception
	    String SqlStatement = "SELECT * FROM department WHERE deptid = ?"; //again, ? placeholders for parameters, this time select only rows with certain department ID, since dept ID is primary key, there will be only one row returned if found, null if not found
	    
	    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
	         PreparedStatement stmt = conn.prepareStatement(SqlStatement)) {// parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement
	    	//prepared statement (NOT Statement only) + user input = SECURE 
	        stmt.setInt(1, id);//number 1 means it is the first ?/parameter to be putting ID
	        ResultSet result = stmt.executeQuery(); //get the result set like the JSON the query returns, everything the query returns

	        if (result.next()) {//if there is another line, or more rows, keep reading, if not stop at one, this should only execute once because the query specifies limit one, meaning only one row will be retrieved with the highest id
	            int deptId = result.getInt("deptid"); //retrieve department ID
	            String name = result.getString("deptname");//retrieve department name these will be used to create our department object, department object to then use on the GUI to display data, before we would use the file to do this, now we use the database
	            Department departmentToDisplay = new Department(deptId, name); //instantiate our department object using data from database
	            
	            return departmentToDisplay; //return the department necessary
	        } else { //if there are no rows in result.next(), then return null, no object was found
	            return null; // object not found
	        }
	    }
	    catch (Exception e) { //catch the exception
	        e.printStackTrace(); // to show a detailed description for debugging
	        System.err.println("Error loading departments from database."); // display necessary error for user
	        return null; //in the catch block object will never be found
	    }
	}
	
	//update department function, identical to file manager one but now in databse logic instead of files
	public boolean updateDepartment(int id, String newName) throws SQLException {//if we don't catch the exception ourselves, we need the throws SQL Exception, so Java will catch it for us, to be safe I caught it myself and included the throws SQLException
	    String SqlStatement = "UPDATE department SET deptname = ? WHERE deptid = ?"; //update the department name with the new department name ONLY on the row with the specified department id in the where clause, set is to change the name

	    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
	         PreparedStatement stmt = conn.prepareStatement(SqlStatement)) {// parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement

	        stmt.setString(1, newName); // first parameter, first ? insert the new name
	        stmt.setInt(2, id);         // second parameter, second ? only on rows/records where the id matches the id in the system, specified by the user that the user wants to update
	        
	        int numberOfRowsAffectedBySQLStatement = stmt.executeUpdate(); //returns the number of rows changed by SQL statement, if it's positive the insert was successful, if negative unsuccessful
            boolean updateSuccessful = false; //flag variable to return whether or not update was successful
            if (numberOfRowsAffectedBySQLStatement > 0) {
            	updateSuccessful = true;
            }
	       
	        return updateSuccessful;

	    } catch (Exception e) {//catch exception
	        e.printStackTrace();// to show a detailed description for debugging
	        System.err.println("Error updating department."); //error message for the user, or for us to see a simple version of what happened
	        return false;//in the catch block update will always be false because we couldn't finish try, or possibly even connect to the database 
	    }
	       
	}
	
	public ArrayList<String> getAllDepartmentNames() throws SQLException {
		ArrayList<String> departmentNames = new ArrayList<>(); //using an arraylist for greater retrieval from the GUI, easy way to use data
	    String SqlStatement = "SELECT deptname FROM department"; //select all rows from the department table but only the department name column and load it into an array list
	    //okay to use statement not prepared statement bc there is no user input in SQL query
	    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
	         Statement stmt = conn.createStatement(); // less secure, but NO user input in query so okay and safe, parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement
	         ResultSet result = stmt.executeQuery(SqlStatement)) { //get the result similar to JSON from the DB

	        while (result.next()) {//need a while loop not an if to get all department names, not just one, in the insert, etc, only need if for one but now need a while loop
	            departmentNames.add(result.getString("deptname")); //add the names to our array list
	        }
	    } catch (Exception e) {//catch exception
	        e.printStackTrace();// to show a detailed description for debugging
	        System.err.println("Error updating department."); //error message for the user, or for us to see a simple version of what happened
	        return null;//in the catch block update will always be null because we couldn't finish try, or possibly even connect to the database 
	    }

	    return departmentNames; //return the names or null if not found
	}
	
	// ***** STUDENT FUNCTIONS :) *******

	public boolean addStudent(String studentfirstName, String studentlastName, String studentAddress, String studentCity, String studentState, String studentZip) throws SQLException{ //if we don't catch the exception ourselves, we need the throws SQL Exception, so Java will catch it for us, to be safe I caught it myself and included the throws SQLException
		//adding a department, but the id gets automatically added, we don't need to add id, bc the database takes care of that
        String SqlStatement = "INSERT INTO student (firstname, lastname, address, city, state, zip) VALUES (?, ?, ?, ?, ?, ?)"; //SQL statement will change to stored procedure later, () in parentheses is field name in SQL table, the ?? are the parameters that come from java to put into the field in the database
        //try these steps, if it works stay in the try, if errors switch to catch but will not crash the system
        try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity
             PreparedStatement stmt = conn.prepareStatement(SqlStatement)) { // parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java
            stmt.setString(1, studentfirstName); //number 1 means it is the first ?/parameter to be inserted
            stmt.setString(2, studentlastName); //number 1 means it is the first ?/parameter to be inserted
            stmt.setString(3, studentAddress); //number 2 means it is the second ?/parameter to be inserted
            stmt.setString(4, studentCity); //number 3 means it is the third ?/parameter to be inserted
            stmt.setString(5, studentState); //number 4 means it is the fourth ?/parameter to be inserted
            stmt.setString(6, studentZip); //number 5 means it is the fifth ?/parameter to be inserted
            int numberOfRowsAffectedBySQLStatement = stmt.executeUpdate(); //returns the number of rows changed by SQL statement, if it's positive the insert was successful, if negative unsuccessful
            boolean insertSuccessful = false; //flag variable to return whether or not insert was successful
            if (numberOfRowsAffectedBySQLStatement > 0) {
            	insertSuccessful = true;
            }
            return insertSuccessful;
        } catch (Exception e) { //catch the exception
	        e.printStackTrace(); // to show a detailed description for debugging
	        System.err.println("Error loading students from database."); // display necessary error for user
	        return false; //in the catch block insert will always be false
	    }
    }
	
	public int getLastStudentId() throws SQLException{
	    String SqlStatement = "SELECT studentid FROM student ORDER BY studentid DESC LIMIT 1"; //this gets the last department ID so I can fill the GUI on the front end, might not work bc in React, things in the Database take time to happen will look to alternative fixes if this doesn't work
	    try (Connection conn = ConnectToDatabase.getConnection();//using connect to database class for simplicity, make the connection to connect to the database
	    	// note statement instead of prepared statement is okay here bc there is NO user input, to prevent hackers, whenever there is user input in an SQL query we NEED to do PREPARED STATEMENT not statement, 
	    	//bc prepared statement does not pass whole string sql query to DB, instead the names/user input are sent separately from the whole query statement string, this way we can check before the user just automatically does SQL injection and deletes the database
	         Statement stmt = conn.createStatement(); // parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement
	         ResultSet result = stmt.executeQuery(SqlStatement)) { //get the result set like the JSON the query returns, everything the query returns

	        if (result.next()) { //if there is another line, or more rows, keep reading, if not stop at one, this should only execute once because the query specifies limit one, meaning only one row will be retrieved with the highest id
	            return result.getInt("studentid");
	        }
	        else {
	            // departments table is empty
	            System.out.println("No students found in database.");
	            return 0; // 0 would be the last department id, bc in GUI add one to it to display which department comes next 
	        }
	    }  
	    catch (Exception e) { //catch the exception
	        e.printStackTrace(); // to show a detailed description for debugging
	        System.err.println("Error loading students from database."); // display necessary error for user
	        return -1; // return -1, meaning problems in the try block
	    }
	}
	
	public Student getStudent(int id) throws SQLException {//throws clause to throw SQL exception
	    String SqlStatement = "SELECT * FROM student WHERE studentid = ?"; //again, ? placeholders for parameters, this time select only rows with certain department ID, since dept ID is primary key, there will be only one row returned if found, null if not found
	    
	    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
	         PreparedStatement stmt = conn.prepareStatement(SqlStatement)) {// parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement
	    	//prepared statement (NOT Statement only) + user input = SECURE 
	        stmt.setInt(1, id);//number 1 means it is the first ?/parameter to be putting ID
	        ResultSet result = stmt.executeQuery(); //get the result set like the JSON the query returns, everything the query returns

	        if (result.next()) {//if there is another line, or more rows, keep reading, if not stop at one, this should only execute once because the query specifies limit one, meaning only one row will be retrieved with the highest id
	            int studentId = result.getInt("studentId"); //retrieve department ID
	            String firstname = result.getString("firstname");//retrieve department name these will be used to create our department object, department object to then use on the GUI to display data, before we would use the file to do this, now we use the database
	            String lastname = result.getString("lastname");//retrieve department name these will be used to create our department object, department object to then use on the GUI to display data, before we would use the file to do this, now we use the database
	            String address = result.getString("address");//retrieve department name these will be used to create our department object, department object to then use on the GUI to display data, before we would use the file to do this, now we use the database
	            String city = result.getString("city");//retrieve department name these will be used to create our department object, department object to then use on the GUI to display data, before we would use the file to do this, now we use the database
	            String state = result.getString("state");//retrieve department name these will be used to create our department object, department object to then use on the GUI to display data, before we would use the file to do this, now we use the database
	            String zip = result.getString("zip");//retrieve department name these will be used to create our department object, department object to then use on the GUI to display data, before we would use the file to do this, now we use the database
	            Student studentToDisplay = new Student(studentId,firstname, lastname, address,city,state,zip); //instantiate our department object using data from database
	            
	            return studentToDisplay; //return the department necessary
	        } else { //if there are no rows in result.next(), then return null, no object was found
	            return null; // object not found
	        }
	    }
	    catch (Exception e) { //catch the exception
	        e.printStackTrace(); // to show a detailed description for debugging
	        System.err.println("Error loading departments from database."); // display necessary error for user
	        return null; //in the catch block object will never be found
	    }
	}
	
	//update department function, identical to file manager one but now in databse logic instead of files
		public boolean updateStudent(int id, String newfirstname, String newlastname, String newaddress, String city, String state, String zip)  throws SQLException {//if we don't catch the exception ourselves, we need the throws SQL Exception, so Java will catch it for us, to be safe I caught it myself and included the throws SQLException
		    String SqlStatement = "UPDATE student SET firstname = ?, lastname = ?, address = ?, city = ?, state = ?, zip = ?  WHERE studentid = ?"; //update the department name with the new department name ONLY on the row with the specified department id in the where clause, set is to change the name

		    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
		         PreparedStatement stmt = conn.prepareStatement(SqlStatement)) {// parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement

		        stmt.setString(1, newfirstname); // first parameter, first ? insert the new name
		        stmt.setString(2, newlastname);         // second parameter, second ? only on rows/records where the id matches the id in the system, specified by the user that the user wants to update
		        stmt.setString(3, newaddress); // first parameter, first ? insert the new name
		        stmt.setString(4, city);         // second parameter, second ? only on rows/records where the id matches the id in the system, specified by the user that the user wants to update
		        stmt.setString(5, state); // first parameter, first ? insert the new name
		        stmt.setString(6, zip); // first parameter, first ? insert the new name
		        stmt.setInt(7, id);         // second parameter, second ? only on rows/records where the id matches the id in the system, specified by the user that the user wants to update

		        int numberOfRowsAffectedBySQLStatement = stmt.executeUpdate(); //returns the number of rows changed by SQL statement, if it's positive the insert was successful, if negative unsuccessful
	            boolean updateSuccessful = false; //flag variable to return whether or not update was successful
	            if (numberOfRowsAffectedBySQLStatement > 0) {
	            	updateSuccessful = true;
	            }
		       
		        return updateSuccessful;

		    } catch (Exception e) {//catch exception
		        e.printStackTrace();// to show a detailed description for debugging
		        System.err.println("Error updating student."); //error message for the user, or for us to see a simple version of what happened
		        return false;//in the catch block update will always be false because we couldn't finish try, or possibly even connect to the database 
		    }
		       
		}
		
		// ***** INSTRUCTOR FUNCTIONS :D *******
		public boolean addInstructor(String instructorName, String departmentName) throws SQLException{ //if we don't catch the exception ourselves, we need the throws SQL Exception, so Java will catch it for us, to be safe I caught it myself and included the throws SQLException
			//adding a department, but the id gets automatically added, we don't need to add id, bc the database takes care of that
	        String SqlStatement = "INSERT INTO professor (professorname, deptid) VALUES (?, (SELECT deptid FROM department WHERE deptname = ? LIMIT 1))"; //SQL statement will change to stored procedure later, () in parentheses is field name in SQL table, the ?? are the parameters that come from java to put into the field in the database
	        //try these steps, if it works stay in the try, if errors switch to catch but will not crash the system
	        try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity
	             PreparedStatement stmt = conn.prepareStatement(SqlStatement)) { // parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java
	            stmt.setString(1, instructorName); //number 1 means it is the first ?/parameter to be inserted
	            stmt.setString(2, departmentName); //number 1 means it is the first ?/parameter to be inserted
	            int numberOfRowsAffectedBySQLStatement = stmt.executeUpdate(); //returns the number of rows changed by SQL statement, if it's positive the insert was successful, if negative unsuccessful
	            boolean insertSuccessful = false; //flag variable to return whether or not insert was successful
	            if (numberOfRowsAffectedBySQLStatement > 0) {
	            	insertSuccessful = true;
	            }
	            return insertSuccessful;
	        } catch (Exception e) { //catch the exception
		        e.printStackTrace(); // to show a detailed description for debugging
		        System.err.println("Error loading instructors from database."); // display necessary error for user
		        return false; //in the catch block insert will always be false
		    }
	    }
		
		public int getLastInstructorId() throws SQLException{
		    String SqlStatement = "SELECT profid FROM professor ORDER BY profid DESC LIMIT 1"; //this gets the last department ID so I can fill the GUI on the front end, might not work bc in React, things in the Database take time to happen will look to alternative fixes if this doesn't work
		    try (Connection conn = ConnectToDatabase.getConnection();//using connect to database class for simplicity, make the connection to connect to the database
		    	// note statement instead of prepared statement is okay here bc there is NO user input, to prevent hackers, whenever there is user input in an SQL query we NEED to do PREPARED STATEMENT not statement, 
		    	//bc prepared statement does not pass whole string sql query to DB, instead the names/user input are sent separately from the whole query statement string, this way we can check before the user just automatically does SQL injection and deletes the database
		         Statement stmt = conn.createStatement(); // parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement
		         ResultSet result = stmt.executeQuery(SqlStatement)) { //get the result set like the JSON the query returns, everything the query returns

		        if (result.next()) { //if there is another line, or more rows, keep reading, if not stop at one, this should only execute once because the query specifies limit one, meaning only one row will be retrieved with the highest id
		            return result.getInt("profid");
		        }
		        else {
		            // departments table is empty
		            System.out.println("No instructors found in database.");
		            return 0; // 0 would be the last department id, bc in GUI add one to it to display which department comes next 
		        }
		    }  
		    catch (Exception e) { //catch the exception
		        e.printStackTrace(); // to show a detailed description for debugging
		        System.err.println("Error loading instructors from database."); // display necessary error for user
		        return -1; // return -1, meaning problems in the try block
		    }
		}
		
		public Instructor getInstructor(int id) throws SQLException {//throws clause to throw SQL exception
		    String SqlStatement = "SELECT professor.profid, professor.professorname, department.deptname "
		    							+ "FROM professor " 
		    							+ "JOIN department ON professor.deptid = department.deptid "
		    							+ "WHERE profid = ?"; //again, ? placeholders for parameters, this time select only rows with certain department ID, since dept ID is primary key, there will be only one row returned if found, null if not found
		    
		    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
		         PreparedStatement stmt = conn.prepareStatement(SqlStatement)) {// parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement
		    	//prepared statement (NOT Statement only) + user input = SECURE 
		        stmt.setInt(1, id);//number 1 means it is the first ?/parameter to be putting ID
		        ResultSet result = stmt.executeQuery(); //get the result set like the JSON the query returns, everything the query returns

		        if (result.next()) {//if there is another line, or more rows, keep reading, if not stop at one, this should only execute once because the query specifies limit one, meaning only one row will be retrieved with the highest id
		            int deptId = result.getInt("profid"); //retrieve department ID
		            String instructorName = result.getString("professorname");//retrieve department name these will be used to create our department object, department object to then use on the GUI to display data, before we would use the file to do this, now we use the database
		            String departmentName = result.getString("deptname");//retrieve department name these will be used to create our department object, department object to then use on the GUI to display data, before we would use the file to do this, now we use the database
		            Instructor instructorToDisplay = new Instructor(deptId, instructorName, departmentName); //instantiate our department object using data from database
		            
		            return instructorToDisplay; //return the department necessary
		        } else { //if there are no rows in result.next(), then return null, no object was found
		            return null; // object not found
		        }
		    }
		    catch (Exception e) { //catch the exception
		        e.printStackTrace(); // to show a detailed description for debugging
		        System.err.println("Error loading instructors from database."); // display necessary error for user
		        return null; //in the catch block object will never be found
		    }
		}
		
		//update department function, identical to file manager one but now in databse logic instead of files
		public boolean updateInstructor(int instructorId, String newInstructorName, String newDepartmentName) throws SQLException {//if we don't catch the exception ourselves, we need the throws SQL Exception, so Java will catch it for us, to be safe I caught it myself and included the throws SQLException
		    String SqlStatement = "UPDATE professor " //can update without a join using a correlated subquery
					+ "SET professorname = ?, deptid = ( " //correlated subquery using parentheses to get the id from the name
					+ "SELECT deptid FROM department WHERE deptname = ? LIMIT 1 "
					+ ") "
					+ "WHERE profid = ?;"; //again, ? placeholders for parameters, this time select only rows with certain department ID, since dept ID is primary key, there will be only one row returned if found, null if not found

		    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
		         PreparedStatement stmt = conn.prepareStatement(SqlStatement)) {// parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement

		        stmt.setString(1, newInstructorName); // first parameter, first ? insert the new name
		        stmt.setString(2, newDepartmentName); // first parameter, first ? insert the new name
		        stmt.setInt(3, instructorId);         // second parameter, second ? only on rows/records where the id matches the id in the system, specified by the user that the user wants to update
		        
		        int numberOfRowsAffectedBySQLStatement = stmt.executeUpdate(); //returns the number of rows changed by SQL statement, if it's positive the insert was successful, if negative unsuccessful
	            boolean updateSuccessful = false; //flag variable to return whether or not update was successful
	            if (numberOfRowsAffectedBySQLStatement > 0) {
	            	updateSuccessful = true;
	            }
		       
		        return updateSuccessful;

		    } catch (Exception e) {//catch exception
		        e.printStackTrace();// to show a detailed description for debugging
		        System.err.println("Error updating department."); //error message for the user, or for us to see a simple version of what happened
		        return false;//in the catch block update will always be false because we couldn't finish try, or possibly even connect to the database 
		    }
		       
		}
		
		public ArrayList<String> getInstructorNamesByDepartment(String departmentName) throws SQLException { //converted old function to use SQL
			ArrayList<String> filteredInstructors = new ArrayList<>();
		    
		    String SqlStatement =  //join between two tables, table name. field, from whatever two tables you are joining default is an inner join and that suffices here bc we need data to be present in BOTH tables not just one table
		        "SELECT professor.professorname " + //each line of string in java needs quotes or won't compile syntax error without quotes and +
		        "FROM professor " +
		        "JOIN department ON professor.deptid = department.deptid " +
		        "WHERE department.deptname = ?";

		    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
		         PreparedStatement stmt = conn.prepareStatement(SqlStatement)) {// parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement

		        stmt.setString(1, departmentName);

		        ResultSet rs = stmt.executeQuery();//run query 

		        while (rs.next()) {//need a while loop not an if to get all department names, not just one, in the insert, etc, only need if for one but now need a while loop
		            String name = rs.getString("professorname");
		            filteredInstructors.add(name);
		        }

		    } catch (SQLException e) {//catch exceptions
		        e.printStackTrace();
		        System.err.println("Error retrieving instructor names by department.");
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.err.println("Error retrieving instructor names by department.");
		    }

		    return filteredInstructors;
		}
		
		public ArrayList<String> getAllInstructorNames() throws SQLException {
			ArrayList<String> instructorNames = new ArrayList<>(); //using an arraylist for greater retrieval from the GUI, easy way to use data
		    String SqlStatement = "SELECT professorname FROM professor"; //select all rows from the department table but only the department name column and load it into an array list
		    //okay to use statement not prepared statement bc there is no user input in SQL query
		    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
		         Statement stmt = conn.createStatement(); // less secure, but NO user input in query so okay and safe, parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement
		         ResultSet result = stmt.executeQuery(SqlStatement)) { //get the result similar to JSON from the DB

		        while (result.next()) {//need a while loop not an if to get all department names, not just one, in the insert, etc, only need if for one but now need a while loop
		        	instructorNames.add(result.getString("professorname")); //add the names to our array list
		        }
		    } catch (Exception e) {//catch exception
		        e.printStackTrace();// to show a detailed description for debugging
		        System.err.println("Error displaying instructors."); //error message for the user, or for us to see a simple version of what happened
		        return null;//in the catch block update will always be null because we couldn't finish try, or possibly even connect to the database 
		    }

		    return instructorNames; //return the names or null if not found
		}
		
		// ***** COURSE FUNCTIONS :D *******
		
		public boolean addCourse(String courseName, int courseNumber, String courseDepartmentName, String instructructorName) throws SQLException{ //if we don't catch the exception ourselves, we need the throws SQL Exception, so Java will catch it for us, to be safe I caught it myself and included the throws SQLException
			//adding a department, but the id gets automatically added, we don't need to add id, bc the database takes care of that
	        String SqlStatement = "INSERT INTO course "  + 
	        							"(coursetitle, coursenumber, deptid, profid) " + //correlated subquery using parentheses to get the id from the name\n"
	        							
	        							 "VALUES (?, ?, (SELECT deptid FROM department WHERE deptname = ? LIMIT 1), (SELECT profid FROM professor WHERE professorname = ? LIMIT 1))"; //SQL statement will change to stored procedure later, () in parentheses is field name in SQL table, the ?? are the parameters that come from java to put into the field in the database
	        //try these steps, if it works stay in the try, if errors switch to catch but will not crash the system
	        try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity
	             PreparedStatement stmt = conn.prepareStatement(SqlStatement)) { // parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java
	            stmt.setString(1, courseName); //number 1 means it is the first ?/parameter to be inserted
	            stmt.setInt(2, courseNumber); //number 1 means it is the first ?/parameter to be inserted
	            stmt.setString(3, courseDepartmentName); //number 1 means it is the first ?/parameter to be inserted
	            stmt.setString(4, instructructorName); //number 1 means it is the first ?/parameter to be inserted
	            int numberOfRowsAffectedBySQLStatement = stmt.executeUpdate(); //returns the number of rows changed by SQL statement, if it's positive the insert was successful, if negative unsuccessful
	            boolean insertSuccessful = false; //flag variable to return whether or not insert was successful
	            if (numberOfRowsAffectedBySQLStatement > 0) {
	            	insertSuccessful = true;
	            }
	            return insertSuccessful;
	        } catch (Exception e) { //catch the exception
		        e.printStackTrace(); // to show a detailed description for debugging
		        System.err.println("Error loading courses from database."); // display necessary error for user
		        return false; //in the catch block insert will always be false
		    }
	    }
		
		public int getLastCourseId() throws SQLException{
		    String SqlStatement = "SELECT courseid FROM course ORDER BY courseid DESC LIMIT 1"; //this gets the last department ID so I can fill the GUI on the front end, might not work bc in React, things in the Database take time to happen will look to alternative fixes if this doesn't work
		    try (Connection conn = ConnectToDatabase.getConnection();//using connect to database class for simplicity, make the connection to connect to the database
		    	// note statement instead of prepared statement is okay here bc there is NO user input, to prevent hackers, whenever there is user input in an SQL query we NEED to do PREPARED STATEMENT not statement, 
		    	//bc prepared statement does not pass whole string sql query to DB, instead the names/user input are sent separately from the whole query statement string, this way we can check before the user just automatically does SQL injection and deletes the database
		         Statement stmt = conn.createStatement(); // parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement
		         ResultSet result = stmt.executeQuery(SqlStatement)) { //get the result set like the JSON the query returns, everything the query returns

		        if (result.next()) { //if there is another line, or more rows, keep reading, if not stop at one, this should only execute once because the query specifies limit one, meaning only one row will be retrieved with the highest id
		            return result.getInt("courseid");
		        }
		        else {
		            // departments table is empty
		            System.out.println("No courses found in database.");
		            return 0; // 0 would be the last department id, bc in GUI add one to it to display which department comes next 
		        }
		    }  
		    catch (Exception e) { //catch the exception
		        e.printStackTrace(); // to show a detailed description for debugging
		        System.err.println("Error courses instructors from database."); // display necessary error for user
		        return -1; // return -1, meaning problems in the try block
		    }
		}
		
		public Course getCourse(int id) throws SQLException {//throws clause to throw SQL exception 
			//fields for course obj for course GUI private int courseId;
			/*private String courseName;
			private int courseNumber;
			private String department;
			private String instructor;*/
			
		    String SqlStatement = "SELECT course.courseid, course.coursetitle, course.coursenumber, department.deptname, professor.professorname "//YAY first three table join in java, so to get all the fields from three tables we need to think of links, i go to the schema to visualize, we need course, professor, and department, course has no dept id, so we start at course, and professor have prof id, then prof to department to have department id then we get all the fields we need :D
		    							+ "FROM course " 
		    							+ "JOIN professor ON course.profid = professor.profid "
		    							+ "JOIN department ON professor.deptid = department.deptid "
		    							+ "WHERE courseid = ?"; //again, ? placeholders for parameters, this time select only rows with certain department ID, since dept ID is primary key, there will be only one row returned if found, null if not found
		    
		    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
		         PreparedStatement stmt = conn.prepareStatement(SqlStatement)) {// parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement
		    	//prepared statement (NOT Statement only) + user input = SECURE 
		        stmt.setInt(1, id);//number 1 means it is the first ?/parameter to be putting ID
		        ResultSet result = stmt.executeQuery(); //get the result set like the JSON the query returns, everything the query returns

		        if (result.next()) {//if there is another line, or more rows, keep reading, if not stop at one, this should only execute once because the query specifies limit one, meaning only one row will be retrieved with the highest id
		            int courseId = result.getInt("courseid"); //retrieve department ID
		            String courseName = result.getString("coursetitle");//retrieve department name these will be used to create our department object, department object to then use on the GUI to display data, before we would use the file to do this, now we use the database
		            int courseNumber = result.getInt("coursenumber");//retrieve department name these will be used to create our department object, department object to then use on the GUI to display data, before we would use the file to do this, now we use the database
		            String departmentName = result.getString("deptname");//retrieve department name these will be used to create our department object, department object to then use on the GUI to display data, before we would use the file to do this, now we use the database
		            String instructorName = result.getString("professorname");//retrieve department name these will be used to create our department object, department object to then use on the GUI to display data, before we would use the file to do this, now we use the database
		            
		            Course courseToDisplay = new Course(courseId, courseName, courseNumber,departmentName,instructorName); //instantiate our department object using data from database
		            
		            return courseToDisplay; //return the department necessary
		        } else { //if there are no rows in result.next(), then return null, no object was found
		            return null; // object not found
		        }
		    }
		    catch (Exception e) { //catch the exception
		        e.printStackTrace(); // to show a detailed description for debugging
		        System.err.println("Error loading courses from database."); // display necessary error for user
		        return null; //in the catch block object will never be found
		    }
		}
		
		//update course function, identical to file manager one but now in databse logic instead of files
		//String courseName, int courseNumber, String courseDepartmentName, String instructructorName
		public boolean updateCourse(int courseId, String newCourseName, int newCourseNumber, String newDepartmentName, String newInstructorName) throws SQLException {//if we don't catch the exception ourselves, we need the throws SQL Exception, so Java will catch it for us, to be safe I caught it myself and included the throws SQLException
		    String SqlStatement = "UPDATE course " //can update without a join using a correlated subquery
					+ "SET coursetitle = ?, coursenumber = ?, deptid = ( " //correlated subquery using parentheses to get the id from the name
					+ "SELECT deptid FROM department WHERE deptname = ? LIMIT 1 "
					+ ") "
					+ ", profid = ( "
					+ "SELECT profid FROM professor WHERE professorname = ? LIMIT 1 "
					+ ") "
					+ "WHERE courseid = ?;"; //again, ? placeholders for parameters, this time select only rows with certain department ID, since dept ID is primary key, there will be only one row returned if found, null if not found

		    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
		         PreparedStatement stmt = conn.prepareStatement(SqlStatement)) {// parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement

		        stmt.setString(1, newCourseName); // first parameter, first ? insert the new name
		        stmt.setInt(2, newCourseNumber); // first parameter, first ? insert the new name
		        stmt.setString(3, newDepartmentName); // first parameter, first ? insert the new name
		        stmt.setString(4, newInstructorName); // first parameter, first ? insert the new name
		        stmt.setInt(5, courseId);         // second parameter, second ? only on rows/records where the id matches the id in the system, specified by the user that the user wants to update
		        
		        int numberOfRowsAffectedBySQLStatement = stmt.executeUpdate(); //returns the number of rows changed by SQL statement, if it's positive the insert was successful, if negative unsuccessful
	            boolean updateSuccessful = false; //flag variable to return whether or not update was successful
	            if (numberOfRowsAffectedBySQLStatement > 0) {
	            	updateSuccessful = true;
	            }
		       
		        return updateSuccessful;

		    } catch (Exception e) {//catch exception
		        e.printStackTrace();// to show a detailed description for debugging
		        System.err.println("Error updating course."); //error message for the user, or for us to see a simple version of what happened
		        return false;//in the catch block update will always be false because we couldn't finish try, or possibly even connect to the database 
		    }
		       
		}
		
		// ***** ENROLLMENT FUNCTIONS :D *******
		
		public boolean addEnrollment(int sid, int cid, int year, String semester, char grade) throws SQLException{ //if we don't catch the exception ourselves, we need the throws SQL Exception, so Java will catch it for us, to be safe I caught it myself and included the throws SQLException

			//adding a department, but the id gets automatically added, we don't need to add id, bc the database takes care of that
	        String SqlStatement = "INSERT INTO enrollment (studentid, courseid, year, semester, grade) VALUES (?, ?, ?, ?, ?)"; //SQL statement will change to stored procedure later, () in parentheses is field name in SQL table, the ?? are the parameters that come from java to put into the field in the database
	        //try these steps, if it works stay in the try, if errors switch to catch but will not crash the system
	        try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity
	             PreparedStatement stmt = conn.prepareStatement(SqlStatement)) { // parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java
	            stmt.setInt(1, sid); //number 1 means it is the first ?/parameter to be inserted
	            stmt.setInt(2, cid); //number 1 means it is the first ?/parameter to be inserted
	            stmt.setInt(3, year); //number 1 means it is the first ?/parameter to be inserted
	            stmt.setString(4, semester); //number 1 means it is the first ?/parameter to be inserted
	            stmt.setString(5, String.valueOf(grade).toUpperCase()); // needed to convert the string to a char using static class String String.valueOf(grade).toUpperCase()//number 1 means it is the first ?/parameter to be inserted
	            int numberOfRowsAffectedBySQLStatement = stmt.executeUpdate(); //returns the number of rows changed by SQL statement, if it's positive the insert was successful, if negative unsuccessful
	            boolean insertSuccessful = false; //flag variable to return whether or not insert was successful
	            if (numberOfRowsAffectedBySQLStatement > 0) {
	            	insertSuccessful = true;
	            }
	            return insertSuccessful;
	        } catch (SQLIntegrityConstraintViolationException e) {
	            // Exception type to catch duplicate enrollment or bad foreign keys
	            System.err.println("Error: duplicate enrollment. Combo of student ID, course ID, year, and semester must be unique.");
	            return false;

	        } catch (Exception e) { //catch the exception
		        e.printStackTrace(); // to show a detailed description for debugging
		        System.err.println("Error loading courses from database."); // display necessary error for user
		        return false; //in the catch block insert will always be false
		    }
		}
		public int getLastEnrollmentId() throws SQLException{
		    String SqlStatement = "SELECT enrollmentid FROM enrollment ORDER BY enrollmentid DESC LIMIT 1"; //this gets the last department ID so I can fill the GUI on the front end, might not work bc in React, things in the Database take time to happen will look to alternative fixes if this doesn't work
		    try (Connection conn = ConnectToDatabase.getConnection();//using connect to database class for simplicity, make the connection to connect to the database
		    	// note statement instead of prepared statement is okay here bc there is NO user input, to prevent hackers, whenever there is user input in an SQL query we NEED to do PREPARED STATEMENT not statement, 
		    	//bc prepared statement does not pass whole string sql query to DB, instead the names/user input are sent separately from the whole query statement string, this way we can check before the user just automatically does SQL injection and deletes the database
		         Statement stmt = conn.createStatement(); // parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement
		         ResultSet result = stmt.executeQuery(SqlStatement)) { //get the result set like the JSON the query returns, everything the query returns

		        if (result.next()) { //if there is another line, or more rows, keep reading, if not stop at one, this should only execute once because the query specifies limit one, meaning only one row will be retrieved with the highest id
		            return result.getInt("enrollmentid");
		        }
		        else {
		            // departments table is empty
		            System.out.println("No enrollments found in database.");
		            return 0; // 0 would be the last department id, bc in GUI add one to it to display which department comes next 
		        }
		    } catch (SQLIntegrityConstraintViolationException e) {
	            // Exception type to catch duplicate enrollment or bad foreign keys
	            System.err.println("Error: duplicate enrollment. Combo of student ID, course ID, year, and semester must be unique.");
	            return -1;

	        } catch (Exception e) { //catch the exception
		        e.printStackTrace(); // to show a detailed description for debugging
		        System.err.println("Error enrollments instructors from database."); // display necessary error for user
		        return -1; // return -1, meaning problems in the try block
		    }
		}
		
		
		public Enrollment getEnrollment(int sid, int cid, int year, String semester) throws SQLException {
		    String sql = "SELECT * FROM enrollment WHERE studentid = ? AND courseid = ? AND year = ? AND semester = ?"; //SQL statement to validate that the combination of student ID, course ID, semester, and year is unique so no duplicate enrollments are added
		    
		    try (Connection conn = ConnectToDatabase.getConnection();//using connect to database class for simplicity
		         PreparedStatement stmt = conn.prepareStatement(sql)) {// parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java

		    	//set each ? to the appropriate parameter
		        stmt.setInt(1, sid); 
		        stmt.setInt(2, cid);
		        stmt.setInt(3, year);
		        stmt.setString(4, semester);

		        ResultSet rs = stmt.executeQuery(); //get and read the results

		        if (rs.next()) {// if there is data, read it in so we don't create a duplicate enrollment
		            int enrollmentId = rs.getInt("enrollmentid");
		            int studentId = rs.getInt("studentid");
		            int courseId = rs.getInt("courseid");
		            int yr = rs.getInt("year");
		            String sem = rs.getString("semester");
		            String grade = rs.getString("grade");

		            return new Enrollment(enrollmentId, studentId, courseId, yr, sem, 'X');
		        } else {
		            return null;
		        }
		    } catch (Exception e) { //catch the exception
		        e.printStackTrace(); // to show a detailed description for debugging
		        System.err.println("Error loading enrollments from database."); // display necessary error for user
		        return null; //in the catch block insert will always be false
		    }
		}
		
		//update course function, identical to file manager one but now in databse logic instead of files
		//String courseName, int courseNumber, String courseDepartmentName, String instructructorName
		public boolean updateEnrollment(int enrollmentId, int newSid, int newCid, int newYear, String newSemester, char newGrade) throws SQLException {//if we don't catch the exception ourselves, we need the throws SQL Exception, so Java will catch it for us, to be safe I caught it myself and included the throws SQLException
		    String SqlStatement = "UPDATE enrollment " //can update without a join using a correlated subquery
					+ "SET studentid = ?, courseid = ?, year = ?, semester = ? , grade = ? WHERE enrollmentid = ?;"; //again, ? placeholders for parameters, this time select only rows with certain department ID, since dept ID is primary key, there will be only one row returned if found, null if not found

		    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
		         PreparedStatement stmt = conn.prepareStatement(SqlStatement)) {// parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement

		        stmt.setInt(1, newSid); // first parameter, first ? insert the new name
		        stmt.setInt(2, newCid); // first parameter, first ? insert the new name
		        stmt.setInt(3, newYear); // first parameter, first ? insert the new name
		        stmt.setString(4, newSemester); // first parameter, first ? insert the new name
		        stmt.setString(5,  String.valueOf(newGrade).toUpperCase());         // second parameter, second ? only on rows/records where the id matches the id in the system, specified by the user that the user wants to update
		        stmt.setInt(6, enrollmentId);         // second parameter, second ? only on rows/records where the id matches the id in the system, specified by the user that the user wants to update
		        
		        int numberOfRowsAffectedBySQLStatement = stmt.executeUpdate(); //returns the number of rows changed by SQL statement, if it's positive the insert was successful, if negative unsuccessful
	            boolean updateSuccessful = false; //flag variable to return whether or not update was successful
	            if (numberOfRowsAffectedBySQLStatement > 0) {
	            	updateSuccessful = true;
	            }
		       
		        return updateSuccessful;

		    } catch (SQLIntegrityConstraintViolationException e) {
	            // Exception type to catch duplicate enrollment or bad foreign keys
	            System.err.println("Error: duplicate enrollment. Combo of student ID, course ID, year, and semester must be unique.");
	            return false;

	        } catch (Exception e) {//catch exception
		        e.printStackTrace();// to show a detailed description for debugging
		        System.err.println("Error updating enrollment."); //error message for the user, or for us to see a simple version of what happened
		        return false;//in the catch block update will always be false because we couldn't finish try, or possibly even connect to the database 
		    }
		       
		}
		
		public ArrayList<Enrollment> getListOfEnrollmentsWithStudentId(int studentId) throws SQLException { //converted old function to use SQL
			ArrayList<Enrollment> listOfEnrollmentsWithStudentId = new ArrayList<>();
		    
		    String SqlStatement =  //join between two tables, table name. field, from whatever two tables you are joining default is an inner join and that suffices here bc we need data to be present in BOTH tables not just one table
		        "SELECT * " + //each line of string in java needs quotes or won't compile syntax error without quotes and +
		        "FROM enrollment " +
		        "WHERE studentid = ?";

		    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
		         PreparedStatement stmt = conn.prepareStatement(SqlStatement)) {// parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement

		        stmt.setInt(1, studentId);

		        ResultSet rs = stmt.executeQuery();//run query 

		        while (rs.next()) {//need a while loop not an if to get all department names, not just one, in the insert, etc, only need if for one but now need a while loop
		            int eid = rs.getInt("enrollmentid");
		            int sid = rs.getInt("studentid");
		            int cid = rs.getInt("courseid");
		            int year = rs.getInt("year");
		            String semester = rs.getString("semester");
		            char grade = rs.getString("grade").charAt(0);
		            listOfEnrollmentsWithStudentId.add(new Enrollment(eid, sid, cid, year, semester, grade));
		            //int enrollmentId, int studentId, int courseId, int year, String semester, char grade
		        }

		    } catch (SQLIntegrityConstraintViolationException e) {
	            // Exception type to catch duplicate enrollment or bad foreign keys
	            System.err.println("Error: duplicate enrollment. Combo of student ID, course ID, year, and semester must be unique.");
	        } catch (SQLException e) {//catch exceptions
		        e.printStackTrace();
		        System.err.println("Error retrieving enrollments.");
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.err.println("Error retrieving enrollments.");
		    }

		    return listOfEnrollmentsWithStudentId;
		}
		
		public ArrayList<Enrollment> getListOfEnrollmentsWithCourseId(int courseId) throws SQLException { //converted old function to use SQL
			ArrayList<Enrollment> listOfEnrollmentsWithCourseId = new ArrayList<>();
		    
		    String SqlStatement =  //join between two tables, table name. field, from whatever two tables you are joining default is an inner join and that suffices here bc we need data to be present in BOTH tables not just one table
		        "SELECT * " + //each line of string in java needs quotes or won't compile syntax error without quotes and +
		        "FROM enrollment " +
		        "WHERE courseid = ?";

		    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
		         PreparedStatement stmt = conn.prepareStatement(SqlStatement)) {// parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement

		        stmt.setInt(1, courseId);

		        ResultSet rs = stmt.executeQuery();//run query 

		        while (rs.next()) {//need a while loop not an if to get all department names, not just one, in the insert, etc, only need if for one but now need a while loop
		            int eid = rs.getInt("enrollmentid");
		            int sid = rs.getInt("studentid");
		            int cid = rs.getInt("courseid");
		            int year = rs.getInt("year");
		            String semester = rs.getString("semester");
		            char grade = rs.getString("grade").charAt(0);
		            listOfEnrollmentsWithCourseId.add(new Enrollment(eid, sid, cid, year, semester, grade));
		            //int enrollmentId, int studentId, int courseId, int year, String semester, char grade
		        }

		    } catch (SQLIntegrityConstraintViolationException e) {
	            // Exception type to catch duplicate enrollment or bad foreign keys
	            System.err.println("Error: duplicate enrollment. Combo of student ID, course ID, year, and semester must be unique.");
	        } catch (SQLException e) {//catch exceptions
		        e.printStackTrace();
		        System.err.println("Error retrieving enrollments.");
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.err.println("Error retrieving enrollments.");
		    }

		    return listOfEnrollmentsWithCourseId;
		}
		
		//int cid, String semester, int year
		
		public ArrayList<Enrollment> generateReport(int cid, String semester, int year) throws SQLException { //converted old function to use SQL
			ArrayList<Enrollment> listOfEnrollmentsForReport = new ArrayList<>();
		    
		    String SqlStatement =  //join between two tables, table name. field, from whatever two tables you are joining default is an inner join and that suffices here bc we need data to be present in BOTH tables not just one table
		        "SELECT * " + //each line of string in java needs quotes or won't compile syntax error without quotes and +
		        "FROM enrollment " +
		        "WHERE courseid = ? AND semester = ? AND year = ?";

		    try (Connection conn = ConnectToDatabase.getConnection(); //using connect to database class for simplicity, make the connection to connect to the database
		         PreparedStatement stmt = conn.prepareStatement(SqlStatement)) {// parses the SQL and prevents injection from hackers bc this is not done on the front end but in Java, better to do this on the backend and not in the GUI interprets SQL statement

		        stmt.setInt(1, cid);
		        stmt.setString(2, semester);
		        stmt.setInt(3, year);

		        ResultSet rs = stmt.executeQuery();//run query 

		        while (rs.next()) {//need a while loop not an if to get all department names, not just one, in the insert, etc, only need if for one but now need a while loop
		            int eid = rs.getInt("enrollmentid");
		            int sid = rs.getInt("studentid");
		            char grade = rs.getString("grade").charAt(0);
		            listOfEnrollmentsForReport.add(new Enrollment(eid, sid, cid, year, semester, grade));
		            //int enrollmentId, int studentId, int courseId, int year, String semester, char grade
		        }

		    } catch (SQLIntegrityConstraintViolationException e) {
	            // Exception type to catch duplicate enrollment or bad foreign keys
	            System.err.println("Error: duplicate enrollment. Combo of student ID, course ID, year, and semester must be unique.");
	        } catch (SQLException e) {//catch exceptions
		        e.printStackTrace();
		        System.err.println("Error retrieving enrollments.");
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.err.println("Error retrieving enrollments.");
		    }

		    return listOfEnrollmentsForReport;
		}
		
		public boolean dropEnrollment(int sid, int cid, int year, String semester) {
		    String sql = "DELETE FROM enrollment WHERE studentid = ? AND courseid = ? AND year = ? AND semester = ?";

		    try (Connection conn = ConnectToDatabase.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {

		        stmt.setInt(1, sid);
		        stmt.setInt(2, cid);
		        stmt.setInt(3, year);
		        stmt.setString(4, semester);
		        
		        int numberOfRowsDeleted = stmt.executeUpdate(); //returns the number of rows changed by SQL statement, if it's positive the insert was successful, if negative unsuccessful
	            boolean dropSuccessful = false; //flag variable to return whether or not insert was successful
	            if (numberOfRowsDeleted > 0) {
	            	dropSuccessful = true;
	            }
	            return dropSuccessful;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.err.println("Error deleting enrollment.");
		        return false;
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.err.println("Error retrieving enrollments.");
		        return false;
		    }
		}
		
		
		


}



class ZipCode { //used this class as is from the example, need this class and all the fields
    @SerializedName("post code")
    private String postCode;
    private String country;

    @SerializedName("country abbreviation")
    private String countryabbreviation;
    private Place[] places;

    public String getPostCode() { return postCode; }
    public String getCountry() { return country; }
    public String getCountryAbbreviation() { return countryabbreviation; }
    public Place[] getPlaces() { return places; }

    class Place {
        @SerializedName("place name")
        private String placename;
        private String longitude;
        private String state;

        @SerializedName("state abbreviation")
        private String stateabbreviation;
        private String latitude;

        public String getPlaceName() { return placename; }
        public String getLongitude() { return longitude; }
        public String getState() { return state; }
        public String getStateAbbreviation() { return stateabbreviation; }
        public String getLatitude() { return latitude; }
    }
}


//goes with my other classes, not in the GUI, static method will be called from the GUI
class ZipCodeClient {
 public static ZipCode lookupZip(String zipCode) throws IOException {
     String apiUrl = "https://api.zippopotam.us/us/" + zipCode;//concatenate the strings with the zip code variable to get the one they look up
     URL url = new URL(apiUrl);
     HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //open the connection
     connection.setRequestMethod("GET");

     if (connection.getResponseCode() != 200) {//if we get anything other than a valid response of 200, return this error message on the alert as an exception
         throw new IOException("Zip code not found or invalid.");
     }
     
     //variables to read each line similar to file reading, but we are reading the JSON response (javascript is easier but this works too! :D)

     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
     StringBuilder response = new StringBuilder();
     String line;
     
     //keep reading the json input

     while ((line = reader.readLine()) != null) {
         response.append(line);
     }
     reader.close();

     Gson gson = new Gson();
     
     //return the JSON response to get what we need
     return gson.fromJson(response.toString(), ZipCode.class);
 }
}


public class CS136Final extends Application 
{
	//private field variables so that these fields can be accessible from other functions within the class and can be changed like event handlers
	private BorderPane borderPane; //student fields
	private TextField studentIdField;
	private TextField studentFirstNameField;
	private TextField studentLastNameField;
	private TextField studentAddressField;
	private TextField studentCityField;
	private TextField studentStateField;
	private ChoiceBox<String> stateChoiceBox;
	private TextField studentZipCodeField;
	
	private TextField instructorIdField;
	private TextField departmentField;
	
	private TextField courseIdField; // course fields
	private TextField courseNameField;
	private TextField courseNumberField;
	private TextField instructorNameField;
	private ChoiceBox<String> instructorNameChoiceBox;
	private ChoiceBox<String> departmentChoiceBox;
	private TextField courseDepartmentField;
	
	private TextField studentFullNameField; //enrollment fields
	private TextField enrollmentIdField;
	
	private TextField departmentIdField;
	private TextField departmentNameField;
	
	private ChoiceBox<String> enrollmentSemesterChoiceBox;
	private ChoiceBox<String> enrollmentYearChoiceBox;
	
	// grade choice box
	private ChoiceBox<Character> gradeByStudentChoiceBox;
	private ChoiceBox<Character> gradeByCourseChoiceBox;
	
	//create reference variables for objects, will use a dependency injection to instantiate them, I believe that's what it's called? That's what my resource said to do
	//private StudentFileManager studentFileManager;
	//private CourseFileManager courseFileManager;
	//private EnrollmentFileManager enrollmentFileManager;
	//private InstructorFileManager instructorFileManager;
	//private DepartmentFileManager departmentFileManager;
	private DatabaseManager databaseManager;
	
	
	
	

	


	
   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }
   
   
   
   @Override
   public void start(Stage primaryStage)
   {
	   
	   try {
		   //instantiate objects and assign it to the appropriate methods and use a dependency injection
           //studentFileManager = new StudentFileManager("student.txt");
           //courseFileManager = new CourseFileManager("course.txt");
           //enrollmentFileManager = new EnrollmentFileManager("enrollment.txt");
           //instructorFileManager = new InstructorFileManager("instructor.txt");
           databaseManager = new DatabaseManager();
           //departmentFileManager = new DepartmentFileManager("department.txt");
       } catch (Exception e) {
           System.err.println("Error initializing database manager: " + e.getMessage());
           // Handle the exception appropriately (e.g., show an error dialog)
           return;
       }
      // Constants for the scene dimensions, set these as constants so we can easily change the dimensions of the stage wherever the height and width come up in one place
      final double WIDTH = 800.0, HEIGHT = 500.0;
      
      // Create the menu bar. similar to a nav in html, basically where users navigate to different pages of the application
      MenuBar menuBar = new MenuBar();
 
      // Create the File menu.
      Menu fileMenu = new Menu("File"); //add home menu item
      MenuItem exitItem = new MenuItem("Exit");
      fileMenu.getItems().add(exitItem);
      
      
      
      // Register an event handler for the exit item. simple event handler only needs access to final variables, other ones may not be so simple
      exitItem.setOnAction(event ->
      {
         primaryStage.close();
      });
   
      //menu for students
      Menu studentsMenu = new Menu("Students");
      MenuItem viewStudentItem = new MenuItem("View Student");
      MenuItem addStudentItem = new MenuItem("Add Student");
      MenuItem editStudentItem = new MenuItem("Edit Student");
      studentsMenu.getItems().addAll(viewStudentItem, addStudentItem, editStudentItem);
      
      // create a class to register the student event handler items with
      class ViewStudentClickHandler implements EventHandler<ActionEvent> {
	      @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  //create a grid so that items can be in multiple rows and columns
	    	  //necessary labels and fields in their appropriate positions on the grid
	    	  GridPane gridpane = new GridPane();
	    	  
	    	  Label studentIdLabel = new Label("Student ID"); // create a label
	    	  gridpane.add(studentIdLabel, 0, 0); // add it to the grid
	    	  
	    	  studentIdField = new TextField(); //similarly with field and buttons
	    	  gridpane.add(studentIdField, 1, 0);
	    	  
	    	  Button searchStudentIdButton = new Button("Search");
	    	  gridpane.add(searchStudentIdButton, 2, 0);
	    	  
	    	  Label studentFirstNameLabel = new Label("First Name");
	    	  gridpane.add(studentFirstNameLabel, 0, 1);
	    	  
	    	  studentFirstNameField = new TextField();
	    	  studentFirstNameField.setEditable(false); // user can't edit these fields
	    	  gridpane.add(studentFirstNameField, 1, 1);
	    	  
	    	  Label studentLastNameLabel = new Label("Last Name");
	    	  gridpane.add(studentLastNameLabel, 0, 2);
	    	  
	    	  studentLastNameField = new TextField();
	    	  studentLastNameField.setEditable(false);
	    	  gridpane.add(studentLastNameField, 1, 2);
	    	  
	    	  Label studentAddressLabel = new Label("Address");
	    	  gridpane.add(studentAddressLabel, 0, 3);
	    	  
	    	  studentAddressField = new TextField();
	    	  studentAddressField.setEditable(false);
	    	  gridpane.add(studentAddressField, 1, 3);
	    	  
	    	  Label studentCityLabel = new Label("City");
	    	  gridpane.add(studentCityLabel, 0, 4);
	    	  
	    	  studentCityField = new TextField();
	    	  studentCityField.setEditable(false);
	    	  gridpane.add(studentCityField, 1, 4);
	    	  
	    	  Label studentStateLabel = new Label("State");
	    	  gridpane.add(studentStateLabel, 0, 5);
	    	  
	    	  studentStateField = new TextField();
	    	  studentStateField.setEditable(false);
	    	  gridpane.add(studentStateField, 1, 5);
	  
	    	  Label studentZipCodeLabel = new Label("Zip Code");
	    	  gridpane.add(studentZipCodeLabel, 0, 6);
	    	  
	    	  studentZipCodeField = new TextField();
	    	  studentZipCodeField.setEditable(false);
	    	  gridpane.add(studentZipCodeField, 1, 6);
	    	  
	    	  gridpane.setHgap(10); // give horizontal spacing between items on the grid, also vertical spacing, and padding which is space between grid and stage
	    	  gridpane.setVgap(10);
	    	  gridpane.setPadding(new Insets(10));
	    	  
	    	  
	    	  
	          
	          borderPane.setCenter(gridpane); // add the grid to the center of the border pane (top has the menu)
	          
	          gridpane.setAlignment(Pos.CENTER); // position the grid in the center
	                   
	          // Update the existing scene with the new center, I needed to look this up but basically you can't just create multiple scenes, you need to replace the existing scene using this syntax
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class ViewStudentSearchBtnClickHandler implements EventHandler<ActionEvent> { //searches for student ID if found displays student info in respective fields
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  //convert field info to a string variable so we can work with it like we did in console application
	    	    	  String studentIdString = studentIdField.getText();
	    	    	  int studentId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  //if student ID field is blank, show an alert with an error message
	    	    	  if (studentIdString.equals( "")) {
	    	    		  studentFirstNameField.setText("");
	    	    		  studentLastNameField.setText("");
	    	    		  studentAddressField.setText("");
	    	    		  studentCityField.setText("");
	    	    		  studentStateField.setText("");
	    	    		  studentZipCodeField.setText("");
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Student ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  
	    	    	  else  {
	    	    		  try {//using a try catch to gracefully catch and correct errors
	    	    			  //if there is trouble changing the student ID to an integer, display an error message and clear all fields below the id
	    	    			  studentId = Integer.parseInt(studentIdString);
	    	    			  Student studentToDisplay = databaseManager.getStudent(studentId);
	    	    	    	  
	    	    	    	  if (studentToDisplay != null) {
	    	    	    		  studentFirstNameField.setText(studentToDisplay.getStudentFirstName());
	    	    	    		  studentLastNameField.setText(studentToDisplay.getStudentLastName());
	    	    	    		  studentAddressField.setText(studentToDisplay.getStudentAddress());
	    	    	    		  studentCityField.setText(studentToDisplay.getStudentCity());
	    	    	    		  studentStateField.setText(studentToDisplay.getStudentState());
	    	    	    		  studentZipCodeField.setText(studentToDisplay.getStudentZip());
	    	    	    	  } else {
	    	    	    		  studentFirstNameField.setText("");
	    	    	    		  studentLastNameField.setText("");
	    	    	    		  studentAddressField.setText("");
	    	    	    		  studentCityField.setText("");
	    	    	    		  studentStateField.setText("");
	    	    	    		  studentZipCodeField.setText("");
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("Student ID not found.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    		  
	    	    	    	  }
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {//clear all fields if trouble converting to an integer and display an error alert message
	    	    			  studentFirstNameField.setText("");
    	    	    		  studentLastNameField.setText("");
    	    	    		  studentAddressField.setText("");
    	    	    		  studentCityField.setText("");
    	    	    		  studentStateField.setText("");
    	    	    		  studentZipCodeField.setText("");
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Student ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {//catch the exception for any unknown errors that can occur when the program runs
	    	    			  studentFirstNameField.setText("");
    	    	    		  studentLastNameField.setText("");
    	    	    		  studentAddressField.setText("");
    	    	    		  studentCityField.setText("");
    	    	    		  studentStateField.setText("");
    	    	    		  studentZipCodeField.setText("");
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for searching the student ID, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          searchStudentIdButton.setOnAction(new ViewStudentSearchBtnClickHandler());
	      }
      }
      
      // Register the event handler for viewing the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
      viewStudentItem.setOnAction(new ViewStudentClickHandler());

      
      class AddStudentClickHandler implements EventHandler<ActionEvent> { // everything is similar with addstudent handler as it is to view student except for choice box
    	  private boolean zipValidated = false;
    	  private String lastValidatedZip = "";

    	  
	      @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  GridPane gridpane = new GridPane();
	    	  Label studentLabel = new Label("New Student Information");
	    	  gridpane.add(studentLabel, 1, 0); // Column 1, Row 0
	    	  
	    	  Label studentIdLabel = new Label("Student ID");
	    	  gridpane.add(studentIdLabel, 0, 1);
	    	  
	    	  studentIdField = new TextField();
	    	  
	    	  String studentId  = null;
	    	  try {
	    		  studentId = Integer.toString(databaseManager.getLastStudentId() + 1);
	    	  } catch (SQLException sqlException) {
	    		  	sqlException.printStackTrace();
			        System.err.println("Error loading student ID from database.");
			     // Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading student ID from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  } catch (Exception exception) {
	    		// Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading student ID from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  }
	    	  
	    	  studentIdField.setText(studentId); 
	    	  studentIdField.setEditable(false);
	    	  
	    	  gridpane.add(studentIdField, 1, 1);
	    	  
	    	  Label studentFirstNameLabel = new Label("First Name");
	    	  gridpane.add(studentFirstNameLabel, 0, 2);
	    	  
	    	  studentFirstNameField = new TextField();
	    	  gridpane.add(studentFirstNameField, 1, 2);
	    	  
	    	  Label studentLastNameLabel = new Label("Last Name");
	    	  gridpane.add(studentLastNameLabel, 0, 3);
	    	  
	    	  studentLastNameField = new TextField();
	    	  gridpane.add(studentLastNameField, 1, 3);
	    	  
	    	  Label studentAddressLabel = new Label("Address");
	    	  gridpane.add(studentAddressLabel, 0, 4);
	    	  
	    	  studentAddressField = new TextField();
	    	  gridpane.add(studentAddressField, 1, 4);
	    	  
	    	  Label studentCityLabel = new Label("City");
	    	  gridpane.add(studentCityLabel, 0, 5);
	    	  
	    	  studentCityField = new TextField();
	    	  
	    	  studentCityField.setEditable(false); // Make non-editable
	    	  studentCityField.setVisible(false);  // Hide initially
	    	  
	    	  gridpane.add(studentCityField, 1, 5);
	    	  
	    	  Label studentStateLabel = new Label("State");
	    	  gridpane.add(studentStateLabel, 2, 5);
	    	  
	    	  studentStateField = new TextField(); //used videos for reference, but this is the same as a combo box in the book, but with a check mark to the left of selected items in the list
	    	  
	    	  studentStateField.setPrefWidth(50);
	    	  
	    	  studentStateField.setEditable(false); // Make non-editable
	    	  studentStateField.setVisible(false);    // Hide initially
	    	 
	    	  gridpane.add(studentStateField, 3, 5);
	    	  
	    	  Label studentZipCodeLabel = new Label("Zip Code");
	    	  gridpane.add(studentZipCodeLabel, 0, 6);
	    	  
	    	  studentZipCodeField = new TextField();
	    	  gridpane.add(studentZipCodeField, 1, 6);
	    	  
	    	  Button searchZipButton = new Button("Search");
	    	  gridpane.add(searchZipButton, 2, 6);  // Next to the zip code field
	    	  
	    	  Button createStudentButton = new Button("Create Student");
	    	  
	    	  createStudentButton.setDisable(true);
	    	  
	    	  gridpane.add(createStudentButton, 1, 7);
	    	  
	    	  Label badge = new Label("Search for zip code first");
	    	  badge.getStyleClass().add("badge"); // add the css class
	    	  badge.setVisible(true);             // initially badge will read this
	    	  
	    	  gridpane.add(badge, 1, 8);
	    	  
	    	  searchZipButton.setOnAction(e -> { //lambda event handler (arrow fn in JS)
	    		    String zip = studentZipCodeField.getText(); //get the zip the user enters after clicking the search Zip button

	    		    if (zip.equals("")) { //if the zip is blank upon clicking, user cannot create student
	    		    	createStudentButton.setDisable(true);
	    		    	studentStateField.setEditable(false); // Make non-editable
	    		    	studentStateField.setVisible(false);    // Hide initially
	    		    	studentCityField.setEditable(false); // Make non-editable
	    		    	studentCityField.setVisible(false);  // Hide initially
	    		    	badge.setText("Zip code cannot be blank");
	    		    	badge.setVisible(true);
	    		    	
	    		    	// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Zip code cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    		        return;
	    		    }

	    		    try {
	    		    	//instantiate a new ZipCodeClient object and call the funciton lookupZip of the zip code, so it reads the file and returns the object with the appropriate response
	    		        ZipCode zipInfo = new ZipCodeClient().lookupZip(zip);
	    		        String city = zipInfo.getPlaces()[0].getPlaceName(); //read through the json response study the json object response to get the desired data
	    		        String state = zipInfo.getPlaces()[0].getStateAbbreviation();

	    		        //assign our returned values from JSON response to the city and state fields
	    		        studentCityField.setText(city);
	    		        studentStateField.setText(state);

	    		        //let the user see the fields with data and create a student
	    		        studentCityField.setVisible(true);
	    		        studentStateField.setVisible(true);
	    		        createStudentButton.setDisable(false);
	    		        badge.setVisible(false);                // hide the badge
	    		        
	    		        zipValidated = true;
	    		        lastValidatedZip = studentZipCodeField.getText();

	    		    } catch (IOException ex) {
	    		    	
	    		    	//if there is any input output exception (like with reading the JSON response similar to reading the files), then don't show fields and don't let user create the student, remember we are throwing an exception of IO if response returned is not 200, so if it is not found this error will show too
	    		    	createStudentButton.setDisable(true);
	    		    	studentStateField.setEditable(false); // Make non-editable
	    		    	studentStateField.setVisible(false);    // Hide initially
	    		    	studentCityField.setEditable(false); // Make non-editable
	    		    	studentCityField.setVisible(false);  // Hide initially
	    		    	badge.setText("Zip code not found.");
	    		    	badge.setVisible(true);
	    		    	// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Zip code not found.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();

	    		    } catch (Exception ex) {//if any other exception happens like not being able to reach the API show this error message
	    		    	createStudentButton.setDisable(true);
	    		    	studentStateField.setEditable(false); // Make non-editable
	    		    	studentStateField.setVisible(false);    // Hide initially
	    		    	studentCityField.setEditable(false); // Make non-editable
	    		    	studentCityField.setVisible(false);  // Hide initially
	    		    	badge.setText("Unexpected error. Try again and contact IT.");
	    		    	badge.setVisible(true);
	    		    	// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Unexpected error. Please try again.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	            

	    		    }
	    		});
	    	  
	    	  //listener to identify when the text of the zip code changes, as in if it ever changes, not having to do with the button being clicked 
	    	  studentZipCodeField.textProperty().addListener((obs, oldText, newText) -> { //obs is not used, similar to _ in javascript
	    		    if (!newText.equals(lastValidatedZip)) {
	    		        zipValidated = false;
	    		        createStudentButton.setDisable(true);
	    		        badge.setText("Zip code changed.\nPlease re-validate.");
	    		        badge.setVisible(true);
	    		    }
	    		});

	    	  
	    	  gridpane.setHgap(10);
	    	  gridpane.setVgap(10);
	    	  gridpane.setPadding(new Insets(10));
	    	  
	    	  
	    	  
	          
	          borderPane.setCenter(gridpane);
	          
	          gridpane.setAlignment(Pos.CENTER);
	                   
	          // Update the existing scene with the new center
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class createStudentBtnClickHandler implements EventHandler<ActionEvent> { //class to create a student when the user clicks the create student button
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  //string variables that are converted from field inputs, we cannot work with field inputs but we can work with Strings in the console app
	    	    	  String studentIdString = studentIdField.getText();
	    	    	  String studentFirstNameString = studentFirstNameField.getText();
	    	    	  String studentLastNameString = studentLastNameField.getText();
	    	    	  String studentAddressString = studentAddressField.getText();
	    	    	  String studentCityString = studentCityField.getText();
	    	    	  String stateString = studentStateField.getText(); //need to use the get value method instead of the get text method when working with a choice box
	    	    	  String studentZipCodeString = studentZipCodeField.getText();
	    	    	  
	    	    	  int studentId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (studentIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Student ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  //check if any of the fields are empty and show an alert with an error message and don't try to create the student
	    	    	  else  if (studentFirstNameString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Student First Name cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (studentLastNameString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Student Last Name cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (studentAddressString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Student Address cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (studentCityString.equals("")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Student City cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (stateString.equals("")) {//for a  choice box you use equal to null intead of .equals empty string
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Student State cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (studentZipCodeString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Student Zip Code cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  {//only if none of the fields are empty, try the following steps
	    	    		  try {
	    	    			  //try to convert the student ID string to an integer, if that works continue
	    	    			  studentId = Integer.parseInt(studentIdString);
	    	    	    	  
	    	    			  //add student by calling the add student method of the student file manager object, and keep track of whether or not it's successful in a boolean variable
	    	    			  boolean addStudentSuccessful = databaseManager.addStudent(studentFirstNameString, studentLastNameString, studentAddressString, studentCityString, stateString, studentZipCodeString);
	    	    	    	  
	    	    	    	  if (!addStudentSuccessful) {//if adding the student was unsuccessful, display an alert stating why and an error message
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("Student ID already exists so the student cannot be added at this time.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } else {//if it is sucessful, clear all field inputs after student is added
	    	    	    		  studentIdField.setText(Integer.toString(databaseManager.getLastStudentId() + 1)); 
	    	    		    	  studentIdField.setEditable(false);
	    	    	    		  studentFirstNameField.setText("");
	    	    	    		  studentLastNameField.setText("");
	    	    	    		  studentAddressField.setText("");
	    	    	    		  studentCityField.setText("");
	    	    	    		  studentStateField.setText("");
	    	    	    		  studentZipCodeField.setText("");
	    	    	    		  createStudentButton.setDisable(true);
	    		    		    studentStateField.setEditable(false); // Make non-editable
	    		    		    studentStateField.setVisible(false);    // Hide initially
	    		    		    studentCityField.setEditable(false); // Make non-editable
	    		    		    studentCityField.setVisible(false);  // Hide initially
	    		    		    badge.setText("Search for zip code first");
	    		    		    badge.setVisible(true);
	    		    		    studentIdField.deselect();
	    		    		    
	    		    		 // Optional: Clear focus completely
	    		    		    gridpane.requestFocus(); // or use a dummy label as described above
	    	    	    	  }
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {//if can't convert student id to an integer, show an alert with the appropriate error message
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Student ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {//if any other exception occurs, put an alert with the appropriate error message
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for creating the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          createStudentButton.setOnAction(new createStudentBtnClickHandler());
	      }
      }
      
      // Register the event handler.
      addStudentItem.setOnAction(new AddStudentClickHandler());
      
      
      class EditStudentClickHandler implements EventHandler<ActionEvent> { //similar to view student click handler
    	  private boolean zipValidated = false;
    	  private boolean studentIdValidated = false;
    	  private String lastValidatedZip = "";
    	  
    	  private void checkUpdateReady(Button updateButton, Label badgeId, Label badgeZip) {
    		    boolean ready = zipValidated && studentIdValidated;

    		    updateButton.setDisable(!ready);

    		    badgeId.setVisible(!studentIdValidated);
    		    badgeZip.setVisible(!zipValidated);
    		}

    	  
	      @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  GridPane gridpane = new GridPane();
	    	  Label studentLabel = new Label("Edit Student Information");
	    	  gridpane.add(studentLabel, 1, 0); // Column 1, Row 0
	  
	    	  Label studentIdLabel = new Label("Student ID");
	    	  gridpane.add(studentIdLabel, 0, 1);
	    	  
	    	  studentIdField = new TextField();	    	  
	    	  gridpane.add(studentIdField, 1, 1);
	    	  
	    	  Button searchStudentIdButton = new Button("Search");
	    	  gridpane.add(searchStudentIdButton, 2, 1);
	    	  
	    	  Label studentFirstNameLabel = new Label("First Name");
	    	  gridpane.add(studentFirstNameLabel, 0, 2);
	    	  
	    	  studentFirstNameField = new TextField();
	    	  gridpane.add(studentFirstNameField, 1, 2);
	    	  
	    	  Label studentLastNameLabel = new Label("Last Name");
	    	  gridpane.add(studentLastNameLabel, 0, 3);
	    	  
	    	  studentLastNameField = new TextField();
	    	  gridpane.add(studentLastNameField, 1, 3);
	    	  
	    	  Label studentAddressLabel = new Label("Address");
	    	  gridpane.add(studentAddressLabel, 0, 4);
	    	  
	    	  studentAddressField = new TextField();
	    	  gridpane.add(studentAddressField, 1, 4);
	    	  
	    	  Label studentCityLabel = new Label("City");
	    	  gridpane.add(studentCityLabel, 0, 5);
	    	  
	    	  studentCityField = new TextField();
	    	  
	    	  studentCityField = new TextField();
	 
	    	  studentCityField.setVisible(false);  // Hide initially   	  
	    	  studentCityField.setEditable(false);
	    	  
	    	  gridpane.add(studentCityField, 1, 5);
	    	  
	    	  Label studentStateLabel = new Label("State");
	    	  gridpane.add(studentStateLabel, 0, 6);
	    	  
	    	  studentStateField = new TextField();
	    	  
	    	  studentStateField.setEditable(false);	  
	    	  studentStateField.setVisible(false);  // Hide initially
	    	  
	    	  gridpane.add(studentStateField, 1, 6);
	    	  
	    	  Label studentZipCodeLabel = new Label("Zip Code");
	    	  gridpane.add(studentZipCodeLabel, 0, 7);
	    	  
	    	  studentZipCodeField = new TextField();
	    	  gridpane.add(studentZipCodeField, 1, 7);
	    	  
	    	  Button searchZipButton = new Button("Search");
	    	  gridpane.add(searchZipButton, 2, 7);  // Next to the zip code field
	    	  
	    	  Button studentResetButton = new Button("Reset");
	    	  gridpane.add(studentResetButton, 1, 8);
	    	  
	    	  Button studentUpdateButton = new Button("Update");
	    	  
	    	  studentUpdateButton.setDisable(true);
	    	  
	    	  gridpane.add(studentUpdateButton, 2, 8);
	    	  
	    	  Label badgeId = new Label("Search for ID first");
	    	  badgeId.getStyleClass().add("badge"); // add the css class
	    	  badgeId.setVisible(true);             // initially badge will read this
	    	  
	    	  gridpane.add(badgeId, 1, 9);
	    	  
	    	  Label badgeZip = new Label("Search for zip code first");
	    	  badgeZip.getStyleClass().add("badge"); // add the css class
	    	  badgeZip.setVisible(true);             // initially badge will read this
	    	  
	    	  gridpane.add(badgeZip, 1, 10);
	    	  
	    	  //see comments in add student search Zip button this is similar, just now the update button gets disabled
	    	  searchZipButton.setOnAction(e -> {
	    		    String zip = studentZipCodeField.getText();

	    		    if (zip.equals("")) {
	    		    	//studentUpdateButton.setDisable(true);
	    		    	studentStateField.setEditable(false); // Make non-editable
	    		    	studentStateField.setVisible(false);    // Hide initially
	    		    	studentCityField.setEditable(false); // Make non-editable
	    		    	studentCityField.setVisible(false);  // Hide initially
	    		    	badgeZip.setText("Zip code cannot be blank");
	    		    	badgeZip.setVisible(true);
	    		    	
	    		    	checkUpdateReady(studentUpdateButton, badgeId, badgeZip);
	    		    	
	    		    	// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Zip code cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    		        return;
	    		    }

	    		    try {
	    		        ZipCode zipInfo = new ZipCodeClient().lookupZip(zip);
	    		        String city = zipInfo.getPlaces()[0].getPlaceName();
	    		        String state = zipInfo.getPlaces()[0].getStateAbbreviation();

	    		        studentCityField.setText(city);
	    		        studentStateField.setText(state);

	    		        studentCityField.setVisible(true);
	    		        studentStateField.setVisible(true);
	    		        //studentUpdateButton.setDisable(false);
	    		        badgeZip.setVisible(false);                // hide the badge
	    		        
	    		        zipValidated = true;
	    		        lastValidatedZip = studentZipCodeField.getText();
	    		        checkUpdateReady(studentUpdateButton, badgeId, badgeZip);

	    		    } catch (IOException ex) {
	    		    	studentUpdateButton.setDisable(true);
	    		    	studentStateField.setEditable(false); // Make non-editable
	    		    	studentStateField.setVisible(false);    // Hide initially
	    		    	studentCityField.setEditable(false); // Make non-editable
	    		    	studentCityField.setVisible(false);  // Hide initially
	    		    	badgeZip.setText("Zip code not found.");
	    		    	badgeZip.setVisible(true);
	    		    	
	    		    	checkUpdateReady(studentUpdateButton, badgeId, badgeZip);
	    		    	// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Zip code not found.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();

	    		    } catch (Exception ex) {
	    		    	studentUpdateButton.setDisable(true);
	    		    	studentStateField.setEditable(false); // Make non-editable
	    		    	studentStateField.setVisible(false);    // Hide initially
	    		    	studentCityField.setEditable(false); // Make non-editable
	    		    	studentCityField.setVisible(false);  // Hide initially
	    		    	badgeZip.setText("Unexpected error. Try again and contact IT.");
	    		    	badgeZip.setVisible(true);
	    		    	checkUpdateReady(studentUpdateButton, badgeId, badgeZip);
	    		    	// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Unexpected error. Please try again.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	            

	    		    }
	    		});
	    	  
	    	  //listener to identify when the text of the zip code changes
	    	  studentZipCodeField.textProperty().addListener((obs, oldText, newText) -> { //obs is not used, similar to _ in javascript
	    		    if (!newText.equals(lastValidatedZip)) {
	    		        zipValidated = false;
	    		        studentUpdateButton.setDisable(true);
	    		        badgeZip.setText("Zip code changed.\nPlease re-validate.");
	    		        badgeZip.setVisible(true);
	    		        checkUpdateReady(studentUpdateButton, badgeId, badgeZip);
	    		    }
	    		});
	    	  
	    	  gridpane.setHgap(10);
	    	  gridpane.setVgap(10);
	    	  gridpane.setPadding(new Insets(10));
	    	  
	    	  
	    	  
	          
	          borderPane.setCenter(gridpane);
	          
	          gridpane.setAlignment(Pos.CENTER);
	                   
	          // Update the existing scene with the new center
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class editStudentSearchStudentIdBtnClickHandler implements EventHandler<ActionEvent> {//class for the event in the edit student button, similar to the search student id button in the view student event handler
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  
	    	    	  String studentIdString = studentIdField.getText();
	    	    	
	    	    	  int studentId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (studentIdString.equals( "")) {
	    	    		  badgeId.setText("Student ID cannot be blank");
		    		      badgeId.setVisible(true);
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Student ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    			  studentId = Integer.parseInt(studentIdString);
	    	    	    	  
	    	    			  Student studentToUpdate = databaseManager.getStudent(studentId);
	    	    			  
	    	    	    	  
	    	    	    	  if (studentToUpdate == null) {
	    	    	    	   
	    	    	    		  badgeId.setText("Student ID not found");
	    		    		      badgeId.setVisible(true);
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The student ID could not be found. Choose a different ID for a successful update.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } else {//after searching the student id, if the student id is found, show fields with the current information of the student before any update would take place and lock the student id field
	    	    	    		  studentIdField.setDisable(true);
	    	    	    		  studentIdValidated = true;
	    	    	    		  checkUpdateReady(studentUpdateButton, badgeId, badgeZip);
	    	    	    		  studentFirstNameField.setText(studentToUpdate.getStudentFirstName());
	    	    	    		  studentLastNameField.setText(studentToUpdate.getStudentLastName());
	    	    	    		  studentAddressField.setText(studentToUpdate.getStudentAddress());
	    	    	    		  studentCityField.setText(studentToUpdate.getStudentCity());
	    	    	    		  studentStateField.setText(studentToUpdate.getStudentState());
	    	    	    		  studentZipCodeField.setText(studentToUpdate.getStudentZip());
	    	    	    		  
	    	    	    	  }
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			  
	    	    			  badgeId.setText("Student ID not found");
			    		      badgeId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please enter an integer value for Student ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			  badgeId.setText("Student ID not found");
			    		      badgeId.setVisible(true);
	    	    			  
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for viewing the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          searchStudentIdButton.setOnAction(new editStudentSearchStudentIdBtnClickHandler());
	          
	          class updateStudentBtnClickHandler implements EventHandler<ActionEvent> { //update student button class
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  //get the information from the update student fields using get text for fields or get value for a choice box, convert all of these variables to strings so they are easier to work with and can be integrated with the console app
	    	    	  String studentIdString = studentIdField.getText();
	    	    	  String studentFirstNameString = studentFirstNameField.getText();
	    	    	  String studentLastNameString = studentLastNameField.getText();
	    	    	  String studentAddressString = studentAddressField.getText();
	    	    	  String studentCityString = studentCityField.getText();
	    	    	  String stateString = studentStateField.getText();
	    	    	  String studentZipCodeString = studentZipCodeField.getText();
	    	    	  
	    	    	  int studentId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  //make sure no field is left blank
	    	    	  if (studentIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Student ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  if (studentFirstNameString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Student First Name cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (studentLastNameString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Student Last Name cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (studentAddressString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Student Address cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (studentCityString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Student City cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (stateString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Student State cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (studentZipCodeString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Student Zip Code cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  {
	    	    		  try {//try to convert the student id to an integer and update the student
	    	    			  studentId = Integer.parseInt(studentIdString);
	    	    			 
	    	    			  boolean updateStudentSuccessful = databaseManager.updateStudent(studentId, studentFirstNameString, studentLastNameString, studentAddressString, studentCityString, stateString, studentZipCodeString);
	    	    	    	  
	    	    	    	  if (!updateStudentSuccessful) {
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The student ID could not be found, so no information can be updated at this time. Try searching for the student ID before the update using the search button.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } else {//if student gets added successfully clear all the inputs in the fields
	    	    	    		  studentIdField.setText("");
	    	    	    		  studentFirstNameField.setText("");
	    	    	    		  studentLastNameField.setText("");
	    	    	    		  studentAddressField.setText("");
	    	    	    		  studentCityField.setText("");
	    	    	    		  studentStateField.setText("");
	    	    	    		  studentZipCodeField.setText("");
	    	    	    		  studentIdField.setDisable(false);
	    	    	    		  badgeZip.setText("Search for zip code first");
	    	    	    		  badgeZip.setVisible(true);
	    	    	    		  badgeId.setText("Search for ID first");
	    	    	    		  badgeId.setVisible(true);
		    		    		  studentStateField.setEditable(false);	  
		    			    	  studentStateField.setVisible(false);  // Hide initially
		    			    	  studentCityField.setEditable(false);	  
		    			    	  studentCityField.setVisible(false);  // Hide initially
		    			    	  
		    			    	  studentIdValidated = false;
		    			    	  zipValidated = false;
		    			    	  checkUpdateReady(studentUpdateButton, badgeId, badgeZip);
	    	    	    		  
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    	    	              alert.setTitle("Complete");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("Student info updated");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  }
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {//if the id number can't be converted to an integer show an alert with the error message
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Student ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {//if any unknown exception occurs show the alert with the message
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for updating the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          studentUpdateButton.setOnAction(new updateStudentBtnClickHandler());
	          
	          class updateStudentResetBtnClickHandler implements EventHandler<ActionEvent> {//class to reset student update information
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    		  try {//try to clear all the input fields
	    	    	    		  studentIdField.setText("");
	    	    	    		  studentFirstNameField.setText("");
	    	    	    		  studentLastNameField.setText("");
	    	    	    		  studentAddressField.setText("");
	    	    	    		  studentCityField.setText("");
	    	    	    		  studentStateField.setText("");
	    	    	    		  studentZipCodeField.setText("");
	    	    	    		  studentIdField.setDisable(false);
	    	    	    		  badgeZip.setText("Search for zip code first");
	    	    	    		  badgeZip.setVisible(true);
		    		    		  studentStateField.setEditable(false);	  
		    			    	  studentStateField.setVisible(false);  // Hide initially
		    			    	  studentCityField.setEditable(false);	  
		    			    	  studentCityField.setVisible(false);  // Hide initially
		    			    	  
		    			    	  studentIdValidated = false;
		    			    	  zipValidated = false;
		    			    	  checkUpdateReady(studentUpdateButton, badgeId, badgeZip);
	    	    	    	  }
	    	    		  catch (Exception e) {//if it doesn't work, catch the exception by displaying an alert box
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for reseting the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          studentResetButton.setOnAction(new updateStudentResetBtnClickHandler());
	      }
      }
      
      // Register the event handler.
      editStudentItem.setOnAction(new EditStudentClickHandler());
      
    //menu for students
      Menu instructorsMenu = new Menu("Instructors");
      MenuItem viewInstructorItem = new MenuItem("View Instructor");
      MenuItem addInstructorItem = new MenuItem("Add Instructor");
      MenuItem editInstructorItem = new MenuItem("Edit Instructor");
      instructorsMenu.getItems().addAll(viewInstructorItem, addInstructorItem, editInstructorItem);
      
  	//private TextField instructorIdField;
  	//private TextField departmentField;
      
   // create a class to register the student event handler items with
      class ViewInstructorClickHandler implements EventHandler<ActionEvent> {
	      @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  //create a grid so that items can be in multiple rows and columns
	    	  //necessary labels and fields in their appropriate positions on the grid
	    	  GridPane gridpane = new GridPane();
	    	  
	    	  Label instructorIdLabel = new Label("Instructor ID"); // create a label
	    	  gridpane.add(instructorIdLabel, 0, 0); // add it to the grid
	    	  
	    	  instructorIdField = new TextField(); //similarly with field and buttons
	    	  gridpane.add(instructorIdField, 1, 0);
	    	  
	    	  Button searchInstructorIdButton = new Button("Search");
	    	  gridpane.add(searchInstructorIdButton, 2, 0);
	    	  
	    	  Label instructorNameLabel = new Label("Name");
	    	  gridpane.add(instructorNameLabel, 0, 1);
	    	  
	    	  instructorNameField = new TextField();
	    	  instructorNameField.setEditable(false); // user can't edit these fields
	    	  gridpane.add(instructorNameField, 1, 1);
	    	  
	    	  Label instructorDeoartmentLabel = new Label("Department");
	    	  gridpane.add(instructorDeoartmentLabel, 0, 2);
	    	  
	    	  departmentField = new TextField();
	    	  departmentField.setEditable(false);
	    	  gridpane.add(departmentField, 1, 2);
	    	  
	    	  gridpane.setHgap(10); // give horizontal spacing between items on the grid, also vertical spacing, and padding which is space between grid and stage
	    	  gridpane.setVgap(10);
	    	  gridpane.setPadding(new Insets(10));
	    	  
	    	  
	    	  
	          
	          borderPane.setCenter(gridpane); // add the grid to the center of the border pane (top has the menu)
	          
	          gridpane.setAlignment(Pos.CENTER); // position the grid in the center
	                   
	          // Update the existing scene with the new center, I needed to look this up but basically you can't just create multiple scenes, you need to replace the existing scene using this syntax
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class ViewInstructorSearchBtnClickHandler implements EventHandler<ActionEvent> { //searches for student ID if found displays student info in respective fields
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  //convert field info to a string variable so we can work with it like we did in console application
	    	    	  String instructorIdString = instructorIdField.getText();
	    	    	  int instructorId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  //if student ID field is blank, show an alert with an error message
	    	    	  if (instructorIdString.equals( "")) {
	    	    		  instructorNameField.setText("");
	    	    		  departmentField.setText("");
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Instructor ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  
	    	    	  else  {
	    	    		  try {//using a try catch to gracefully catch and correct errors
	    	    			  //if there is trouble changing the student ID to an integer, display an error message and clear all fields below the id
	    	    			  instructorId = Integer.parseInt(instructorIdString);
	    	    			  Instructor instructorToDisplay = databaseManager.getInstructor(instructorId);
	    	    	    	  
	    	    	    	  if (instructorToDisplay != null) {
	    	    	    		  instructorNameField.setText(instructorToDisplay.getInstructorName());
	    	    	    		  departmentField.setText(instructorToDisplay.getInstructorDepartment());
	    	    	    	  } else {
	    	    	    		  instructorNameField.setText("");
	    	    	    		  departmentField.setText("");
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("Instructor ID not found.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    		  
	    	    	    	  }
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {//clear all fields if trouble converting to an integer and display an error alert message
	    	    			  instructorNameField.setText("");
    	    	    		  departmentField.setText("");
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Instructor ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {//catch the exception for any unknown errors that can occur when the program runs
	    	    			  instructorNameField.setText("");
    	    	    		  departmentField.setText("");
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for searching the student ID, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          searchInstructorIdButton.setOnAction(new ViewInstructorSearchBtnClickHandler());  //need to uncomment
	      }
      }
      
      // Register the event handler for viewing the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
      viewInstructorItem.setOnAction(new ViewInstructorClickHandler());
      
      class AddInstructorClickHandler implements EventHandler<ActionEvent> { // everything is similar with addstudent handler as it is to view student except for choice box
	      @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  GridPane gridpane = new GridPane();
	    	  Label studentLabel = new Label("New Instructor Information");
	    	  gridpane.add(studentLabel, 1, 0); // Column 1, Row 0
	    	  
	    	  Label instructorIdLabel = new Label("Instructor ID");
	    	  gridpane.add(instructorIdLabel, 0, 1);
	    	  
	    	  instructorIdField = new TextField();
	    	  gridpane.add(instructorIdField, 1, 1);
	  
	    	  
	    	  String instructorId = null;
	    	  try {
	    		  instructorId = Integer.toString(databaseManager.getLastInstructorId() + 1);
	    	  } catch (SQLException sqlException) {
	    		  	sqlException.printStackTrace();
			        System.err.println("Error loading instructors from database.");
			     // Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading instructors from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  } catch (Exception exception) {
	    		// Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading instructors from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  }
	    	  
	    	  instructorIdField.setText(instructorId); 
	    	  instructorIdField.setEditable(false);
	    	  
	    	  
	    	  Label instructorNameLabel = new Label("Name");
	    	  gridpane.add(instructorNameLabel, 0, 2);
	    	  
	    	  instructorNameField = new TextField();
	    	  gridpane.add(instructorNameField, 1, 2);
	    	  
	    	  Label departmentLabel = new Label("Department");
	    	  gridpane.add(departmentLabel, 0, 3);
	    	  
	    	  departmentChoiceBox = new ChoiceBox<>(); //used videos for reference, but this is the same as a combo box in the book, but with a check mark to the left of selected items in the list
	    	  
	    	  
	    	// Add the department names to the dropdown, dynamically get them from the file and need to loop through them bc we are using a generic linked list
	    	  
	    	  ArrayList<String> departmentNames = null;
	    	  try {
	    		  departmentNames = databaseManager.getAllDepartmentNames();
	    	  } catch (SQLException sqlException) {
	    		  	sqlException.printStackTrace();
			        System.err.println("Error loading departments from database.");
			     // Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading departments from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  } catch (Exception exception) {
	    		// Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading departments from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  }

	    	  for (int i = 0; i < departmentNames.size(); i++) {
	    	      departmentChoiceBox.getItems().add(departmentNames.get(i));
	    	  }
	    	  
	    	  
	    	  gridpane.add(departmentChoiceBox, 1, 3);
	    	  
	    	  Button createInstructorButton = new Button("Create Instructor");
	    	  gridpane.add(createInstructorButton, 1, 4);
	    	  
	    	  gridpane.setHgap(10);
	    	  gridpane.setVgap(10);
	    	  gridpane.setPadding(new Insets(10));
	    	  
	    	  
	    	  
	          
	          borderPane.setCenter(gridpane);
	          
	          gridpane.setAlignment(Pos.CENTER);
	                   
	          // Update the existing scene with the new center
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class createInstructorBtnClickHandler implements EventHandler<ActionEvent> { //class to create a student when the user clicks the create student button
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  //string variables that are converted from field inputs, we cannot work with field inputs but we can work with Strings in the console app
	    	    	  String instructorIdString = instructorIdField.getText();
	    	    	  String instructorNameString = instructorNameField.getText();
	    	    	  String instructorDepartmentString = departmentChoiceBox.getValue(); //need to use the get value method instead of the get text method when working with a choice box
	    	    	  
	    	    	  int instructorId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (instructorIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Instructor ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  //check if any of the fields are empty and show an alert with an error message and don't try to create the student
	    	    	  else  if (instructorNameString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Instructor Name cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    
	    	    	  else  if (instructorDepartmentString == null) {//for a  choice box you use equal to null intead of .equals empty string
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Instructor Department cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  {//only if none of the fields are empty, try the following steps
	    	    		  try {
	    	    			  //try to convert the student ID string to an integer, if that works continue
	    	    			  instructorId = Integer.parseInt(instructorIdString);
	    	    	    	  
	    	    			  //add student by calling the add student method of the student file manager object, and keep track of whether or not it's successful in a boolean variable
	    	    			  boolean addInstructorSuccessful = databaseManager.addInstructor(instructorNameString, instructorDepartmentString);
	    	    	    	  
	    	    	    	  if (!addInstructorSuccessful) {//if adding the student was unsuccessful, display an alert stating why and an error message
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("Instructor ID already exists so the instructor cannot be added at this time.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } else {//if it is sucessful, clear all field inputs after student is added
	    	    		    	  instructorIdField.setText(Integer.toString(databaseManager.getLastInstructorId() + 1)); 
	    	    	    		  instructorIdField.setEditable(false);
	    	    	    		  instructorNameField.setText("");
	    	    	    		  departmentChoiceBox.setValue(null);
	    	    	    	  }
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {//if can't convert student id to an integer, show an alert with the appropriate error message
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Instructor ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {//if any other exception occurs, put an alert with the appropriate error message
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for creating the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          createInstructorButton.setOnAction(new createInstructorBtnClickHandler());
	      }
      }
      
      // Register the event handler.
      addInstructorItem.setOnAction(new AddInstructorClickHandler());
      
      class EditInstructorClickHandler implements EventHandler<ActionEvent> { //similar to view student click handler
    	  private boolean instructorIdValidated = false;
    	  
    	  private void checkUpdateReady(Button updateButton, Label badgeId) {
    		    boolean ready = instructorIdValidated;

    		    updateButton.setDisable(!ready);

    		    badgeId.setVisible(!instructorIdValidated);
    		}
    	  
    	  @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  GridPane gridpane = new GridPane();
	    	  Label instructorLabel = new Label("Edit Instructor Information");
	    	  gridpane.add(instructorLabel, 1, 0); // Column 1, Row 0
	    	  
	    	  Label instructorIdLabel = new Label("Instructor ID");
	    	  gridpane.add(instructorIdLabel, 0, 1);
	    	  
	    	  instructorIdField = new TextField();
	    	  gridpane.add(instructorIdField, 1, 1);
	    	  
	    	  Button searchInstructorIdButton = new Button("Search");
	    	  gridpane.add(searchInstructorIdButton, 2, 1);
	    	  
	    	  Label instructorNameLabel = new Label("Name");
	    	  gridpane.add(instructorNameLabel, 0, 2);
	    	  
	    	  instructorNameField = new TextField();
	    	  gridpane.add(instructorNameField, 1, 2);
	    	  
	    	  Label departmentLabel = new Label("Department");
	    	  gridpane.add(departmentLabel, 0, 3);
	    	  
	    	  ChoiceBox<String> departmentChoiceBox = new ChoiceBox<>();
	    	  // Add the department names to the dropdown, dynamically get them from the file and need to loop through them bc we are using a generic linked list
	    	  
	    	  ArrayList<String> departmentNames = null;
	    	  try {
	    		  departmentNames = databaseManager.getAllDepartmentNames();
	    	  } catch (SQLException sqlException) {
	    		  	sqlException.printStackTrace();
			        System.err.println("Error loading departments from database.");
			     // Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading departments from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  } catch (Exception exception) {
	    		// Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading departments from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  }

	    	  for (int i = 0; i < departmentNames.size(); i++) {
	    	      departmentChoiceBox.getItems().add(departmentNames.get(i));
	    	  }
	    	  gridpane.add(departmentChoiceBox, 1, 3);
	    	  
	    	  Button instructorResetButton = new Button("Reset");
	    	  gridpane.add(instructorResetButton, 1, 4);
	    	  
	    	  Button instructorUpdateButton = new Button("Update");
	    	  
	    	  instructorUpdateButton.setDisable(true);
	    	  
	    	  gridpane.add(instructorUpdateButton, 2, 4);
	    	  
	    	  Label badgeId = new Label("Search for ID first");
	    	  badgeId.getStyleClass().add("badge"); // add the css class
	    	  badgeId.setVisible(true);             // initially badge will read this
	    	  
	    	  gridpane.add(badgeId, 1, 5);
	    	  
	    	  gridpane.setHgap(10);
	    	  gridpane.setVgap(10);
	    	  gridpane.setPadding(new Insets(10));
	    	  
	    	  
	    	  
	          
	          borderPane.setCenter(gridpane);
	          
	          gridpane.setAlignment(Pos.CENTER);
	                   
	          // Update the existing scene with the new center
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class editInstructorSearchStudentIdBtnClickHandler implements EventHandler<ActionEvent> {//class for the event in the edit student button, similar to the search student id button in the view student event handler
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  
	    	    	  String instructorIdString = instructorIdField.getText();
	    	    	
	    	    	  int instructorId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (instructorIdString.equals( "")) {
	    	    		  badgeId.setText("Instructor ID cannot be blank");
		    		      badgeId.setVisible(true);
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Instructor ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    			  instructorId = Integer.parseInt(instructorIdString);
	    	    	    	  
	    	    			  Instructor instructorToUpdate = databaseManager.getInstructor(instructorId);
	    	    			  
	    	    	    	  
	    	    	    	  if (instructorToUpdate == null) {
	    	    	    		  
	    	    	    		  badgeId.setText("Instructor ID not found");
	    		    		      badgeId.setVisible(true);
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The instructor ID could not be found. Choose a different ID for a successful update.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } else {//after searching the student id, if the student id is found, show fields with the current information of the student before any update would take place and lock the student id field
	    	    	    		  instructorIdField.setDisable(true);
	    	    	    		  instructorIdValidated = true;
	    	    	    		  checkUpdateReady(instructorUpdateButton, badgeId);
	    	    	    		  instructorNameField.setText(instructorToUpdate.getInstructorName());
	    	    	    		  departmentChoiceBox.setValue(instructorToUpdate.getInstructorDepartment());
	    	    	    		  
	    	    	    	  }
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			  
	    	    			  badgeId.setText("Instructor ID not found");
			    		      badgeId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Instructor ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			  badgeId.setText("Instructor ID not found");
			    		      badgeId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for viewing the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          searchInstructorIdButton.setOnAction(new editInstructorSearchStudentIdBtnClickHandler());
	          
	          class updateInstructorBtnClickHandler implements EventHandler<ActionEvent> { //update student button class
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  //get the information from the update student fields using get text for fields or get value for a choice box, convert all of these variables to strings so they are easier to work with and can be integrated with the console app
	    	    	  String instructorIdString = instructorIdField.getText();
	    	    	  String instructorNameString = instructorNameField.getText();
	    	    	  String departmentString = departmentChoiceBox.getValue();
	    	    	  
	    	    	  int instructorId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  //make sure no field is left blank
	    	    	  if (instructorIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Instructor ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  if (instructorNameString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Instructor Name cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (departmentString == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Instructor Department cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  {
	    	    		  try {//try to convert the student id to an integer and update the student
	    	    			  instructorId = Integer.parseInt(instructorIdString);
	    	    			 
	    	    			  boolean updateInstructorSuccessful = databaseManager.updateInstructor(instructorId, instructorNameString, departmentString);
	    	    	    	  
	    	    	    	  if (!updateInstructorSuccessful) {
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The instructor ID could not be found, so no information can be updated at this time. Try searching for the instructor ID before the update using the search button.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } else {//if student gets added successfully clear all the inputs in the fields
	    	    	    		  instructorIdField.setText("");
	    	    	    		  instructorNameField.setText("");
	    	    	    		  departmentChoiceBox.setValue(null);
	    	    	    		  instructorIdField.setDisable(false);
	    	    	    		  
	    	    	    		  badgeId.setText("Search for ID first");
	    	    	    		  badgeId.setVisible(true);
	    	    	    		  
	    	    	    		  instructorIdValidated = false;
		    			    	  checkUpdateReady(instructorUpdateButton, badgeId);
	    	    	    		  
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    	    	              alert.setTitle("Complete");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("Instructor info updated");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  }
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {//if the id number can't be converted to an integer show an alert with the error message
	    	    			  
	    	    			  badgeId.setText("Instructor ID not found");
			    		      badgeId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Instructor ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {//if any unknown exception occurs show the alert with the message
	    	    			  badgeId.setText("Instructor ID not found");
			    		      badgeId.setVisible(true);
	    	    			  
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for updating the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          instructorUpdateButton.setOnAction(new updateInstructorBtnClickHandler());
	          
	          class updateInstructorResetBtnClickHandler implements EventHandler<ActionEvent> {//class to reset student update information
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    		  try {//try to clear all the input fields
	    	    			  instructorIdField.setText("");
    	    	    		  instructorNameField.setText("");
    	    	    		  departmentChoiceBox.setValue(null);
    	    	    		  instructorIdField.setDisable(false);
    	    	    		  badgeId.setText("Search for ID first");
			    		      badgeId.setVisible(true);
			    		      
			    		      instructorUpdateButton.setDisable(true);
	    	    	    	  }
	    	    		  catch (Exception e) {//if it doesn't work, catch the exception by displaying an alert box
	    	    			  badgeId.setText("Instructor ID not found");
			    		      badgeId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for reseting the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          instructorResetButton.setOnAction(new updateInstructorResetBtnClickHandler());
	      }
      }
      
      // Register the event handler.
      editInstructorItem.setOnAction(new EditInstructorClickHandler());
      
    //menu for students
      Menu departmentsMenu = new Menu("Departments");
      MenuItem viewDepartmentItem = new MenuItem("View Department");
      MenuItem addDepartmentItem = new MenuItem("Add Department");
      MenuItem editDepartmentItem = new MenuItem("Edit Department");
      departmentsMenu.getItems().addAll(viewDepartmentItem, addDepartmentItem, editDepartmentItem);
      
  	//private TextField instructorIdField;
  	//private TextField departmentField;
      
   // create a class to register the student event handler items with
      class ViewDepartmentClickHandler implements EventHandler<ActionEvent> {
	      @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  //create a grid so that items can be in multiple rows and columns
	    	  //necessary labels and fields in their appropriate positions on the grid
	    	  GridPane gridpane = new GridPane();
	    	  
	    	  Label departmentIdLabel = new Label("Department ID"); // create a label
	    	  gridpane.add(departmentIdLabel, 0, 0); // add it to the grid
	    	  
	    	  departmentIdField = new TextField(); //similarly with field and buttons
	    	  gridpane.add(departmentIdField, 1, 0);
	    	  
	    	  Button searchDepartmentIdButton = new Button("Search");
	    	  gridpane.add(searchDepartmentIdButton, 2, 0);
	    	  
	    	  Label departmentNameLabel = new Label("Name");
	    	  gridpane.add(departmentNameLabel, 0, 1);
	    	  
	    	  departmentNameField = new TextField();
	    	  departmentNameField.setEditable(false); // user can't edit these fields
	    	  gridpane.add(departmentNameField, 1, 1);
	    	  
	    	  gridpane.setHgap(10); // give horizontal spacing between items on the grid, also vertical spacing, and padding which is space between grid and stage
	    	  gridpane.setVgap(10);
	    	  gridpane.setPadding(new Insets(10));
	    	  
	    	  
	    	  
	          
	          borderPane.setCenter(gridpane); // add the grid to the center of the border pane (top has the menu)
	          
	          gridpane.setAlignment(Pos.CENTER); // position the grid in the center
	                   
	          // Update the existing scene with the new center, I needed to look this up but basically you can't just create multiple scenes, you need to replace the existing scene using this syntax
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class ViewDepartmentSearchBtnClickHandler implements EventHandler<ActionEvent> { //searches for student ID if found displays student info in respective fields
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  //convert field info to a string variable so we can work with it like we did in console application
	    	    	  String departmentIdString = departmentIdField.getText();
	    	    	  int departmentId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  //if student ID field is blank, show an alert with an error message
	    	    	  if (departmentIdString.equals( "")) {
	    	    		  departmentNameField.setText("");
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Department ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  
	    	    	  else  {
	    	    		  try {//using a try catch to gracefully catch and correct errors
	    	    			  //if there is trouble changing the student ID to an integer, display an error message and clear all fields below the id
	    	    			  departmentId = Integer.parseInt(departmentIdString);
	    	    			  Department departmentToDisplay = databaseManager.getDepartment(departmentId);
	    	    	    	  
	    	    	    	  if (departmentToDisplay != null) {
	    	    	    		  departmentNameField.setText(departmentToDisplay.getDepartmentName());
	    	    	    	  } else {
	    	    	    		  departmentNameField.setText("");
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("Department ID not found.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    		  
	    	    	    	  }
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {//clear all fields if trouble converting to an integer and display an error alert message
	    	    			  departmentNameField.setText("");
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Department ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {//catch the exception for any unknown errors that can occur when the program runs
	    	    			  departmentNameField.setText("");
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for searching the student ID, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          searchDepartmentIdButton.setOnAction(new ViewDepartmentSearchBtnClickHandler());  //need to uncomment
	      }
      }
      
      // Register the event handler for viewing the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
      viewDepartmentItem.setOnAction(new ViewDepartmentClickHandler());
      
      class AddDepartmentClickHandler implements EventHandler<ActionEvent> { // everything is similar with addstudent handler as it is to view student except for choice box
	      @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  GridPane gridpane = new GridPane();
	    	  Label departmentLabel = new Label("New Department Information");
	    	  gridpane.add(departmentLabel, 1, 0); // Column 1, Row 0
	    	  
	    	  Label departmentIdLabel = new Label("Department ID");
	    	  gridpane.add(departmentIdLabel, 0, 1);
	    	  
	    	  departmentIdField = new TextField();
	    	  gridpane.add(departmentIdField, 1, 1);
	    	  
	    	  String departmentId = null; 
	    	  
	    	  try {
	    		  departmentId = Integer.toString(databaseManager.getLastDepartmentId() + 1);
	    	  } catch (SQLException sqlException) {
	    		  	sqlException.printStackTrace();
			        System.err.println("Error loading department ID from database.");
			     // Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading department ID from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  } catch (Exception exception) {
	    		// Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading department ID from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  }
	    	  
	    	  departmentIdField.setText(departmentId); 
	    	  departmentIdField.setEditable(false);
	    	  
	    	  
	    	  Label departmentNameLabel = new Label("Name");
	    	  gridpane.add(departmentNameLabel, 0, 2);
	    	  
	    	  departmentNameField = new TextField();
	    	  gridpane.add(departmentNameField, 1, 2);
	    	  
	    	  Button createDepartmentButton = new Button("Create Department");
	    	  gridpane.add(createDepartmentButton, 1, 3);
	    	  
	    	  gridpane.setHgap(10);
	    	  gridpane.setVgap(10);
	    	  gridpane.setPadding(new Insets(10));
	    	  
	    	  
	    	  
	          
	          borderPane.setCenter(gridpane);
	          
	          gridpane.setAlignment(Pos.CENTER);
	                   
	          // Update the existing scene with the new center
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class createDepartmentBtnClickHandler implements EventHandler<ActionEvent> { //class to create a student when the user clicks the create student button
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  //string variables that are converted from field inputs, we cannot work with field inputs but we can work with Strings in the console app
	    	    	  String departmentIdString = departmentIdField.getText();
	    	    	  String departmentNameString = departmentNameField.getText();
	    	    	  
	    	    	  int departmentId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (departmentIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Department ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  //check if any of the fields are empty and show an alert with an error message and don't try to create the student
	    	    	  else  if (departmentNameString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Department Name cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  {//only if none of the fields are empty, try the following steps
	    	    		  try {
	    	    			  //try to convert the student ID string to an integer, if that works continue
	    	    			  departmentId = Integer.parseInt(departmentIdString);
	    	    	    	  
	    	    			  //add student by calling the add student method of the student file manager object, and keep track of whether or not it's successful in a boolean variable
	    	    			  boolean addDepartmentSuccessful = databaseManager.addDepartment(departmentNameString);
	    	    	    	  
	    	    	    	  if (!addDepartmentSuccessful) {//if adding the student was unsuccessful, display an alert stating why and an error message
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("Department ID already exists so the department cannot be added at this time.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } else {//if it is sucessful, clear all field inputs after student is added
	    	    	    		  departmentIdField.setText(Integer.toString(databaseManager.getLastDepartmentId() + 1)); 
	    	    	    		  departmentNameField.setText("");
	    	    	    	  }
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {//if can't convert student id to an integer, show an alert with the appropriate error message
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Department ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {//if any other exception occurs, put an alert with the appropriate error message
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for creating the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          createDepartmentButton.setOnAction(new createDepartmentBtnClickHandler());
	      }
      }
      
      // Register the event handler.
      addDepartmentItem.setOnAction(new AddDepartmentClickHandler());
      
      class EditDepartmentClickHandler implements EventHandler<ActionEvent> { //similar to view student click handler
    	  private boolean departmentIdValidated = false;
    	  
    	  private void checkUpdateReady(Button updateButton, Label badgeId) {
    		    boolean ready = departmentIdValidated;

    		    updateButton.setDisable(!ready);

    		    badgeId.setVisible(!departmentIdValidated);
    		}
    	  
    	  @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  GridPane gridpane = new GridPane();
	    	  Label departmentLabel = new Label("Edit Department Information");
	    	  gridpane.add(departmentLabel, 1, 0); // Column 1, Row 0
	    	  
	    	  Label departmentIdLabel = new Label("Department ID");
	    	  gridpane.add(departmentIdLabel, 0, 1);
	    	  
	    	  departmentIdField = new TextField();
	    	  gridpane.add(departmentIdField, 1, 1);
	    	  
	    	  Button searchDepartmentIdButton = new Button("Search");
	    	  gridpane.add(searchDepartmentIdButton, 2, 1);
	    	  
	    	  Label departmentNameLabel = new Label("Name");
	    	  gridpane.add(departmentNameLabel, 0, 2);
	    	  
	    	  departmentNameField = new TextField();
	    	  gridpane.add(departmentNameField, 1, 2);
	    	  
	    	  Button departmentResetButton = new Button("Reset");
	    	  gridpane.add(departmentResetButton, 1, 3);
	    	  
	    	  Button departmentUpdateButton = new Button("Update");
	    	  
	    	  departmentUpdateButton.setDisable(true);
	    	  
	    	  gridpane.add(departmentUpdateButton, 2, 3);
	    	  
	    	  Label badgeId = new Label("Search for ID first");
	    	  badgeId.getStyleClass().add("badge"); // add the css class
	    	  badgeId.setVisible(true);             // initially badge will read this
	    	  
	    	  gridpane.add(badgeId, 1, 4);
	    	  
	    	  gridpane.setHgap(10);
	    	  gridpane.setVgap(10);
	    	  gridpane.setPadding(new Insets(10));
	    	  
	    	  
	    	  
	          
	          borderPane.setCenter(gridpane);
	          
	          gridpane.setAlignment(Pos.CENTER);
	                   
	          // Update the existing scene with the new center
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class editDepartmentSearchStudentIdBtnClickHandler implements EventHandler<ActionEvent> {//class for the event in the edit student button, similar to the search student id button in the view student event handler
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  
	    	    	  String departmentIdString = departmentIdField.getText();
	    	    	
	    	    	  int departmentId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (departmentIdString.equals( "")) {
	    	    		  
	    	    		  badgeId.setText("Department ID cannot be blank");
		    		      badgeId.setVisible(true);
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Department ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    			  departmentId = Integer.parseInt(departmentIdString);
	    	    	    	  
	    	    			  Department departmentToUpdate = databaseManager.getDepartment(departmentId);
	    	    			  
	    	    	    	  
	    	    	    	  if (departmentToUpdate == null) {
	    	    	    		  badgeId.setText("Deparment ID not found");
	    		    		      badgeId.setVisible(true);
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The department ID could not be found. Choose a different ID for a successful update.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } else {//after searching the student id, if the student id is found, show fields with the current information of the student before any update would take place and lock the student id field
	    	    	    		  departmentIdField.setDisable(true);
	    	    	    		  departmentNameField.setText(departmentToUpdate.getDepartmentName());
	    	    	    		  
	    	    	    		  departmentIdValidated = true;
	    	    	    		  checkUpdateReady(departmentUpdateButton, badgeId);
	    	    	    		  
	    	    	    	  }
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			  
	    	    			  badgeId.setText("Department ID not found");
			    		      badgeId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Department ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			  badgeId.setText("Department ID not found");
			    		      badgeId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for viewing the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          searchDepartmentIdButton.setOnAction(new editDepartmentSearchStudentIdBtnClickHandler());
	          
	          class updateDepartmentBtnClickHandler implements EventHandler<ActionEvent> { //update student button class
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  //get the information from the update student fields using get text for fields or get value for a choice box, convert all of these variables to strings so they are easier to work with and can be integrated with the console app
	    	    	  String departmentIdString = departmentIdField.getText();
	    	    	  String departmentNameString = departmentNameField.getText();
	    	    	  
	    	    	  int departmentId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  //make sure no field is left blank
	    	    	  if (departmentIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Department ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  if (departmentNameString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Department Name cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  {
	    	    		  try {//try to convert the student id to an integer and update the student
	    	    			  departmentId = Integer.parseInt(departmentIdString);
	    	    			 
	    	    			  boolean updateDepartmentSuccessful = databaseManager.updateDepartment(departmentId, departmentNameString);
	    	    	    	  
	    	    	    	  if (!updateDepartmentSuccessful) {
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The department ID could not be found, so no information can be updated at this time. Try searching for the department ID before the update using the search button.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } else {//if student gets added successfully clear all the inputs in the fields
	    	    	    		  departmentIdField.setText("");
	    	    	    		  departmentNameField.setText("");
	    	    	    		  departmentIdField.setDisable(false);
	    	    	    		  
	    	    	    		  badgeId.setText("Search for ID first");
	    	    	    		  badgeId.setVisible(true);
	    	    	    		  
	    	    	    		  departmentIdValidated = false;
		    			    	  checkUpdateReady(departmentUpdateButton, badgeId);
	    	    	    		  
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    	    	              alert.setTitle("Complete");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("Department info updated");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  }
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {//if the id number can't be converted to an integer show an alert with the error message
	    	    			  badgeId.setText("Department ID not found");
			    		      badgeId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Department ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {//if any unknown exception occurs show the alert with the message
	    	    			  badgeId.setText("Department ID not found");
			    		      badgeId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for updating the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          departmentUpdateButton.setOnAction(new updateDepartmentBtnClickHandler());
	          
	          class updateDepartmentResetBtnClickHandler implements EventHandler<ActionEvent> {//class to reset student update information
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    		  try {//try to clear all the input fields
	    	    			  departmentIdField.setText("");
    	    	    		  departmentNameField.setText("");
    	    	    		  departmentIdField.setDisable(false);
    	    	    		  
    	    	    		  badgeId.setText("Search for ID first");
    	    	    		  badgeId.setVisible(true);
    	    	    		  
    	    	    		  departmentUpdateButton.setDisable(true);
	    	    	    	  }
	    	    		  catch (Exception e) {//if it doesn't work, catch the exception by displaying an alert box
	    	    			  badgeId.setText("Department ID not found");
			    		      badgeId.setVisible(true);
	    	    			  
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for reseting the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          departmentResetButton.setOnAction(new updateDepartmentResetBtnClickHandler());
	      }
      }
      
      // Register the event handler.
      editDepartmentItem.setOnAction(new EditDepartmentClickHandler());
      
		
	  Menu coursesMenu = new Menu("Courses"); 
      MenuItem viewCourseItem = new MenuItem("View Course");
      MenuItem addCourseItem = new MenuItem("Add Course");
      MenuItem editCourseItem = new MenuItem("Edit Course");
      coursesMenu.getItems().addAll(viewCourseItem, addCourseItem, editCourseItem);
      
      class ViewCourseClickHandler implements EventHandler<ActionEvent> { // similar to view student
	      @Override
	      public void handle(ActionEvent event)
	      {
	    	  GridPane gridpane = new GridPane();
	    	  
	    	  Label courseIdLabel = new Label("Course ID");
	    	  gridpane.add(courseIdLabel, 0, 0);
	    	  
	    	  courseIdField = new TextField(); 	  
	    	  gridpane.add(courseIdField, 1, 0);
	    	  
	    	  Button searchCourseIdButton = new Button("Search");
	    	  gridpane.add(searchCourseIdButton, 2, 0);
	    	  
	    	  Label coursetNameLabel = new Label("Course Name");
	    	  gridpane.add(coursetNameLabel, 0, 1);
	    	  
	    	  courseNameField = new TextField();
	    	  courseNameField.setEditable(false);
	    	  gridpane.add(courseNameField, 1, 1);
	    	  
	    	  Label courseNumberLabel = new Label("Course Number");
	    	  gridpane.add(courseNumberLabel, 0, 2);
	    	  
	    	  courseNumberField = new TextField();
	    	  courseNumberField.setEditable(false);
	    	  gridpane.add(courseNumberField, 1, 2);
	    	  
	    	  Label courseDepartmentLabel = new Label("Department");
	    	  gridpane.add(courseDepartmentLabel, 0, 3);
	    	  
	    	  courseDepartmentField = new TextField();
	    	  courseDepartmentField.setEditable(false);
	    	  gridpane.add(courseDepartmentField, 1, 3);
	    	  
	    	  Label instructorNameLabel = new Label("Instructor");
	    	  gridpane.add(instructorNameLabel, 0, 4);
	    	  
	    	  instructorNameField = new TextField();
	    	  instructorNameField.setEditable(false);
	    	  gridpane.add(instructorNameField, 1, 4);
	    	  
	    	  gridpane.setHgap(10);
	    	  gridpane.setVgap(10);
	    	  gridpane.setPadding(new Insets(10));
	    	  
	    	  
	    	  
	          
	          borderPane.setCenter(gridpane);
	          
	          gridpane.setAlignment(Pos.CENTER);
	                   
	          // Update the existing scene with the new center
	          primaryStage.getScene().setRoot(borderPane);
	    	  
	          class ViewCourseSearchBtnClickHandler implements EventHandler<ActionEvent> { // similar to view student
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  String courseIdString = courseIdField.getText();
	    	    	  int courseId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (courseIdString.equals( "")) {
	    	    		  studentFirstNameField.setText("");
	    	    		  studentLastNameField.setText("");
	    	    		  studentAddressField.setText("");
	    	    		  studentCityField.setText("");
	    	    		  studentStateField.setText("");
	    	    		  studentZipCodeField.setText("");
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Course ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  
	    	    	  else  {
	    	    		  try {
	    	    			  courseId = Integer.parseInt(courseIdString);
	    	    			  Course courseToDisplay = databaseManager.getCourse(courseId);
	    	    	    	  
	    	    	    	  if (courseToDisplay != null) {
	    	    	    		  courseNameField.setText(courseToDisplay.getCourseName());
	    	    	    		  courseNumberField.setText(Integer.toString(courseToDisplay.getCourseNumber()));
	    	    	    		  courseDepartmentField.setText(courseToDisplay.getDepartment());
	    	    	    		  instructorNameField.setText(courseToDisplay.getInstructor());
	    	    	    	  } else {
	    	    	    		  courseNameField.setText("");
	    	    	    		  courseNumberField.setText("");
	    	    	    		  courseDepartmentField.setText("");
	    	    	    		  instructorNameField.setText("");
	    	    	    		 
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("Course ID not found.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    		  
	    	    	    	  }
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			  courseNameField.setText("");
    	    	    		  courseNumberField.setText("");
    	    	    		  courseDepartmentField.setText("");
    	    	    		  instructorNameField.setText("");
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Course ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			  courseNameField.setText("");
    	    	    		  courseNumberField.setText("");
    	    	    		  courseDepartmentField.setText("");
    	    	    		  instructorNameField.setText("");
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	      }
	          }
	          
	          // Register the event handler.
	          searchCourseIdButton.setOnAction(new ViewCourseSearchBtnClickHandler());
	          
	          
	      }
      }
      
      // Register the event handler.
      viewCourseItem.setOnAction(new ViewCourseClickHandler());
      
      class AddCourseClickHandler implements EventHandler<ActionEvent> { // similar to view student
	      @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  GridPane gridpane = new GridPane();
	    	  Label courseLabel = new Label("New Course Information");
	    	  gridpane.add(courseLabel, 1, 0); // Column 1, Row 0
	    	  
	    	  Label courseIdLabel = new Label("Course ID");
	    	  gridpane.add(courseIdLabel, 0, 1);
	    	  
	    	  courseIdField = new TextField();
	    	  
	    	  
	    	  
	    	  String courseId = null;
	    	  try {
	    		  courseId = Integer.toString(databaseManager.getLastCourseId() + 1); 
	    	  } catch (SQLException sqlException) {
	    		  	sqlException.printStackTrace();
			        System.err.println("Error loading courses from database.");
			     // Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading courses from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  } catch (Exception exception) {
	    		// Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading courses from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  }
	    	  
	    	  courseIdField.setText(courseId); 
	    	  courseIdField.setEditable(false);
	    	  
	    	  gridpane.add(courseIdField, 1, 1);
	    	  
	    	  Label coursetNameLabel = new Label("Course Name");
	    	  gridpane.add(coursetNameLabel, 0, 2);
	    	  
	    	  courseNameField = new TextField();
	    	  gridpane.add(courseNameField, 1, 2);
	    	  
	    	  Label courseDepartmentLabel = new Label("Department");
	    	  gridpane.add(courseDepartmentLabel, 0, 3);
	    	  
	    	  departmentChoiceBox = new ChoiceBox<>();
	    	  // Add the department names to the dropdown, dynamically get them from the file and need to loop through them bc we are using a generic linked list
	    	  
	    	  ArrayList<String> departmentNames = null;
	    	  try {
	    		  departmentNames = databaseManager.getAllDepartmentNames();
	    	  } catch (SQLException sqlException) {
	    		  	sqlException.printStackTrace();
			        System.err.println("Error loading departments from database.");
			     // Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading departments from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  } catch (Exception exception) {
	    		// Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading departments from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  }
	    	  

	    	  for (int i = 0; i < departmentNames.size(); i++) {
	    	      departmentChoiceBox.getItems().add(departmentNames.get(i));
	    	  }
	    	  
	    	  gridpane.add(departmentChoiceBox, 1, 3);
	    	  
	    	  //lambda expression, using it because the class action event is really small and only used in this class, teaching myself lambda expressions
	    	  departmentChoiceBox.setOnAction(e -> {//here we are pairing the departmentChoiceBox with this event listener/lambda expression, e is a placeholder in this case, similar to _ in javascript, e could be used if we need to know more about the action event, but it doesn't have to be used
	    		    String selectedDepartment = departmentChoiceBox.getValue(); //need the selected department saved so that we know what to look up and filter instructors based on department
	    		    instructorNameChoiceBox.getItems().clear(); // clear old list

	    		    if (selectedDepartment != null) {
	    		        
	    		        ArrayList<String> filteredNames = null;
	    		    	  try {
	    		    		  filteredNames = databaseManager.getInstructorNamesByDepartment(selectedDepartment);//get the filtered instructor names by department in my generic list
	    		    	  } catch (SQLException sqlException) {
	    		    		  	sqlException.printStackTrace();
	    				        System.err.println("Error loading instructors from database.");
	    				     // Create an alert
	    	  	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	  	              alert.setTitle("Error");
	    	  	              alert.setHeaderText(null);
	    	  	              alert.setContentText("Error loading instructors from database.");
	    	  	              
	    	  	              DialogPane dialogPane = alert.getDialogPane();
	    	  	              dialogPane.getStylesheets().add("application/application.css");
	    	  	              dialogPane.getStyleClass().add("myDialog");
	    	  	              
	    	  	              // Show the alert
	    	  	              alert.showAndWait();
	    		    	  } catch (Exception exception) {
	    		    		// Create an alert
	    	  	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	  	              alert.setTitle("Error");
	    	  	              alert.setHeaderText(null);
	    	  	              alert.setContentText("Error loading instructors from database.");
	    	  	              
	    	  	              DialogPane dialogPane = alert.getDialogPane();
	    	  	              dialogPane.getStylesheets().add("application/application.css");
	    	  	              dialogPane.getStyleClass().add("myDialog");
	    	  	              
	    	  	              // Show the alert
	    	  	              alert.showAndWait();
	    		    	  }

	    		        
	    		        //need to manually loop through them to add names because it is not an array list, its a my generic list
	    		        for (int i = 0; i < filteredNames.size(); i++) {
	    		            instructorNameChoiceBox.getItems().add(filteredNames.get(i));
	    		        }
	    		        
	    		        //only populate the choice box if there is one instructor for the department, then choose to populate it and choose for the user, otherwise and if not do nothing
	    		        if (instructorNameChoiceBox.getItems().size() ==1) {
	    		            instructorNameChoiceBox.setValue(instructorNameChoiceBox.getItems().get(0));
	    		        }
	    		    }
	    		});

	    	  
	    	  Label courseNumberLabel = new Label("Course Number");
	    	  gridpane.add(courseNumberLabel, 0, 4);
	    	  
	    	  courseNumberField = new TextField();
	    	  gridpane.add(courseNumberField, 1, 4);
	    	  
	    	  Label instructorNameLabel = new Label("Instructor Name");
	    	  gridpane.add(instructorNameLabel, 0, 5);
	    	  
	    	  instructorNameChoiceBox = new ChoiceBox<>();
	    	  	    	  
	    	  
	    	  ArrayList<String> instructorNames = null;
	    	  try {
	    		  instructorNames = databaseManager.getAllInstructorNames();
	    	  } catch (SQLException sqlException) {
	    		  	sqlException.printStackTrace();
			        System.err.println("Error loading instructors from database.");
			     // Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading instructors from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  } catch (Exception exception) {
	    		// Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading instructors from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  }

	    	  for (int i = 0; i < instructorNames.size(); i++) {
	    	      instructorNameChoiceBox.getItems().add(instructorNames.get(i));
	    	  }
	    	  
	    	  gridpane.add(instructorNameChoiceBox, 1, 5);
	    	  
	    	  Button createCourseButton = new Button("Create Course");
	    	  gridpane.add(createCourseButton, 1, 6);
	    	  
	    	  gridpane.setHgap(10);
	    	  gridpane.setVgap(10);
	    	  gridpane.setPadding(new Insets(10));
	    	  
	    	  
	    	  
	          
	          borderPane.setCenter(gridpane);
	          
	          gridpane.setAlignment(Pos.CENTER);
	                   
	          // Update the existing scene with the new center
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class AddCourseBtnClickHandler implements EventHandler<ActionEvent> { // similar to view student
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  String courseIdString = courseIdField.getText();
	    	    	  String courseNameString = courseNameField.getText();
	    	    	  String departmentString = departmentChoiceBox.getValue();
	    	    	  String courseNumberString = courseNumberField.getText();
	    	    	  String instructorNameString = instructorNameChoiceBox.getValue();
	    	    	  
	    	    	  int courseId, courseNumber;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (courseIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Course ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  if (courseNameString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Course Name cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (departmentString == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Course Department cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (courseNumberString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Course Number cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (instructorNameString == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Course Instructor Name cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    			  courseId = Integer.parseInt(courseIdString);
	    	    			  courseNumber = Integer.parseInt(courseNumberString);
	    	    			  
	    	    	    	  
	    	    			  boolean addCourseSuccessful = databaseManager.addCourse(courseNameString, courseNumber, departmentString, instructorNameString);
	    	    	    	  
	    	    	    	  if (!addCourseSuccessful) {
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("Course ID already exists so the course cannot be added at this time.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } else {
	    	    	    		  courseIdField.setText(Integer.toString(databaseManager.getLastCourseId() + 1)); 
	    	    		    	  courseIdField.setEditable(false);
	    	    	    		  courseNameField.setText("");
	    	    	    		  departmentChoiceBox.setValue(null);
	    	    	    		  courseNumberField.setText("");
	    	    	    		  instructorNameChoiceBox.setValue(null);
	    	    	    	  }
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Course ID and Course Number.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	      }
	          }
	          
	          // Register the event handler.
	          createCourseButton.setOnAction(new AddCourseBtnClickHandler());
	      }
      }
      
      // Register the event handler.
      addCourseItem.setOnAction(new AddCourseClickHandler());
      
      class EditCourseClickHandler implements EventHandler<ActionEvent> {// similar to view student
    	  private boolean courseIdValidated = false;
    	  
    	  private void checkUpdateReady(Button updateButton, Label badgeId) {
    		    boolean ready = courseIdValidated;

    		    updateButton.setDisable(!ready);

    		    badgeId.setVisible(!courseIdValidated);
    		}
    	  
    	  @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  GridPane gridpane = new GridPane();
	    	  Label courseLabel = new Label("Edit Course Information");
	    	  gridpane.add(courseLabel, 1, 0); // Column 1, Row 0
	    	  
	    	  Label courseIdLabel = new Label("Course ID");
	    	  gridpane.add(courseIdLabel, 0, 1);
	    	  
	    	  courseIdField = new TextField();
	    	  
	    	  //String courseId = Integer.toString(courseFileManager.getLastCourseId() + 1);  
	    	  
	    	  //courseIdField.setText(courseId); 
	    	  //courseIdField.setEditable(false);
	    	  
	    	  gridpane.add(courseIdField, 1, 1);
	    	  
	    	  Button searchCourseIdButton = new Button("Search");
	    	  gridpane.add(searchCourseIdButton, 2, 1);
	    	  
	    	  Label coursetNameLabel = new Label("Course Name");
	    	  gridpane.add(coursetNameLabel, 0, 2);
	    	  
	    	  courseNameField = new TextField();
	    	  gridpane.add(courseNameField, 1, 2);
	    	  
	    	  Label courseDepartmentLabel = new Label("Department");
	    	  gridpane.add(courseDepartmentLabel, 0, 3);
	    	  
	    	  departmentChoiceBox = new ChoiceBox<>();
	    	  // Add the department names to the dropdown, dynamically get them from the file and need to loop through them bc we are using a generic linked list
	    	  
	    	  ArrayList<String> departmentNames = null;
	    	  try {
	    		  departmentNames = databaseManager.getAllDepartmentNames();
	    	  } catch (SQLException sqlException) {
	    		  	sqlException.printStackTrace();
			        System.err.println("Error loading departments from database.");
			     // Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading departments from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  } catch (Exception exception) {
	    		// Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading departments from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  }

	    	  for (int i = 0; i < departmentNames.size(); i++) {
	    	      departmentChoiceBox.getItems().add(departmentNames.get(i));
	    	  }
	    	  
	    	  gridpane.add(departmentChoiceBox, 1, 3);
	    	  
	    	//lambda expression, using it because the class action event is really small and only used in this class, teaching myself lambda expressions
	    	  departmentChoiceBox.setOnAction(e -> {//here we are pairing the departmentChoiceBox with this event listener/lambda expression, e is a placeholder in this case, similar to _ in javascript, e could be used if we need to know more about the action event, but it doesn't have to be used
	    		    String selectedDepartment = departmentChoiceBox.getValue(); //need the selected department saved so that we know what to look up and filter instructors based on department
	    		    instructorNameChoiceBox.getItems().clear(); // clear old list

	    		    if (selectedDepartment != null) {
	    		        
	    		        ArrayList<String> filteredNames = null;
	    		    	  try {
	    		    		  filteredNames = databaseManager.getInstructorNamesByDepartment(selectedDepartment);//get the filtered instructor names by department in my generic list
	    		    	  } catch (SQLException sqlException) {
	    		    		  	sqlException.printStackTrace();
	    				        System.err.println("Error loading instructors from database.");
	    				     // Create an alert
	    	  	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	  	              alert.setTitle("Error");
	    	  	              alert.setHeaderText(null);
	    	  	              alert.setContentText("Error loading instructors from database.");
	    	  	              
	    	  	              DialogPane dialogPane = alert.getDialogPane();
	    	  	              dialogPane.getStylesheets().add("application/application.css");
	    	  	              dialogPane.getStyleClass().add("myDialog");
	    	  	              
	    	  	              // Show the alert
	    	  	              alert.showAndWait();
	    		    	  } catch (Exception exception) {
	    		    		// Create an alert
	    	  	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	  	              alert.setTitle("Error");
	    	  	              alert.setHeaderText(null);
	    	  	              alert.setContentText("Error loading instructors from database.");
	    	  	              
	    	  	              DialogPane dialogPane = alert.getDialogPane();
	    	  	              dialogPane.getStylesheets().add("application/application.css");
	    	  	              dialogPane.getStyleClass().add("myDialog");
	    	  	              
	    	  	              // Show the alert
	    	  	              alert.showAndWait();
	    		    	  }

	    		        
	    		        //need to manually loop through them to add names because it is not an array list, its a my generic list
	    		        for (int i = 0; i < filteredNames.size(); i++) {
	    		            instructorNameChoiceBox.getItems().add(filteredNames.get(i));
	    		        }
	    		        
	    		        //only populate the choice box if there is one instructor for the department, then choose to populate it and choose for the user, otherwise and if not do nothing
	    		        if (instructorNameChoiceBox.getItems().size() ==1) {
	    		            instructorNameChoiceBox.setValue(instructorNameChoiceBox.getItems().get(0));
	    		        }
	    		    }
	    		});
	    	  
	    	  Label courseNumberLabel = new Label("Course Number");
	    	  gridpane.add(courseNumberLabel, 0, 4);
	    	  
	    	  courseNumberField = new TextField();
	    	  gridpane.add(courseNumberField, 1, 4);
	    	  
	    	  Label instructorNameLabel = new Label("Instructor Name");
	    	  gridpane.add(instructorNameLabel, 0, 5);
	    	  
	    	  instructorNameChoiceBox = new ChoiceBox();
	    	  
	    	  ArrayList<String> instructorNames = null;
	    	  try {
	    		  instructorNames = databaseManager.getAllInstructorNames();
	    	  } catch (SQLException sqlException) {
	    		  	sqlException.printStackTrace();
			        System.err.println("Error loading instructors from database.");
			     // Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading instructors from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  } catch (Exception exception) {
	    		// Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading instructors from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  }

	    	  for (int i = 0; i < instructorNames.size(); i++) {
	    	      instructorNameChoiceBox.getItems().add(instructorNames.get(i));
	    	  }
	    	  
	    	  gridpane.add(instructorNameChoiceBox, 1, 5);
	    	  
	    	  Button courseResetButton = new Button("Reset");
	    	  gridpane.add(courseResetButton, 1, 6);
	    	  
	    	  Button courseUpdateButton = new Button("Update");
	    	  
	    	  courseUpdateButton.setDisable(true);
	    	  
	    	  gridpane.add(courseUpdateButton, 2, 6);
	    	  
	    	  Label badgeId = new Label("Search for ID first");
	    	  badgeId.getStyleClass().add("badge"); // add the css class
	    	  badgeId.setVisible(true);             // initially badge will read this
	    	  
	    	  gridpane.add(badgeId, 1, 7);
	    	  
	    	  gridpane.setHgap(10);
	    	  gridpane.setVgap(10);
	    	  gridpane.setPadding(new Insets(10));
	    	  
	    	  
	    	  
	          
	          borderPane.setCenter(gridpane);
	          
	          gridpane.setAlignment(Pos.CENTER);
	                   
	          // Update the existing scene with the new center
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class EditCourseSearchCourseIdBtnClickHandler implements EventHandler<ActionEvent> { // similar to view student
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {

	    	    	  String courseIdString = courseIdField.getText();
	    	    	
	    	    	  int courseId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (courseIdString.equals( "")) {
	    	    		  badgeId.setText("Course ID cannot be blank");
		    		      badgeId.setVisible(true);
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Course ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    			  courseId = Integer.parseInt(courseIdString);
	    	    	    	  
	    	    			  Course courseToUpdate = databaseManager.getCourse(courseId);
	    	    			  
	    	    	    	  
	    	    	    	  if (courseToUpdate == null) {
	    	    	    		  badgeId.setText("Course ID not found");
	    		    		      badgeId.setVisible(true);
	    	    	    		  
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The course ID could not be found. Choose a different ID for a successful update.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } else {
	    	    	    		  courseNameField.setText(courseToUpdate.getCourseName());
	    	    	    		  courseNumberField.setText(Integer.toString(courseToUpdate.getCourseNumber()));
	    	    	    		  departmentChoiceBox.setValue(courseToUpdate.getDepartment());
	    	    	    		  instructorNameChoiceBox.setValue(courseToUpdate.getInstructor());
	    	    	            	
	    	    	              courseIdField.setDisable(true);
	    	    	              
	    	    	              courseIdValidated = true;
	    	    	              checkUpdateReady(courseUpdateButton, badgeId);
	    	    	              
	    	    	    	  }
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			  badgeId.setText("Course ID not found");
			    		      badgeId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Course ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			  badgeId.setText("Course ID not found");
			    		      badgeId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	      }
	          }
	          
	          // Register the event handler.
	          searchCourseIdButton.setOnAction(new EditCourseSearchCourseIdBtnClickHandler());
	          
	          class updateCourseBtnClickHandler implements EventHandler<ActionEvent> {
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  
	    	    	  String courseIdString = courseIdField.getText();
	    	    	  String courseNameString = courseNameField.getText();
	    	    	  String departmentString = departmentChoiceBox.getValue();
	    	    	  String courseNumberString = courseNumberField.getText();
	    	    	  String instructorNameString = instructorNameChoiceBox.getValue();
	    	    	  
	    	    	  int courseId, courseNumber;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (courseIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Course ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  if (courseNameString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Course Name cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (departmentString == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Course Department cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (courseNumberString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Course Number cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (instructorNameString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Course Instructor cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    			  courseId = Integer.parseInt(courseIdString);
	    	    			  courseNumber = Integer.parseInt(courseNumberString);
	    	    			 
	    	    			  boolean updateCourseSuccessful = databaseManager.updateCourse(courseId, courseNameString, courseNumber, departmentString, instructorNameString);
	    	    	    	  
	    	    	    	  if (!updateCourseSuccessful) {
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The course ID could not be found, so no information can be updated at this time. Try searching for the course ID before the update using the search button.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } else {
	    	    	    		  courseIdField.setText("");
	    	    	    		  courseNameField.setText("");
	    	    	    		  departmentChoiceBox.setValue(null);
	    	    	    		  courseNumberField.setText("");
	    	    	    		  instructorNameChoiceBox.setValue(null);
	    	    	    		  courseIdField.setDisable(false);
	    	    	    		  
	    	    	    		  badgeId.setText("Search for ID first");
	    	    	    		  badgeId.setVisible(true);
	    	    	    		  
	    	    	    		  courseIdValidated = false;
		    			    	  checkUpdateReady(courseUpdateButton, badgeId);
	    	    	    		  
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    	    	              alert.setTitle("Complete");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("Course info updated");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  }
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			  badgeId.setText("Course ID not found");
			    		      badgeId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Course ID and Course Number.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			  badgeId.setText("Course ID not found");
			    		      badgeId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for viewing the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          courseUpdateButton.setOnAction(new updateCourseBtnClickHandler());
	          
	          class updateCourseResetBtnClickHandler implements EventHandler<ActionEvent> {
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    		  try {
	    	    			  courseIdField.setText("");
    	    	    		  courseNameField.setText("");
    	    	    		  departmentChoiceBox.setValue(null);
    	    	    		  courseNumberField.setText("");
    	    	    		  instructorNameChoiceBox.setValue(null);
    	    	    		  courseIdField.setDisable(false);
    	    	    		  badgeId.setText("Search for ID first");
    	    	    		  badgeId.setVisible(true);
    	    	    		  courseUpdateButton.setDisable(true);
	    	    	    	  }
	    	    		  catch (Exception e) {
	    	    			  badgeId.setText("Course ID not found");
			    		      badgeId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for viewing the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          courseResetButton.setOnAction(new updateCourseResetBtnClickHandler());
	      }
      }
      
      // Register the event handler.
      editCourseItem.setOnAction(new EditCourseClickHandler());
	  
	  Menu enrollmentsMenu = new Menu("Enrollments"); //add the menu items underneath the menu can be nested multiple levels
      MenuItem viewEditEnrollmentItem = new MenuItem("View/Edit Enrollment");
      MenuItem addEnrollmentItem = new MenuItem("Add Enrollment");
      enrollmentsMenu.getItems().addAll(viewEditEnrollmentItem, addEnrollmentItem);
      
      class AddEnrollmentClickHandler implements EventHandler<ActionEvent> {// similar to view student but with prompt text
    	  private boolean studentIdValidated = false;
    	  private boolean courseIdValidated = false;

    	  private void checkUpdateReady(Button addButton, Label badgestudentId, Label badgeCourseId) {
    		    boolean ready = courseIdValidated && studentIdValidated;

    		    addButton.setDisable(!ready);

    		    badgestudentId.setVisible(!studentIdValidated);
    		    badgeCourseId.setVisible(!courseIdValidated);
    		}
	      @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  GridPane gridpane = new GridPane();
	    	  Label enrollmentLabel = new Label("New Enrollment Information");
	    	  gridpane.add(enrollmentLabel, 0, 0); // Column 0, Row 0, columns first then rows, opposite of matrices in math
	    	  
	    	  enrollmentIdField = new TextField();
	    	  
	 
	    	  String enrollmentIdString = null;
	    	  try {
	    		  enrollmentIdString = Integer.toString(databaseManager.getLastEnrollmentId() + 1);
	    	  } catch (SQLException sqlException) {
	    		  	sqlException.printStackTrace();
			        System.err.println("Error loading enrollments from database.");
			     // Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading enrollments from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  } catch (Exception exception) {
	    		// Create an alert
  	              Alert alert = new Alert(Alert.AlertType.ERROR);
  	              alert.setTitle("Error");
  	              alert.setHeaderText(null);
  	              alert.setContentText("Error loading enrollments from database.");
  	              
  	              DialogPane dialogPane = alert.getDialogPane();
  	              dialogPane.getStylesheets().add("application/application.css");
  	              dialogPane.getStyleClass().add("myDialog");
  	              
  	              // Show the alert
  	              alert.showAndWait();
	    	  }
	    	  
	    	  enrollmentIdField.setText(enrollmentIdString); 
	    	  enrollmentIdField.setEditable(false);
	    	  
	    	  gridpane.add(enrollmentIdField, 0, 1);
	    	  
	    	  //Button checkAvailabilityEnrollmentIdButton = new Button("Check Availability");
	    	  //gridpane.add(checkAvailabilityEnrollmentIdButton, 1, 1);
	    	  
	    	  studentIdField = new TextField();
	    	  studentIdField.setPromptText("Student ID"); //prompt text similar to a placeholder text in html, greyed out text that lets the user know what to type there
	    	  gridpane.add(studentIdField, 0, 2);
	    	  
	    	  Button findStudentButton = new Button("Find Student");
	    	  gridpane.add(findStudentButton, 1, 2);
	    	  
	    	  studentFullNameField = new TextField();
	    	  studentFullNameField.setPromptText("Student Name");
	    	  studentFullNameField.setEditable(false); //not editable
	    	  gridpane.add(studentFullNameField, 0, 3);
	    	  
	    	  courseIdField = new TextField();
	    	  courseIdField.setPromptText("Course ID");
	    	  gridpane.add(courseIdField, 0, 4);
	    	  
	    	  Button findCourseButton = new Button("Find Course");
	    	  gridpane.add(findCourseButton, 1, 4);
	    	  
	    	  courseNumberField = new TextField(); 
	    	  courseNumberField.setPromptText("Course Number");
	    	  courseNumberField.setEditable(false); //not editable
	    	  gridpane.add(courseNumberField, 0, 5);
	    	  
	    	  courseNameField = new TextField(); 
	    	  courseNameField.setPromptText("Course Name");
	    	  courseNameField.setEditable(false); //not editable
	    	  gridpane.add(courseNameField, 0, 6);
	    	  	   
	    	  Label enrollmentSemesterLabel = new Label("Semester");
	    	  gridpane.add(enrollmentSemesterLabel, 0, 7);
	    	  
	    	  enrollmentSemesterChoiceBox = new ChoiceBox<>();
	    	  enrollmentSemesterChoiceBox.setItems(FXCollections.observableArrayList(
					"SPRING", "SUMMER", 
					"FALL", "WINTER"));
	    	  gridpane.add(enrollmentSemesterChoiceBox, 0, 8);
	    	  
	    	  Label enrollmentYearLabel = new Label("Year");
	    	  gridpane.add(enrollmentYearLabel, 1, 7);
	    	  
	    	  enrollmentYearChoiceBox = new ChoiceBox<>();
	    	  enrollmentYearChoiceBox.setItems(FXCollections.observableArrayList(
					"2023", "2024", 
					"2025", "2026"));
	    	  gridpane.add(enrollmentYearChoiceBox, 1, 8);
	    	  
	    	  Button createEnrollmentButton = new Button("Create Enrollment");
	    	  gridpane.add(createEnrollmentButton, 0, 9);
	    	  createEnrollmentButton.setDisable(true);
	    	  
	    	  Button enrollmentResetButton = new Button("Reset");
	    	  gridpane.add(enrollmentResetButton, 1, 9);
	    	  
	    	  Label badgeStudentId = new Label("Search for student ID first");
	    	  badgeStudentId.getStyleClass().add("badge"); // add the css class
	    	  badgeStudentId.setVisible(true);             // initially badge will read this
	    	  
	    	  gridpane.add(badgeStudentId, 0, 10);
	    	  
	    	  Label badgeCourseId = new Label("Search for course ID first");
	    	  badgeCourseId.getStyleClass().add("badge"); // add the css class
	    	  badgeCourseId.setVisible(true);             // initially badge will read this
	    	  
	    	  gridpane.add(badgeCourseId, 0, 11);
	    	  
	    	  gridpane.setHgap(10);
	    	  gridpane.setVgap(10);
	    	  gridpane.setPadding(new Insets(10));
	    	  
	    	  
	    	  
	          
	          borderPane.setCenter(gridpane);
	          
	          gridpane.setAlignment(Pos.CENTER);
	                   
	          // Update the existing scene with the new center
	          primaryStage.getScene().setRoot(borderPane);
	          
	          
	          // Register the event handler.
	          //checkAvailabilityEnrollmentIdButton.setOnAction(new AddEnrollmentCheckAvailabilityEnrollmentIdBtnClickHandler());
	          
	          class AddEnrollmentFindStudentButtonClickHandler implements EventHandler<ActionEvent> { // similar to view student
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {

	    	    	  String studentIdString = studentIdField.getText();
	    	    	
	    	    	  int studentId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (studentIdString.equals( "")) {
	    	    		  
	    	    		  studentFullNameField.setText("");
	    	    		  badgeStudentId.setText("Student ID cannot be blank.");
	    	    		  badgeStudentId.setVisible(true);
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Student ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    			  studentId = Integer.parseInt(studentIdString);
	    	    	    	  
	    	    			  Student studentToDisplay = databaseManager.getStudent(studentId);
	    	    			  
	    	    	    	  
	    	    	    	  if (studentToDisplay == null) {
	    	    	    	   
	    	    	    		  studentFullNameField.setText("");
	    	    	    		  createEnrollmentButton.setDisable(true);

	    	    	    		  badgeStudentId.setText("Student ID not found.");
	    	    	    		  badgeStudentId.setVisible(true);
	    	    	    		  
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The student ID could not be found. Choose a different ID to create a successful enrollment.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } else {
	    	    	    		  studentFullNameField.setText(studentToDisplay.getStudentFirstName() + " " + studentToDisplay.getStudentLastName());
	    	    	    		  studentIdValidated = true;
	    	    	    		  studentIdField.setDisable(true);
	    		    		      checkUpdateReady(createEnrollmentButton, badgeStudentId, badgeCourseId);

	    	    	            	
	    	    	              
	    	    	    	  }
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			  
	    	    			  studentFullNameField.setText("");
	    	    			  createEnrollmentButton.setDisable(true);

    	    	    		  badgeStudentId.setText("Student ID not found.");
    	    	    		  badgeStudentId.setVisible(true);
	    	    			  
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Student ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			  studentFullNameField.setText("");
	    	    			  createEnrollmentButton.setDisable(true);

    	    	    		  badgeStudentId.setText("Student ID not found.");
    	    	    		  badgeStudentId.setVisible(true);
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	      }
	          }
	          
	          // Register the event handler.
	          findStudentButton.setOnAction(new AddEnrollmentFindStudentButtonClickHandler());
	          
	          class AddEnrollmentFindCourseButtonClickHandler implements EventHandler<ActionEvent> { // similar to view student
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {

	    	    	  String courseIdString = courseIdField.getText();
	    	    	
	    	    	  int courseId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (courseIdString.equals( "")) {
	    	    		  
	    	    		  courseNumberField.setText("");
	    	    		  courseNameField.setText("");
	    	    		  badgeCourseId.setText("Course ID cannot be blank.");
	    	    		  badgeCourseId.setVisible(true);
	    	    		  
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Course ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    			  courseId = Integer.parseInt(courseIdString);
	    	    	    	  
	    	    			  Course courseToDisplay = databaseManager.getCourse(courseId);
	    	    			  
	    	    	    	  
	    	    	    	  if (courseToDisplay == null) {
	    	    	    	   
	    	    	    		  courseNumberField.setText("");
	    	    	    		  courseNameField.setText("");
	    	    	    		  createEnrollmentButton.setDisable(true);
	    	    	   

	    	    	    		  badgeCourseId.setText("Course ID not found.");
	    	    	    		  badgeCourseId.setVisible(true);
	    	    	    		  
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The course ID could not be found. Choose a different ID to create a successful enrollment.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait(); 
	    	    	    	  } else {
	    	    	    		  courseNumberField.setText(courseToDisplay.getDepartment() + " " + Integer.toString(courseToDisplay.getCourseNumber()));
	    	    	    		  courseNameField.setText(courseToDisplay.getCourseName());
	    	    	    		  courseIdValidated = true;
	    	    	    		  courseIdField.setDisable(true);
	    		    		      checkUpdateReady(createEnrollmentButton, badgeStudentId, badgeCourseId);

	    	    	            	
	    	    	              
	    	    	    	  }
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			  
	    	    			  courseNumberField.setText("");
	    	    			  courseNameField.setText("");
	    	    			  badgeCourseId.setText("Course ID not found.");
    	    	    		  badgeCourseId.setVisible(true);
	    	    			  
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Course ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			  courseNumberField.setText("");
	    	    			  courseNameField.setText("");
	    	    			  badgeCourseId.setText("Course ID not found.");
    	    	    		  badgeCourseId.setVisible(true);
	    	    			  
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	      }
	          }
	          
	          // Register the event handler.
	          findCourseButton.setOnAction(new AddEnrollmentFindCourseButtonClickHandler());
	          
	          class createEnrollmentButtonClickHandler implements EventHandler<ActionEvent> { // similar to view student
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  String studentIdString = studentIdField.getText(); // converting fields to strings so they are easier to work with
	    	    	  String enrollmentIdString = enrollmentIdField.getText();
	    	    	  String courseIdString = courseIdField.getText();
	    	    	  String semesterString = enrollmentSemesterChoiceBox.getValue();
	    	    	  String yearString = enrollmentYearChoiceBox.getValue();
	    	    	 
	    	    	  
	    	    	  int courseId, studentId, year, enrollmentId; //integer variables to try converting strings to integer
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (enrollmentIdString.equals( "")) { //show errors if any fields are blank
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Enrollment ID cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else if (studentIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Student ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  if (courseIdString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Course ID cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (semesterString == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Semester cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  if (yearString == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Year cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    	    	  //try to convert from string to integer and if there is an error show an alert
	    	    			  enrollmentId = Integer.parseInt(enrollmentIdString);
	    	    	    	  studentId = Integer.parseInt(studentIdString);
	    	    	    	  courseId = Integer.parseInt(courseIdString);
	    	    	    	  year = Integer.parseInt(yearString);
	    	    			  
	    	    	    	  //use the object you created in the midterm to add an enrollment to the file
	    	    			  boolean addEnrollmentSuccessful = databaseManager.addEnrollment(studentId, courseId, year, semesterString, 'X');
	    	    	    	  
	    	    			  //if an enrollment could not be added show an error
	    	    	    	  if (!addEnrollmentSuccessful) {
	    	    	    		  
	    	    	    		  studentIdField.setDisable(false);
	    	    	    		  courseIdField.setDisable(false);
	    	    	    		  badgeStudentId.setText("Search for student ID first");
	    	    	    		  badgeStudentId.setVisible(true);
	    	    	    		  badgeCourseId.setText("Search for course ID first");
	    	    	    		  badgeCourseId.setVisible(true);
		    			    	  
		    			    	  studentIdValidated = false;
		    			    	  courseIdValidated = false;
		    			    	  checkUpdateReady(createEnrollmentButton, badgeStudentId, badgeCourseId);
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("Error: Duplicate enrollment. Combination of enrollment ID, student ID, course ID, and year must be unique, so no enrollment can be added at this time.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } else {// if enrollment could be added set all the fields to blank and show a confirmation alert
	    	    	    		  
	    		    	    	  studentIdField.setText("");
	    		    	    	  courseIdField.setText("");
	    		    	    	  enrollmentSemesterChoiceBox.setValue(null);
	    		    	    	  enrollmentYearChoiceBox.setValue(null);
	    		    	    	  studentFullNameField.setText("");
	    		    	    	  courseNumberField.setText("");
	    		    	    	  courseNameField.setText("");
	    		    	    	     		    	    	  
	    		    	    	  enrollmentIdField.setText(Integer.toString(databaseManager.getLastEnrollmentId() + 1)); 
	    		    	    	  enrollmentIdField.setEditable(false);
	    		    	    	  
	    		    	    	  studentIdField.setDisable(false);
	    	    	    		  courseIdField.setDisable(false);
	    	    	    		  badgeStudentId.setText("Search for student ID first");
	    	    	    		  badgeStudentId.setVisible(true);
	    	    	    		  badgeCourseId.setText("Search for course ID first");
	    	    	    		  badgeCourseId.setVisible(true);
		    			    	  
		    			    	  studentIdValidated = false;
		    			    	  courseIdValidated = false;
		    			    	  checkUpdateReady(createEnrollmentButton, badgeStudentId, badgeCourseId);
	    		    	    	  
	    		    	    	// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    	    	              alert.setTitle("Complete");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("Enrollment created");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  }
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  }
	    	    		  catch (NumberFormatException parseIntFail) {// show an alert if can't convert to integer
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Enrollment ID, Student ID, and Course ID");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) { //if anything else goes wrong, show the error message instead of having the program crash
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	      }
	          }
	          
	          // Register the event handler.
	          createEnrollmentButton.setOnAction(new createEnrollmentButtonClickHandler());
	          
	          class addEnrollmentResetBtnClickHandler implements EventHandler<ActionEvent> {//class to reset student update information
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    		  try {//try to clear all the input fields
	    	    			      studentIdField.setText("");
	    	    			      studentFullNameField.setText("");
	    	    			      courseIdField.setText("");
	    	    			      courseNumberField.setText("");
	    	    			      courseNameField.setText("");
	    	    			      enrollmentSemesterChoiceBox.setValue(null);
	    	    			      enrollmentYearChoiceBox.setValue(null);
	    	    	    		  studentIdField.setDisable(false);
	    	    	    		  courseIdField.setDisable(false);
	    	    	    		  badgeStudentId.setText("Search for student ID first");
	    	    	    		  badgeStudentId.setVisible(true);
	    	    	    		  badgeCourseId.setText("Search for course ID first");
	    	    	    		  badgeCourseId.setVisible(true);
		    			    	  
		    			    	  studentIdValidated = false;
		    			    	  courseIdValidated = false;
		    			    	  checkUpdateReady(createEnrollmentButton, badgeStudentId, badgeCourseId);
	    	    	    	  }
	    	    		  catch (Exception e) {//if it doesn't work, catch the exception by displaying an alert box
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
		    	              dialogPane.getStylesheets().add("application/application.css");
		    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  
	    	    	  
	    	    	  
	    	    	 
	    	  
	    	      }
	          }
	          
	          // Register the event handler for reseting the student, if you don't register it this way, nothing happens when you click, similar to add event listener on click in javascript
	          enrollmentResetButton.setOnAction(new addEnrollmentResetBtnClickHandler());
	          
	          
	      }
      }
      
      // Register the event handler.
      addEnrollmentItem.setOnAction(new AddEnrollmentClickHandler());
      
      class viewEditEnrollmentClickHandler implements EventHandler<ActionEvent> {// similar to view student but now adding a table view, this was just a GUI skeleton, but I need to create/use existing classes to actually fill the table view, this is just an empty table view but more work needs to be done, referencing google, chatGPT and videos on youtube for this
	      @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  
	    	  //Label studentIdLabel = new Label("Student ID");
	    	  
	    	  studentIdField = new TextField();
	    	  studentIdField.setPromptText("Student ID");
	    	  studentIdField.setMaxWidth(100);
	    	  
	    	  Button populateEnrollmentButton = new Button("Populate");
	    	  
	    	  //Label courseIdLabel = new Label("Course ID");
	    	  
	    	  courseIdField = new TextField();
	    	  courseIdField.setPromptText("Course ID");
	    	  courseIdField.setMaxWidth(100);
	    	  
	    	  Label enrollmentSemesterLabel = new Label("Sem");
	    	  
	    	  enrollmentSemesterChoiceBox = new ChoiceBox<>();
	    	  enrollmentSemesterChoiceBox.setItems(FXCollections.observableArrayList(
					"SPRING", "SUMMER", 
					"FALL", "WINTER"));
	    	
	    	  Label enrollmentYearLabel = new Label("Yr");
	    	 
	    	  enrollmentYearChoiceBox = new ChoiceBox<>();
	    	  enrollmentYearChoiceBox.setItems(FXCollections.observableArrayList(
					"2023", "2024", 
					"2025", "2026"));
	    	 
	    	  Button dropClassButton = new Button("Drop Class");
	    	  
	    	  
	    	  HBox hbox = new HBox(10, studentIdField, populateEnrollmentButton, courseIdField,  enrollmentSemesterLabel, enrollmentSemesterChoiceBox, enrollmentYearLabel, enrollmentYearChoiceBox, dropClassButton);//could line this up exactly with the table view, moving on to save time for now, come back to this
	    	  
	    	  hbox.setPadding(new Insets(10));
	    	  
	    	// Create TableView
	          TableView<EnrollmentTableViewData> enrollmentTableView = new TableView<>(); //table view creates a pretty spreadsheet for easy viewing and organization of data
	          
	          // Create TableColumn headers
	          TableColumn<EnrollmentTableViewData, String> nameColumn = new TableColumn<>("Name");
	          TableColumn<EnrollmentTableViewData, String> courseNumberColumn = new TableColumn<>("Course #");
	          TableColumn<EnrollmentTableViewData, Integer> courseIdColumn = new TableColumn<>("Course ID");
	          TableColumn<EnrollmentTableViewData, String> semesterColumn = new TableColumn<>("Semester");
	          TableColumn<EnrollmentTableViewData, Integer> yearColumn = new TableColumn<>("Year");
	          TableColumn<EnrollmentTableViewData, Character> gradeColumn = new TableColumn<>("Grade");
	          
	          boolean dataFailedToLoad = false; 
	          try {
	    			//bind the data in the object to the data in the table view columns
	    			nameColumn.setCellValueFactory(new StudentNameCellValueFactory());
	    			courseNumberColumn.setCellValueFactory(new CourseNumberCellValueFactory());
	    			courseIdColumn.setCellValueFactory(new CourseIdCellValueFactory());
	    			semesterColumn.setCellValueFactory(new SemesterCellValueFactory());
	    			yearColumn.setCellValueFactory(new YearCellValueFactory());
	    			gradeColumn.setCellValueFactory(new GradeCellValueFactory());
	    			// Set cell value factories using lambda expressions
	    			
	  	          
	    		}
	    		
	    		catch (Exception e) {
	    			dataFailedToLoad = true; 
	    		}
	       
	          
	          /*nameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
	          courseNumberColumn.setCellValueFactory(new PropertyValueFactory<>("courseNumber"));
	          courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
	          semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
	          yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
	          gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));*///didn't work for me spent hours trying to fix it trying another route
	          
	          nameColumn.setMinWidth(240); //240 min width better for name, enough space for name, was doing demo and not enough space for name
	          courseNumberColumn.setMinWidth(105); //127 min width better for equally large columns, doesn't look as good though
	          courseIdColumn.setMinWidth(105);
	          semesterColumn.setMinWidth(105);
	          yearColumn.setMinWidth(105);
	          gradeColumn.setMinWidth(105);
	          
	          // Add TableColumn headers to the TableView
	          enrollmentTableView.getColumns().addAll(nameColumn, courseNumberColumn, courseIdColumn , semesterColumn, yearColumn, gradeColumn);

	          // Populate TableView with data (empty data for demonstration)
	          ObservableList<EnrollmentTableViewData> enrollmentTableViewData = FXCollections.observableArrayList(
	          );
	          enrollmentTableView.setItems(enrollmentTableViewData);
	          
	          
	          // Create a Label for the message
	          Label messageLabel = new Label("No content in table");
	          
	          // Set the message label as the TableView's placeholder, shows up when the table is empty
	          enrollmentTableView.setPlaceholder(messageLabel);
	          
	    	  
	    	  VBox vbox = new VBox(10, hbox, enrollmentTableView);
	    	  
	    	  vbox.setPadding(new Insets(0, 15, 0, 15)); // 15px padding left and right of the tableview, still doesn't line up perfectly with labels above it but I can perfect that later if time, still looks okay
	    	  
	          borderPane.setCenter(vbox);
	               
	          // Update the existing scene with the new center
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class viewEditEnrollmentPopulateEnrollmentBtnClickHandler implements EventHandler<ActionEvent> {// similar to view student but now adding a table view, this was just a GUI skeleton, but I need to create/use existing classes to actually fill the table view, this is just an empty table view but more work needs to be done, referencing google, chatGPT and videos on youtube for this
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  String studentIdString = studentIdField.getText();
		    	    	
	    	    	  int studentId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  // check if the field is blank and if so show an alert
	    	    	  if (studentIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Student ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  {
	    	    		  try {//try to convert the student id into an integer
	    	    			  studentId = Integer.parseInt(studentIdString);
	    	    	    	  
	    	    			  //get the student with the id to see if it exists, if it doesn't exist, show an error message
	    	    			  Student studentToDisplay = databaseManager.getStudent(studentId);
	    	    			  // get list of enrollments with the specified student id
	    	    			  ArrayList<Enrollment> enrollmentDetailsList = databaseManager.getListOfEnrollmentsWithStudentId(studentId);
	    	    			  
	    	    	    	  
	    	    	    	  if (studentToDisplay == null) {
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The student ID could not be found. Choose a different ID for a successful view and edit data.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } 
	    	    	    	  
	    	    	    	  else if (enrollmentDetailsList.size() == 0) {
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    	    	              alert.setTitle("No Enrollments for Specified Student ID");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The student ID has no enrollments. Choose a different ID for a successful view and edit data.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  }
	    	    	    	  
	    	    	    	  else {
	    	    	    		  boolean dataFailedToLoad = false; 
	    	    	    	
	    	    	    		  //need an observable list for the table view data so it can be displayed
	    	    	    		  ObservableList<EnrollmentTableViewData> enrollmentTableViewDataList = FXCollections.observableArrayList();
	    	    	    		  
	    	    	    		  for (int i = 0; i < enrollmentDetailsList.size(); i++) {//for loop for each of the enrollments in the array list of all enrollments with the specified student id
	    	    	    			  
	    	    	    			  Enrollment enrollment = enrollmentDetailsList.get(i);
	    	    	    			  
	    	    	    			  EnrollmentTableViewData enrollmentTableViewData = new EnrollmentTableViewData();// create a new object for each entry in the table view, need a brand new object since objects are passed by reference
	    	    	    			  
	    	    	    			  Course course = databaseManager.getCourse(enrollment.getCourseId()); //create a course object for each enrollment
	    	    	    			  if (course != null) {// if the course exists, set the course number
	    	    	    			      enrollmentTableViewData.setCourseNumber(course.getDepartment() + " " + Integer.toString(course.getCourseNumber()));
	    	    	    			      //enrollmentTableViewData.setCourseName(course.getCourseName());
	    	    	    			  } else {
	    	    	    			      // Handle the case where the course is not found
	    	    	    			      // For example, set a default or error value
	    	    	    			      enrollmentTableViewData.setCourseNumber(""); // Or some other default/error value
	    	    	    			  }
	    	    	    			  //set the values in the object that will populate each entry in the table view, these objects are all inside the observable list
	    	    	    			  enrollmentTableViewData.setStudentName(studentToDisplay.getStudentFirstName() + " " + studentToDisplay.getStudentLastName());
	    	    	    			  enrollmentTableViewData.setCourseId(enrollment.getCourseId());
	    	    	    			  //enrollmentTableViewData.setCourseId(enrollment.getStudentId());
	    	    	    			  enrollmentTableViewData.setSemester(enrollment.getSemester());
	    	    	    			  enrollmentTableViewData.setYear(enrollment.getYear());
	    	    	    			  enrollmentTableViewData.setGrade(enrollment.getGrade());
	    	    	    			  //enrollmentTableViewData.setStudentId(studentId);
	    	    	    			  
	    	    	    		
	    	    	    			    
	    	    	    			    // Wrap data in SimpleStringProperty, SimpleIntegerProperty, SimpleObjectProperty, need to do this because that's how the binding the object data to the table view data works
	    	    	    			    SimpleStringProperty studentNameProperty = new SimpleStringProperty(enrollmentTableViewData.getStudentName());
	    	    	    			    //SimpleStringProperty courseNameProperty = new SimpleStringProperty(enrollmentTableViewData.getCourseName());
	    	    	    			    SimpleIntegerProperty courseIdProperty = new SimpleIntegerProperty(enrollmentTableViewData.getCourseId());
	    	    	    			    //SimpleIntegerProperty studentIdProperty = new SimpleIntegerProperty(enrollmentTableViewData.getStudentId());
	    	    	    			    SimpleStringProperty courseNumberProperty = new SimpleStringProperty(enrollmentTableViewData.getCourseNumber());
	    	    	    			    SimpleStringProperty semesterProperty = new SimpleStringProperty(enrollmentTableViewData.getSemester());
	    	    	    			    SimpleIntegerProperty yearProperty = new SimpleIntegerProperty(enrollmentTableViewData.getYear());
	    	    	    			    SimpleObjectProperty<Character> gradeProperty = new SimpleObjectProperty<>(enrollmentTableViewData.getGrade());
	    	    	    			    
	    	    	    			    // Add wrapped properties to the TableView, note that some of the items repeat, i did this because i didn't want to create properties that weren't used
	    	    	    			    enrollmentTableViewDataList.add(new EnrollmentTableViewData(
	    	    	    			            studentNameProperty,
	    	    	    			            studentNameProperty,
	    	    	    			            courseIdProperty,
	    	    	    			            courseIdProperty,
	    	    	    			            courseNumberProperty,
	    	    	    			            semesterProperty,
	    	    	    			            yearProperty,
	    	    	    			            gradeProperty
	    	    	    			    ));
	    	    	    		
	    	    	    		  }
	    	    	    		  
	    	    	    		// Set the populated list to the TableView
	    	    	    		  enrollmentTableView.setItems(enrollmentTableViewDataList);

	    	    	    		  // Refresh the TableView
	    	    	    		  enrollmentTableView.refresh();
	    	    	    		  
	    	    	    		  //System.out.println(enrollmentTableViewDataList); leaving this message in case i need to debug again
	    	    	    		  
	    	    	    		  
	    	    	    	
	    	    	    		  //if the data failed to load, show an alert
	    	    	    		  if (dataFailedToLoad) {
	    	    	    			// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("At least one of the rows failed to load.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
	    	    	    		  }
	    	    	    		  
	    	    	    		
	    	    	    	}
	    	    		  }
	    	    	    	  
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Student ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	     
	    	      }
	    	  }
	          
	          // Register the event handler.
	          populateEnrollmentButton.setOnAction(new viewEditEnrollmentPopulateEnrollmentBtnClickHandler());
	          
	          class viewEditEnrollmentDropClassButtonBtnClickHandler implements EventHandler<ActionEvent> {// this does the same exact thing as the populate button, but it also calls the drop method instead of the add method of the enrollment file manager class
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  String studentIdString = studentIdField.getText();
	    	    	  String courseIdString = courseIdField.getText();
	    	    	  String semesterString = enrollmentSemesterChoiceBox.getValue();
	    	    	  String yearString = enrollmentYearChoiceBox.getValue();
	    	    	  
		    	    	
	    	    	  int studentId, courseId, year;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (studentIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Student ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else if (courseIdString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Course ID cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else if (semesterString == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Semester cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else if (yearString == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Year cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    			  studentId = Integer.parseInt(studentIdString);
	    	    			  courseId = Integer.parseInt(courseIdString);
	    	    			  year = Integer.parseInt(yearString);
	    	    	    	  
	    	    			  Student studentToDisplay = databaseManager.getStudent(studentId);
	    	    			  Course courseToDisplay = databaseManager.getCourse(courseId);
	    	    			  Enrollment enrollmentToDrop = databaseManager.getEnrollment(studentId, courseId, year, semesterString);
	    	    			  ArrayList<Enrollment> enrollmentDetailsList = databaseManager.getListOfEnrollmentsWithStudentId(studentId);
	    	    			  
	    	    	    	  
	    	    	    	  if (studentToDisplay == null) {
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The student ID could not be found. Choose a different ID for a successful view and edit data.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } 
	    	    	    	  
	    	    	    	  else if (courseToDisplay == null) {
		    	    	    	   
		    	    	    		// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("The course ID could not be found. Choose a different ID for a successful view and edit data.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
		    	    	    	  } 
	    	    	    	  else if (enrollmentDetailsList.size() == 0) {
		    	    	    		// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("The student ID has no enrollments so no classes can be dropped at this time. Choose a different ID for a successful drop class.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
		    	    	    	  }
	    	    	    	  
	    	    	    	  else if (enrollmentToDrop == null) {
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The student ID, course ID, semester and year combination has no enrollments. Choose a different combination of data for a successful drop class.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  }
	    	    	    	  
	    	    	    	  else {
	    	    	    		  boolean dataFailedToLoad = false;
	    	    	    		  boolean dropEnrollmentSuccessful;
	    	    	    		  
	    	    	    		  enrollmentDetailsList.clear();
	    	    	    	
	    	    	    		  ObservableList<EnrollmentTableViewData> enrollmentTableViewDataList = FXCollections.observableArrayList();
	    	    	    		  
	    	    	    		  
	    	    	    		  dropEnrollmentSuccessful = databaseManager.dropEnrollment(studentId, courseId, year, semesterString);
	    	    	    		  enrollmentDetailsList.clear();//clear the enrollment data list so that brand new modifed data can be entered
	    	    	    		  enrollmentDetailsList = databaseManager.getListOfEnrollmentsWithStudentId(studentId);
	    	    	    		  
	    	    	    		  for (int i = 0; i < enrollmentDetailsList.size(); i++) {
	    	    	    			  Enrollment enrollment = enrollmentDetailsList.get(i);
	    	    	    			  
	    	    	    			  EnrollmentTableViewData enrollmentTableViewData = new EnrollmentTableViewData();
	    	    	    			  
	    	    	    			  Course course = databaseManager.getCourse(enrollment.getCourseId());
	    	    	    			  if (course != null) {
	    	    	    			      enrollmentTableViewData.setCourseNumber(course.getDepartment() + " " + Integer.toString(course.getCourseNumber()));
	    	    	    			  } else {
	    	    	    			      // Handle the case where the course is not found
	    	    	    			      // For example, set a default or error value
	    	    	    			      enrollmentTableViewData.setCourseNumber(""); // Or some other default/error value
	    	    	    			  }
	    	    	    			  
	    	    	    			  enrollmentTableViewData.setStudentName(studentToDisplay.getStudentFirstName() + " " + studentToDisplay.getStudentLastName());
	    	    	    			  enrollmentTableViewData.setCourseId(enrollment.getCourseId());
	    	    	    			  enrollmentTableViewData.setSemester(enrollment.getSemester());
	    	    	    			  enrollmentTableViewData.setYear(enrollment.getYear());
	    	    	    			  enrollmentTableViewData.setGrade(enrollment.getGrade());
	    	    	    			
	    	    	    			    
	    	    	    			    // Wrap data in SimpleStringProperty, SimpleIntegerProperty, SimpleObjectProperty
	    	    	    			    SimpleStringProperty studentNameProperty = new SimpleStringProperty(enrollmentTableViewData.getStudentName());
	    	    	    			    SimpleIntegerProperty courseIdProperty = new SimpleIntegerProperty(enrollmentTableViewData.getCourseId());
	    	    	    			    SimpleStringProperty courseNumberProperty = new SimpleStringProperty(enrollmentTableViewData.getCourseNumber());
	    	    	    			    SimpleStringProperty semesterProperty = new SimpleStringProperty(enrollmentTableViewData.getSemester());
	    	    	    			    SimpleIntegerProperty yearProperty = new SimpleIntegerProperty(enrollmentTableViewData.getYear());
	    	    	    			    SimpleObjectProperty<Character> gradeProperty = new SimpleObjectProperty<>(enrollmentTableViewData.getGrade());
	    	    	    			    
	    	    	    			    // Add wrapped properties to the TableView
	    	    	    			    enrollmentTableViewDataList.add(new EnrollmentTableViewData(
	    	    	    			            studentNameProperty,
	    	    	    			            studentNameProperty, //supposed to be course Name but not used so doesn't matter here
	    	    	    			            courseIdProperty,
	    	    	    			            courseIdProperty, //supposed to be student ID but not used so doesn't matter here
	    	    	    			            courseNumberProperty,
	    	    	    			            semesterProperty,
	    	    	    			            yearProperty,
	    	    	    			            gradeProperty
	    	    	    			    ));
	    	    	    		
	    	    	    			
	    	    	    		
	    	    	              /*System.out.println(enrollmentTableViewData.getStudentName());
	    	    	              System.out.println(enrollmentTableViewData.getCourseNumber());
	    	    	              System.out.println(enrollmentTableViewData.getCourseId());
	    	    	              System.out.println(enrollmentTableViewData.getSemester());
	    	    	              System.out.println(enrollmentTableViewData.getYear());
	    	    	              System.out.println(enrollmentTableViewData.getGrade());*/
	    	    	    		  }
	    	    	    		  
	    	    	    		// Set the populated list to the TableView
	    	    	    		  enrollmentTableView.setItems(enrollmentTableViewDataList);

	    	    	    		  // Refresh the TableView
	    	    	    		  enrollmentTableView.refresh();
	    	    	    		  
	    	    	    		 // System.out.println(enrollmentTableViewDataList);
	    	    	    		  
	    	    	    		  
	    	    	    		  if (dropEnrollmentSuccessful) {
	    	    	    			// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    	    	              alert.setTitle("Complete");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("Class dropped");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
	    	    	    		  }
	    	    	    		  
	    	    	    		  else if (!dropEnrollmentSuccessful) {
	    	    	    			  Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("Class could not be dropped. Check that the data you entered is valid.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
	    	    	    		  }
	    	    	    	
	    	    	    		  else if (dataFailedToLoad) {
	    	    	    			// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("At least one of the rows failed to load.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
	    	    	    		  }
	    	    	    		  
	    	    	    		  
	    	    	    		  
	    	    	    		
	    	    	    	}
	    	    		  }
	    	    	    	  
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Student ID, Course ID, and Year.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	     
	    	      }
	    	  }
	          
	          // Register the event handler.
	          dropClassButton.setOnAction(new viewEditEnrollmentDropClassButtonBtnClickHandler());
	      }
      }
      
      // Register the event handler.
      viewEditEnrollmentItem.setOnAction(new viewEditEnrollmentClickHandler());
	  
	  Menu gradesMenu = new Menu("Grades");
	  
	  Menu addEditGradesMenu = new Menu("Add/Edit Grades"); //submenu of a menu grades, multilayered menu
	  
	  MenuItem addEditGradesByStudentItem = new MenuItem("By Student"); //final menu items under the submenu
	  MenuItem addEditGradesByCourseItem = new MenuItem("By Course");
	  
	  gradesMenu.getItems().addAll(addEditGradesMenu);
	  addEditGradesMenu.getItems().addAll(addEditGradesByStudentItem, addEditGradesByCourseItem);
	  
	  class addEditGradesByStudentClickHandler implements EventHandler<ActionEvent> {//similar to view edit enrollment, use the same exact table view as the view edit enrollment
	      @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  
	    	  Label studentIdLabel = new Label("Student ID");
	 
	    	  studentIdField = new TextField();
	    	  studentIdField.setMaxWidth(125);
	    	  
	    	  Button getGradesButton = new Button("Get Grades");
	    	  
	    	  
	    	  HBox hboxStudentId = new HBox(10, studentIdLabel, studentIdField, getGradesButton);//could line this up exactly with the table view, moving on to save time for now, come back to this
	    	  
	    	  hboxStudentId.setPadding(new Insets(10));
	    	
	    	  
	    	// Create TableView
	          TableView<EnrollmentTableViewData> addEditGradesByStudentTableView = new TableView<>(); //table view creates a pretty spreadsheet for easy viewing and organization of data
	          
	          // Create TableColumn headers
	          TableColumn<EnrollmentTableViewData, String> nameColumn = new TableColumn<>("Name");
	          TableColumn<EnrollmentTableViewData, String> courseNumberColumn = new TableColumn<>("Course #");
	          TableColumn<EnrollmentTableViewData, Integer> courseIdColumn = new TableColumn<>("Course ID");
	          TableColumn<EnrollmentTableViewData, String> semesterColumn = new TableColumn<>("Semester");
	          TableColumn<EnrollmentTableViewData, Integer> yearColumn = new TableColumn<>("Year");
	          TableColumn<EnrollmentTableViewData, Character> gradeColumn = new TableColumn<>("Grade");
	    	  
	    	  
	          
	          nameColumn.setMinWidth(240);
	          courseNumberColumn.setMinWidth(105);
	          courseIdColumn.setMinWidth(105);
	          semesterColumn.setMinWidth(105);
	          yearColumn.setMinWidth(105);
	          gradeColumn.setMinWidth(105);
	          
	          boolean dataFailedToLoad = false; 
	          try {
	    			
	    			nameColumn.setCellValueFactory(new StudentNameCellValueFactory());
	    			courseNumberColumn.setCellValueFactory(new CourseNumberCellValueFactory());
	    			courseIdColumn.setCellValueFactory(new CourseIdCellValueFactory());
	    			semesterColumn.setCellValueFactory(new SemesterCellValueFactory());
	    			yearColumn.setCellValueFactory(new YearCellValueFactory());
	    			gradeColumn.setCellValueFactory(new GradeCellValueFactory());
	    			
	    		}
	    		
	    		catch (Exception e) {
	    			dataFailedToLoad = true; 
	    		}
	          
	          // Add TableColumn headers to the TableView
	          addEditGradesByStudentTableView.getColumns().addAll(nameColumn, courseNumberColumn, courseIdColumn , semesterColumn, yearColumn, gradeColumn);
	          
	       // Populate TableView with data (dummy data for demonstration)
	          ObservableList<EnrollmentTableViewData> addEditGradesByStudentTableViewData = FXCollections.observableArrayList(
	          );
	          addEditGradesByStudentTableView.setItems(addEditGradesByStudentTableViewData);
	          
	      
	          
	          // Create a Label for the message
	          Label messageLabel = new Label("No content in table");
	          
	          // Set the message label as the TableView's placeholder
	          addEditGradesByStudentTableView.setPlaceholder(messageLabel);
	          
	          
	          Label courseIdLabel = new Label("Course ID");
	    	  
	    	  courseIdField = new TextField();
	    	  //courseIdField.setPromptText("Course ID");
	    	  courseIdField.setMaxWidth(125);
	    	  
	    	  /*courseIdField = new TextField();
	    	  courseIdField.setPromptText("Course ID");
	    	  courseIdField.setMaxWidth(100);*/
	    	  
	    	  Label enrollmentSemesterLabel = new Label("Sem");
	    	  
	    	  enrollmentSemesterChoiceBox = new ChoiceBox<>();
	    	  enrollmentSemesterChoiceBox.setItems(FXCollections.observableArrayList(
					"SPRING", "SUMMER", 
					"FALL", "WINTER"));
	    	
	    	  Label enrollmentYearLabel = new Label("Yr");
	    	 
	    	  enrollmentYearChoiceBox = new ChoiceBox<>();
	    	  enrollmentYearChoiceBox.setItems(FXCollections.observableArrayList(
					"2023", "2024", 
					"2025", "2026"));
	    	  
	    	  Label gradeLabel = new Label("New Grade");
	    	  
	    	  gradeByStudentChoiceBox = new ChoiceBox<>();
	    	  gradeByStudentChoiceBox.setItems(FXCollections.observableArrayList(
	    			  'A', 'B', 
	    			  'C', 'D', 'F' ));
	    	  
	    	  Button editGradeButton = new Button("Edit Grade");
	    	  
	    	  HBox hboxCourseId = new HBox(10, courseIdLabel, courseIdField, enrollmentSemesterLabel, enrollmentSemesterChoiceBox, enrollmentYearLabel, enrollmentYearChoiceBox, gradeLabel, gradeByStudentChoiceBox, editGradeButton);//could line this up exactly with the table view, moving on to save time for now, come back to this
	    	  
	    	  hboxCourseId.setPadding(new Insets(10));
	          
	    	  VBox vbox = new VBox(10, hboxStudentId, addEditGradesByStudentTableView, hboxCourseId);
	    	  
	    	  vbox.setPadding(new Insets(0, 15, 0, 15)); // 10px padding left and right
	    	  
	          borderPane.setCenter(vbox);
	               
	          // Update the existing scene with the new center
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class addEditGradesByStudentGradesBtnClickHandler implements EventHandler<ActionEvent> {// similar to view student but now adding a table view, this was just a GUI skeleton, but I need to create/use existing classes to actually fill the table view, this is just an empty table view but more work needs to be done, referencing google, chatGPT and videos on youtube for this
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  String studentIdString = studentIdField.getText();
		    	    	
	    	    	  int studentId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (studentIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Student ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    			  studentId = Integer.parseInt(studentIdString);
	    	    	    	  
	    	    			  Student studentToDisplay = databaseManager.getStudent(studentId);
	    	    			  ArrayList<Enrollment> enrollmentDetailsList = databaseManager.getListOfEnrollmentsWithStudentId(studentId);
	    	    			  
	    	    	    	  
	    	    	    	  if (studentToDisplay == null) {
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The student ID could not be found. Choose a different ID for a successful view and edit grades.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } 
	    	    	    	  
	    	    	    	  else if (enrollmentDetailsList.size() == 0) {
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    	    	              alert.setTitle("No Enrollments for Specified Student ID");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The student ID has no enrollments. Choose a different ID for a successful view and edit grades.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  }
	    	    	    	  
	    	    	    	  else {
	    	    	    		  boolean dataFailedToLoad = false; 
	    	    	    	
	    	    	    		  ObservableList<EnrollmentTableViewData> enrollmentTableViewDataList = FXCollections.observableArrayList();
	    	    	    		  
	    	    	    		  for (int i = 0; i < enrollmentDetailsList.size(); i++) {
	    	    	    			  Enrollment enrollment = enrollmentDetailsList.get(i);
	    	    	    			  
	    	    	    			  EnrollmentTableViewData enrollmentTableViewData = new EnrollmentTableViewData();
	    	    	    			  
	    	    	    			  Course course = databaseManager.getCourse(enrollment.getCourseId());
	    	    	    			  if (course != null) {
	    	    	    			      enrollmentTableViewData.setCourseNumber(course.getDepartment() + " " + Integer.toString(course.getCourseNumber()));
	    	    	    			  } else {
	    	    	    			      // Handle the case where the course is not found
	    	    	    			      // For example, set a default or error value
	    	    	    			      enrollmentTableViewData.setCourseNumber(""); // Or some other default/error value
	    	    	    			  }
	    	    	    			  
	    	    	    			  enrollmentTableViewData.setStudentName(studentToDisplay.getStudentFirstName() + " " + studentToDisplay.getStudentLastName());
	    	    	    			  enrollmentTableViewData.setCourseId(enrollment.getCourseId());
	    	    	    			  enrollmentTableViewData.setSemester(enrollment.getSemester());
	    	    	    			  enrollmentTableViewData.setYear(enrollment.getYear());
	    	    	    			  enrollmentTableViewData.setGrade(enrollment.getGrade());
	    	    	    			  
	    	    	    			    
	    	    	    			    // Wrap data in SimpleStringProperty, SimpleIntegerProperty, SimpleObjectProperty
	    	    	    			    SimpleStringProperty studentNameProperty = new SimpleStringProperty(enrollmentTableViewData.getStudentName());
	    	    	    			    SimpleIntegerProperty courseIdProperty = new SimpleIntegerProperty(enrollmentTableViewData.getCourseId());
	    	    	    			    SimpleStringProperty courseNumberProperty = new SimpleStringProperty(enrollmentTableViewData.getCourseNumber());
	    	    	    			    SimpleStringProperty semesterProperty = new SimpleStringProperty(enrollmentTableViewData.getSemester());
	    	    	    			    SimpleIntegerProperty yearProperty = new SimpleIntegerProperty(enrollmentTableViewData.getYear());
	    	    	    			    SimpleObjectProperty<Character> gradeProperty = new SimpleObjectProperty<>(enrollmentTableViewData.getGrade());
	    	    	    			    
	    	    	    			    // Add wrapped properties to the TableView
	    	    	    			    enrollmentTableViewDataList.add(new EnrollmentTableViewData(
	    	    	    			            studentNameProperty,
	    	    	    			            studentNameProperty,
	    	    	    			            courseIdProperty,
	    	    	    			            courseIdProperty,
	    	    	    			            courseNumberProperty,
	    	    	    			            semesterProperty,
	    	    	    			            yearProperty,
	    	    	    			            gradeProperty
	    	    	    			    ));
	    	    	    		
	    	    	    		  }
	    	    	    		  
	    	    	    		// Set the populated list to the TableView
	    	    	    		  addEditGradesByStudentTableView.setItems(enrollmentTableViewDataList);

	    	    	    		  // Refresh the TableView
	    	    	    		  addEditGradesByStudentTableView.refresh();
	    	    	    		  
	    	    	    		  System.out.println(enrollmentTableViewDataList);
	    	    	    		  
	    	    	    		  
	    	    	    	
	    	    	    	
	    	    	    		  if (dataFailedToLoad) {
	    	    	    			// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("At least one of the rows failed to load.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
	    	    	    		  }
	    	    	    		  
	    	    	    		
	    	    	    	}
	    	    		  }
	    	    	    	  
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Student ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	     
	    	      }
	    	  }
	          
	          // Register the event handler.
	          getGradesButton.setOnAction(new addEditGradesByStudentGradesBtnClickHandler());
	          
	          class addEditGradesByStudenteditGradeBtnClickHandler implements EventHandler<ActionEvent> {// similar to view student but now adding a table view, this was just a GUI skeleton, but I need to create/use existing classes to actually fill the table view, this is just an empty table view but more work needs to be done, referencing google, chatGPT and videos on youtube for this
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  String studentIdString = studentIdField.getText();
	    	    	  String courseIdString = courseIdField.getText();
	    	    	  String semesterString = enrollmentSemesterChoiceBox.getValue();
	    	    	  String yearString = enrollmentYearChoiceBox.getValue();
	    	    	  Character gradeChar = gradeByStudentChoiceBox.getValue();
	    	    	  
	    	    	  
		    	    	
	    	    	  int studentId, courseId, year;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (studentIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Student ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else if (courseIdString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Course ID cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else if (semesterString == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Semester cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else if (yearString == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Year cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else if (gradeChar == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Grade cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    			  studentId = Integer.parseInt(studentIdString);
	    	    			  courseId = Integer.parseInt(courseIdString);
	    	    			  year = Integer.parseInt(yearString);
	    	    	    	  
	    	    			  Student studentToDisplay = databaseManager.getStudent(studentId);
	    	    			  Course courseToDisplay = databaseManager.getCourse(courseId);
	    	    			  Enrollment enrollmentToUpdate = databaseManager.getEnrollment(studentId, courseId, year, semesterString);
	    	    			  ArrayList<Enrollment> enrollmentDetailsList = databaseManager.getListOfEnrollmentsWithStudentId(studentId);
	    	    			  
	    	    	    	  
	    	    	    	  if (studentToDisplay == null) {
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The student ID could not be found. Choose a different ID for a successful add and edit grades.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } 
	    	    	    	  
	    	    	    	  else if (courseToDisplay == null) {
		    	    	    	   
		    	    	    		// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("The course ID could not be found. Choose a different ID for a successful add and edit grades.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
		    	    	    	  } 
	    	    	    	  else if (enrollmentDetailsList.size() == 0) {
		    	    	    		// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("The student ID has no enrollments so no grades can be added or changed at this time. Choose a different ID for a successful add and edit grades.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
		    	    	    	  }
	    	    	    	  
	    	    	    	  else if (enrollmentToUpdate == null) {
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The student ID, course ID, semester and year combination has no enrollments. Choose a different combination of data for a successful add and edit grades.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  }
	    	    	    	  
	    	    	    	  else {
	    	    	    		  boolean dataFailedToLoad = false;
	    	    	    		  boolean updateEnrollmentSuccessful;
	    	    	    		  
	    	    	    		  enrollmentDetailsList.clear();
	    	    	    	
	    	    	    		  ObservableList<EnrollmentTableViewData> enrollmentTableViewDataList = FXCollections.observableArrayList();
	    	    	    		  
	    	    	    		  
	    	    	    		  int enrollmentId = enrollmentToUpdate.getEnrollmentId();
	    	    	    		  
	    	    	    		  updateEnrollmentSuccessful = databaseManager.updateEnrollment(enrollmentId, studentId, courseId, year, semesterString, gradeChar);
	    	    	    		  enrollmentDetailsList.clear();
	    	    	    		  enrollmentDetailsList = databaseManager.getListOfEnrollmentsWithStudentId(studentId);
	    	    	    		  
	    	    	    		  for (int i = 0; i < enrollmentDetailsList.size(); i++) {
	    	    	    			  Enrollment enrollment = enrollmentDetailsList.get(i);
	    	    	    			  
	    	    	    			  EnrollmentTableViewData enrollmentTableViewData = new EnrollmentTableViewData();
	    	    	    			  
	    	    	    			  Course course = databaseManager.getCourse(enrollment.getCourseId());
	    	    	    			  if (course != null) {
	    	    	    			      enrollmentTableViewData.setCourseNumber(course.getDepartment() + " " + Integer.toString(course.getCourseNumber()));
	    	    	    			  } else {
	    	    	    			      // Handle the case where the course is not found
	    	    	    			      // For example, set a default or error value
	    	    	    			      enrollmentTableViewData.setCourseNumber(""); // Or some other default/error value
	    	    	    			  }
	    	    	    			  
	    	    	    			  enrollmentTableViewData.setStudentName(studentToDisplay.getStudentFirstName() + " " + studentToDisplay.getStudentLastName());
	    	    	    			  enrollmentTableViewData.setCourseId(enrollment.getCourseId());
	    	    	    			  enrollmentTableViewData.setSemester(enrollment.getSemester());
	    	    	    			  enrollmentTableViewData.setYear(enrollment.getYear());
	    	    	    			  enrollmentTableViewData.setGrade(enrollment.getGrade());
	    	    	    			
	    	    	    			    
	    	    	    			    // Wrap data in SimpleStringProperty, SimpleIntegerProperty, SimpleObjectProperty
	    	    	    			    SimpleStringProperty studentNameProperty = new SimpleStringProperty(enrollmentTableViewData.getStudentName());
	    	    	    			    SimpleIntegerProperty courseIdProperty = new SimpleIntegerProperty(enrollmentTableViewData.getCourseId());
	    	    	    			    SimpleStringProperty courseNumberProperty = new SimpleStringProperty(enrollmentTableViewData.getCourseNumber());
	    	    	    			    SimpleStringProperty semesterProperty = new SimpleStringProperty(enrollmentTableViewData.getSemester());
	    	    	    			    SimpleIntegerProperty yearProperty = new SimpleIntegerProperty(enrollmentTableViewData.getYear());
	    	    	    			    SimpleObjectProperty<Character> gradeProperty = new SimpleObjectProperty<>(enrollmentTableViewData.getGrade());
	    	    	    			    
	    	    	    			    // Add wrapped properties to the TableView
	    	    	    			    enrollmentTableViewDataList.add(new EnrollmentTableViewData(
	    	    	    			            studentNameProperty,
	    	    	    			            studentNameProperty,
	    	    	    			            courseIdProperty,
	    	    	    			            courseIdProperty,
	    	    	    			            courseNumberProperty,
	    	    	    			            semesterProperty,
	    	    	    			            yearProperty,
	    	    	    			            gradeProperty
	    	    	    			    ));
	    	    	    		
	    	    	    			
	    	    	    		
	    	    	              /*System.out.println(enrollmentTableViewData.getStudentName());
	    	    	              System.out.println(enrollmentTableViewData.getCourseNumber());
	    	    	              System.out.println(enrollmentTableViewData.getCourseId());
	    	    	              System.out.println(enrollmentTableViewData.getSemester());
	    	    	              System.out.println(enrollmentTableViewData.getYear());
	    	    	              System.out.println(enrollmentTableViewData.getGrade());*/
	    	    	    		  }
	    	    	    		  
	    	    	    		// Set the populated list to the TableView
	    	    	    		  addEditGradesByStudentTableView.setItems(enrollmentTableViewDataList);

	    	    	    		  // Refresh the TableView
	    	    	    		  addEditGradesByStudentTableView.refresh();
	    	    	    		  
	    	    	    		 // System.out.println(enrollmentTableViewDataList);
	    	    	    		  
	    	    	    		  
	    	    	    		  if (updateEnrollmentSuccessful) {
	    	    	    			// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    	    	              alert.setTitle("Complete");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("Grade updated");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
	    	    	    		  }
	    	    	    		  
	    	    	    		  else if (!updateEnrollmentSuccessful) {
	    	    	    			  Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("Grade could not be added or changed. Check that the data you entered is valid.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
	    	    	    		  }
	    	    	    	
	    	    	    		  else if (dataFailedToLoad) {
	    	    	    			// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("At least one of the rows failed to load.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
	    	    	    		  }
	    	    	    		  
	    	    	    		  
	    	    	    		  
	    	    	    		
	    	    	    	}
	    	    		  }
	    	    	    	  
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Student ID, Course ID, and Year.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	     
	    	      }
	    	  }
	          
	          // Register the event handler.
	          editGradeButton.setOnAction(new addEditGradesByStudenteditGradeBtnClickHandler());
	      }
      }
      
      // Register the event handler.
	  addEditGradesByStudentItem.setOnAction(new addEditGradesByStudentClickHandler());
	  
	  class addEditGradesByCourseClickHandler implements EventHandler<ActionEvent> {//first table view I created on my own, I modified the existing object myself and I learned a lot by doing that. Basically, the pattern in the enrollment data object needs to be followed exactly, even with the no argument default constructor, I added fields for the report one too. so they would all work properly, and not all fields need to be used in every table view, i learned that too, use the same object for all five table views
	      @Override
	      public void handle(ActionEvent event)
	      {
	    	  
	    	  
	    	  Label courseIdLabel = new Label("Course ID");
	    	  
	    	  courseIdField = new TextField();
	    	  courseIdField.setMaxWidth(125);
	    	  
	    	  Button getGradesButton = new Button("Get Grades");
	    	  
	    	  
	    	  HBox hboxStudentId = new HBox(10, courseIdLabel, courseIdField, getGradesButton);//could line this up exactly with the table view, moving on to save time for now, come back to this
	    	  
	    	  hboxStudentId.setPadding(new Insets(10));
	    	
	    	// Create TableView
	          TableView<EnrollmentTableViewData> addEditGradesByCourseTableView = new TableView<>(); //table view creates a pretty spreadsheet for easy viewing and organization of data
	          
	          // Create TableColumn headers
	          TableColumn<EnrollmentTableViewData, String> courseNumberColumn = new TableColumn<>("Course #");
	          TableColumn<EnrollmentTableViewData, String> nameColumn = new TableColumn<>("Name");
	          TableColumn<EnrollmentTableViewData, Integer> studentIdColumn = new TableColumn<>("Student ID");
	          TableColumn<EnrollmentTableViewData, String> semesterColumn = new TableColumn<>("Semester");
	          TableColumn<EnrollmentTableViewData, Integer> yearColumn = new TableColumn<>("Year");
	          TableColumn<EnrollmentTableViewData, Character> gradeColumn = new TableColumn<>("Grade");
	    	  
	          
	          nameColumn.setMinWidth(240);
	          courseNumberColumn.setMinWidth(105);
	          studentIdColumn.setMinWidth(105);
	          semesterColumn.setMinWidth(105);
	          yearColumn.setMinWidth(105);
	          gradeColumn.setMinWidth(105);
	          
	          
	          
	          
	          
	          boolean dataFailedToLoad = false; 
	          try {
	    			
	    			nameColumn.setCellValueFactory(new StudentNameCellValueFactory());
	    			courseNumberColumn.setCellValueFactory(new CourseNumberCellValueFactory());
	    			studentIdColumn.setCellValueFactory(new StudentIdCellValueFactory());
	    			semesterColumn.setCellValueFactory(new SemesterCellValueFactory());
	    			yearColumn.setCellValueFactory(new YearCellValueFactory());
	    			gradeColumn.setCellValueFactory(new GradeCellValueFactory());
	    			
	    		}
	    		
	    		catch (Exception e) {
	    			dataFailedToLoad = true; 
	    		}
	          
	          // Add TableColumn headers to the TableView
	          addEditGradesByCourseTableView.getColumns().addAll(courseNumberColumn, nameColumn, studentIdColumn , semesterColumn, yearColumn, gradeColumn);
	          
	       // Populate TableView with data (dummy data for demonstration)
	          ObservableList<EnrollmentTableViewData> addEditGradesByStudentTableViewData = FXCollections.observableArrayList(
	          );
	          addEditGradesByCourseTableView.setItems(addEditGradesByStudentTableViewData);
	          
	          
	          
	          
	          // Add TableColumn headers to the TableView
	          /*addEditGradesByCourseTableView.getColumns().addAll(courseNumberColumn, nameColumn, studentIdColumn , semesterColumn, yearColumn, gradeColumn);

	          // Set an empty ObservableList as TableView's items
	          addEditGradesByCourseTableView.setItems(FXCollections.emptyObservableList());*/
	          
	          // Create a Label for the message
	          Label messageLabel = new Label("No content in table");
	          
	          // Set the message label as the TableView's placeholder
	          addEditGradesByCourseTableView.setPlaceholder(messageLabel);
	          
	          Label studentIdLabel = new Label("Student ID");
	     	 
	    	  studentIdField = new TextField();
	    	  studentIdField.setMaxWidth(125);
	    	  
	    	  
	    	  Label enrollmentSemesterLabel = new Label("Sem");
	    	  
	    	  enrollmentSemesterChoiceBox = new ChoiceBox<>();
	    	  enrollmentSemesterChoiceBox.setItems(FXCollections.observableArrayList(
					"SPRING", "SUMMER", 
					"FALL", "WINTER"));
	    	
	    	  Label enrollmentYearLabel = new Label("Yr");
	    	 
	    	  enrollmentYearChoiceBox = new ChoiceBox<>();
	    	  enrollmentYearChoiceBox.setItems(FXCollections.observableArrayList(
					"2023", "2024", 
					"2025", "2026"));
	    	  
	    	  
	    	  Label gradeLabel = new Label("New Grade");
	    	  
	    	  gradeByStudentChoiceBox = new ChoiceBox<>();
	    	  gradeByStudentChoiceBox.setItems(FXCollections.observableArrayList(
	    			  'A', 'B', 
	    			  'C', 'D', 'F' ));
	    	  
	    	  Button editGradeButton = new Button("Edit Grade");
	    	  
	    	  HBox hboxCourseId = new HBox(10, studentIdLabel, studentIdField, enrollmentSemesterLabel, enrollmentSemesterChoiceBox, enrollmentYearLabel, enrollmentYearChoiceBox, gradeLabel, gradeByStudentChoiceBox, editGradeButton);//could line this up exactly with the table view, moving on to save time for now, come back to this
	    	  
	    	  hboxCourseId.setPadding(new Insets(10));
	          
	    	  VBox vbox = new VBox(10, hboxStudentId, addEditGradesByCourseTableView, hboxCourseId);
	    	  
	    	  vbox.setPadding(new Insets(0, 15, 0, 15)); // 15px padding left and right
	    	  
	          borderPane.setCenter(vbox);
	               
	          // Update the existing scene with the new center
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class addEditGradesByCourseGradesBtnClickHandler implements EventHandler<ActionEvent> {// similar to view student but now adding a table view, this was just a GUI skeleton, but I need to create/use existing classes to actually fill the table view, this is just an empty table view but more work needs to be done, referencing google, chatGPT and videos on youtube for this
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  String courseIdString = courseIdField.getText();
		    	    	
	    	    	  int courseId;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (courseIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Course ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    			  courseId = Integer.parseInt(courseIdString);
	    	    	    	  
	    	    			  Course courseToDisplay = databaseManager.getCourse(courseId);
	    	    			  ArrayList<Enrollment> enrollmentDetailsList = databaseManager.getListOfEnrollmentsWithCourseId(courseId);
	    	    			  
	    	    	    	  
	    	    	    	  if (courseToDisplay == null) {
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The course ID could not be found. Choose a different ID for a successful view and edit grades.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } 
	    	    	    	  
	    	    	    	  else if (enrollmentDetailsList.size() == 0) {
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    	    	              alert.setTitle("No Enrollments for Specified Course ID");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The course ID has no students. Choose a different ID for a successful view and edit grades.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  }
	    	    	    	  
	    	    	    	  else {
	    	    	    		  boolean dataFailedToLoad = false; 
	    	    	    	
	    	    	    		  ObservableList<EnrollmentTableViewData> enrollmentTableViewDataList = FXCollections.observableArrayList();
	    	    	    		  
	    	    	    		  for (int i = 0; i < enrollmentDetailsList.size(); i++) {
	    	    	    			  Enrollment enrollment = enrollmentDetailsList.get(i);
	    	    	    			  
	    	    	    			  EnrollmentTableViewData enrollmentTableViewData = new EnrollmentTableViewData();
	    	    	    			  
	    	    	    			  Student student = databaseManager.getStudent(enrollment.getStudentId());
	    	    	    			  if (student != null) {
	    	    	    				  enrollmentTableViewData.setStudentName(student.getStudentFirstName() + " " + student.getStudentLastName());
	    	    	    				  enrollmentTableViewData.setStudentId(student.getStudentId());
	    	    	    			  } else {
	    	    	    			      // Handle the case where the course is not found
	    	    	    			      // For example, set a default or error value
	    	    	    			      enrollmentTableViewData.setCourseNumber(""); // Or some other default/error value
	    	    	    			  }
	    	    	    			  
	    	    	    			  enrollmentTableViewData.setCourseNumber(courseToDisplay.getDepartment() + " " + Integer.toString(courseToDisplay.getCourseNumber()));
	    	    	    			  //enrollmentTableViewData.setCourseId(enrollment.getCourseId());
	    	    	    			  enrollmentTableViewData.setSemester(enrollment.getSemester());
	    	    	    			  enrollmentTableViewData.setYear(enrollment.getYear());
	    	    	    			  enrollmentTableViewData.setGrade(enrollment.getGrade());
	    	    	    			  
	    	    	    			    
	    	    	    			    // Wrap data in SimpleStringProperty, SimpleIntegerProperty, SimpleObjectProperty
	    	    	    			    SimpleStringProperty studentNameProperty = new SimpleStringProperty(enrollmentTableViewData.getStudentName());
	    	    	    			    SimpleIntegerProperty studentIdProperty = new SimpleIntegerProperty(enrollmentTableViewData.getStudentId());
	    	    	    			    SimpleStringProperty courseNumberProperty = new SimpleStringProperty(enrollmentTableViewData.getCourseNumber());
	    	    	    			    SimpleStringProperty semesterProperty = new SimpleStringProperty(enrollmentTableViewData.getSemester());
	    	    	    			    SimpleIntegerProperty yearProperty = new SimpleIntegerProperty(enrollmentTableViewData.getYear());
	    	    	    			    SimpleObjectProperty<Character> gradeProperty = new SimpleObjectProperty<>(enrollmentTableViewData.getGrade());
	    	    	    			    
	    	    	    			    // Add wrapped properties to the TableView
	    	    	    			    enrollmentTableViewDataList.add(new EnrollmentTableViewData(
	    	    	    			            studentNameProperty,
	    	    	    			            studentNameProperty,
	    	    	    			            studentIdProperty,
	    	    	    			            studentIdProperty,
	    	    	    			            courseNumberProperty,
	    	    	    			            semesterProperty,
	    	    	    			            yearProperty,
	    	    	    			            gradeProperty
	    	    	    			    ));
	    	    	    		
	    	    	    		  }
	    	    	    		  
	    	    	    		// Set the populated list to the TableView
	    	    	    		  addEditGradesByCourseTableView.setItems(enrollmentTableViewDataList);

	    	    	    		  // Refresh the TableView
	    	    	    		  addEditGradesByCourseTableView.refresh();
	    	    	    		  
	    	    	    		  System.out.println(enrollmentTableViewDataList);
	    	    	    		  
	    	    	    		  
	    	    	    	
	    	    	    	
	    	    	    		  if (dataFailedToLoad) {
	    	    	    			// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("At least one of the rows failed to load.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
	    	    	    		  }
	    	    	    		  
	    	    	    		
	    	    	    	}
	    	    		  }
	    	    	    	  
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Course ID.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	     
	    	      }
	    	  }
	          
	          // Register the event handler.
	          getGradesButton.setOnAction(new addEditGradesByCourseGradesBtnClickHandler());
	          
	          class addEditGradesByCourseEditGradeBtnClickHandler implements EventHandler<ActionEvent> {// similar to view student but now adding a table view, this was just a GUI skeleton, but I need to create/use existing classes to actually fill the table view, this is just an empty table view but more work needs to be done, referencing google, chatGPT and videos on youtube for this
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  String studentIdString = studentIdField.getText();
	    	    	  String courseIdString = courseIdField.getText();
	    	    	  String semesterString = enrollmentSemesterChoiceBox.getValue();
	    	    	  String yearString = enrollmentYearChoiceBox.getValue();
	    	    	  Character gradeChar = gradeByStudentChoiceBox.getValue();
	    	    	  
	    	    	  
		    	    	
	    	    	  int studentId, courseId, year;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  if (courseIdString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Course ID cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else if (studentIdString.equals( "")) {
	    	    		  
	    	    		// Create an alert
	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	              alert.setTitle("Error");
	    	              alert.setHeaderText(null);
	    	              alert.setContentText("Student ID cannot be blank.");
	    	              
	    	              DialogPane dialogPane = alert.getDialogPane();
	    	              dialogPane.getStylesheets().add("application/application.css");
	    	              dialogPane.getStyleClass().add("myDialog");
	    	              
	    	              // Show the alert
	    	              alert.showAndWait();
	    	    	  }
	    	    	  
	    	    	  else if (semesterString == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Semester cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else if (yearString == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Year cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else if (gradeChar == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Grade cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    			  studentId = Integer.parseInt(studentIdString);
	    	    			  courseId = Integer.parseInt(courseIdString);
	    	    			  year = Integer.parseInt(yearString);
	    	    	    	  
	    	    			  Student studentToDisplay = databaseManager.getStudent(studentId);
	    	    			  Course courseToDisplay = databaseManager.getCourse(courseId);
	    	    			  Enrollment enrollmentToUpdate = databaseManager.getEnrollment(studentId, courseId, year, semesterString);
	    	    			  ArrayList<Enrollment> enrollmentDetailsList = databaseManager.getListOfEnrollmentsWithCourseId(courseId);
	    	    			  
	    	    	    	  
	    	    	    	  if (studentToDisplay == null) {
	    	    	    	   
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The student ID could not be found. Choose a different ID for a successful add and edit grades.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  } 
	    	    	    	  
	    	    	    	  else if (courseToDisplay == null) {
		    	    	    	   
		    	    	    		// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("The course ID could not be found. Choose a different ID for a successful add and edit grades.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
		    	    	    	  } 
	    	    	    	  else if (enrollmentDetailsList.size() == 0) {
		    	    	    		// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("The course ID has no students so no grades can be added or changed at this time. Choose a different ID for a successful add and edit grades.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
		    	    	    	  }
	    	    	    	  
	    	    	    	  else if (enrollmentToUpdate == null) {
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The student ID, course ID, semester and year combination has no enrollments. Choose a different combination of data for a successful add and edit grades.");
	    	    	              
	    	    	              DialogPane dialogPane = alert.getDialogPane();
	    	    	              dialogPane.getStylesheets().add("application/application.css");
	    	    	              dialogPane.getStyleClass().add("myDialog");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  }
	    	    	    	  
	    	    	    	  else {
	    	    	    		  boolean dataFailedToLoad = false;
	    	    	    		  boolean updateEnrollmentSuccessful;
	    	    	    		  
	    	    	    		  enrollmentDetailsList.clear();
	    	    	    	
	    	    	    		  ObservableList<EnrollmentTableViewData> enrollmentTableViewDataList = FXCollections.observableArrayList();
	    	    	    		  
	    	    	    		  
	    	    	    		  int enrollmentId = enrollmentToUpdate.getEnrollmentId();
	    	    	    		  
	    	    	    		  updateEnrollmentSuccessful = databaseManager.updateEnrollment(enrollmentId, studentId, courseId, year, semesterString, gradeChar);
	    	    	    		  enrollmentDetailsList.clear();
	    	    	    		  enrollmentDetailsList = databaseManager.getListOfEnrollmentsWithCourseId(courseId);
	    	    	    		  
	    	    	    		  for (int i = 0; i < enrollmentDetailsList.size(); i++) {
	    	    	    			  Enrollment enrollment = enrollmentDetailsList.get(i);
	    	    	    			  
	    	    	    			  EnrollmentTableViewData enrollmentTableViewData = new EnrollmentTableViewData();
	    	    	    			  
	    	    	    			  Student student = databaseManager.getStudent(enrollment.getStudentId());
	    	    	    			  if (student != null) {
	    	    	    				  enrollmentTableViewData.setStudentName(student.getStudentFirstName() + " " + student.getStudentLastName());
		    	    	    			  enrollmentTableViewData.setStudentId(student.getStudentId());
	    	    	    			      
	    	    	    			  } else {
	    	    	    			      // Handle the case where the course is not found
	    	    	    			      // For example, set a default or error value
	    	    	    			      enrollmentTableViewData.setCourseNumber(""); // Or some other default/error value
	    	    	    			  }
	    	    	    			  
	    	    	    			  enrollmentTableViewData.setCourseNumber(courseToDisplay.getDepartment() + " " + Integer.toString(courseToDisplay.getCourseNumber()));
	    	    	    			  enrollmentTableViewData.setSemester(enrollment.getSemester());
	    	    	    			  enrollmentTableViewData.setYear(enrollment.getYear());
	    	    	    			  enrollmentTableViewData.setGrade(enrollment.getGrade());
	    	    	    			
	    	    	    			    
	    	    	    			    // Wrap data in SimpleStringProperty, SimpleIntegerProperty, SimpleObjectProperty
	    	    	    			    SimpleStringProperty studentNameProperty = new SimpleStringProperty(enrollmentTableViewData.getStudentName());
	    	    	    			    SimpleIntegerProperty studentIdProperty = new SimpleIntegerProperty(enrollmentTableViewData.getStudentId());
	    	    	    			    SimpleStringProperty courseNumberProperty = new SimpleStringProperty(enrollmentTableViewData.getCourseNumber());
	    	    	    			    SimpleStringProperty semesterProperty = new SimpleStringProperty(enrollmentTableViewData.getSemester());
	    	    	    			    SimpleIntegerProperty yearProperty = new SimpleIntegerProperty(enrollmentTableViewData.getYear());
	    	    	    			    SimpleObjectProperty<Character> gradeProperty = new SimpleObjectProperty<>(enrollmentTableViewData.getGrade());
	    	    	    			    
	    	    	    			    // Add wrapped properties to the TableView
	    	    	    			    enrollmentTableViewDataList.add(new EnrollmentTableViewData(
	    	    	    			            studentNameProperty,
	    	    	    			            studentNameProperty,
	    	    	    			            studentIdProperty,
	    	    	    			            studentIdProperty,
	    	    	    			            courseNumberProperty,
	    	    	    			            semesterProperty,
	    	    	    			            yearProperty,
	    	    	    			            gradeProperty
	    	    	    			    ));
	    	    	    		
	    	    	    			
	    	    	    		
	    	    	              /*System.out.println(enrollmentTableViewData.getStudentName());
	    	    	              System.out.println(enrollmentTableViewData.getCourseNumber());
	    	    	              System.out.println(enrollmentTableViewData.getCourseId());
	    	    	              System.out.println(enrollmentTableViewData.getSemester());
	    	    	              System.out.println(enrollmentTableViewData.getYear());
	    	    	              System.out.println(enrollmentTableViewData.getGrade());*/
	    	    	    		  }
	    	    	    		  
	    	    	    		// Set the populated list to the TableView
	    	    	    		  addEditGradesByCourseTableView.setItems(enrollmentTableViewDataList);

	    	    	    		  // Refresh the TableView
	    	    	    		  addEditGradesByCourseTableView.refresh();
	    	    	    		  
	    	    	    		 // System.out.println(enrollmentTableViewDataList);
	    	    	    		  
	    	    	    		  
	    	    	    		  if (updateEnrollmentSuccessful) {
	    	    	    			// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    	    	              alert.setTitle("Complete");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("Grade updated");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
	    	    	    		  }
	    	    	    		  
	    	    	    		  else if (!updateEnrollmentSuccessful) {
	    	    	    			  Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("Grade could not be added or changed. Check that the data you entered is valid.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
	    	    	    		  }
	    	    	    	
	    	    	    		  else if (dataFailedToLoad) {
	    	    	    			// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("At least one of the rows failed to load.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
	    	    	    		  }
	    	    	    		  
	    	    	    		  
	    	    	    		  
	    	    	    		
	    	    	    	}
	    	    		  }
	    	    	    	  
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Student ID, Course ID, and Year.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	     
	    	      }
	    	  }
	          
	          // Register the event handler.
	          editGradeButton.setOnAction(new addEditGradesByCourseEditGradeBtnClickHandler());
	      }
      }
      
      // Register the event handler.
	  addEditGradesByCourseItem.setOnAction(new addEditGradesByCourseClickHandler());

	  Menu reportsMenu = new Menu("Reports");
	  MenuItem generateReportsItem = new MenuItem("Generate Report");
	  
	  reportsMenu.getItems().addAll(generateReportsItem);
	  
	  class generateReportClickHandler implements EventHandler<ActionEvent> {
	      @Override
	      public void handle(ActionEvent event)
	      {
	    	  GridPane gridpane = new GridPane();
	    	  
	    	  Label courseIdLabel = new Label("Course ID");
	    	  gridpane.add(courseIdLabel, 0, 0); 
	    	  
	    	  Label semesterLabel = new Label("Semester");
	    	  gridpane.add(semesterLabel, 1, 0); 
	    	  
	    	  Label yearLabel = new Label("Year");
	    	  gridpane.add(yearLabel, 2, 0); 
	    	  
	    	  courseIdField = new TextField();
	    	  gridpane.add(courseIdField, 0, 1); 
	    	  
	    	  enrollmentSemesterChoiceBox = new ChoiceBox<>();
	    	  enrollmentSemesterChoiceBox.setItems(FXCollections.observableArrayList(
					"SPRING", "SUMMER", 
					"FALL", "WINTER"));
	    	  gridpane.add(enrollmentSemesterChoiceBox, 1, 1);
	    	  
	    	  enrollmentYearChoiceBox = new ChoiceBox<>();
	    	  enrollmentYearChoiceBox.setItems(FXCollections.observableArrayList(
					"2023", "2024", 
					"2025", "2026"));
	    	  gridpane.add(enrollmentYearChoiceBox, 2, 1);
	    	  
	    	  Button generateReportButton = new Button("Generate Report");
	    	  gridpane.add(generateReportButton, 3, 1); 
	    	  
	    	  gridpane.setHgap(10);
	    	  gridpane.setVgap(10);
	    	  
	    	  gridpane.setPadding(new Insets(10));
	    	  
	    	  Label tableViewTitle = new Label("");
	    	 
	    	  HBox hbox = new HBox(10, tableViewTitle);
	    	
	    	  
	    	  
	    	// Create TableView
	          TableView<EnrollmentTableViewData> reportTableView = new TableView<>(); //table view creates a pretty spreadsheet for easy viewing and organization of data
	          
	          // Create TableColumn headers
	          TableColumn<EnrollmentTableViewData, String> nameColumn = new TableColumn<>("Name");
	          TableColumn<EnrollmentTableViewData, Character> gradeColumn = new TableColumn<>("Grade");
	    	  
	          
	          nameColumn.setMinWidth(300);
	          gradeColumn.setMinWidth(100);
	          
	          boolean dataFailedToLoad = false; 
	          try {
	    			
	    			nameColumn.setCellValueFactory(new StudentNameCellValueFactory());
	    			gradeColumn.setCellValueFactory(new GradeCellValueFactory());
	    			
	    		}
	    		
	    		catch (Exception e) {
	    			dataFailedToLoad = true; 
	    		}
	          
	          // Add TableColumn headers to the TableView
	          reportTableView.getColumns().addAll(nameColumn, gradeColumn);
	          
	       // Populate TableView with data (dummy data for demonstration)
	          ObservableList<EnrollmentTableViewData> ReportTableViewData = FXCollections.observableArrayList(
	          );
	          reportTableView.setItems(ReportTableViewData);
	          
	          
	          // Create a Label for the message
	          Label messageLabel = new Label("No content in table");
	          
	          // Set the message label as the TableView's placeholder
	          reportTableView.setPlaceholder(messageLabel);
	          
	    	  VBox vbox = new VBox(10, gridpane, hbox, reportTableView);
	    	  
	    	  vbox.setPadding(new Insets(0, 15, 15, 15)); // 10px padding left and right
	    	  
	          borderPane.setCenter(vbox);
	               
	          // Update the existing scene with the new center
	          primaryStage.getScene().setRoot(borderPane);
	          
	          class generateReportButtonClickHandler implements EventHandler<ActionEvent> {// similar to view student but now adding a table view, this was just a GUI skeleton, but I need to create/use existing classes to actually fill the table view, this is just an empty table view but more work needs to be done, referencing google, chatGPT and videos on youtube for this
	    	      @Override
	    	      public void handle(ActionEvent event)
	    	      {
	    	    	  String courseIdString = courseIdField.getText();
	    	    	  String semesterString = enrollmentSemesterChoiceBox.getValue();
	    	    	  String yearString = enrollmentYearChoiceBox.getValue();
	    	    	  
	    	    	  
		    	    	
	    	    	  int studentId, courseId, year;
	    	    	  //boolean errorAlert = false; 
	    	    	  
	    	    	  
	    	    	  if (courseIdString.equals( "")) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Course ID cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else if (semesterString == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Semester cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else if (yearString == null) {
	    	    		  
		    	    		// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Year cannot be blank.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	    	  }
	    	    	  else  {
	    	    		  try {
	    	    			 
	    	    			  courseId = Integer.parseInt(courseIdString);
	    	    			  year = Integer.parseInt(yearString);
	    	    	    	  //System.out.println("here");
	    	    			  
	    	    			  Course courseToDisplay = databaseManager.getCourse(courseId);
	    	    			  //Enrollment enrollmentToUpdate = enrollmentFileManager.GetEnrollment(studentId, courseId, year, semesterString);
	    	    			  //System.out.println("here2");
	    	    			  ArrayList<Enrollment> enrollmentDetailsList = databaseManager.generateReport(courseId, semesterString, year);
	    	    			  
	    	    			  
	    	    	    	  
	    	    	    	  if (courseToDisplay == null) {
		    	    	    	   
		    	    	    		// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("The course ID could not be found. Choose a different ID for a successful view report.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
		    	    	    	  } 
	    	    	    	  else if (enrollmentDetailsList.size() == 0) {
		    	    	    		// Create an alert
	    	    	    		  //System.out.println(enrollmentDetailsList);
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("The course ID has no students so no report can be viewed at this time. Choose a different ID for a successful view report.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
		    	    	    	  }
	    	    	    	  
	    	    	    	  /*else if (enrollmentToUpdate == null) {
	    	    	    		// Create an alert
	    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    	              alert.setTitle("Error");
	    	    	              alert.setHeaderText(null);
	    	    	              alert.setContentText("The student ID, course ID, semester and year combination has no enrollments. Choose a different combination of data for a successful add and edit grades.");
	    	    	              
	    	    	              // Show the alert
	    	    	              alert.showAndWait();
	    	    	    	  }*/
	    	    	    	  
	    	    	    	  else {
	    	    	    		  boolean dataFailedToLoad = false;
	    	    	    		  //boolean updateEnrollmentSuccessful;
	    	    	    		  
	    	    	    		  //enrollmentDetailsList.clear();
	    	    	    	
	    	    	    		  ObservableList<EnrollmentTableViewData> enrollmentTableViewDataList = FXCollections.observableArrayList();
	    	    	    		  
	    	    	    		  
	    	    	    		  //int enrollmentId = enrollmentToUpdate.getEnrollmentId();
	    	    	    		  
	    	    	    		  //updateEnrollmentSuccessful = enrollmentFileManager.updateEnrollment(enrollmentId, studentId, courseId, year, semesterString, gradeChar);
	    	    		    		  //enrollmentDetailsList.clear();
	    	    	    		  //enrollmentDetailsList = enrollmentFileManager.getListOfEnrollmentsWithStudentId(studentId);
	    	    	    		  
	    	        		  for (int i = 0; i < enrollmentDetailsList.size(); i++) {
	    	        			      Enrollment enrollment = enrollmentDetailsList.get(i);
	    	    	    			  
	    	    	    			  EnrollmentTableViewData enrollmentTableViewData = new EnrollmentTableViewData();
	    	    	    			  
	    	    	    			  Student student = databaseManager.getStudent(enrollment.getStudentId());
	    	    	    			  
	    	    	    			  if (student != null) {
		    	    	    			  enrollmentTableViewData.setStudentName(student.getStudentFirstName() + " " + student.getStudentLastName());
	    	    	    			      
	    	    	    			  } else {
	    	    	    			      // Handle the case where the course is not found
	    	    	    			      // For example, set a default or error value
	    	    	    				  enrollmentTableViewData.setStudentName("");// Or some other default/error value
	    	    	    			  }
	    	    	    			  

	    	    	    			  enrollmentTableViewData.setCourseId(enrollment.getCourseId());
	    	    	    			  enrollmentTableViewData.setSemester(enrollment.getSemester());
	    	    	    			  enrollmentTableViewData.setYear(enrollment.getYear());
	    	    	    			  enrollmentTableViewData.setGrade(enrollment.getGrade());
	    	    	    			  enrollmentTableViewData.setCourseNumber(courseToDisplay.getDepartment() + " " + Integer.toString(courseToDisplay.getCourseNumber()));
	    	    	    			  enrollmentTableViewData.setCourseName(courseToDisplay.getCourseName());
	    	    	    			  
	    	    	    			  tableViewTitle.setText(enrollmentTableViewData.getCourseNumber() + " " + enrollmentTableViewData.getCourseName() + " Report");
	    	    	    			    
	    	    	    			    // Wrap data in SimpleStringProperty, SimpleIntegerProperty, SimpleObjectProperty
	    	    	    			    SimpleStringProperty studentNameProperty = new SimpleStringProperty(enrollmentTableViewData.getStudentName());
	    	    	    			    //SimpleIntegerProperty courseIdProperty = new SimpleIntegerProperty(enrollmentTableViewData.getCourseId());
	    	    	    			    //SimpleStringProperty courseNumberProperty = new SimpleStringProperty(enrollmentTableViewData.getCourseNumber());
	    	    	    			    //SimpleStringProperty semesterProperty = new SimpleStringProperty(enrollmentTableViewData.getSemester());
	    	    	    			    SimpleIntegerProperty yearProperty = new SimpleIntegerProperty(enrollmentTableViewData.getYear());
	    	    	    			    SimpleObjectProperty<Character> gradeProperty = new SimpleObjectProperty<>(enrollmentTableViewData.getGrade());
	    	    	    			    
	    	    	    			    // Add wrapped properties to the TableView
	    	    	    			    enrollmentTableViewDataList.add(new EnrollmentTableViewData(
	    	    	    			            studentNameProperty,
	    	    	    			            studentNameProperty,
	    	    	    			            yearProperty,
	    	    	    			            yearProperty,
	    	    	    			            studentNameProperty,
	    	    	    			            studentNameProperty,
	    	    	    			            yearProperty,
	    	    	    			            gradeProperty
	    	    	    			    ));
	    	    	    		
	    	    	    			
	    	    	    		
	    	    	              /*System.out.println(enrollmentTableViewData.getStudentName());
	    	    	              System.out.println(enrollmentTableViewData.getCourseNumber());
	    	    	              System.out.println(enrollmentTableViewData.getCourseId());
	    	    	              System.out.println(enrollmentTableViewData.getSemester());
	    	    	              System.out.println(enrollmentTableViewData.getYear());
	    	    	              System.out.println(enrollmentTableViewData.getGrade());*/
	    	    	    		  }
	    	    	    		  
	    	    	    		// Set the populated list to the TableView
	    	    	    		  reportTableView.setItems(enrollmentTableViewDataList);

	    	    	    		  // Refresh the TableView
	    	    	    		  reportTableView.refresh();
	    	    	    		  
	    	    	    		 // System.out.println(enrollmentTableViewDataList);
	    	    	    		  
	    	    	    		  
	    	    	    		  
	    	    	    	
	    	    	    		  if (dataFailedToLoad) {
	    	    	    			// Create an alert
		    	    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	    	              alert.setTitle("Error");
		    	    	              alert.setHeaderText(null);
		    	    	              alert.setContentText("At least one of the rows failed to load.");
		    	    	              
		    	    	              DialogPane dialogPane = alert.getDialogPane();
		    	    	              dialogPane.getStylesheets().add("application/application.css");
		    	    	              dialogPane.getStyleClass().add("myDialog");
		    	    	              
		    	    	              // Show the alert
		    	    	              alert.showAndWait();
	    	    	    		  }
	    	    	    		  
	    	    	    		  
	    	    	    		  
	    	    	    		
	    	    	    	}
	    	    		  }
	    	    	    	  
	    	    	    	  
	    	    	    	  
	    	    	    		  
	    	    	    	  
	    	    		  
	    	    		  catch (NumberFormatException parseIntFail) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Please an integer value for Student ID, Course ID, and Year.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
		    	              
		    	    	  }
	    	    			  
	    	    		  
	    	    		  catch (Exception e) {
	    	    			// Create an alert
		    	              Alert alert = new Alert(Alert.AlertType.ERROR);
		    	              alert.setTitle("Error");
		    	              alert.setHeaderText(null);
		    	              alert.setContentText("Here is the error:\n" + e.getMessage() + "\nPlease contact your system administrator or IT support for more details.");
		    	              
		    	              DialogPane dialogPane = alert.getDialogPane();
    	    	              dialogPane.getStylesheets().add("application/application.css");
    	    	              dialogPane.getStyleClass().add("myDialog");
		    	              
		    	              // Show the alert
		    	              alert.showAndWait();
	    	    		  }
	    	    	  }
	    	     
	    	      }
	    	  }
	          
	          // Register the event handler.
	          generateReportButton.setOnAction(new generateReportButtonClickHandler());
	          
	          
	      }
      }
      
      // Register the event handler.
	  generateReportsItem.setOnAction(new generateReportClickHandler());

	 

      // Add the File menu to the menu bar.
      menuBar.getMenus().addAll(fileMenu, studentsMenu, instructorsMenu, departmentsMenu, coursesMenu, enrollmentsMenu, gradesMenu, reportsMenu );
         
      // Add the menu bar to a BorderPane.
      borderPane = new BorderPane();
      borderPane.setTop(menuBar); //put the menu/nav on top of the stage
      
      Label welcomeMessageLabel = new Label("Welcome to University Enrollment");
      welcomeMessageLabel.getStyleClass().add("cursive-large-italic");
      HBox centerWelcomeHBox = new HBox(welcomeMessageLabel);
      borderPane.setCenter(centerWelcomeHBox);
      
      centerWelcomeHBox.setAlignment(Pos.CENTER);
               
      
      
      // Create a Scene and display it.
      Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
      scene.getStylesheets().add("application/application.css");
      primaryStage.setScene(scene);
      
      // Set the stage title.
      primaryStage.setTitle("University Enrollment");
      
      primaryStage.show();
      
      
   }
}