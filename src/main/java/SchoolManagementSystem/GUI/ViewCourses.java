package SchoolManagementSystem.GUI;

import SchoolManagementSystem.AppResource;
import SchoolManagementSystem.Class.CoursesDetails;
import SchoolManagementSystem.Class.TeachersDetails;
import SchoolManagementSystem.Data.Database;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This class is used to display all courses list
 * This also contains:
 *      1) Add New course
 *      2) Assign/Remove course
 *      3) Display specific class students
 *      4) Display specific course attendance
 *
 * @author HannanAhmad(2094868)
 */
public class ViewCourses extends JFrame {

    /**
     * Used to get gui window and manage to separate in two sections.
     */
    Container mainContainer;

    /**
     * Constructor: Used to display list of courses
     */
    public ViewCourses() {
        setTitle(AppResource.VIEW_COURSES);
        setSize(AppResource.WINDOW_WIDTH + 200, AppResource.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        ImageIcon img = new ImageIcon(AppResource.IMG_LOGO_PNG);
        setIconImage(img.getImage());

        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(8, 6));
        mainContainer.setBackground(Color.decode(AppResource.COLOR_BACKGROUND));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.decode(AppResource.COLOR_BACKGROUND)));

        new Database();
        initializeComponents();
        displayList();
        setVisible(true);
    }

    /**
     * Method: Used to initialise components in GUI
     */
    private void initializeComponents() {
        JPanel header = new JPanel();
        header.setBorder(new LineBorder(Color.decode(AppResource.COLOR_BACKGROUND), 3));
        header.setLayout(new FlowLayout(5));
        header.setBackground(Color.decode(AppResource.COLOR_BACKGROUND));
        JButton btnBack = new JButton();
        JButton btnAddCourse = new JButton(AppResource.TXT_ADD_COURSES);

        btnBack.setIcon(new ImageIcon(AppResource.IMG_BTN_BACK));
        btnAddCourse.setIcon(new ImageIcon(AppResource.IMG_BTN_BG));

        //Setting Button backgrounds, Adding Action Listeners
        JButton[] buttons = {btnBack, btnAddCourse};

        for (JButton button : buttons) {
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
        }

        btnAddCourse.setHorizontalTextPosition(JButton.CENTER);
        btnAddCourse.setVerticalTextPosition(JButton.CENTER);
        btnAddCourse.setForeground(Color.white);

        btnBack.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new MainScreen();
                dispose();
            }
        });

        btnAddCourse.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new AddUpdateCourse();
                dispose();
            }
        });

        header.add(btnBack);
        header.add(btnAddCourse);
        mainContainer.add(header, BorderLayout.NORTH);
    }

    /**
     * Method: Used to assign and display list of all courses
     */
    private void displayList() {
        JPanel container = new JPanel();
        container.setBorder(new LineBorder(Color.decode(AppResource.COLOR_BACKGROUND), 1));
        GridLayout gridLayout = new GridLayout(0, 4);
        gridLayout.setVgap(30);
        gridLayout.setHgap(5);
        container.setLayout(gridLayout);
        container.setBackground(Color.decode(AppResource.COLOR_BACKGROUND));
        Database database = new Database();
        List<CoursesDetails> courseList = database.displayCourses();

        for (CoursesDetails coursesDetails : courseList) {
            String lbltext =
                    coursesDetails.getCourseID() + ") Course Name: " + coursesDetails.getCourseName() +
                            "\n" + AppResource.TXT_TAB + "Start Date: " + coursesDetails.getStartDate() +
                            "\n" + AppResource.TXT_TAB + "End Date: " + coursesDetails.getEndDate() +
                            "\n" + AppResource.TXT_TAB + "Pre Requisite: " + coursesDetails.getPreRequisite() +
                            "\n" + AppResource.TXT_TAB + "Teacher: " + coursesDetails.getTeacher();
            JLabel lblCourseDetails = new JLabel();
            lblCourseDetails.setText("<html>" + lbltext.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
            JButton btnAssignRemoveChangeTeacher = new JButton("<html><center>Assign<br/>Teacher</center></html>");
            JButton btnShowStudents = new JButton("<html><center>Display Class<br/>Students</center></html>");
            JButton btnShowAttendance = new JButton("Show Attendance");
            lblCourseDetails.setSize(new Dimension(300, 40));
            btnAssignRemoveChangeTeacher.setSize(new Dimension(120, 40));
            btnShowStudents.setSize(new Dimension(120, 40));
            btnShowAttendance.setSize(new Dimension(120, 40));

            //Setting Button backgrounds, Adding Action Listeners
            JButton[] buttons = {btnAssignRemoveChangeTeacher, btnShowStudents, btnShowAttendance};

            for (JButton button : buttons) {
                button.setIcon(new ImageIcon(AppResource.IMG_BTN_BG));
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setOpaque(false);
                button.setHorizontalTextPosition(JButton.CENTER);
                button.setVerticalTextPosition(JButton.CENTER);
                button.setForeground(Color.white);
            }

            try {
                Date currentDate = new Date();
                DateFormat formatter = new SimpleDateFormat(AppResource.PATTERN_DATE_FORMAT);
                Date enddate = formatter.parse(coursesDetails.getEndDate());
                if (coursesDetails.getTeacher().equals("None") && currentDate.before(enddate)) {
                    btnAssignRemoveChangeTeacher.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            new AddUpdateCourse(coursesDetails.getCourseName(), coursesDetails.getStartDate(), coursesDetails.getEndDate(), coursesDetails.getPreRequisite());
                            dispose();
                        }
                    });
                } else if(currentDate.before(enddate)){
                    btnAssignRemoveChangeTeacher.setText("<html><center>Remove <br/>Teacher</center></html>");
                    btnAssignRemoveChangeTeacher.setIcon(new ImageIcon(AppResource.IMG_BTN_REMOVE));
                    btnAssignRemoveChangeTeacher.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            removeTeacher(coursesDetails.getCourseID());
                        }
                    });
                } else {
                    btnAssignRemoveChangeTeacher.setVisible(false);
                }

                if(currentDate.before(enddate)){
                    btnShowStudents.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            new ViewClassStudents(coursesDetails.getCourseID(), coursesDetails.getCourseName(), coursesDetails.getEndDate());
                            dispose();
                        }
                    });
                } else {
                    btnShowStudents.setText("<html><center>Course Has<br/>Ended</center></html>");
                    btnShowStudents.setIcon(new ImageIcon(AppResource.IMG_BTN_REMOVE));
                }

                Date startdate = formatter.parse(coursesDetails.getStartDate());
                if (currentDate.after(startdate)) {
                    btnShowAttendance.setIcon(new ImageIcon(AppResource.IMG_BTN_ADD));
                    btnShowAttendance.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            showMessageDialog(null, "Current Class Average attendance is: " + coursesDetails.getAttendance() + "%");
                        }
                    });
                } else {
                    btnShowAttendance.setVisible(false);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            container.add(lblCourseDetails);
            container.add(btnAssignRemoveChangeTeacher);
            container.add(btnShowStudents);
            container.add(btnShowAttendance);
        }
        JScrollPane scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(new Dimension(AppResource.WINDOW_WIDTH, AppResource.WINDOW_HEIGHT));
        scrollPane.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
        mainContainer.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Method: Used to remove teacher from specific course
     * @param courseID takes course id
     */
    private void removeTeacher(int courseID) {
        Database db = new Database();
        List<CoursesDetails> CourseList = db.displayCourses();
        List<TeachersDetails> TeacherList = db.displayTeachers();
        for (CoursesDetails coursesDetails : CourseList) {
            if (coursesDetails.getCourseID() == courseID) {
                for (TeachersDetails teachersDetails: TeacherList){
                    if(teachersDetails.getTeacherName().equals(coursesDetails.getTeacher())){
                        String[] teacherCurrentCourses = teachersDetails.getCurrentCourses().split(",");
                        if(teacherCurrentCourses.length == 2){
                            if(teacherCurrentCourses[0].equals(coursesDetails.getCourseName())){
                                teachersDetails.setCurrentCourses(teacherCurrentCourses[1]);
                            } else if(teacherCurrentCourses[1].equals(coursesDetails.getCourseName())){
                                teachersDetails.setCurrentCourses(teacherCurrentCourses[0]);
                            }
                        } else if(teacherCurrentCourses[0].equals(coursesDetails.getCourseName()) && teacherCurrentCourses.length == 1){
                            teachersDetails.setCurrentCourses("");
                        } else {
                            showMessageDialog(null, AppResource.MESSAGE_SOMETHING_WRONG);
                        }
                    }
                }
                coursesDetails.setTeacher("None");
            }
        }
        if(db.updateCourse(CourseList) && db.updateTeacher(TeacherList)){
            showMessageDialog(null, "Teacher Has Been Removed...");
            dispose();
            new ViewCourses();
        } else {
            showMessageDialog(null, AppResource.MESSAGE_SOMETHING_WRONG);
        }
    }
}