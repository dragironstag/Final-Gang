// Programmer: Monique Ligo 12108266 and Alyssa Hammelswang 12108185
// File: BuildAddress.java
// Date: October 2 2019
// Purpose: COIT11134 assignment two - CQ Air Conditioning Installers (CQAC) windowed application

public class BuildAddress
{
    private String buildingAddress;         // Address for installation

    // Default constructor
   public BuildAddress ()
   {
       buildingAddress = "";
   }

     // Parameterised Constructor
    public BuildAddress (String buildingAddress)
    {
        this.buildingAddress = buildingAddress;
    }

	// SET
    // The set method for building address 
    public void setBuildAddress (String buildingAddress)
	    {
	        this.buildingAddress = buildingAddress;
    }

    // GET
    // The get method for building address
    public String getBuildAddress ()        // Access buildAddress outside this class
    {
        return buildingAddress;
    }


    // A toString method that formats the buildingAddress into the GUI
    @Override
	public String toString ()
	{

        return
                String.format("%-20s", buildingAddress);

    }

}