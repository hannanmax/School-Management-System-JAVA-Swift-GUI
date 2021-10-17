package SchoolManagementSystem;

/**
 * This file contains all the needed resources for application.
 * @author  HannanAhmad(2094868)
 */
public class AppResource {
    //String Values
    /**
     * Used for college name.
     */
    public static final String COLLEGE_NAME = "Cégep de la Gaspésie et des Îles";
    /**
     * Used for alert message box when fields are empty
     */
    public static final String MESSAGE_FILL_ALL_FIELDS = "Fill All Fields";
    /**
     * Used for alert message box when something is wrong
     */
    public static final String MESSAGE_SOMETHING_WRONG = "Something went wrong...";
    /**
     * Used for alert message box when application runs first time
     */
    public static final String MESSAGE_WELCOME = "Welcome to the " + COLLEGE_NAME + " management system";
    /**
     * Used for showing text
     */
    public static final String TXT_ASSIGN_COURSE = "Assign Course";
    /**
     * Used for showing text
     */
    public static final String TXT_ASSIGN_STUDENT = "Assign Student Course";
    /**
     * Used for showing text
     */
    public static final String TXT_ASSIGN_TEACHER = "Assign Teacher";
    /**
     * Used for showing text
     */
    public static final String TXT_ADD_COURSES = "Add Course";
    /**
     * Used for showing text
     */
    public static final String TXT_ADD_GRADES = "Add Grades";
    /**
     * Used for showing text
     */
    public static final String TXT_ADD_STUDENTS = "Add Student";
    /**
     * Used for showing text
     */
    public static final String TXT_ADD_TEACHERS = "Add Teacher";
    /**
     * Used for showing text
     */
    public static final String TXT_CANCEL = "Cancel";
    /**
     * Used for showing text course and for json node name
     */
    public static final String TXT_COURSE = "course";
    /**
     * Used for showing text
     */
    public static final String TXT_REMOVE_STUDENT_FROM_COURSE = "Remove Student Course";
    /**
     * Used for showing text student and for json node name
     */
    public static final String TXT_STUDENT = "student";
    /**
     * Used for showing text teacher and for json node name
     */
    public static final String TXT_TEACHER = "teacher";
    /**
     * Used for give tab(4 Spaces) in text
     */
    public static final String TXT_TAB = "&nbsp;&nbsp;&nbsp;&nbsp;";
    /**
     * Used for give 4 tab(4 * 4(Spaces)) in text
     */
    public static final String TXT_TAB_4 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    /**
     * Used for showing text/title
     */
    public static final String VIEW_CLASS_STUDENT = " STUDENT LIST";
    /**
     * Used for showing text/title
     */
    public static final String VIEW_COURSES = "View Courses";
    /**
     * Used for showing text/title
     */
    public static final String VIEW_STUDENTS = "View Students";
    /**
     * Used for showing text/title
     */
    public static final String VIEW_TEACHERS = "View Teachers";

    //Int Values
    /**
     * Used for frame window height
     */
    public static final int WINDOW_HEIGHT = 360;
    /**
     * Used for frame window width
     */
    public static final int WINDOW_WIDTH = 620;

    //JSON path Values
    /**
     * Used to specify path of where you want to store the json files
     */
    public static final String JSON_PATH = "src/main/java/SchoolManagementSystem/Data/";
    /**
     * Used for course json file name with path
     */
    public static final String JSON_COURSE_PATH = JSON_PATH + "course.json";
    /**
     * Used for student json file name with path
     */
    public static final String JSON_STUDENTS_PATH = JSON_PATH + "student.json";
    /**
     * Used for teacher json file name with path
     */
    public static final String JSON_TEACHER_PATH = JSON_PATH + "teacher.json";

    //Image Resources Values
    /**
     * Used for Add/Submit button background
     */
    public static final String IMG_BTN_ADD = "src/main/java/SchoolManagementSystem/Images/img_btn_add_bg.png";
    /**
     * Used for back button background
     */
    public static final String IMG_BTN_BACK = "src/main/java/SchoolManagementSystem/Images/img_btn_back.png";
    /**
     * Used for simple button background such as view
     */
    public static final String IMG_BTN_BG = "src/main/java/SchoolManagementSystem/Images/img_btn_bg.png";
    /**
     * Used for Remove/Cancel button background
     */
    public static final String IMG_BTN_REMOVE = "src/main/java/SchoolManagementSystem/Images/img_btn_remove_bg.png";
    /**
     * Used for Main screen design image
     */
    public static final String IMG_CLIPART_MAIN_SCREEN_PNG = "src/main/java/SchoolManagementSystem/Images/img_clipart_mainscreen.png";
    /**
     * Used for application icon
     */
    public static final String IMG_LOGO_PNG = "src/main/java/SchoolManagementSystem/Images/img_logo.png";

    //Patterns
    /**
     * Used for managing date pattern
     */
    public static final String PATTERN_DATE_FORMAT = "dd/MM/yyyy";
    /**
     * Used for checking if string only has spaces and character
     */
    public static final String PATTERN_CHARACTER_SPACE = "^[\\p{L} .'-]+$";
    /**
     * Used for checking if string only contains number
     */
    public static final String PATTERN_NUMBER_ONLY = "[0-9]+";

    //Colors
    /**
     * Used to contain background color
     */
    public static final String COLOR_BACKGROUND = "#E6F1FF";
}