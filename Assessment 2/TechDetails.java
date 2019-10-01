// Programmer: Monique Ligo 12108266 and Alyssa Hammelswang 12108185
// File: TechDetails.java
// Date: October 2 2019
// Purpose: COIT11134 assignment two - CQ Air Conditioning Installers (CQAC) windowed application

public class TechDetails
{
    private String techName;        // Name of technician
    private int contactNum;         // Contact number of said technician



    // Default constructor
    public TechDetails ()
    {
        techName = "";                  // Equals to an empty string
        contactNum = 0;                 // Equals to a number because its an integer
    }

     // Parameterised Constructor
    public TechDetails (String techName, int contactNum)
    {
        this.techName = techName;
        this.contactNum = contactNum;

    }

	// SETTERS
    // The set method for TechName
    // Allows setTechName to store variables
    public void setTechName (String techName)
    {
        this.techName = techName;
    }

    // The set method for contactNum
    // Allows setContactNum to store variables
    public void setContactNum (int contactNum)
    {

		this.contactNum = contactNum;
    }

    // GETTERS
    // The get method for TechName
    public String getTechName ()        // Access TechName outside this class
    {
        return techName;
    }

    // The get method for ContactNum
    public int getContactNum ()        // Access contactNum outside this class
    {
        return contactNum;
    }

    // A toString method that formats the TechDetails into the GUI
    @Override
	public String toString ()
	{

        return
                String.format("%-20s", techName) +           // Inserts techName into the string format
                String.format("%-20s", contactNum);          // Inserts contact into the string format
    }

}