package ca.dawsoncollege;

public class FullDisplayView {

    private String courseNumber;
    private String courseName;
    private int classHours;
    private int labHours;
    private int homeworkHours;
    private int totalHours;
    private int termId;
    private double credits;
    private String comp_id;
    private String competencyName;
    private String specification;

    public FullDisplayView(String courseNumber, String courseName, int classHours, int labHours, int homeworkHours, int totalHours,
    int termId, double credits, String comp_id, String competencyName, String specification) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.classHours = classHours;
        this.labHours = labHours;
        this.homeworkHours = homeworkHours;
        this.totalHours = totalHours;
        this.termId = termId;
        this.credits = credits;
        this.comp_id = comp_id;
        this.competencyName = competencyName;
        this.specification = specification;
    }

    @Override
    public String toString(){
        return "Term: " + this.termId +
        "\n" + "Course Name: " + this.courseName +
        "\n" + "Course Id: " + this.courseNumber +
        "\n" + "Competency Name: " + this.competencyName +
        "\n" + "Competency Id: " + this.comp_id +
        "\n" + "Specification: " + this.specification +
        "\n" + "Total Hours: " + this.totalHours +
        "\n" + "Class-Lab-Homework Hours: " + this.classHours + "-" + this.labHours + "-" + this.homeworkHours +
        "\n" + "Credits: " + this.credits + "\n";
    }
}
