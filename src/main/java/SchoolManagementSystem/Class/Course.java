package SchoolManagementSystem.Class;

import SchoolManagementSystem.AppResource;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to manage course details in form of List {@code <CoursesDetails>}
 * @author HannanAhmad(2094868)
 */
public class Course {

    /**
     * Used for setting json node name.
     */
    @JsonProperty(AppResource.TXT_COURSE)
    private List<CoursesDetails> courseList = new ArrayList<>();

    /**
     * Used to get All/Specific course details in list
     * @return courses Details in form of List {@code <CourseDetails>}
     */
    public List<CoursesDetails> getCourseList() {
        return courseList;
    }

    /**
     * Used to set All/Specific courses details in list
     * @param courseList list of course/courses
     */
    public void setCourseList(List<CoursesDetails> courseList) {
        this.courseList = courseList;
    }
}