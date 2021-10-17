package SchoolManagementSystem.GUI;

import SchoolManagementSystem.AppResource;
import SchoolManagementSystem.Class.StudentsDetails;
import SchoolManagementSystem.Data.Database;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.regex.Pattern;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This class is used to display AddStudentGrades GUI.
 * It takes specific student details and displays current grades if exists or display none in grades field.
 * On the submit you can update student grades.
 * Note: 1) If the course has ended you cannot update grades.
 *       2) You can add grades infinite amount of time if courses has not ended
 *
 * @author HannanAhmad(2094868)
 */
public class AddStudentGrades extends JFrame {

    /**
     * Takes Student course id and student id.
     */
    private final int courseID, studentID;
    /**
     * Takes Courses name and it's end date.
     */
    private final String courseName, endDate;

    /**
     * Constructor: Default constructor with parameters.
     *         Displays GUI (Student name and Grade) fields.
     *         By default Student name field is disabled so that user cannot edit it and also gets idea for which student user is adding grades.
     * @param courseID takes course id
     * @param courseName takes course name
     * @param endDate takes end date
     * @param studentID takes student id
     */
    public AddStudentGrades(int courseID, String courseName, String endDate, int studentID) {
        setTitle(courseName + " " + AppResource.TXT_ADD_GRADES);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        ImageIcon img = new ImageIcon(AppResource.IMG_LOGO_PNG);
        setIconImage(img.getImage());

        this.studentID = studentID;
        this.courseID = courseID;
        this.endDate = endDate;
        this.courseName = courseName;
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

        //Grade Label
        JLabel lblGrade = new JLabel();
        lblGrade.setText("Grade :");
        JTextField edtGrade = new JTextField();

        // Submit
        JButton btnSubmit = new JButton(AppResource.TXT_ADD_GRADES);
        btnSubmit.setIcon(new ImageIcon(AppResource.IMG_BTN_ADD));
        // Cancel
        JButton btnCancel = new JButton(AppResource.TXT_CANCEL);
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

        GridLayout gridLayout = new GridLayout(3, 2);
        gridLayout.setVgap(5);
        gridLayout.setHgap(5);
        JPanel panel = new JPanel(gridLayout);
        Border border = panel.getBorder();
        Border margin = new EmptyBorder(10, 10, 10, 10);
        panel.setBorder(new CompoundBorder(border, margin));

        Database database = new Database();
        List<StudentsDetails> studentList = database.displayStudents();

        for (StudentsDetails studentsDetails : studentList) {
            if (studentsDetails.getStudentID() == this.studentID) {
                edtStudentFullName.setText(studentsDetails.getStudentName());
                edtStudentFullName.setEditable(false);
                String[] courses = studentsDetails.getCurrentCoursesGrades().split("-");
                if (courses[0].split(":")[1].equals(this.courseName) && !courses[0].split(":")[2].equals("NA")) {
                    edtGrade.setText(courses[0].split(":")[2]);
                } else if (courses[1].split(":")[1].equals(this.courseName) && !courses[1].split(":")[2].equals("NA")) {
                    edtGrade.setText(courses[1].split(":")[2]);
                } else if (courses[2].split(":")[1].equals(this.courseName) && !courses[2].split(":")[2].equals("NA")) {
                    edtGrade.setText(courses[2].split(":")[2]);
                }
            }
        }

        panel.add(lblStudenetFullName);
        panel.add(edtStudentFullName);
        panel.add(lblGrade);
        panel.add(edtGrade);

        btnSubmit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    if (edtStudentFullName.getText().isEmpty()) {
                        showMessageDialog(null, "Please Enter Student Name...");
                    } else if (edtGrade.getText().isEmpty()) {
                        showMessageDialog(null, "Please Enter Student Name...");
                    } else if (Pattern.matches(AppResource.PATTERN_NUMBER_ONLY, edtGrade.getText())) {
                        if (Integer.parseInt(edtGrade.getText()) <= 100 && Integer.parseInt(edtGrade.getText()) >= 0) {
                            //showMessageDialog(null, "All ok...");
                            addGrade(studentID,courseName,Integer.parseInt(edtGrade.getText()));
                        } else {
                            showMessageDialog(null, "Invalid Grade...");
                        }
                    } else {
                        showMessageDialog(null, "Invalid Grade...");
                    }
                } catch (NullPointerException e) {
                    showMessageDialog(null, AppResource.MESSAGE_FILL_ALL_FIELDS);
                }
            }
        });

        btnCancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new ViewClassStudents(courseID,courseName,endDate);
                dispose();
            }
        });

        panel.add(btnCancel);
        panel.add(btnSubmit);
        panel.setBackground(Color.decode(AppResource.COLOR_BACKGROUND));
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Method: Used to add grades to specified student id in parameter.
     * @param studentID takes student id
     * @param course takes course name in which user want to add grades
     * @param grades takes course grade
     */
    private void addGrade(int studentID, String course,int grades) {
        Database db = new Database();
        List<StudentsDetails> studentList = db.displayStudents();
        for(StudentsDetails studentsDetails: studentList){
            if(studentID == studentsDetails.getStudentID()){
                String[] courses = studentsDetails.getCurrentCoursesGrades().split("-");
                String course1data,course2data,course3data;

                if(courses[0].split(":")[1].equals(course)){
                    course1data = courses[0].split(":")[0] + ":" + courses[0].split(":")[1] + ":" + grades;
                } else {
                    course1data = courses[0];
                }
                if(courses[1].split(":")[1].equals(course)){
                    course2data = courses[1].split(":")[0] + ":" + courses[1].split(":")[1] + ":" + grades;
                } else {
                    course2data = courses[1];
                }
                if(courses[2].split(":")[1].equals(course)){
                    course3data = courses[3].split(":")[0] + ":" + courses[3].split(":")[1] + ":" + grades;
                } else {
                    course3data = courses[2];
                }
                String currentCourses = course1data + "-" + course2data + "-" + course3data;
                studentsDetails.setCurrentCoursesGrades(currentCourses);
            }
        }

        if (db.updateStudent(studentList)) {
            showMessageDialog(null, "Grades added to student...");
            new ViewClassStudents(this.courseID,this.courseName,this.endDate);
            dispose();
        } else {
            showMessageDialog(null, AppResource.MESSAGE_SOMETHING_WRONG);
        }
    }
}