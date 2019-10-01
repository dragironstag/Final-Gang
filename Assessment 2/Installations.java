// Programmer: Monique Ligo 12108266 and Alyssa Hammelswang 12108185
// File: Installations.java
// Date: October 2 2019
// Purpose: COIT11134 assignment two - CQ Air Conditioning Installers (CQAC) windowed application
public class Installations
{
	private String start;                   // start date
	private String end;                     // end date
    private Designs designs;                // aggregations
	private TechDetails techDetails;
	private BuildAddress buildAddress;


	// Default constructor for Installations
	// No arguments needed
	public Installations ()
	{
		start = "";                     // All equals to a string
		end = "";
	}

	// Parameterised Constructor #1
	public Installations(String start, String end)
	{
		this.start = start;
		this.end = end;
	}

	// Parameterised Constructor #2
	public Installations (TechDetails techDetails, BuildAddress buildAddress, String start, String end, Designs designs )
	{
		this.techDetails = techDetails;
	    this.buildAddress = buildAddress;
		this.start = start;
		this.end = end;
		this.designs = designs;
	}

	
	// SETTERS
    // Allows start and end to store variables
	public void setStart(String start)
	{
		this.start = start;
	}

	public void setEnd(String end)
	{
		this.end = end;
	}
	
	// Setters for aggregations
	public void setDesigns (Designs designs)
	{
		this.designs = designs;
    }

	public void setTechDetails (TechDetails techDetails)
    {
        this.techDetails = techDetails;
    }

	public void setBuildingAddress (BuildAddress buildAddress)
	{
		this.buildAddress = buildAddress;
	}

    // GETTERS
    // Allows access of start and end outside the class
	public String getStart()
	{
		return start;
	}

	public String getEnd()
	{
		return end;
	}

	// Getters for aggregations
    public Designs getDesigns()
    {
		return designs;
	}
	public TechDetails gettechDetails ()
    {
        return techDetails;
	}

	public BuildAddress getbuildAddress ()
	{
		return buildAddress;
	}


    // Formats the header shown on the display text area
	public static String toStringHeader ()
	{
		return String.format ("%-20s", "Tech Name") +
			   String.format("%-20s", "Contact #") +
			   String.format("%-20s", "Building Address  ") +
               String.format("%-20s", " Start Date  ") +
               String.format("%-20s", " End Date  ") +
               String.format("%-20s", "   Designs");


	}

	// Formats the inputted information shown on the display text area
	@Override
	public String toString ()
	{
        return
        		techDetails.toString() +
        		buildAddress.toString() +
				String.format("%-20s", start) +
				String.format("%-20s",end) +
                designs.toString();
    }

}
