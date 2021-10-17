package SchoolManagementSystem.Class;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to set and get teacher details.
 * @author HannanAhmad(2094868)
 */
public class TeachersDetails {

    /**
     * to set key name in json node of courses
     */
    @JsonProperty("teacherID")
    int teacherID;
    /**
     * to set key name in json node of courses
     */
    @JsonProperty("teacherName")
    String teacherName;
    /**
     * to set key name in json node of courses
     */
    @JsonProperty("currentCourses")
    String currentCourses;
    /**
     * to set key name in json node of courses
     */
    @JsonProperty("pastCourses")
    String pastCourses;

    /**
     * Empty default constructor
     */
    public TeachersDetails(){}

    /**
     * Default constructor with parameters
     * @param teacherID takes teacher id
     * @param teacherName takes teacher name
     * @param currentCourses takes current courses
     * @param pastCourses takes past courses
     */
    public TeachersDetails(int teacherID, String teacherName, String currentCourses, String pastCourses){
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.currentCourses = currentCourses;
        this.pastCourses = pastCourses;
    }

    /**
     * Used to get teacher id
     * @return teacher id
     */
    public int getTeacherID() {
        return teacherID;
    }

    /**
     * Used to set teacher id
     * @param teacherID takes teacher id
     */
    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    /**
     * Used to take teacher name
     * @return teacher name
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * Used to set teacher name
     * @param teacherName takes teacher name
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * Used to get current courses of teacher
     * @return current courses of teacher
     */
    public String getCurrentCourses() {
        return currentCourses;
    }

    /**
     * Used to set current courses of teacher
     * @param currentCourses takes current courses
     */
    public void setCurrentCourses(String currentCourses) {
        this.currentCourses = currentCourses;
    }

    /**
     * Used to get past courses of teacher
     * @return past courses of teacher
     */
    public String getPastCourses() {
        return pastCourses;
    }

    /**
     * Used to set past courses of teacher
     * @param pastCourses takes past courses
     */
    public void setPastCourses(String pastCourses) {
        this.pastCourses = pastCourses;
    }
}
