package SchoolManagementSystem;

import SchoolManagementSystem.GUI.MainScreen;

/**
 * This program is used to manage courses, teachers, and students of any school.
 * Note:
 *      1) School Name: To specify college name you can go to AppResource.java file and Change {@code COLLEGE_NAME}
 *          SchoolManagementSystem.AppResource to manage application resourses
 *
 *      2) Program Icon: To change program Icon replace the file "img_logo.png" with same name.
 *          SchoolManagementSystem.AppResource to manage application resourses
 *
 *      3) Course attandance is only shown if course has started
 *
 *      4) For Teacher Past Courses and Student past courses the system will automatically remove
 *         teacher current course, student current course to past courses when course has ended.
 *
 *      5) You can only add teacher if there is no course teacher or if course has not ended
 *
 *      6) You can only add student to course if course has not ended
 *
 *      7) Once a course has 5 students than you can't add student to that course
 *
 * @author HannanAhmad(2094868)
 * @version 1.0
 * @since 29/09/2021
 */
public class Main {
    public static void main(String[] args) {
        /**
         * You can ViewCourses,ViewTeachers,ViewStudents through calling MainScreen()
         */
        new MainScreen();
    }
}
