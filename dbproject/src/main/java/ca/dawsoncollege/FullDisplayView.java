package ca.dawsoncollege;

public class FullDisplayView {

    private String courseNumber;
    private String courseName;
    private int classHours;
    private int labHours;
    private int homeworkHours;
    private int totalHours;
    private int termId;
    private String competencyName;
    private String specification;
    private String elementName;

    public FullDisplayView(String courseNumber, String courseName, int classHours, int labHours, int homeworkHours, int totalHours,
    int termId, String competencyName, String specification, String elementName) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.classHours = classHours;
        this.labHours = labHours;
        this.homeworkHours = homeworkHours;
        this.totalHours = totalHours;
        this.termId = termId;
        this.competencyName = competencyName;
        this.specification = specification;
        this.elementName = elementName;
    }

    @Override
    public String toString(){
        return "e";
    }
}
