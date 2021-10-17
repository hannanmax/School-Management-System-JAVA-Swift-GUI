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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This class is used to display Add Student and assign student courses GUI.
 * It has two different constructor.
 *       1) AddUpdateStudent() for Adding student
 *       2) AddUpdateStudent(int studentID, String studentName, String currentCoursesGrades) for assigning courses to student.
 *
 * Note: 1) Once student has been added you cannot change anything in student except courses.
 *       2) You can assign courses to student till course has not ended.
 *
 * @author HannanAhmad(2094868)
 */
public class AddUpdateStudent extends JFrame {

    /**
     * Used to takes student id in case of assign course.
     */
    private int studentID;

    /**
     * Used to takes student name and it's current courses and grades in case of assign course.
     */
    private String studentName, currentCoursesGrades;

    /**
     * Used to manage if class has called for Assigning student course or not
     * False: (By default) indicates Class is called to add new student
     * True: indicates Class is called to Assign course to student
     */
    private boolean assignCourse = false;

    /**
     * Constructor: Used to add new student in student.json
     */
    public AddUpdateStudent() {
        setTitle(AppResource.TXT_ADD_STUDENTS);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        ImageIcon img = new ImageIcon(AppResource.IMG_LOGO_PNG);
        setIconImage(img.getImage());

        initializeComponents();
        setVisible(true);
    }

    /**
     * Constructor: Used to assign course to specific student
     * @param studentID takes student id
     * @param studentName takes student name
     * @param currentCoursesGrades takes current courses and it's grades
     */
    public AddUpdateStudent(int studentID, String studentName, String currentCoursesGrades) {
        setTitle(AppResource.TXT_ASSIGN_STUDENT);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        ImageIcon img = new ImageIcon(AppResource.IMG_LOGO_PNG);
        setIconImage(img.getImage());

        assignCourse = true;
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
        JButton btnSubmit = new JButton(AppResource.TXT_ADD_STUDENTS);
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
        if (assignCourse) {
            edtStudentFullName.setText(studentName);
            btnSubmit.setText(AppResource.TXT_ASSIGN_COURSE);
            edtStudentFullName.setEditable(false);
            if (currentCoursesGrades.split("-")[0].split(":")[1].equals("None")) {
                edtCourse1 = new JComboBox<>(coursesNames);
            } else {
                String[] temp = new String[]{currentCoursesGrades.split("-")[0].split(":")[1]};
                edtCourse1 = new JComboBox<>(temp);
                edtCourse1.setEditable(false);
            }
            if (currentCoursesGrades.split("-")[1].split(":")[1].equals("None")) {
                edtCourse2 = new JComboBox<>(coursesNames);
            } else {
                String[] temp = new String[]{currentCoursesGrades.split("-")[1].split(":")[1]};
                edtCourse2 = new JComboBox<>(temp);
                edtCourse1.setEditable(false);
            }
            if (currentCoursesGrades.split("-")[2].split(":")[1].equals("None")) {
                edtCourse3 = new JComboBox<>(coursesNames);
            } else {
                String[] temp = new String[]{currentCoursesGrades.split("-")[2].split(":")[1]};
                edtCourse3 = new JComboBox<>(temp);
                edtCourse1.setEditable(false);
            }
        } else {
            edtCourse1 = new JComboBox<>(coursesNames);
            edtCourse2 = new JComboBox<>(coursesNames);
            edtCourse3 = new JComboBox<>(coursesNames);
        }

        GridLayout gridLayout = new GridLayout(5, 1);
        gridLayout.setVgap(5);
        gridLayout.setHgap(5);
        JPanel panel = new JPanel(gridLayout);
        Border border = panel.getBorder();
        Border margin = new EmptyBorder(10, 10, 10, 10);
        panel.setBorder(new CompoundBorder(border, margin));

        panel.add(lblStudenetFullName);
        panel.add(edtStudentFullName);
        panel.add(lblCourse1);
        panel.add(edtCourse1);
        panel.add(lblCourse2);
        panel.add(edtCourse2);
        panel.add(lblCourse3);
        panel.add(edtCourse3);

        btnSubmit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    if (assignCourse) {
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
                            addUpdateStudent(studentID, edtStudentFullName.getText(), coursesID[edtCourse1.getSelectedIndex()] + ":" + Objects.requireNonNull(edtCourse1.getSelectedItem()), coursesID[edtCourse2.getSelectedIndex()] + ":" + Objects.requireNonNull(edtCourse2.getSelectedItem()), coursesID[edtCourse3.getSelectedIndex()] + ":" + Objects.requireNonNull(edtCourse3.getSelectedItem()));
                        }
                    } else {
                        if (edtStudentFullName.getText().isEmpty()) {
                            showMessageDialog(null, "Please Enter Student Name...");
                        } else if ((edtCourse1.getSelectedIndex() != 0 && edtCourse2.getSelectedIndex() != 0) && edtCourse1.getSelectedIndex() == edtCourse2.getSelectedIndex()) {
                            showMessageDialog(null, "Course 1 and Course 2 cannot be same...");
                        } else if ((edtCourse2.getSelectedIndex() != 0 && edtCourse3.getSelectedIndex() != 0) && edtCourse2.getSelectedIndex() == edtCourse3.getSelectedIndex()) {
                            showMessageDialog(null, "Course 2 and Course 3 cannot be same...");
                        } else if ((edtCourse3.getSelectedIndex() != 0 && edtCourse1.getSelectedIndex() != 0) && edtCourse3.getSelectedIndex() == edtCourse1.getSelectedIndex()) {
                            showMessageDialog(null, "Course 1 and Course 3 cannot be same...");
                        } else if(!Pattern.matches(AppResource.PATTERN_CHARACTER_SPACE,edtStudentFullName.getText())){
                            showMessageDialog(null, "Invalid Student Name...");
                        } else {
                            addUpdateStudent(0, edtStudentFullName.getText(), coursesID[edtCourse1.getSelectedIndex()] + ":" + Objects.requireNonNull(edtCourse1.getSelectedItem()), coursesID[edtCourse2.getSelectedIndex()] + ":" + Objects.requireNonNull(edtCourse2.getSelectedItem()), coursesID[edtCourse3.getSelectedIndex()] + ":" + Objects.requireNonNull(edtCourse3.getSelectedItem()));
                        }
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
        panel.setBackground(Color.decode(AppResource.COLOR_BACKGROUND));
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Method: Used add new student or assign course to specific student in student.json
     * @param studentID takes student id
     * @param studentName takes student name
     * @param course1 takes student course 1
     * @param course2 takes student course 2
     * @param course3 takes student course 3
     */
    private void addUpdateStudent(int studentID, String studentName, String course1, String course2, String course3) {
        if(assignCourse){
            Database db = new Database();
            List<StudentsDetails> studentList = db.displayStudents();
            for(StudentsDetails studentsDetails: studentList){
                if(studentID == studentsDetails.getStudentID()){
                    String[] courses = studentsDetails.getCurrentCoursesGrades().split("-");
                    String course1data,course2data,course3data;
                    if(courses[0].split(":")[1].equals("None")){
                        course1data = course1 + ":NA";
                    } else {
                        course1data = courses[0];
                    }
                    if(courses[1].split(":")[1].equals("None")){
                        course2data = course2 + ":NA";
                    } else {
                        course2data = courses[1];
                    }
                    if(courses[2].split(":")[1].equals("None")){
                        course3data = course3 + ":NA";
                    } else {
                        course3data = courses[2];
                    }
                    String currentCourses = course1data + "-" + course2data + "-" + course3data;
                    studentsDetails.setCurrentCoursesGrades(currentCourses);
                }
            }

            if (db.updateStudent(studentList)) {
                showMessageDialog(null, "Courses has been assign to student...");
                dispose();
                new ViewStudents();
            } else {
                showMessageDialog(null, AppResource.MESSAGE_SOMETHING_WRONG);
            }
        } else {
            String currentCourses = course1 + ":NA-" + course2 + ":NA-" + course3 + ":NA";
            Database db = new Database();
            List<StudentsDetails> tempStudentList = db.displayStudents();
            List<StudentsDetails> studentList = new ArrayList<>();
            studentList.add(new StudentsDetails(tempStudentList.size() + 1, studentName, currentCourses, ""));
            if (db.addNewStudent(studentList)) {
                showMessageDialog(null, "New Student Has been added...");
                dispose();
                new ViewStudents();
            } else {
                showMessageDialog(null, AppResource.MESSAGE_SOMETHING_WRONG);
            }
        }
    }
}
