package ca.dawsoncollege;

public class CoursesView {

    private String courseNumber;
    private String courseName;
    private String courseDescription;
    private int classHours;
    private int labHours;
    private int homeworkHours;
    private int totalHours;
    private double credits;
    private int termId;
    private String seasonName;
    private String educationType;
    private String domainName;
    private String domainDescription;

    public CoursesView(String courseNumber, String courseName, String courseDescription, int classHours, int labHours, int homeworkHours, 
    int totalHours, double credits, int termId, String seasonName, String educationType, String domainName, String domainDescription) {
                this.courseNumber = courseNumber;
                this.courseName = courseName;
                this.courseDescription = courseDescription;
                this.classHours = classHours;
                this.labHours = labHours;
                this.homeworkHours = homeworkHours;
                this.totalHours = totalHours;
                this.credits = credits;
                this.termId = termId;
                this.seasonName = seasonName;
                this.educationType = educationType;
                this.domainName = domainName;
                this.domainDescription = domainDescription;
    }

    @Override
    public String toString() {
        return this.courseNumber + ": " + this.courseName + 
        "\n" + "Course Description: " + this.courseDescription + 
        "\n" + "Class-Lab-Homework Hours: " + this.classHours + "-" + this.labHours + "-" + this.homeworkHours + 
        "\n" + "Total Hours: " + this.totalHours + 
        "\n" + "Credits: " + this.credits +
        "\n" + "Term Number: " + this.termId + 
        "\n" + "Season Name: " + this.seasonName + 
        "\n" + "Education Type: " + this.educationType + 
        "\n" + "Domain Name: " + this.domainName + 
        "\n" + "Domain Description: " + this.domainDescription + "\n";
    }


}
