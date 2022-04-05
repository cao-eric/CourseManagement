import java.util.List;
public class Student {
    //Name, Age, Gender, Major, School ID, Password, Total Credits, Current Credits, Taken Classes, Current Classes
    String name, DoB,  gender, major, schoolID, password;
    int totalCredits, currentCredits;
    List<String> takenClasses, currentClasses;

    public Student(String name, String DoB,String gender, String major, String schoolID, String password, int totalCredits, int currentCredits, List<String> takenClasses, List<String> currentClasses) {
        this.name = name;
        this.gender = gender;
        this.major = major;
        this.schoolID = schoolID;
        this.password = password;
        this.DoB = DoB;
        this.totalCredits = totalCredits;
        this.currentCredits = currentCredits;
        this.takenClasses = takenClasses;
        this.currentClasses = currentClasses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return DoB;
    }

    public void setAge(String DoB) {
        this.DoB = DoB;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public void setCurrentCredits(int currentCredits) {
        this.currentCredits = currentCredits;
    }

    public List<String> getTakenClasses() {
        return takenClasses;
    }

    public void setTakenClasses(List<String> takenClasses) {
        this.takenClasses = takenClasses;
    }

    public List<String> getCurrentClasses() {
        return currentClasses;
    }

    public void setCurrentClasses(List<String> currentClasses) {
        this.currentClasses = currentClasses;
    }
}
