package SchoolManagementSystem.Class;

import SchoolManagementSystem.AppResource;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to manage student details in form of List {@code <StudentsDetails>}
 * @author HannanAhmad(2094868)
 */
public class Student {

    /**
     * Used for setting json node name.
     */
    @JsonProperty(AppResource.TXT_STUDENT)
    private List<StudentsDetails> studentsDetails = new ArrayList<>();

    /**
     * Used to get All/Specific student details in list
     * @return students Details in form of List {@code <StudentsDetails>}
     */
    public List<StudentsDetails> getStudentsDetails() {
        return studentsDetails;
    }

    /**
     * Used to set All/Specific students details in list
     * @param studentsDetails list of Student/Students
     */
    public void setStudentsDetails(List<StudentsDetails> studentsDetails) {
        this.studentsDetails = studentsDetails;
    }
}
