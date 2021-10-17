package SchoolManagementSystem.GUI;

import SchoolManagementSystem.AppResource;
import SchoolManagementSystem.Class.CoursesDetails;
import SchoolManagementSystem.Class.TeachersDetails;
import SchoolManagementSystem.Data.Database;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This class is used to display Add course and assign course teacher GUI.
 * It has two different constructor.
 *       1) AddUpdateCourse() for Adding course
 *       2) AddUpdateCourse(String courseName, String startDate, String endDate, String prerequisite) for assigning teacher.
 *
 * Note: 1) Once course has been added you cannot change anything in course except course teacher.
 *       2) You can add courses teacher till course has not ended.
 *
 * @author HannanAhmad(2094868)
 */
public class AddUpdateCourse extends JFrame{

    /**
     * Used to takes Course name, Stare Date, End Date Prerequisite in case of Assign teacher of course.
     */
    private String courseName,startDate,endDate,prerequisite;

    /**
     * Used to manage if class has called for Assigning teacher or not
     * False: (By default) indicates Class is called to add course
     * True: indicates Class is called to Assign teacher
     */
    private boolean AssignTeacher = false;

    /**
     * Constructor: Used to assign teacher to specific course
     * @param courseName takes course name
     * @param startDate takes start date of course
     * @param endDate takes end date of course
     * @param prerequisite talk course prerequisite
     */
    public AddUpdateCourse(String courseName, String startDate, String endDate, String prerequisite){
        setTitle(AppResource.TXT_ASSIGN_TEACHER);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        ImageIcon img = new ImageIcon(AppResource.IMG_LOGO_PNG);
        setIconImage(img.getImage());

        this.courseName =courseName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prerequisite = prerequisite;
        AssignTeacher = true;

        initializeComponents();
        setVisible(true);
    }

    /**
     * Constructor: Used to add new course in course.json
     */
    public AddUpdateCourse(){
        setTitle(AppResource.TXT_ADD_COURSES);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        ImageIcon img = new ImageIcon(AppResource.IMG_LOGO_PNG);
        setIconImage(img.getImage());

        initializeComponents();
        setVisible(true);
    }

    /**
     * Method: Used to initialise components in GUI
     */
    private void initializeComponents() {
        //CourseName Label
        JLabel lblcourseName = new JLabel();
        lblcourseName.setText("Course Name :");
        JTextField edtCourseName = new JTextField();

        //StartDate
        JLabel lblStartDate = new JLabel();
        lblStartDate.setText("Start Date :");
        JXDatePicker edtStartDate = new JXDatePicker();
        edtStartDate.setFormats(new SimpleDateFormat(AppResource.PATTERN_DATE_FORMAT));

        //EndDate
        JLabel lblEndDate = new JLabel();
        lblEndDate.setText("End Date :");
        JXDatePicker edtEndDate = new JXDatePicker();
        edtEndDate.setFormats(new SimpleDateFormat(AppResource.PATTERN_DATE_FORMAT));

        //Prerequsites
        JLabel lblPreRequisite = new JLabel();
        lblPreRequisite.setText("Pre requisites :");
        JTextField edtPreRequisite = new JTextField();

        // Submit
        JButton btnSubmit = new JButton(AppResource.TXT_ADD_COURSES);
        // Cancel
        JButton btnCancel = new JButton(AppResource.TXT_CANCEL);

        if(AssignTeacher){
            try {
                edtCourseName.setText(courseName);
                edtCourseName.setEditable(false);
                DateFormat formatter = new SimpleDateFormat(AppResource.PATTERN_DATE_FORMAT);
                Date date;
                date = formatter.parse(startDate);
                edtStartDate.setDate(date);
                edtStartDate.setEditable(false);
                edtStartDate.disable();
                date = formatter.parse(endDate);
                edtEndDate.setDate(date);
                edtEndDate.setEditable(false);
                edtEndDate.disable();
                edtPreRequisite.setText(prerequisite);
                edtPreRequisite.setEditable(false);
                btnSubmit.setText(AppResource.TXT_ASSIGN_TEACHER);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //Teacher
        JLabel lblTeacherName = new JLabel();
        lblTeacherName.setText("Teacher Name :");

        Database db = new Database();
        List<TeachersDetails> teachersDetails = db.displayTeachers();
        int count = 1;
        for (TeachersDetails teachersDetail : teachersDetails) {
            String str = teachersDetail.getCurrentCourses();
            String[] arrOfStr = str.split(",");
            if (arrOfStr.length < 2) {
                count++;
            }
        }
        String[] teacherNames = new String[count];
        int[] teacherID = new int[count];
        teacherNames[0] = "None";
        teacherID[0] = 0;
        count = 1;
        for (TeachersDetails teachersDetail : teachersDetails) {
            String str = teachersDetail.getCurrentCourses();
            String[] arrOfStr = str.split(",");
            if (arrOfStr.length < 2) {
                teacherNames[count] = teachersDetail.getTeacherName();
                teacherID[count] = teachersDetail.getTeacherID();
                count++;
            }
        }
        JComboBox<String> edtTeacherName = new JComboBox<>(teacherNames);

        GridLayout gridLayout = new GridLayout(6,1);
        gridLayout.setVgap(10);
        gridLayout.setHgap(10);
        JPanel panel = new JPanel(gridLayout);
        Border border = panel.getBorder();
        Border margin = new EmptyBorder(10,10,10,10);
        panel.setBorder(new CompoundBorder(border, margin));

        panel.add(lblcourseName);
        panel.add(edtCourseName);
        panel.add(lblStartDate);
        panel.add(edtStartDate);
        panel.add(lblEndDate);
        panel.add(edtEndDate);
        panel.add(lblPreRequisite);
        panel.add(edtPreRequisite);
        panel.add(lblTeacherName);
        panel.add(edtTeacherName);

        btnSubmit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if(edtTeacherName.getSelectedIndex() == 0 && AssignTeacher){
                    showMessageDialog(null, "No Changes has been made...");
                    new ViewCourses();
                    dispose();
                } else {
                    try{
                        Date currentDate = new Date();
                        Calendar c = Calendar.getInstance();
                        c.setTime(edtStartDate.getDate());
                        c.add(Calendar.MONTH, 3);
                        Date expenctedDate = c.getTime();

                        if(edtCourseName.getText().isEmpty()){
                            showMessageDialog(null, "Please Enter Course Name...");
                        } else if(edtStartDate.getDate().before(currentDate)){
                            showMessageDialog(null, "Course date can't be of current date or past dates...");
                        } else if(edtEndDate.getDate().before(expenctedDate)){
                            showMessageDialog(null, "Course lenght should be atleast 3 months");
                        } else if(edtPreRequisite.getText().isEmpty()){
                            showMessageDialog(null, "Please Enter PreRequisite...");
                        } else if(!Pattern.matches(AppResource.PATTERN_CHARACTER_SPACE,edtCourseName.getText())){
                            showMessageDialog(null, "Invalid Name...");
                        } else if(!Pattern.matches(AppResource.PATTERN_CHARACTER_SPACE,edtPreRequisite.getText())){
                            showMessageDialog(null, "Invalid PreRequisite...");
                        } else {
                            addUpdateCourse(edtCourseName.getText(), edtStartDate.getDate(), edtEndDate.getDate(), edtPreRequisite.getText(), Objects.requireNonNull(edtTeacherName.getSelectedItem()).toString(),teacherID[edtTeacherName.getSelectedIndex()]);
                        }
                    } catch (NullPointerException e){
                        showMessageDialog(null, AppResource.MESSAGE_FILL_ALL_FIELDS);
                    }
                }
            }
        });

        btnCancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new ViewCourses();
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
     * Method: Used add new course or assign teacher in specific course in course.json
     * @param courseName takes course name
     * @param tempStartDate takes start date of course
     * @param tempEndDate takes end date of course
     * @param preRequisite takes pre requisite of course
     * @param teacherName takes teacher name
     * @param teacherId takes teacher id
     */
    private void addUpdateCourse(String courseName, Date tempStartDate, Date tempEndDate, String preRequisite, String teacherName, int teacherId){
        if(AssignTeacher){
            Database db = new Database();
            List<CoursesDetails> courseList = db.displayCourses();
            for(CoursesDetails coursesDetails: courseList){
                if(courseName.equals(coursesDetails.getCourseName())){
                    coursesDetails.setTeacher(teacherName);
                }
            }

            List<TeachersDetails> teacherList = db.displayTeachers();
            if(teacherId != 0){
                for(TeachersDetails teachersDetails: teacherList){
                    if(teacherId == teachersDetails.getTeacherID()){
                        String[] arrOfStr = teachersDetails.getCurrentCourses().split(",");
                        if(!arrOfStr[0].isEmpty()){
                            String tempCourse = teachersDetails.getCurrentCourses() + "," + courseName;
                            teachersDetails.setCurrentCourses(tempCourse);
                        } else {
                            teachersDetails.setCurrentCourses(courseName);
                        }
                    }
                }
            }
            if(db.updateCourse(courseList) && db.updateTeacher(teacherList)){
                showMessageDialog(null, "Course teacher Has been assigned...");
                dispose();
                new ViewCourses();
            } else {
                showMessageDialog(null, AppResource.MESSAGE_SOMETHING_WRONG);
            }
        } else {
            String startDate = new SimpleDateFormat(AppResource.PATTERN_DATE_FORMAT).format(tempStartDate);
            String endDate = new SimpleDateFormat(AppResource.PATTERN_DATE_FORMAT).format(tempEndDate);
            int attandance = new Random().nextInt(100-75) + 75;
            Database db = new Database();
            List<CoursesDetails> tempCourseList = db.displayCourses();
            List<CoursesDetails> courseList = new ArrayList<>();
            courseList.add(new CoursesDetails(tempCourseList.size() + 1, courseName, startDate, endDate, preRequisite, teacherName,attandance));

            List<TeachersDetails> teacherList = db.displayTeachers();
            if(teacherId != 0){
                for(TeachersDetails teachersDetails: teacherList){
                    if(teacherId == teachersDetails.getTeacherID()){
                        String[] teacherCurrentCourses = teachersDetails.getCurrentCourses().split(",");
                        if(!teacherCurrentCourses[0].isEmpty()){
                            String tempCourse = teachersDetails.getCurrentCourses() + "," + courseName;
                            teachersDetails.setCurrentCourses(tempCourse);
                        } else {
                            teachersDetails.setCurrentCourses(courseName);
                        }
                    }
                }
            }
            if(db.addNewCourse(courseList) && db.updateTeacher(teacherList)){
                showMessageDialog(null, "New Course Has been added...");
                dispose();
                new ViewCourses();
            } else {
                showMessageDialog(null, AppResource.MESSAGE_SOMETHING_WRONG);
            }
        }
    }
}