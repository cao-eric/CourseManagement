import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class CourseManagement implements ActionListener{

    CourseManagementUI cmUI;
    public CourseManagement(){
        cmUI = new CourseManagementUI(this);
    }

    public static void main(String[] args){

        CourseManagement courseManagement = new CourseManagement();

        Vector<String> temp = new Vector<>();
        temp.add("Item 1");
        temp.add("Item 2");

        courseManagement.cmUI.setSubjectComboBox(temp);
        System.out.println("Hello World");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if user selects a subject on the subject drop-down box
        if(e.getSource() == cmUI.subjectComboBox){
            System.out.println(cmUI.subjectComboBox.getSelectedItem());
        }
    }
}
