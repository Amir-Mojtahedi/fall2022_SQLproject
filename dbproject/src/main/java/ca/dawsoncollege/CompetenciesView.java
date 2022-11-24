package ca.dawsoncollege;

import java.util.List;

public class CompetenciesView {
    private String competencyId;
    private String competencyName;
    private String competencyDescription;
    private String specification;
    private String elementNumber;
    private String elementName;
    private String elementDescription;

   
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


    public String toString(){
        return this.competencyId + ": " + this.competencyName +
        "\n" + "Competency Description: " + this.competencyDescription +
        "\n" + "Specification: " + this.specification +
        "\n" + "Element Name: "+ this.elementName +
        "\n" + "Element Description: " + this.elementDescription + "\n";
    }


   
}
