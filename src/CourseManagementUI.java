import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class CourseManagementUI implements ActionListener {

    JFrame frame;
    JComboBox<String> subjectComboBox, addCourseComboBox, deleteCourseComboBox;
    ActionListener actionListener;

    /**
     * Creates the GUI window frame for the application.
     */
    private void createFrame(){
        frame = new JFrame("Course Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes when window is closed
        frame.setSize(900, 750); //width x height of frame
        frame.setLocationRelativeTo(null); //places window in the middle of the screen
        frame.setLayout(null); //no layout
    }

    /**
     * Initializes the drop-down boxes of the add menu
     */
    private void createAddComboBoxes(){
        subjectComboBox = new JComboBox<>(); //initialize the combo box
        subjectComboBox.setBounds(300, 150, 450, 40); //sets the size and location of the box
        subjectComboBox.setVisible(false); //cannot be seen initially
        subjectComboBox.addActionListener(actionListener); //Gives combo box action listener to obtain data
        frame.add(subjectComboBox); //add it to the frame

        addCourseComboBox = new JComboBox<>();;
        addCourseComboBox.setBounds(300, 350, 450, 40);
        addCourseComboBox.setVisible(false);
        addCourseComboBox.addActionListener(actionListener);
        frame.add(addCourseComboBox);
    }

    /**
     * Fills the subject drop box with subjects for the user to select
     * @param subjects is a vector containing the available subjects the user can select
     */
    public void setSubjectComboBox(Vector<String> subjects){
        //Clears all current items
        subjectComboBox.removeAllItems();

        //Transfers courses from the subject vector to the combo box
        for (String subject : subjects) {
            subjectComboBox.addItem(subject);
        }
    }

    /**
     * Fills the course drop box with courses for the user to select
     * @param courses a vector containing the available courses for users
     */
    public void setAddCourseComboBox(Vector<String> courses){
        //Combo box clears itself of all items
        addCourseComboBox.removeAllItems();

        //Transfers courses from the course vector to the combo box
        for (String cours : courses) {
            addCourseComboBox.addItem(cours);
        }
    }

    /**
     * Allows the add menu to be seen: subject + course drop-down box and add course button
     * @param visibility if true, viewer can see this menu, false if otherwise
     */
    public void setAddMenuVisibility(boolean visibility){
        subjectComboBox.setVisible(visibility);
        addCourseComboBox.setVisible(visibility);
    }

    /**
     * Constructor that initializes the GUI object
     */
    public CourseManagementUI(ActionListener actionListener){

        createFrame(); //creates the GUI frame
        this.actionListener = actionListener;

        //Creates combo boxes
        createAddComboBoxes();

        //Sets add menu visibility to true
        setAddMenuVisibility(true);
        frame.setVisible(true); //allows user to see the frame
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
