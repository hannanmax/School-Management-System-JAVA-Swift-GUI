package SchoolManagementSystem.GUI;

import SchoolManagementSystem.AppResource;
import SchoolManagementSystem.Class.StudentsDetails;
import SchoolManagementSystem.Data.Database;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * This class is used to display all student list
 * This also contains:
 *      1) Add New student
 *      2) Assign student course
 *      3) Remove student course
 *
 * @author HannanAhmad(2094868)
 */
public class ViewStudents extends JFrame {

    /**
     * Used to get gui window and manage to separate in two sections.
     */
    Container mainContainer;

    /**
     * Constructor: Used to display list of students
     */
    public ViewStudents() {
        setTitle(AppResource.VIEW_STUDENTS);
        setSize(AppResource.WINDOW_WIDTH, AppResource.WINDOW_HEIGHT);
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
        JButton btnAddStudent = new JButton(AppResource.TXT_ADD_STUDENTS);

        btnBack.setIcon(new ImageIcon(AppResource.IMG_BTN_BACK));
        btnAddStudent.setIcon(new ImageIcon(AppResource.IMG_BTN_BG));

        //Setting Button backgrounds, Adding Action Listeners
        JButton[] buttons = {btnBack, btnAddStudent};

        for (JButton button : buttons) {
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
        }

        btnAddStudent.setHorizontalTextPosition(JButton.CENTER);
        btnAddStudent.setVerticalTextPosition(JButton.CENTER);
        btnAddStudent.setForeground(Color.white);

        btnBack.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new MainScreen();
                dispose();
            }
        });

        btnAddStudent.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new AddUpdateStudent();
                dispose();
            }
        });

        header.add(btnBack);
        header.add(btnAddStudent);
        mainContainer.add(header, BorderLayout.NORTH);
    }

    /**
     * Method: Used to assign and display list of all student
     */
    private void displayList() {
        JPanel container = new JPanel();
        container.setBorder(new LineBorder(Color.decode(AppResource.COLOR_BACKGROUND), 1));
        GridLayout gridLayout = new GridLayout(0, 3);
        gridLayout.setVgap(5);
        gridLayout.setHgap(5);
        container.setLayout(gridLayout);
        container.setBackground(Color.decode(AppResource.COLOR_BACKGROUND));
        Database database = new Database();
        List<StudentsDetails> studentList = database.displayStudents();

        for (StudentsDetails studentsDetails : studentList) {
            String tempCurrentCourses = studentsDetails.getCurrentCoursesGrades();
            String[] currentCoursesData = tempCurrentCourses.split("-");
            int countRunningCourses = 0;
            StringBuilder txtRunningCourses = new StringBuilder();
            for (String dataCourses : currentCoursesData) {
                String[] CourseData = dataCourses.split(":");
                if (!CourseData[1].equals("None")) {
                    countRunningCourses++;
                    txtRunningCourses.append("\n" + AppResource.TXT_TAB_4).append(CourseData[1]).append(": ");
                    if (CourseData[2].equals("NA")) {
                        txtRunningCourses.append("No Grades Posted");
                    } else {
                        txtRunningCourses.append("Grades ").append(CourseData[2]).append("%");
                    }
                }
            }
            String txtcurrentCourses = countRunningCourses + txtRunningCourses.toString();

            String txtPastCourses = "";
            String tempPastCourses = studentsDetails.getPastCoursesGrades();
            if(!tempPastCourses.isEmpty()){
                String[] pastCoursesData = tempPastCourses.split("-");
                int countPastCourses = 0;
                if(pastCoursesData.length != 0 && !pastCoursesData[0].isEmpty()){
                    for (String dataCourses : pastCoursesData) {
                        String[] CourseData = dataCourses.split(":");
                        if (!CourseData[1].equals("None")) {
                            countPastCourses++;
                            txtPastCourses = "\n" + AppResource.TXT_TAB_4 + CourseData[1] + ":";
                            if (CourseData[2].equals("NA")) {
                                txtPastCourses += "No Grades Posted";
                            } else {
                                txtPastCourses += "Grades " + CourseData[2] + "%";
                            }
                        }
                    }
                } else {
                    txtPastCourses = studentsDetails.getPastCoursesGrades();
                }
                txtPastCourses = countPastCourses + txtPastCourses;
            } else {
                txtPastCourses = "0";
            }

            String lbltext =
                    studentsDetails.getStudentID() + ") Student Name: " + studentsDetails.getStudentName() +
                            "\n" + AppResource.TXT_TAB + "Current Courses: " + txtcurrentCourses +
                            "\n" + AppResource.TXT_TAB + "Past Courses: " + txtPastCourses;

            JLabel lblStudentDetails = new JLabel();
            lblStudentDetails.setText("<html>" + lbltext.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
            JButton btnAssignStudentToClass = new JButton("<html><center>Assign<br/>Class</center></html>");
            JButton btnRemoveStudentFromClass = new JButton("<html><center>Remove<br/>Class</center></html>");
            lblStudentDetails.setSize(new Dimension(300, 40));

            //Setting Button backgrounds, Adding Action Listeners
            JButton[] buttons = {btnAssignStudentToClass,btnRemoveStudentFromClass};

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
            btnRemoveStudentFromClass.setIcon(new ImageIcon(AppResource.IMG_BTN_REMOVE));

            String[] currentCourses = studentsDetails.getCurrentCoursesGrades().split("-");
            int count = 0;
            for(String data: currentCourses){
                String[] course = data.split(":");
                if(!course[1].equals("None")){
                    count++;
                }
            }
            if (count < 3) {
                btnAssignStudentToClass.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        new AddUpdateStudent(studentsDetails.getStudentID(),studentsDetails.getStudentName(),studentsDetails.getCurrentCoursesGrades());
                        dispose();
                    }
                });
            } else {
                btnAssignStudentToClass.setVisible(false);
            }
            if(countRunningCourses > 0){
                btnRemoveStudentFromClass.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        new RemoveStudentCourse(studentsDetails.getStudentID(),studentsDetails.getStudentName(),studentsDetails.getCurrentCoursesGrades());
                        dispose();
                        //showMessageDialog(null,"Click...");
                    }
                });
            } else {
                btnRemoveStudentFromClass.setVisible(false);
            }

            container.add(lblStudentDetails);
            container.add(btnAssignStudentToClass);
            container.add(btnRemoveStudentFromClass);
        }

        JScrollPane scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(new Dimension(AppResource.WINDOW_WIDTH, AppResource.WINDOW_HEIGHT));
        scrollPane.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
        mainContainer.add(scrollPane, BorderLayout.CENTER);
    }
}