// Programmer: Monique Ligo 12108266 and Alyssa Hammelswang 12108185
// File: Designs.java
// Date: October 2 2019 
// Purpose: COIT11134 assignment two - CQ Air Conditioning Installers (CQAC) windowed application 

public class Designs 
{
    private String brand;				// Name of Air Con brand 
	private String horsePower;         	// horsePower description
	private String zones;     		   	// Aircon zone
	private String outlets ;          	// Aircon outlets 
	
	

	// A default constructor 
	public Designs ()
	{
        brand = "";		
		horsePower = "";
		zones = "";
		outlets  = "";
	}
	
	// Parameterised Constructor
	public Designs(String brand, String horsePower, String zones, String outlets )
	{
        this.brand = brand;
		this.horsePower = horsePower;
		this.zones = zones;
		this.outlets  = outlets;
	}

	// SETTERS 
	// Allows brand, horsePower, zones, and outlets to store variables
    public void setBrand (String brand)
	{
		this.brand = brand;
    }
    
	public void setHorsePower(String horsePower)
	{
		this.horsePower = horsePower;
	}

	public void setZones(String zones)
	{
		this.zones = zones;
	}
	
	public void setOutlets (String outlets)
	{
		this.outlets  = outlets ;
	}

	// GETTERS
	// Allows variables to be used outside of class
    public String getBrand()
	{
		return brand;
    } 
    
	public String getHorsePower()
	{
		return horsePower;
	} 
	
	public String getZones()
	{
		return zones;
	}
	
	public String getOutlets ()
	{
		return outlets ; 
	}

	// Another toString method that formats the Designs into the GUI
	@Override
	public String toString () 
	{
        return 
                String.format("%-20s", brand) + 
				String.format("%-20s", horsePower) + 
				String.format("%-20s", zones) + 
				String.format("%-20s", outlets );

	}	

}