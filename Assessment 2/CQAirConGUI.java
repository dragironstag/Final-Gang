// Programmer: Monique Ligo 12108266 and Alyssa Hammelswang 12108185
// File: CQAirConGUI.java
// Date: October 2 2019 
// Purpose: COIT11134 assignment two - CQ Air Conditioning Installers (CQAC) windowed application  
// Java Program using handlers, GUI, constructors, etc..

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.util.*;
import java.io.*;

public class CQAirConGUI extends JFrame 
{

    // Labels
    JLabel techLabel = new JLabel("  Technician Name: ");
    JLabel techContactLabel = new JLabel("  Technician Contact Number: ");
    JLabel buildAddLabel = new JLabel("  Building Address: ");
    JLabel startLabel = new JLabel("  Start Date: ");
    JLabel endLabel = new JLabel("  End Date: ");
    JLabel infoLabel = new JLabel(" Clicking load will lose all your inputted information if you don't save. Please save your work before clicking load.");
    

    // Text Fields
    JTextField techTextField = new JTextField(20);                              // Text fields are for information entry
    JTextField techConTextField = new JTextField(12);
    JTextField buildAddTextField = new JTextField(20);
    JTextField startTextField = new JTextField(20);
    JTextField endTextField = new JTextField(20);

    // Combo boxes
    JLabel deisgnLabel = new JLabel("  Designs: ");
    JComboBox<String> designComboBox = new JComboBox<String>();                 // A combo box to show different air con designs

    JLabel installedLabel = new JLabel("  Installed by");                       // A combo box to show technician's name and contact number
    JComboBox<String> installedComboBox = new JComboBox<String>();

    // Display text area
    JTextArea displayTextArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(displayTextArea);

    // Buttons
    JButton testDataButton = new JButton("Test Data");
    JButton addButtonInst = new JButton("Add Installations");
    JButton addButtonTech = new JButton("Add Technician");
    JButton displayButton = new JButton("Display");
    JButton searchButton = new JButton("Search");
    JButton clearButton = new JButton("Clear");
    JButton loadButton = new JButton("Load File");
    JButton saveButton = new JButton("Save File");
    JButton exitButton = new JButton("Exit");

    // Arrays to store collections of data
    private ArrayList<Installations> installations = new ArrayList<Installations>();        // This stores installations information

    private ArrayList<Designs> designs = new ArrayList<Designs>();                          // This stores designs information

    private ArrayList<TechDetails> techDetails = new ArrayList<TechDetails>();              // This stores technician's information

    private ArrayList<BuildAddress> buildAddress = new ArrayList<BuildAddress>();           // This stores building information

    public CQAirConGUI() 
    {
        // Setting the frame
        setVisible(true);
        setTitle("CQ Air Conditioning System by Monique Ligo 12108266 | Alyssa Hammelswang 12108185");
        setLocation(100, 100);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayTextArea.setEditable(false); // Disable typing inside the text area

        // TOP panel section - Technician's Details
        JPanel topPanel = new JPanel();                         // Sets titles and border boxes
        topPanel.setLayout(new GridLayout(3, 3));
        topPanel.setBorder(new TitledBorder(null, "Technician Details", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 15)));    // Set border designs, titles, and fonts

        // Adding the textfield and buttons into the GUI
        topPanel.add(techLabel);
        topPanel.add(techTextField);
        topPanel.add(techContactLabel);
        topPanel.add(techConTextField);
        topPanel.add(searchButton);
        topPanel.add(addButtonTech);
        add(topPanel, BorderLayout.NORTH);                   // Place panel in the north (top) side

        // WEST panel section - Installations
        JPanel westPanel = new JPanel();                     // Sets titles and border boxes
        westPanel.setLayout(new GridLayout(15, 3));
        westPanel.setFont(new Font("Arial", Font.BOLD, 10));
        westPanel.setBorder(new TitledBorder(null, "Installations", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 15)));     // Set border designs, titles, and fonts

        // Adding the textfield and buttons into the GUI
        westPanel.add(buildAddLabel);
        westPanel.add(buildAddTextField);
        westPanel.add(startLabel);
        westPanel.add(startTextField);
        westPanel.add(endLabel);
        westPanel.add(endTextField);
        westPanel.add(deisgnLabel);
        westPanel.add(designComboBox);
        westPanel.add(installedLabel);
        westPanel.add(installedComboBox);
        westPanel.add(addButtonInst);
        westPanel.add(saveButton);
        westPanel.add(loadButton);
    
        westPanel.add(infoLabel);                               // Adds the warning message for load button 
        infoLabel.setFont(new Font("Arial", Font.BOLD, 10));    // Stylised the text
        infoLabel.setForeground(Color.RED);
        infoLabel.setEnabled(false);
        add(westPanel, BorderLayout.WEST);                      // Place panel in the west side

        displayTextArea.setFont(new Font("Arial", Font.BOLD, 15));          // Display prompt for installations
        add(scrollPane, BorderLayout.CENTER);                               // Place panel in the middle/centre

        // Buttons panel
        JPanel buttonsPanel = new JPanel();

        // Adding the buttons to the GUI
        buttonsPanel.add(testDataButton);
        buttonsPanel.add(displayButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(exitButton);
        add(buttonsPanel, BorderLayout.SOUTH);                  // Place all the buttons to the bottom (south) of the frame

        // Add action listener to the buttons
        addButtonInst.addActionListener(event -> addInstallations());
        addButtonTech.addActionListener(event -> addTechnicians());
        displayButton.addActionListener(event -> display(""));
        clearButton.addActionListener(event -> clearButton());
        exitButton.addActionListener(event -> exitApplication());
        testDataButton.addActionListener(event -> testData());
        searchButton.addActionListener(event -> searchbyTechnicians());
        loadButton.addActionListener(event -> loadFile());
        saveButton.addActionListener(event -> saveFile());

        // If the user clicks X, it's safe to do so
        addWindowListener(new WindowAdapter() // Override window closing method
        {
            public void windwClosing(WindowEvent e) 
            {
                exitApplication();      // Exits the GUI application
            }
        });

        // Clear input text fields
        clearInputs();

        // Information inside the combo box
        designs.add(new Designs("Samsung", "2.5 HP", "Zone 2", "Outlets 4"));               // Format is derive from Designs.java
        designs.add(new Designs("Panasonic", "3.6 HP", "Zone 5", "Outlets 7"));
        designs.add(new Designs("Daikin", "1.5 HP", "Zone 3", "Outlets 2"));

        // Loop through designs
        for (int k = 0; k < designs.size(); k++) 
        {
            designComboBox.addItem(designs.get(k).toString());
        }

        loadFile (); // Loads file upon starting GUI

    }

    // Method for adding technicians into the output
    private void addTechnicians() 
    {
        // Initialises technicians name and contact number
        String technician = techTextField.getText().trim();
        String contactStr = techConTextField.getText().trim();

        int contact = 0;

        // Hanldes exception
        try 
        {
            contact = Integer.parseInt(contactStr);             // Pass contact into a string 
        } 
        catch (Exception err) 
        {
            contact = -1;
        }

        // These are the codes for technician's error messages
        // Each if statements reference any events that can cause errors if users do prohibited actions
        if (technician.length() == 0)                   // If theres no technician name 
        {
            // Displays an error for technician's name if leave blank
            JOptionPane.showMessageDialog(CQAirConGUI.this, "Error: Technician's name cannot be blank",
                    "Error Message!", JOptionPane.ERROR_MESSAGE);

            techTextField.requestFocus(); // Request the focus back to the technician's name textfield
        }
        else if (contactStr.length() == 0)              // If theres no contact number
        {
            // Displays an error for contact number name if leave blank
            JOptionPane.showMessageDialog(CQAirConGUI.this, "Error: Contact number cannot be blank", "Error Message!",
                    JOptionPane.ERROR_MESSAGE);

            techConTextField.requestFocus(); // Request the focus back to the contact number textfield
        }
        else 
        {
            // The user inputs are valid
            // Create input information

            TechDetails t = new TechDetails(technician, contact);

            // Adds it to the array list
            techDetails.add(t);

            // Refresh Manufacturers combo box
            installedComboBox.removeAllItems();

            // Add the items to the combo box
            for (int k = 0; k < techDetails.size(); k++) 
            {
                installedComboBox.addItem((String) techDetails.get(k).toString());
            }

            // Clear the input/textfields
            clearInputs();

            // Put the focus back to the textfields
            techTextField.requestFocus();
            techConTextField.requestFocus();

        }
    }

    // Method for adding installations into the output
    private void addInstallations() 
    {
        // Intiliases variables 
        String buildingAddress = buildAddTextField.getText();
        String start = startTextField.getText().trim();
        String end = endTextField.getText().trim();

        // These are the codes for installations error messages
        if (buildingAddress.length() == 0) 
        {
            // Displays an error for building address if leave blank
            JOptionPane.showMessageDialog(CQAirConGUI.this, "Error: Building address cannot be blank", "Error Message!", JOptionPane.ERROR_MESSAGE);

            buildAddTextField.requestFocus(); // Request the focus back to the start date textfield
        } 
        else if (start.length() == 0) 
        {
            // Displays an error for start date if leave blank
            JOptionPane.showMessageDialog(CQAirConGUI.this, "Error: Start date cannot be blank", "Error Message!", JOptionPane.ERROR_MESSAGE);

            startTextField.requestFocus(); // Request the focus back to the start date textfield
        } 
        else if (end.length() == 0) 
        {
            // Displays an error for end date if leave blank
            JOptionPane.showMessageDialog(CQAirConGUI.this, "Error: End date cannot be blank", "Error Message!", JOptionPane.ERROR_MESSAGE);

            endTextField.requestFocus(); // Request the focus back to the end date textfield
        } 
        else 
        {
            BuildAddress b = new BuildAddress(buildingAddress);             // Creates new build address

            // Initialise variables
            Installations i = null;

            i = new Installations(start, end);

            int techInstallIndex = installedComboBox.getSelectedIndex();                // Get the selected technician's name from the combo box
            i.setTechDetails((TechDetails) techDetails.get(techInstallIndex));          // Set the technician's details as the selected technician

            i.setBuildingAddress(b);        // Sets building address

            int desIndex = designComboBox.getSelectedIndex();                           // Get the selected air con designs from the combo box
            i.setDesigns((Designs) designs.get(desIndex));                              // Set the aircon's design as installations details

            // Add it to the installations array list
            installations.add(i);

            // Display the user inputs in textArea
            displayTextArea.setText("");
            displayTextArea.append(Installations.toStringHeader() + "\n");
            displayTextArea.append(i.toString() + "\n");

            // Clear the input/textfields
            clearInputs();

            // Put the focus back to the textfields
            buildAddTextField.requestFocus();
            startTextField.requestFocus();
            endTextField.requestFocus();
        }

    }

    private void clearInputs() 
    {
        // Clears the textfields of any text
        techTextField.setText("");
        techConTextField.setText("");
        buildAddTextField.setText("");
        startTextField.setText("");
        endTextField.setText("");
    }

    private void clearButton() 
    {
        displayTextArea.setText("");            // Clears the display text area of any text
    }

    // A display method for the information to be shown
    private int display(String techName) 
    {
        int count = 0;

        // Erase the textArea:
        displayTextArea.setText("");

        // Display the heading:
        displayTextArea.append(Installations.toStringHeader() + "\n");

        // Loop through all technicians and add them to textArea
        for (int k = 0; k < installations.size(); k++) {
            if ((techName.length() == 0) || (techName.equalsIgnoreCase(installations.get(k).gettechDetails().getTechName()) == true))
            {
                displayTextArea.append(installations.get(k).toString() + "\n");
                count++;
            }
        }
        displayTextArea.append("\n" + count + " installations found." + "\n");              // Display installation count 

        return count;
    }

    // Method for searching technician's
    private void searchbyTechnicians() 
    {
        // Prompt user for a search name
        // Loop through all installations and
        // display the ones with that technicians name

        String techName = JOptionPane.showInputDialog(CQAirConGUI.this, "Enter Technician's name: ");

        System.out.println("name: " + techName);

        // Check a valid technician's name was entered:
        if (techName != null)           // If user did not click the Cancel button
        {
            // Ff technician's name is blank or user only entered spaces, tabs
            if (techName.trim().length() == 0) 
            {
                JOptionPane.showMessageDialog(CQAirConGUI.this, "Error: Please enter a technician's name", "Error Message!", JOptionPane.ERROR_MESSAGE);
            }

            else 
            {
                // Technician's name is valid, let's search
                int count = display(techName);

                // Display an error
                if (count == 0)
                    JOptionPane.showMessageDialog(CQAirConGUI.this, "Error: No installations found for " + techName + ".", "Error Message!", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    // This is the load file
    // MUST ALWAYS click DISPLAY after clicking load file
    // For the text to actually show in the textarea

    // Note: The load file won't actually load any data UNTIL an information is ENTERED and SAVE
    // Inside the .txt file you can actually see information stored in there. But it won't load upon opening GUI
    // Once an information is saved, LOAD button FULLY works. 
    private void loadFile() 
    {
        String fileName = "savefile.txt";

        // Handles load file 
        try 
        {
           Scanner inFile = new Scanner(new FileReader(fileName));              // Scanner that read the savefile.txt

            while(inFile.hasNext())                                             // While the file has data within it
            {
        
                String nextLine = inFile.nextLine();                            // Initialise nextline 

                // Remove all spaces
                String lineWithOutSpaces = nextLine.replaceAll("\\s+","");
                System.out.println(lineWithOutSpaces);                          // Error is somewhere over here. 
  
            }

            inFile.close();                                                     // Closes the file
        }
        
       catch(FileNotFoundException | NoSuchElementException ex)         
       {
            System.out.println ("Error reading file: " + fileName);                 // If file isn't found, it will display this error message in command prompt/terminal
            System.exit(-1);                                                        // Automatically closes GUI
       }
   
    }

    // This is the save file
    private void saveFile ()
    {
        String fileName = "savefile.txt";                   // Creates a txt file called savefile 

        try
        {
            Formatter outputFile = new Formatter (fileName);            // Reads the fileName (which is the savefile.txt)

            // Loop through installations
            for (int k = 0; k < installations.size (); k++)
            {
                // Get installations details such as
                // Tech name, building address, start, and end date
                outputFile.format ("%s\n", installations.get (k).gettechDetails());
                outputFile.format ("%s\n", installations.get (k).getbuildAddress());
                outputFile.format ("%s\n", installations.get (k).getStart() );
                outputFile.format ("%s\n", installations.get (k).getEnd() );

                
                int desIndex = -1;            

                // Loop through designs array 
                for (int d = 0; d < designs.size(); d++)  
                {
                    if (designs.get (d) == installations.get (k). getDesigns())         
                    desIndex = d;
                }
                
                outputFile.format ("%s\n", desIndex);
            }

            outputFile.close();             // Closes the file 

            System.out.println (installations.size () + " lines written to: " + fileName);          // Shows a message in the command prompt / terminal 
        }
        catch(FileNotFoundException ex)                                
        {
            System.out.println  ("File not found: " + fileName);                // If file is not found, this message will appear in the command prompt / terminal 
            System.exit(-1);                                                    // Exits the application 
        }
        catch(NoSuchElementException | FormatterClosedException ex)
        {
            System.out.println ("Error writing to file: " + fileName);         // Error if user cannot write into file 
            System.exit(-1);
        }
    }   

    private void testData ()
    {
        display("");                  // Displays information to the text area without typing any 
    }

    private void exitApplication ()
    {

        // Confirmation dailog message if user wish to exit the program
        int result = JOptionPane.showConfirmDialog (CQAirConGUI.this, "Closing this application will lose all your work", "Exit?", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION)
        {
            // Save to file 
            // Exit 

            System.exit(0);     // Successfully exits application
        }
    
    }

    public static void main(String[] args) 
    {
        CQAirConGUI app = new CQAirConGUI();	
        app.setExtendedState(JFrame.MAXIMIZED_BOTH);                // Makes the GUI application full screen 
        
    }
}