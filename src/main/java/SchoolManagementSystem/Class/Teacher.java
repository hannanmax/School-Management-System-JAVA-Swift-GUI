package SchoolManagementSystem.Class;

import SchoolManagementSystem.AppResource;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to manage teacher details in form of List {@code <TeachersDetails>}
 * @author HannanAhmad(2094868)
 */
public class Teacher {

    /**
     * Used for setting json node name.
     */
    @JsonProperty(AppResource.TXT_TEACHER)
    private List<TeachersDetails> teachersDetails = new ArrayList<>();

    /**
     * Used to get All/Specific teachers details in list
     * @return teachers Details in form of List {@code <TeacherDetails>}
     */
    public List<TeachersDetails> getTeachersDetails() {
        return teachersDetails;
    }

    /**
     * Used to set All/Specific teachers details in list
     * @param teachersDetails list of Teacher/Teachers
     */
    public void setTeachersDetails(List<TeachersDetails> teachersDetails) {
        this.teachersDetails = teachersDetails;
    }
}