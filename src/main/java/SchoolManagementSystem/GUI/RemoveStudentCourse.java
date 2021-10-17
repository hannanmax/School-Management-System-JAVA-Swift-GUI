package SchoolManagementSystem.GUI;

import SchoolManagementSystem.AppResource;
import SchoolManagementSystem.Class.CoursesDetails;
import SchoolManagementSystem.Class.StudentsDetails;
import SchoolManagementSystem.Data.Database;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This class is used to display remove Student from specific courses GUI.
 * Note: You can you can only remove student from course if course has not ended.
 *
 * @author HannanAhmad(2094868)
 */
 public class RemoveStudentCourse extends JFrame {

    /**
     * Used to take student id of student
     */
    private final int studentID;
    /**
     * Used to take student name and it's current courses and grades.
     */
    private final String studentName, currentCoursesGrades;

    /**
     * Constructor: Used to remove student courses
     * @param studentID takes student id
     * @param studentName takes student name
     * @param currentCoursesGrades takes current courses grades
     */
    public RemoveStudentCourse(int studentID, String studentName, String currentCoursesGrades) {
        setTitle(AppResource.TXT_REMOVE_STUDENT_FROM_COURSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        ImageIcon img = new ImageIcon(AppResource.IMG_LOGO_PNG);
        setIconImage(img.getImage());

        this.studentID = studentID;
        this.studentName = studentName;
        this.currentCoursesGrades = currentCoursesGrades;

        initializeComponents();
        setVisible(true);
    }

    /**
     * Method: Used to initialise components in GUI
     */
    private void initializeComponents() {
        //StudentName Label
        JLabel lblStudenetFullName = new JLabel();
        lblStudenetFullName.setText("Full Name :");
        JTextField edtStudentFullName = new JTextField();

        //Course 1
        JLabel lblCourse1 = new JLabel();
        lblCourse1.setText("Course 1 :");

        //Course 2
        JLabel lblCourse2 = new JLabel();
        lblCourse2.setText("Course 2 :");

        //Course 3
        JLabel lblCourse3 = new JLabel();
        lblCourse3.setText("Course 3 :");

        // Submit
        JButton btnSubmit = new JButton("Remove Course");
        // Cancel
        JButton btnCancel = new JButton(AppResource.TXT_CANCEL);


        Database db = new Database();
        List<CoursesDetails> coursesDetails = db.displayCourses();
        int count = 1;
        for (CoursesDetails coursesDetail : coursesDetails) {
            try {
                List<StudentsDetails> studentsDetails = db.displayStudents();
                int courseNumberCount = 0;
                for(StudentsDetails sDetails: studentsDetails){
                    String tempCurrentCourses = sDetails.getCurrentCoursesGrades();
                    String[] currentCoursesData = tempCurrentCourses.split("-");
                    for (String dataCourses : currentCoursesData) {
                        String[] CourseData = dataCourses.split(":");
                        if (coursesDetail.getCourseName().equals(CourseData[1])) {
                            courseNumberCount++;
                        }
                    }
                }

                if(courseNumberCount < 5){
                    DateFormat formatter = new SimpleDateFormat(AppResource.PATTERN_DATE_FORMAT);
                    Date endDate = formatter.parse(coursesDetail.getEndDate());
                    Date currentDate = new Date();
                    if (currentDate.before(endDate)) {
                        count++;
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        String[] coursesNames = new String[count];
        int[] coursesID = new int[count];
        coursesNames[0] = "None";
        coursesID[0] = 0;
        count = 1;

        for (CoursesDetails coursesDetail: coursesDetails) {
            try {
                List<StudentsDetails> studentsDetails = db.displayStudents();
                int courseNumberCount = 0;
                for(StudentsDetails sDetails: studentsDetails){
                    String tempCurrentCourses = sDetails.getCurrentCoursesGrades();
                    String[] currentCoursesData = tempCurrentCourses.split("-");
                    for (String dataCourses : currentCoursesData) {
                        String[] CourseData = dataCourses.split(":");
                        if (coursesDetail.getCourseName().equals(CourseData[1])) {
                            courseNumberCount++;
                        }
                    }
                }
                if(courseNumberCount < 5){
                    DateFormat formatter = new SimpleDateFormat(AppResource.PATTERN_DATE_FORMAT);
                    Date endDate = formatter.parse(coursesDetail.getEndDate());
                    Date currentDate = new Date();
                    if (currentDate.before(endDate)) {
                        coursesNames[count] = coursesDetail.getCourseName();
                        coursesID[count] = coursesDetail.getCourseID();
                        count++;
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        JComboBox<String> edtCourse1;
        JComboBox<String> edtCourse2;
        JComboBox<String> edtCourse3;
        edtStudentFullName.setText(studentName);
        edtStudentFullName.setEditable(false);
        int fieldCount = 0;
        if (currentCoursesGrades.split("-")[0].split(":")[1].equals("None")) {
            edtCourse1 = new JComboBox<>(coursesNames);
            edtCourse1.setVisible(false);
        } else {
            String[] temp = new String[]{"None",currentCoursesGrades.split("-")[0].split(":")[1]};
            edtCourse1 = new JComboBox<>(temp);
            edtCourse1.setSelectedIndex(1);
            fieldCount++;
        }
        if (currentCoursesGrades.split("-")[1].split(":")[1].equals("None")) {
            edtCourse2 = new JComboBox<>(coursesNames);
            edtCourse2.setVisible(false);
        } else {
            String[] temp = new String[]{"None", currentCoursesGrades.split("-")[1].split(":")[1]};
            edtCourse2 = new JComboBox<>(temp);
            edtCourse2.setSelectedIndex(1);
            fieldCount++;
        }
        if (currentCoursesGrades.split("-")[2].split(":")[1].equals("None")) {
            edtCourse3 = new JComboBox<>(coursesNames);
            edtCourse3.setVisible(false);
        } else {
            String[] temp = new String[]{"None", currentCoursesGrades.split("-")[2].split(":")[1]};
            edtCourse3 = new JComboBox<>(temp);
            edtCourse3.setSelectedIndex(1);
            fieldCount++;
        }


        GridLayout gridLayout = new GridLayout(2+fieldCount, 2);
        gridLayout.setVgap(5);
        gridLayout.setHgap(5);
        JPanel panel = new JPanel(gridLayout);
        Border border = panel.getBorder();
        Border margin = new EmptyBorder(10, 10, 10, 10);
        panel.setBorder(new CompoundBorder(border, margin));

        panel.add(lblStudenetFullName);
        panel.add(edtStudentFullName);
        if(edtCourse1.isVisible()){
            panel.add(lblCourse1);
            panel.add(edtCourse1);
        }
        if(edtCourse2.isVisible()){
            panel.add(lblCourse2);
            panel.add(edtCourse2);
        }
        if(edtCourse3.isVisible()){
            panel.add(lblCourse3);
            panel.add(edtCourse3);
        }

        btnSubmit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    boolean course1none = currentCoursesGrades.split("-")[0].split(":")[1].equals("None");
                    boolean course2none = currentCoursesGrades.split("-")[1].split(":")[1].equals("None");
                    boolean course3none = currentCoursesGrades.split("-")[2].split(":")[1].equals("None");
                    if ((course1none) && (edtCourse1.getSelectedIndex() != 0) && (Objects.equals(edtCourse1.getSelectedItem(), edtCourse2.getSelectedItem()) || Objects.equals(edtCourse1.getSelectedItem(), edtCourse3.getSelectedItem()))){
                        showMessageDialog(null, "Course 1 cannot be same as Course 2 or Course 3...");
                    } else if ((course2none) && (edtCourse2.getSelectedIndex() != 0) && (Objects.equals(edtCourse2.getSelectedItem(), edtCourse3.getSelectedItem()) || Objects.equals(edtCourse2.getSelectedItem(), edtCourse1.getSelectedItem()))){
                        showMessageDialog(null, "Course 2 cannot be same as Course 1 or Course 3...");
                    } else if ((course3none) && (edtCourse3.getSelectedIndex() != 0) && (Objects.equals(edtCourse3.getSelectedItem(), edtCourse2.getSelectedItem()) || Objects.equals(edtCourse3.getSelectedItem(), edtCourse1.getSelectedItem()))){
                        showMessageDialog(null, "Course 3 cannot be same as Course 1 or Course 2...");
                    } else {
                        String course1;
                        String course2;
                        String course3;
                        if(edtCourse1.isVisible()){
                            course1 = Objects.requireNonNull(edtCourse1.getSelectedItem()).toString();
                        } else {
                            course1 = "None";
                        }
                        if(edtCourse2.isVisible()){
                            course2 = Objects.requireNonNull(edtCourse2.getSelectedItem()).toString();
                        } else {
                            course2 = "None";
                        }
                        if(edtCourse3.isVisible()){
                            course3 = Objects.requireNonNull(edtCourse3.getSelectedItem()).toString();
                        } else {
                            course3 = "None";
                        }
                        removeStudentFromClass(studentID, course1, course2, course3);
                    }
                } catch (NullPointerException e) {
                    showMessageDialog(null, AppResource.MESSAGE_FILL_ALL_FIELDS);
                }
            }
        });

        btnCancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new ViewStudents();
                dispose();
            }
        });

        btnSubmit.setIcon(new ImageIcon(AppResource.IMG_BTN_ADD));
        btnCancel.setIcon(new ImageIcon(AppResource.IMG_BTN_REMOVE));
        //Setting Button backgrounds, Adding Action Listeners
        JButton[] buttons = {btnSubmit, btnCancel};

        for (JButton button : buttons) {
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setVerticalTextPosition(JButton.CENTER);
            button.setForeground(Color.white);
        }

        panel.add(btnCancel);
        panel.add(btnSubmit);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Method: used to remove specific student from a course
     * @param studentID takes student id
     * @param course1 takes student course 1
     * @param course2 takes student course 2
     * @param course3 takes student course 3
     */
    private void removeStudentFromClass(int studentID, String course1, String course2, String course3) {
        Database db = new Database();
        List<StudentsDetails> studentList = db.displayStudents();
        for(StudentsDetails studentsDetails: studentList){
            if(studentID == studentsDetails.getStudentID()){
                String[] courses = studentsDetails.getCurrentCoursesGrades().split("-");
                String course1data,course2data,course3data;
                if(!courses[0].split(":")[1].equals(course1)){
                    course1data = "0:" + course1 + ":NA";
                } else {
                    course1data = courses[0];
                }
                if(!courses[1].split(":")[1].equals(course2)){
                    course2data = "0:" + course2 + ":NA";
                } else {
                    course2data = courses[1];
                }
                if(!courses[2].split(":")[1].equals(course3)){
                    course3data = "0:" + course3 + ":NA";
                } else {
                    course3data = courses[2];
                }
                String currentCourses = course1data + "-" + course2data + "-" + course3data;
                studentsDetails.setCurrentCoursesGrades(currentCourses);
            }
        }

        if (db.updateStudent(studentList)) {
            showMessageDialog(null, "Courses has been removed from student...");
            dispose();
            new ViewStudents();
        } else {
            showMessageDialog(null, AppResource.MESSAGE_SOMETHING_WRONG);
        }
    }
}
