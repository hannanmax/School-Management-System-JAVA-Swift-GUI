package SchoolManagementSystem.Data;

import SchoolManagementSystem.AppResource;
import SchoolManagementSystem.Class.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This database class manages the data of whole system.
 * This usually stores data in form of json in three different files.
 * Such as course.json, student.json, and teacher.json
 * <p>
 * This class contains methods which are easy to access and add, display and update data.
 *
 * @author HannanAhmad(2094868)
 * @version 1.0
 * @since 29/09/2021
 */
public class Database {

    /**
     * Constructor: Used to check if json files exists or not and if not exists it calls writeDefaultData() method to create files.
     * At the end this constructor calls refreshDatabaseData() to check and end date of courses and update student and teacher courses from current to past.
     */
    public Database() {
        File file = new File(AppResource.JSON_COURSE_PATH);
        if (!file.exists()) {
            try {
                if (!file.getParentFile().mkdirs() && !file.createNewFile()) {
                    showMessageDialog(null, AppResource.MESSAGE_SOMETHING_WRONG);
                } else {
                    writeDefaultData();
                    showMessageDialog(null, AppResource.MESSAGE_WELCOME);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        refreshDatabaseData();
    }

    /**
     * Method: Used to create course.json, student.json and teacher.json file and create an empty node inside those files.
     *
     * @throws IOException used to throw exception while accessing information using streams, files, directories.
     */
    @SuppressWarnings("unchecked")
    private void writeDefaultData() throws IOException {
        //Course Json
        JSONArray CourseDetails = new JSONArray();
        JSONObject CourseObject = new JSONObject();
        CourseObject.put(AppResource.TXT_COURSE, CourseDetails);

        try (FileWriter file = new FileWriter(AppResource.JSON_COURSE_PATH)) {
            file.write(CourseObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Teacher Json
        JSONArray teacherDetails = new JSONArray();
        JSONObject teacherObject = new JSONObject();
        teacherObject.put(AppResource.TXT_TEACHER, teacherDetails);
        try (FileWriter file = new FileWriter(AppResource.JSON_TEACHER_PATH)) {
            file.write(teacherObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Student Json
        JSONArray studentDetails = new JSONArray();
        JSONObject studentObject = new JSONObject();
        studentObject.put(AppResource.TXT_STUDENT, studentDetails);

        try (FileWriter file = new FileWriter(AppResource.JSON_STUDENTS_PATH)) {
            file.write(studentObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method: Used to return list of all courses.
     *
     * @return Course list in List {@code <CoursesDetails>}
     */
    public List<CoursesDetails> displayCourses() {
        Course course = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(AppResource.JSON_COURSE_PATH));
            course = objectMapper.readValue(jsonData, Course.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Objects.requireNonNull(course).getCourseList();
    }

    /**
     * Method: Used to return list of all teachers.
     *
     * @return Teacher list in List {@code <TeacherDetails>}
     */
    public List<TeachersDetails> displayTeachers() {
        Teacher teacher = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(AppResource.JSON_TEACHER_PATH));
            teacher = objectMapper.readValue(jsonData, Teacher.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(teacher).getTeachersDetails();
    }

    /**
     * Method: Used to return list of all students.
     *
     * @return Student list in List {@code <StudentDetails>}
     */
    public List<StudentsDetails> displayStudents() {
        Student student = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(AppResource.JSON_STUDENTS_PATH));
            student = objectMapper.readValue(jsonData, Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(student).getStudentsDetails();
    }

    /**
     * Method: Used to add new course in new course.json
     *
     * @param courseList takes course list
     * @return boolean if added to database or not
     */
    public boolean addNewCourse(List<CoursesDetails> courseList) {
        Course course;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(AppResource.JSON_COURSE_PATH));
            course = objectMapper.readValue(jsonData, Course.class);

            List<CoursesDetails> existingCourseList = course.getCourseList();
            if (null != existingCourseList && existingCourseList.size() > 0) {
                existingCourseList.addAll(courseList);
                course.setCourseList(existingCourseList);
            } else {
                course.setCourseList(courseList);
            }
            objectMapper.writeValue(new File(AppResource.JSON_COURSE_PATH), course);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method: Used to add new teacher in new teacher.json
     *
     * @param TeacherList takes teacher list
     * @return boolean if added to database or not
     */
    public boolean addNewTeacher(List<TeachersDetails> TeacherList) {
        Teacher teacher;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(AppResource.JSON_TEACHER_PATH));
            teacher = objectMapper.readValue(jsonData, Teacher.class);

            List<TeachersDetails> existingTeacherList = teacher.getTeachersDetails();
            if (null != existingTeacherList && existingTeacherList.size() > 0) {
                existingTeacherList.addAll(TeacherList);
                teacher.setTeachersDetails(existingTeacherList);
            } else {
                teacher.setTeachersDetails(TeacherList);
            }
            objectMapper.writeValue(new File(AppResource.JSON_TEACHER_PATH), teacher);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method: Used to add new student in new student.json
     *
     * @param StudentList takes student list
     * @return boolean if added to database or not
     */
    public boolean addNewStudent(List<StudentsDetails> StudentList) {
        Student student;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(AppResource.JSON_STUDENTS_PATH));
            student = objectMapper.readValue(jsonData, Student.class);

            List<StudentsDetails> existingStudentList = student.getStudentsDetails();
            if (null != existingStudentList && existingStudentList.size() > 0) {
                existingStudentList.addAll(StudentList);
                student.setStudentsDetails(existingStudentList);
            } else {
                student.setStudentsDetails(StudentList);
            }
            objectMapper.writeValue(new File(AppResource.JSON_STUDENTS_PATH), student);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method: Used to update course in course.json
     *
     * @param CourseList takes course list
     * @return boolean if added to database or not
     */
    public boolean updateCourse(List<CoursesDetails> CourseList) {
        Course course;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(AppResource.JSON_COURSE_PATH));
            course = objectMapper.readValue(jsonData, Course.class);
            course.setCourseList(CourseList);
            objectMapper.writeValue(new File(AppResource.JSON_COURSE_PATH), course);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method: Used to update teacher in teacher.json
     *
     * @param TeacherList takes teacher list
     * @return boolean if added to database or not
     */
    public boolean updateTeacher(List<TeachersDetails> TeacherList) {
        Teacher teacher;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(AppResource.JSON_TEACHER_PATH));
            teacher = objectMapper.readValue(jsonData, Teacher.class);
            teacher.setTeachersDetails(TeacherList);
            objectMapper.writeValue(new File(AppResource.JSON_TEACHER_PATH), teacher);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method: Used to add student in student.json
     *
     * @param StudentList takes student list
     * @return boolean if added to database or not
     */
    public boolean updateStudent(List<StudentsDetails> StudentList) {
        Student student;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(AppResource.JSON_STUDENTS_PATH));
            student = objectMapper.readValue(jsonData, Student.class);
            student.setStudentsDetails(StudentList);
            objectMapper.writeValue(new File(AppResource.JSON_STUDENTS_PATH), student);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method: Used to check the end date of courses and update student and teacher courses from current courses to past courses.
     */
    private void refreshDatabaseData() {
        List<CoursesDetails> CourseList = displayCourses();
        List<TeachersDetails> TeacherList = displayTeachers();
        List<StudentsDetails> StudentList = displayStudents();
        for (CoursesDetails coursesDetails : CourseList) {
            try {
                Date currentDate = new Date();
                DateFormat formatter = new SimpleDateFormat(AppResource.PATTERN_DATE_FORMAT);
                Date enddate = formatter.parse(coursesDetails.getEndDate());
                if (currentDate.after(enddate)) {
                    for (TeachersDetails teachersDetails : TeacherList) {
                        if (teachersDetails.getTeacherName().equals(coursesDetails.getTeacher())) {
                            List<String> teacherPastCourseslist = Arrays.asList(teachersDetails.getPastCourses().split(","));
                            if (!teacherPastCourseslist.contains(coursesDetails.getCourseName())) {
                                String[] teacherCurrentCourses = teachersDetails.getCurrentCourses().split(",");
                                if (teacherCurrentCourses.length == 2) {
                                    if (teacherCurrentCourses[0].equals(coursesDetails.getCourseName())) {
                                        teachersDetails.setCurrentCourses(teacherCurrentCourses[1]);
                                    } else if (teacherCurrentCourses[1].equals(coursesDetails.getCourseName())) {
                                        teachersDetails.setCurrentCourses(teacherCurrentCourses[0]);
                                    }
                                } else {
                                    teachersDetails.setCurrentCourses("");
                                }

                                String[] teacherPastCourses = teachersDetails.getPastCourses().split(",");
                                if (teacherPastCourses.length >= 2) {
                                    teachersDetails.setPastCourses(teachersDetails.getPastCourses() + "," + coursesDetails.getCourseName());
                                } else {
                                    if (teacherPastCourses[0].isEmpty()) {
                                        teachersDetails.setPastCourses(coursesDetails.getCourseName());
                                    } else {
                                        teachersDetails.setPastCourses(teachersDetails.getPastCourses() + "," + coursesDetails.getCourseName());
                                    }
                                }
                            }
                        }
                    }

                    for (StudentsDetails studentsDetails : StudentList) {
                        String[] courses = studentsDetails.getCurrentCoursesGrades().split("-");
                        String course1data, course2data, course3data;
                        String pastCourse = "";
                        boolean isthereanupdate = false;

                        if (courses[0].split(":")[1].equals(coursesDetails.getCourseName())) {
                            course1data = "0:None:NA";
                            pastCourse = courses[0];
                            isthereanupdate = true;
                        } else {
                            course1data = courses[0];
                        }

                        if (courses[1].split(":")[1].equals(coursesDetails.getCourseName())) {
                            course2data = "0:None:NA";
                            pastCourse = courses[1];
                            isthereanupdate = true;
                        } else {
                            course2data = courses[1];
                        }

                        if (courses[2].split(":")[1].equals(coursesDetails.getCourseName())) {
                            course3data = "0:None:NA";
                            pastCourse = courses[2];
                            isthereanupdate = true;
                        } else {
                            course3data = courses[2];
                        }

                        if (isthereanupdate) {
                            String currentCourses = course1data + "-" + course2data + "-" + course3data;
                            studentsDetails.setCurrentCoursesGrades(currentCourses);
                            String[] studentPastCourses = studentsDetails.getPastCoursesGrades().split(",");
                            if (studentPastCourses.length > 1) {
                                studentsDetails.setPastCoursesGrades(studentsDetails.getPastCoursesGrades() + "," + pastCourse);
                            } else {
                                if (studentPastCourses[0].isEmpty()) {
                                    studentsDetails.setPastCoursesGrades(pastCourse);
                                } else {
                                    studentsDetails.setPastCoursesGrades(studentsDetails.getPastCoursesGrades() + "," + pastCourse);
                                }
                            }
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        updateCourse(CourseList);
        updateTeacher(TeacherList);
        updateStudent(StudentList);
    }
}