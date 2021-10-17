package SchoolManagementSystem.GUI;

import SchoolManagementSystem.AppResource;
import SchoolManagementSystem.Data.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This class is used to display main screen of program GUI.
 * It has 3 buttons
 *      1) View Courses
 *      2) View Teachers
 *      3) View Students
 *
 * @author HannanAhmad(2094868)
 */
public class MainScreen extends JFrame{

    /**
     * Constructor: Used to display main screen.
     */
    public MainScreen(){
        setTitle(AppResource.COLLEGE_NAME);
        setSize(AppResource.WINDOW_WIDTH, AppResource.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.decode(AppResource.COLOR_BACKGROUND));
        ImageIcon img = new ImageIcon(AppResource.IMG_LOGO_PNG);
        setIconImage(img.getImage());

        new Database();
        initializeComponents();
        setVisible(true);
    }

    /**
     * Method: Used to initialise components in GUI
     */
    private void initializeComponents() {
        ImageIcon img = new ImageIcon(AppResource.IMG_CLIPART_MAIN_SCREEN_PNG);
        JLabel lblClipArt = new JLabel(img);
        JButton btnViewCourse = new JButton(AppResource.VIEW_COURSES);
        JButton btnViewTeachers = new JButton(AppResource.VIEW_TEACHERS);
        JButton btnViewStudents = new JButton(AppResource.VIEW_STUDENTS);

        lblClipArt.setBounds(225, 20, 367, 266);
        btnViewCourse.setBounds(60,80,120,40);
        btnViewTeachers.setBounds(60,140,120,40);
        btnViewStudents.setBounds(60,200,120,40);

        //Setting Button backgrounds, Adding Action Listeners
        JButton[] buttons = {
                btnViewCourse,
                btnViewTeachers,
                btnViewStudents
        };

        for (JButton button : buttons) {
            button.setIcon(new ImageIcon(AppResource.IMG_BTN_BG));
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setVerticalTextPosition(JButton.CENTER);
            button.setForeground(Color.white);

            button.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (button == btnViewCourse){
                        new ViewCourses();
                    } else if(button == btnViewTeachers){
                        new ViewTeachers();
                    } else if(button == btnViewStudents){
                        new ViewStudents();
                    }
                    dispose();
                }
            });
            add(button);
        }

        add(lblClipArt);
    }
}
