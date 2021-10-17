package SchoolManagementSystem.GUI;

import SchoolManagementSystem.AppResource;
import SchoolManagementSystem.Class.CoursesDetails;
import SchoolManagementSystem.Class.TeachersDetails;
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
import java.util.List;
import java.util.*;
import java.util.regex.Pattern;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This class is used to display Add Teacher and Update GUI.
 * It has two different constructor.
 *       1) AddUpdateTeacher() for Adding teacher
 *       2) AddUpdateTeacher(int teacherID, String teacherName, String currentCourses) for assigning courses of techer.
 *
 * Note: 1) Once teacher has been added you cannot change anything in teacher except it's current courses.
 *       2) You can assign teacher to courses till course has not ended.
 *
 * @author HannanAhmad(2094868)
 */
public class AddUpdateTeacher extends JFrame {

    /**
     * Used to takes teacher id in case of assign course.
     */
    private int teacherID;

    /**
     * Used to takes teacher name and it's current in case of assign course.
     */
    private String teacherName,currentCourses;

    /**
     * Used to manage if class has called for Assigning teacher course or not
     * False: (By default) indicates Class is called to add new teacher
     * True: indicates Class is called to Assign course to teacher
     */
    private boolean assignCourse = false;

    /**
     * Constructor: Used to add new teacher in teacher.json
     */
    public AddUpdateTeacher() {
        setTitle(AppResource.TXT_ADD_TEACHERS);
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
     * Constructor: Used to assign course to specific teacher
     * @param teacherID takes teacher id
     * @param teacherName takes teacher name
     * @param currentCourses takes teacher current courses
     */
    public AddUpdateTeacher(int teacherID, String teacherName, String currentCourses) {
        setTitle(AppResource.TXT_ASSIGN_COURSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        ImageIcon img = new ImageIcon(AppResource.IMG_LOGO_PNG);
        setIconImage(img.getImage());

        assignCourse = true;
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.currentCourses = currentCourses;

        initializeComponents();
        setVisible(true);
    }

    /**
     * Method: Used to initialise components in GUI
     */
    private void initializeComponents() {
        //TeacherName Label
        JLabel lblTeacherFullName = new JLabel();
        lblTeacherFullName.setText("Full Name :");
        JTextField edtTeacherFullName = new JTextField();

        //Course 1
        JLabel lblCourse1 = new JLabel();
        lblCourse1.setText("Course 1 :");

        //Course 2
        JLabel lblCourse2 = new JLabel();
        lblCourse2.setText("Course 2 :");

        // Submit
        JButton btnSubmit = new JButton(AppResource.TXT_ADD_TEACHERS);
        // Cancel
        JButton btnCancel = new JButton(AppResource.TXT_CANCEL);

        Database db = new Database();
        List<CoursesDetails> coursesDetails = db.displayCourses();
        int count = 1;
        for (CoursesDetails coursesDetail : coursesDetails) {
            try {
                DateFormat formatter = new SimpleDateFormat(AppResource.PATTERN_DATE_FORMAT);
                Date endDate = formatter.parse(coursesDetail.getEndDate());
                Date currentDate = new Date();
                if (currentDate.before(endDate) && coursesDetail.getTeacher().equals("None")) {
                    count++;
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
        for (CoursesDetails coursesDetail : coursesDetails) {
            try {
                DateFormat formatter = new SimpleDateFormat(AppResource.PATTERN_DATE_FORMAT);
                Date endDate = formatter.parse(coursesDetail.getEndDate());
                Date currentDate = new Date();
                if (currentDate.before(endDate) && coursesDetail.getTeacher().equals("None")) {
                    coursesNames[count] = coursesDetail.getCourseName();
                    coursesID[count] = coursesDetail.getCourseID();
                    count++;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        JComboBox<String> edtCourse1;
        JComboBox<String> edtCourse2;
        if (assignCourse) {
            edtTeacherFullName.setText(teacherName);
            btnSubmit.setText(AppResource.TXT_ASSIGN_COURSE);
            edtTeacherFullName.setEditable(false);
            if (currentCourses.split(",")[0].equals("")) {
                edtCourse1 = new JComboBox<>(coursesNames);
            } else {
                String[] temp = new String[]{currentCourses.split(",")[0]};
                edtCourse1 = new JComboBox<>(temp);
                edtCourse1.setEditable(false);
            }

            if (currentCourses.split(",").length > 1) {
                String[] temp = new String[]{currentCourses.split(",")[0]};
                edtCourse2 = new JComboBox<>(temp);
                edtCourse1.setEditable(false);
            } else {
                edtCourse2 = new JComboBox<>(coursesNames);
            }
        } else {
            edtCourse1 = new JComboBox<>(coursesNames);
            edtCourse2 = new JComboBox<>(coursesNames);
        }

        GridLayout gridLayout = new GridLayout(4, 1);
        gridLayout.setVgap(10);
        gridLayout.setHgap(10);
        JPanel panel = new JPanel(gridLayout);
        Border border = panel.getBorder();
        Border margin = new EmptyBorder(10, 10, 10, 10);
        panel.setBorder(new CompoundBorder(border, margin));

        panel.add(lblTeacherFullName);
        panel.add(edtTeacherFullName);
        panel.add(lblCourse1);
        panel.add(edtCourse1);
        panel.add(lblCourse2);
        panel.add(edtCourse2);

        btnSubmit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    if (edtTeacherFullName.getText().isEmpty()) {
                        showMessageDialog(null, "Please Enter Teacher Name...");
                    }  else if(!Pattern.matches(AppResource.PATTERN_CHARACTER_SPACE,edtTeacherFullName.getText())){
                        showMessageDialog(null, "Invalid Teacher Name...");
                    } else if (assignCourse) {
                        boolean course1none = currentCourses.split(",")[0].equals("");
                        if ((course1none) && (edtCourse1.getSelectedIndex() != 0) && (Objects.equals(edtCourse1.getSelectedItem(), edtCourse2.getSelectedItem()))) {
                            showMessageDialog(null, "Course 1 and Course 2 cannot be same...");
                        } else if ((edtCourse2.getSelectedIndex() != 0) && (Objects.equals(edtCourse2.getSelectedItem(), edtCourse1.getSelectedItem()))) {
                            showMessageDialog(null, "Course 1 and Course 2 cannot be same...");
                        } else {
                            addUpdateTeacher(teacherID,edtTeacherFullName.getText(), Objects.requireNonNull(edtCourse1.getSelectedItem()).toString(), Objects.requireNonNull(edtCourse2.getSelectedItem()).toString());
                        }
                    } else {
                        if ((edtCourse1.getSelectedIndex() != 0 && edtCourse2.getSelectedIndex() != 0) && edtCourse1.getSelectedIndex() == edtCourse2.getSelectedIndex()) {
                            showMessageDialog(null, "Course 1 and Course 2 cannot be same...");
                        } else {
                            addUpdateTeacher(0,edtTeacherFullName.getText(), Objects.requireNonNull(edtCourse1.getSelectedItem()).toString(), Objects.requireNonNull(edtCourse2.getSelectedItem()).toString());
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
                new ViewTeachers();
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
     * Method: Used add new teacher or assign course to specific teacher in teacher.json
     * @param teacherId takes teacher id
     * @param teacherName takes teacher name
     * @param course1 takes course 1 of teacher
     * @param course2 takes course 2 of teacher
     */
    private void addUpdateTeacher(int teacherId, String teacherName, String course1, String course2) {
        Database db = new Database();
        if(assignCourse){
            String CurrentCourses = "";
            if(!course1.equals("None") && !course2.equals("None")){
                CurrentCourses = course1 + "," + course2;
            } else if(!course2.equals("None")){
                CurrentCourses = course2;
            } else if(!course1.equals("None")){
                CurrentCourses = course1;
            }
            List<CoursesDetails> courseList = db.displayCourses();
            for(CoursesDetails coursesDetails: courseList){
                if(course1.equals(coursesDetails.getCourseName())){
                    coursesDetails.setTeacher(teacherName);
                } else if(course2.equals(coursesDetails.getCourseName())){
                    coursesDetails.setTeacher(teacherName);
                }
            }
            List<TeachersDetails> teacherList = db.displayTeachers();
            if(teacherId != 0){
                for(TeachersDetails teachersDetails: teacherList){
                    if(teacherId == teachersDetails.getTeacherID()){
                        teachersDetails.setCurrentCourses(CurrentCourses);
                    }
                }
            }

            if(db.updateCourse(courseList) && db.updateTeacher(teacherList)){
                showMessageDialog(null, "Courses Has been assigned to teacher...");
                dispose();
                new ViewTeachers();
            } else {
                showMessageDialog(null, AppResource.MESSAGE_SOMETHING_WRONG);
            }
        } else {
            List<TeachersDetails> tempTeacherList = db.displayTeachers();
            List<TeachersDetails> teacherList = new ArrayList<>();
            String CurrentCourses = "";
            if(!course1.equals("None") && !course2.equals("None")){
                CurrentCourses = course1 + "," + course2;
            } else if(!course2.equals("None")){
                CurrentCourses = course2;
            } else if(!course1.equals("None")){
                CurrentCourses = course1;
            }
            teacherList.add(new TeachersDetails(tempTeacherList.size() + 1, teacherName, CurrentCourses, ""));

            List<CoursesDetails> courseList = db.displayCourses();
            for(CoursesDetails coursesDetails: courseList){
                if(course1.equals(coursesDetails.getCourseName())){
                    coursesDetails.setTeacher(teacherName);
                } else if(course2.equals(coursesDetails.getCourseName())){
                    coursesDetails.setTeacher(teacherName);
                }
            }

            if (db.addNewTeacher(teacherList) && db.updateCourse(courseList)) {
                showMessageDialog(null, "New Teacher Has been added...");
                dispose();
                new ViewTeachers();
            } else {
                showMessageDialog(null, AppResource.MESSAGE_SOMETHING_WRONG);
            }
        }
    }
}
