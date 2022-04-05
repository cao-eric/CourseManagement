import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;

public class CourseManagement {

    private final HashMap<String, Student> map;
    private final List<Class> classList;

    public CourseManagement() throws IOException {
        //For student log in
        map = new HashMap<>();
        classList = new ArrayList<>();
    }
    public void obtainStudentData(String filename) throws IOException {
        File path = new File(filename);

        File [] files = path.listFiles(); //Iterates through every file in the directory
        for (int x = 0; x < (files != null ? files.length : 0); x++){
            if(files[x].isFile()){ //if it is a file
                try {
                    FileReader fr = new FileReader (files[x]); //read the file
                    BufferedReader br = new BufferedReader(fr); //creates a buffer to read the file
                    String name, DOB, gender, major, schoolID, password;
                    int totalCredits, currentCredits;

                    name= br.readLine(); //First line is name
                    DOB = br.readLine(); //DoB is the second line
                    gender = br.readLine(); //Gender is the third line
                    major = br.readLine(); //Major 4th
                    schoolID = br.readLine(); //School ID 5th
                    password = br.readLine(); //Password 6th
                    totalCredits = Integer.parseInt(br.readLine()); //Total credits 7th
                    currentCredits = Integer.parseInt(br.readLine()); //Total credits 8th

                    String[] temp = br.readLine().split(",", 0); //temporary String array
                    List<String> takenClasses = new ArrayList<>(Arrays.asList(temp));

                    temp = br.readLine().split(",", 0); //temporary String array
                    List<String> currentClasses = new ArrayList<>(Arrays.asList(temp));

                    //Creates student object with obtained information
                    Student student = new Student(name, DOB, gender, major, schoolID, password, totalCredits, currentCredits, takenClasses, currentClasses);

                    //Adds student to hashmap
                    map.put(schoolID, student);

                    fr.close(); //close the file
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void obtainClassData(String filename) throws IOException{
        File path = new File(filename);

        File [] files = path.listFiles(); //Iterates through every file in the directory
        for (int x = 0; x < (files != null ? files.length : 0); x++){
            if(files[x].isFile()){ //if it is a file
                try {
                    FileReader fr = new FileReader (files[x]); //read the file
                    BufferedReader br = new BufferedReader(fr); //creates a buffer to read the file
                    String prefixNum, name, instructor, time;

                    prefixNum = br.readLine(); //Prefix and course number are first line
                    name = br.readLine(); //Name of the course is on the second line
                    instructor = br.readLine(); //Instructor is third line
                    time = br.readLine(); //Time of the course is the fourth line

                    String[] temp = br.readLine().split(",", 0); //temporary String array
                    List<String> prerequisites = new ArrayList<>(Arrays.asList(temp)); //Prerequisites are the last line of the file

                    Class course = new Class(prefixNum, name, instructor, time, prerequisites);
                    classList.add(course); //add course to the list of classes
                    fr.close(); //close the file
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public String login(){
        Scanner cin = new Scanner(System.in);
        int tries = 3; //limited tries on password attempt
        boolean validUsername = false; //flag to loop username input
        String username = "";
        while(!validUsername){
            System.out.println("Enter username: "); //get username
            username = cin.next();
            if(!map.containsKey(username))
                System.out.println("Invalid username \n"); //check if valid
            else
                validUsername = true;
        }

        while(tries > 0){
            System.out.println("Enter in password: ");
            String pass = cin.next(); //get password input
            if(!map.get(username).password.equals(pass)){ //if not match
                tries--; //number of tries goes down
                System.out.println(tries + " left.");
            }
            else{
                break;
            }
        }
        if(tries == 0){
            return "ERROR";
        }
        return username;
    }
    public int mainMenu(){
        int choice; //user selection choice

        //Main menu options
        System.out.println("""
                1) View Current Classes
                2) Add Classes
                3) Remove Classes
                4) Log Out
                5) Exit""");
        Scanner cin = new Scanner(System.in);
        choice = cin.nextInt(); //get user choice
        while(choice < 1 || choice > 5){ //loops until user makes proper choice selection
            System.out.println("Invalid choice selection. Please try again."); //DOES NOT WORK AGAINST STRINGS
            choice = cin.nextInt();
        }
        return choice; //return choice
    }
    public Class classExists(String desiredClassName, List<Class> classList){
        Class desiredClass = new Class();
        for (Class aClass : classList) { //go through course list
            if (desiredClassName.equals(aClass.getPrefixNum())) { //validate the selected class exists
                desiredClass = aClass;
                break;
            }
        }
        return desiredClass; //returns null if class does not exist OR the desired class if it exists
    }

    public String classExistsString(String desiredClassName, List<String> classList){ //checks if class exists in string list
        for(int x = 0; x < classList.size(); x++){ //if selected class exists
            if(desiredClassName.equals(classList.get(x))) //return it
                return desiredClassName;
        }

        return ""; //else return empty string
    }
    public boolean checkTakenClass(String desiredClassName, Student student){ //checks if student already enrolled or has taken the class before
        for(int x = 0; x < student.takenClasses.size(); x++){
            if(desiredClassName.equals(student.takenClasses.get(x))){ //Student has already taken that class
                System.out.println("You have already taken that class");
                return false;
            }
        }
        for(int x = 0; x < student.currentClasses.size(); x++){
            if(desiredClassName.equals(student.currentClasses.get(x))){ //Student is already enrolled for that class
                System.out.println("You are already enrolled for that class");
                return false;
            }
        }

        return true; //student is cleared to take course
    }
    public boolean checkPrerequisites(Class course, Student student){
        if(course.prerequisites.get(0).equals("None")) //If no prerequisites exist, ignore
            return true;
        for(int x = 0; x < course.prerequisites.size(); x++){ //iterate through course's prereqs
            boolean prereqCheck = false; //flag to see if they have taken the course
            for(int y = 0; y < student.takenClasses.size(); y++){ //iterate through students' taken courses
                if(course.prerequisites.get(x).equals(student.takenClasses.get(y))){ //if the student has taken it
                    prereqCheck = true;
                    break;
                }
            }
            if(!prereqCheck){ //if not taken prerequisite
                System.out.println("Cannot enroll, missing prerequisite: " + course.prerequisites.get(x)); //Print out missing prereq. RIP if they're missing more than 1 lol
                return false;
            }
        }
        return true; //they have taken the prerequisites
    }
    public boolean checkTimeConflicts(Class course, Student student, List<Class> classList){
        if(student.currentClasses.get(0).equals("None")) //if no classes, then there are no time conflicts
            return true;


        //BEcause I really messed up the implementations with string lists and all that
        List<Class> studentsClasses = new ArrayList<>();
        for(int x = 0; x < student.currentClasses.size(); x++){ //iterate through student's current classes
            for(int y = 0; y < classList.size(); y++){
                if(student.currentClasses.get(x).equals(classList.get(y).getPrefixNum()))
                    studentsClasses.add(classList.get(y)); //BASICALLY turns student's string list into a local class-typed list. yes its duct tape.
            }
        }
        for(int x = 0; x < studentsClasses.size(); x++){
            if(course.time.equals(studentsClasses.get(x).time)){ //if time matches (conflict in schedule)
                System.out.println("Time conflict between: " + course.prefixNum + " and " + studentsClasses.get(x).prefixNum
                + " @" + course.time);
                return false; //return false
            }
        }
        return true;
    }
    public String selectClassToAdd(List<Class> classList){
        System.out.println("Class list:\n-----------------"); //print out selection of classes
        for(int x = 0; x < classList.size(); x++){
            System.out.println(classList.get(x).prefixNum + "- " + classList.get(x).name);
        }
        System.out.println("Type in prefix and number of class to enroll or type X to cancel");
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
    public void enrollCourse(Class course, Student student){ //officially adds coruse to student's course list
        //Check if they current have none enrolled, if so remove "None" from the list
        if(student.currentClasses.get(0).equals("None"))
            student.currentClasses.remove(0);

        //Then add class
        student.currentClasses.add(course.prefixNum);
    }
    public void removeCourse(String courseName, Student student){
        for(int x = 0; x < student.currentClasses.size(); x++){ //search through student's current classes
            if(courseName.equals(student.currentClasses.get(x))){ //if match
                student.currentClasses.remove(x); //remove the course
            }
        }
        if(student.currentClasses.size() == 0) //if removed last class, add string None
            student.currentClasses.add("None");
    }
    public String selectCourseToRemove(Student student){
        System.out.println("Select course to remove:\n" + student.currentClasses);
        System.out.println("Type in prefix and number of class to enroll or type X to cancel");
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }


    public static void main(String[] args) throws IOException {
        String studentFileName = "C:\\Users\\Eric\\OneDrive\\Desktop\\Coding\\Java\\CourseManagement\\StudentFolder";
        String classFileName = "C:\\Users\\Eric\\OneDrive\\Desktop\\Coding\\Java\\CourseManagement\\CourseFolder";
        CourseManagement cm = new CourseManagement();
        cm.obtainStudentData(studentFileName);
        cm.obtainClassData(classFileName);


        String stdUsername = cm.login(); //prompt login screen

        while(stdUsername.equals("ERROR")){ //loop until successful login
            System.out.println("Error logging in. Too many failed attempts.\n");
            stdUsername = cm.login();
        }

        System.out.println("Successful login!\n-----------------------------");
        int mainMenuChoice = cm.mainMenu();
        while(mainMenuChoice != 5){ //5 = exit code
            if(mainMenuChoice == 1){ //View class
                System.out.println(cm.map.get(stdUsername).currentClasses); //print out student's current classes
            }
            if(mainMenuChoice == 2){ //Add class
                System.out.println("Choice 2");
                String desiredClassName = cm.selectClassToAdd(cm.classList); //get the class the user wants to add
                if(desiredClassName.equals("X"))
                    System.out.println("Back to main menu");
                else{
                    Class desiredClass = cm.classExists(desiredClassName, cm.classList); //check if the desired class exists

                    if(desiredClass.name == null) //if it does not exist, go back to main menu
                        System.out.println("Class does not exist");
                    else{ //otherwise, validate if student has not taken it and has the prerequisites
                        if(cm.checkTakenClass(desiredClassName, cm.map.get(stdUsername))) //TRUE if pass taken classes validation
                            if(cm.checkPrerequisites(desiredClass, cm.map.get(stdUsername))) //TRUE if pass prerequisite validation
                                if(cm.checkTimeConflicts(desiredClass, cm.map.get(stdUsername), cm.classList)){ //TRUE if no time conflicts
                            //Enroll student in class
                            cm.enrollCourse(desiredClass, cm.map.get(stdUsername));
                        }
                    }
                }
            }
            if(mainMenuChoice == 3){ //Remove class
                if(cm.map.get(stdUsername).currentClasses.get(0).equals("None")) //if student currently has no classes to delete
                    System.out.println("No classes to remove"); //back to main menu
                else{ //else print out current classes
                    String desiredClass = cm.selectCourseToRemove(cm.map.get(stdUsername)); //user selects which class to remove
                    if(desiredClass.equals("X"))
                        System.out.println("Back to main menu");
                    else{
                        desiredClass = cm.classExistsString(desiredClass, cm.map.get(stdUsername).currentClasses); //checks if chosen classes exists in list
                        if(desiredClass.equals("")){ //desired class does not exist
                            System.out.println("Class does not exist");
                        }
                        else{
                            cm.removeCourse(desiredClass, cm.map.get(stdUsername)); //user removes course
                        }
                    }
                }
            }
            if(mainMenuChoice == 4){ //logout
                System.out.println("Log out successful\n----------------------------------------------");
                stdUsername = cm.login(); //prompt login screen

                while(stdUsername.equals("ERROR")){ //loop until successful login
                    System.out.println("Error logging in. Too many failed attempts.\n");
                    stdUsername = cm.login();
                    System.out.println("Successful login!\n-----------------------------");
                }
            }

            mainMenuChoice = cm.mainMenu(); //reselect main menu option
        }

    }
}
