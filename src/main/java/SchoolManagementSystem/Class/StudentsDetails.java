package SchoolManagementSystem.Class;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to set and get teacher details.
 * @author HannanAhmad(2094868)
 */
public class StudentsDetails {

    /**
     * to set key name in json node of courses
     */
    @JsonProperty("studentID")
    int studentID;
    /**
     * to set key name in json node of courses
     */
    @JsonProperty("studentName")
    String studentName;
    /**
     * to set key name in json node of courses
     */
    @JsonProperty("currentCoursesGrades")
    String currentCoursesGrades;
    /**
     * to set key name in json node of courses
     */
    @JsonProperty("pastCoursesGrades")
    String pastCoursesGrades;

    /**
     * Empty default constructor
     */
    public StudentsDetails(){}

    /**
     * Default constructor with parameters
     * @param studentID takes student id
     * @param studentName take student name
     * @param currentCoursesGrades takes current courses and it's grades
     * @param pastCoursesGrades takes past courses and it's grades
     */
    public StudentsDetails(int studentID, String studentName, String currentCoursesGrades, String pastCoursesGrades) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.currentCoursesGrades = currentCoursesGrades;
        this.pastCoursesGrades = pastCoursesGrades;
    }

    /**
     * Used to get student id
     * @return student id
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * Used to set student id
     * @param studentID takes student id
     */
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    /**
     * Used to get student name
     * @return student name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Used to set student name
     * @param studentName takes student name
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Used to get current courses and it's grades
     * @return current courses and grades
     */
    public String getCurrentCoursesGrades() {
        return currentCoursesGrades;
    }

    /**
     * Used to set current courses and it's grades
     * @param currentCoursesGrades takes student courses
     */
    public void setCurrentCoursesGrades(String currentCoursesGrades) {
        this.currentCoursesGrades = currentCoursesGrades;
    }

    /**
     * Used to get past courses and it's grades
     * @return past courses and it's grades
     */
    public String getPastCoursesGrades() {
        return pastCoursesGrades;
    }

    /**
     * Used to set past courses and it's grades
     * @param pastCoursesGrades takes past courses and it's grades
     */
    public void setPastCoursesGrades(String pastCoursesGrades) {
        this.pastCoursesGrades = pastCoursesGrades;
    }
}
