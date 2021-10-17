package SchoolManagementSystem.GUI;

import SchoolManagementSystem.AppResource;
import SchoolManagementSystem.Class.TeachersDetails;
import SchoolManagementSystem.Data.Database;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * This class is used to display all teacher list
 * This also contains:
 *      1) Add New student
 *      2) Assign teacher course
 *
 * @author HannanAhmad(2094868)
 */
public class ViewTeachers extends JFrame {

    /**
     * Used to get gui window and manage to separate in two sections.
     */
    Container mainContainer;

    /**
     * Constructor: Used to display list of teachers
     */
    public ViewTeachers(){
        setTitle(AppResource.VIEW_TEACHERS);
        setSize(AppResource.WINDOW_WIDTH-200, AppResource.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        ImageIcon img = new ImageIcon(AppResource.IMG_LOGO_PNG);
        setIconImage(img.getImage());

        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        mainContainer.setBackground(Color.decode(AppResource.COLOR_BACKGROUND));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.decode(AppResource.COLOR_BACKGROUND)));

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
        header.setBorder(new LineBorder(Color.decode(AppResource.COLOR_BACKGROUND),3));
        header.setLayout(new FlowLayout(5));
        header.setBackground(Color.decode(AppResource.COLOR_BACKGROUND));
        JButton btnBack = new JButton();
        JButton btnAddTeacher = new JButton(AppResource.TXT_ADD_TEACHERS);

        btnBack.setIcon(new ImageIcon(AppResource.IMG_BTN_BACK));
        btnAddTeacher.setIcon(new ImageIcon(AppResource.IMG_BTN_BG));

        //Setting Button backgrounds, Adding Action Listeners
        JButton[] buttons = {btnBack, btnAddTeacher};

        for (JButton button : buttons) {
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
        }

        btnAddTeacher.setHorizontalTextPosition(JButton.CENTER);
        btnAddTeacher.setVerticalTextPosition(JButton.CENTER);
        btnAddTeacher.setForeground(Color.white);

        btnBack.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new MainScreen();
                dispose();
            }
        });

        btnAddTeacher.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new AddUpdateTeacher();
                dispose();
            }
        });

        header.add(btnBack);
        header.add(btnAddTeacher);
        mainContainer.add(header,BorderLayout.NORTH);
    }

    /**
     * Method: Used to assign and display list of all teachers
     */
    private void displayList(){
        JPanel container = new JPanel();
        container.setBorder(new LineBorder(Color.decode(AppResource.COLOR_BACKGROUND),1));
        GridLayout gridLayout = new GridLayout(0,2);
        gridLayout.setVgap(10);
        container.setLayout(gridLayout);
        container.setBackground(Color.decode(AppResource.COLOR_BACKGROUND));
        Database database = new Database();
        List<TeachersDetails> teacherList = database.displayTeachers();

        for (TeachersDetails teachersDetails : teacherList) {
            String lbltext =
                    teachersDetails.getTeacherID() + ") Teacher Name: " + teachersDetails.getTeacherName() +
                            "\n" + AppResource.TXT_TAB + "Current Courses: " + teachersDetails.getCurrentCourses() +
                            "\n" + AppResource.TXT_TAB + "Past Courses: " + teachersDetails.getPastCourses();
            JLabel lblTeacherDetails = new JLabel();
            lblTeacherDetails.setText("<html>" + lbltext.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");

            JButton btnAssignCourse = new JButton(AppResource.TXT_ASSIGN_COURSE);
            lblTeacherDetails.setSize(new Dimension(300,40));
            btnAssignCourse.setSize(new Dimension(120,40));
            String str = teachersDetails.getCurrentCourses();
            String[] arrOfStr = str.split(",");
            if (arrOfStr.length >= 2) {
                btnAssignCourse.setVisible(false);
            } else {
                btnAssignCourse.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        new AddUpdateTeacher(teachersDetails.getTeacherID(),teachersDetails.getTeacherName(),teachersDetails.getCurrentCourses());
                        dispose();
                    }
                });
            }
            //Setting Button backgrounds, Adding Action Listeners
            JButton[] buttons = {btnAssignCourse};

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

            container.add(lblTeacherDetails);
            container.add(btnAssignCourse);
        }
        JScrollPane scrollPane = new JScrollPane(container,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(new Dimension(AppResource.WINDOW_WIDTH,AppResource.WINDOW_HEIGHT));
        scrollPane.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
        mainContainer.add(scrollPane,BorderLayout.CENTER);
    }
}