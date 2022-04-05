import java.util.ArrayList;
import java.util.List;

public class Class {
    String prefixNum, name, instructor, time;
    List<String> prerequisites = new ArrayList<>();

    public Class(){}
    public Class(String prefixNum, String name, String instructor, String time, List<String> prerequisites) {
        this.prefixNum = prefixNum;
        this.name = name;
        this.instructor = instructor;
        this.time = time;
        this.prerequisites = prerequisites;
    }

    public String getPrefixNum() {
        return prefixNum;
    }

    public void setPrefixNum(String prefixNum) {
        this.prefixNum = prefixNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }
}
