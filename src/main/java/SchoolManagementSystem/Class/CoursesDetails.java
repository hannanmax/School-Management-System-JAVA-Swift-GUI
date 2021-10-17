package SchoolManagementSystem.Class;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used to set and get courses details.
 * @author HannanAhmad(2094868)
 */
public class CoursesDetails {

    /**
     * to set key name in json node of courses
     */
    @JsonProperty("courseID")
    int courseID;
    /**
     * to set key name in json node of courses
     */
    @JsonProperty("courseName")
    String courseName;
    /**
     * to set key name in json node of courses
     */
    @JsonProperty("StartDate")
    String startDate;
    /**
     * to set key name in json node of courses
     */
    @JsonProperty("EndDate")
    String endDate;
    /**
     * to set key name in json node of courses
     */
    @JsonProperty("PreRequisite")
    String preRequisite;
    /**
     * to set key name in json node of courses
     */
    @JsonProperty("Teacher")
    String teacher;
    /**
     * to set key name in json node of courses
     */
    @JsonProperty("Attendance")
    int attendance;

    /**
     * Empty default constructor
     */
    public CoursesDetails(){}

    /**
     * Default Constructor with parameters
     * @param courseID takes course id
     * @param courseName takes course name
     * @param startDate takes course start date
     * @param endDate takes course end date
     * @param preRequisite takes course pre requisite
     * @param teacher takes course teacher name
     * @param attendance takes course attendance
     */
    public CoursesDetails(int courseID, String courseName, String startDate, String endDate, String preRequisite, String teacher, int attendance) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.preRequisite = preRequisite;
        this.teacher = teacher;
        this.attendance = attendance;
    }

    /**
     * Used to get course id
     * @return course id
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * Used to set course id
     * @param courseID takes courses id
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    /**
     * Used to get course name
     * @return courses Name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Used to set course name
     * @param courseName takes courses name
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Used to start date of course
     * @return start date of course
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Used to set start date
     * @param startDate takes start date of course
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Used to get end date
     * @return end date of course
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Used to set End Date
     * @param endDate takes end date of course
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Used to get course prerequisite
     * @return course prerequisite
     */
    public String getPreRequisite() {
        return preRequisite;
    }

    /**
     * Used to set course prerequisite
     * @param preRequisite takes pre requisite
     */
    public void setPreRequisite(String preRequisite) {
        this.preRequisite = preRequisite;
    }

    /**
     * Used to get teacher of course
     * @return course teacher name
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * Used to set teacher name of course
     * @param teacher takes teacher name
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    /**
     * Used to get class attendance
     * @return class attendance
     */
    public int getAttendance() {
        return attendance;
    }

    /**
     * Used to set class attendance
     * @param attendance takes attendance
     */
    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}