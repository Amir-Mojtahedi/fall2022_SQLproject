package ca.dawsoncollege;

public class CompetenciesView {
    private String competencyId;
    private String competencyName;
    private String competencyDescription;
    private String specification;
    private String elementNumber;
    private String elementName;
    private String elementDescription;

   /*
    * Constructor for competencies view
    */
    public CompetenciesView(String competencyId, String competencyName, String competencyDescription,
            String specification, String elementNumber, String elementName, String elementDescription) {
        this.competencyId = competencyId;
        this.competencyName = competencyName;
        this.competencyDescription = competencyDescription;
        this.specification = specification;
        this.elementNumber = elementNumber;
        this.elementName = elementName;
        this.elementDescription = elementDescription;
    }

    /*
    * Prints competencies in user friendly way 
    */
    public String toString(){
        return this.competencyId + ": " + this.competencyName +
        "\n" + "Competency Description: " + this.competencyDescription +
        "\n" + "Specification: " + this.specification +
        "\n" + "Element Number: " + this.elementNumber +
        "\n" + "Element Name: "+ this.elementName +
        "\n" + "Element Description: " + this.elementDescription + "\n";
    }


   
}
