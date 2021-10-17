package SchoolManagementSystem.GUI;

import SchoolManagementSystem.AppResource;
import SchoolManagementSystem.Class.StudentsDetails;
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
 * This class is used to display current class student
 *
 * @author HannanAhmad(2094868)
 */
public class ViewClassStudents extends JFrame {

    /**
     * Used to get gui window and manage to separate in two sections.
     */
    private final Container mainContainer;
    /**
     * Used to take course id of course.
     */
    private final int courseID;
    /**
     * Used to take course name and it's end date.
     */
    private final String courseName, endDate;
    /**
     * Used to set button add student.
     */
    private JButton btnAddStudent;

    /**
     * Constructor: Used to display specific class student
     * @param courseID takes course id
     * @param courseName takes course name
     * @param endDate takes course end date
     */
    public ViewClassStudents(int courseID, String courseName, String endDate) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.endDate = endDate;
        setTitle(courseName + AppResource.VIEW_CLASS_STUDENT);
        setSize(AppResource.WINDOW_WIDTH + 50, AppResource.WINDOW_HEIGHT);
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
        pack();
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
        btnAddStudent = new JButton(AppResource.TXT_ADD_STUDENTS);

        btnBack.setIcon(new ImageIcon(AppResource.IMG_BTN_BACK));
        btnAddStudent.setIcon(new ImageIcon(AppResource.IMG_BTN_BG));


        //Setting Button backgrounds, Adding Action Listeners
        JButton[] buttons = {btnBack, btnAddStudent};

        for (JButton button : buttons) {
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setVerticalTextPosition(JButton.CENTER);
            button.setForeground(Color.white);
        }

        btnBack.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new ViewCourses();
                dispose();
            }
        });

        btnAddStudent.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new ViewStudents();
                dispose();
            }
        });

        header.add(btnBack);
        header.add(btnAddStudent);
        mainContainer.add(header, BorderLayout.NORTH);
    }

    private void displayList() {
        JPanel container = new JPanel();
        container.setBorder(new LineBorder(Color.decode(AppResource.COLOR_BACKGROUND), 1));
        GridLayout gridLayout = new GridLayout(0, 3);
        gridLayout.setVgap(30);
        gridLayout.setHgap(5);
        container.setLayout(gridLayout);
        container.setBackground(Color.decode(AppResource.COLOR_BACKGROUND));
        Database database = new Database();
        List<StudentsDetails> studentsDetails = database.displayStudents();

        int studentCount = 0;
        int count = 1;
        for (StudentsDetails studentsDetail : studentsDetails) {
            String studentName;
            String tempCurrentCourses = studentsDetail.getCurrentCoursesGrades();
            String[] currentCoursesData = tempCurrentCourses.split("-");
            for (String dataCourses : currentCoursesData) {
                String[] CourseData = dataCourses.split(":");
                if (CourseData[0].equals(String.valueOf(courseID))) {
                    studentName = count + ") " + studentsDetail.getStudentName();
                    count++;

                    if (CourseData[2].equals("NA")) {
                        studentName += "\n" + AppResource.TXT_TAB + "No Grades Posted";
                    } else {
                        if(Integer.parseInt(CourseData[2]) < 40){
                            studentName += "\n" + AppResource.TXT_TAB + "Grades " + CourseData[2] + "%";
                            studentName += "\n" + AppResource.TXT_TAB + "Note: Student might failing,";
                            studentName += "\n" + AppResource.TXT_TAB + "Passing criteria is 40%";
                        } else {
                            studentName += "\n" + AppResource.TXT_TAB + "Grades " + CourseData[2] + "%";
                        }
                    }

                    JLabel lblStudentDetails = new JLabel();
                    lblStudentDetails.setText("<html>" + studentName.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
                    lblStudentDetails.setSize(new Dimension(300, 40));
                    JButton btnAddGrades = new JButton(AppResource.TXT_ADD_GRADES);
                    JButton btnRemoveFromClass = new JButton("<html><center>Remove<br/>From Class</center></html>");
                    btnAddGrades.setIcon(new ImageIcon(AppResource.IMG_BTN_BG));
                    btnRemoveFromClass.setIcon(new ImageIcon(AppResource.IMG_BTN_REMOVE));

                    //Setting Button backgrounds, Adding Action Listeners
                    JButton[] buttons = {btnRemoveFromClass,btnAddGrades};
                    for (JButton button : buttons) {
                        button.setBorderPainted(false);
                        button.setContentAreaFilled(false);
                        button.setFocusPainted(false);
                        button.setOpaque(false);
                        button.setHorizontalTextPosition(JButton.CENTER);
                        button.setVerticalTextPosition(JButton.CENTER);
                        button.setForeground(Color.white);
                    }

                    try {
                        DateFormat formatter = new SimpleDateFormat(AppResource.PATTERN_DATE_FORMAT);
                        Date tempendDate = formatter.parse(endDate);
                        Date currentDate = new Date();
                        if (currentDate.before(tempendDate)) {
                            btnRemoveFromClass.addActionListener(new AbstractAction() {
                                @Override
                                public void actionPerformed(ActionEvent event) {
                                    removeStudentFromClass(studentsDetail.getStudentID(), courseName);
                                }
                            });
                            btnAddGrades.addActionListener(new AbstractAction() {
                                @Override
                                public void actionPerformed(ActionEvent event) {
                                    dispose();
                                    new AddStudentGrades(courseID, courseName, endDate, studentsDetail.getStudentID());
                                }
                            });
                        } else {
                            btnAddGrades.setVisible(false);
                            btnRemoveFromClass.setVisible(false);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    container.add(lblStudentDetails);
                    container.add(btnAddGrades);
                    container.add(btnRemoveFromClass);
                    studentCount++;
                }
            }
        }
        if(studentCount >= 5){
            btnAddStudent.setVisible(false);
        }

        JScrollPane scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(new Dimension(AppResource.WINDOW_WIDTH, AppResource.WINDOW_HEIGHT));
        mainContainer.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Method: Used to remove specific student from course
     * @param studentID takes student id
     * @param courseName takes course name
     */
    private void removeStudentFromClass(int studentID, String courseName) {
        Database db = new Database();
        List<StudentsDetails> studentList = db.displayStudents();
        for (StudentsDetails studentsDetails : studentList) {
            if (studentID == studentsDetails.getStudentID()) {
                String[] courses = studentsDetails.getCurrentCoursesGrades().split("-");
                String course1data, course2data, course3data;
                if (courses[0].split(":")[1].equals(courseName)) {
                    course1data = "0:none:NA";
                } else {
                    course1data = courses[0];
                }
                if (courses[1].split(":")[1].equals(courseName)) {
                    course2data = "0:none:NA";
                } else {
                    course2data = courses[1];
                }
                if (courses[2].split(":")[1].equals(courseName)) {
                    course3data = "0:none:NA";
                } else {
                    course3data = courses[2];
                }
                String currentCourses = course1data + "-" + course2data + "-" + course3data;
                studentsDetails.setCurrentCoursesGrades(currentCourses);
            }
        }

        if (db.updateStudent(studentList)) {
            showMessageDialog(null, "Student has been removed from course...");
            dispose();
            new ViewClassStudents(this.courseID, this.courseName, this.endDate);
        } else {
            showMessageDialog(null, AppResource.MESSAGE_SOMETHING_WRONG);
        }
    }
}