/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Entity.Adopter;
import Entity.Pet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vukos
 */
public class DatabaseManager extends AccessDAL {
    ArrayList<Adopter> adopters;
    ArrayList<Pet> pets;
    
     public DatabaseManager() {
        super();// Initialize the parent class (AccessDAL) for database connection
        uploadPetsTable();      // Loads pets
        uploadAdoptersTable(); // Loads adopters
        
    }//constructor
    
    private void uploadPetsTable() {
        pets = new ArrayList<>();// Initialize a new list to avoid duplicates
        try {
            preparedStatement = con.prepareStatement("SELECT * FROM tblPets WHERE status = 'Available'");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // Iterate through the ResultSet to create Pet objects
               Pet temp = new Pet(
                        rs.getString("petID"),
                        rs.getString("petName"),
                        rs.getInt("ageInMonths"),
                        rs.getString("breed"),
                        rs.getString("type"),
                        rs.getDate("intakeDate").toLocalDate(),
                        rs.getBoolean("childFriendly"),
                        rs.getString("energyLevel"),
                        rs.getBoolean("indoor"),
                        rs.getDouble("adoptionFee")
                        
                );
                System.out.println("Adding pet: " + temp.toString());
                pets.add(temp);
            } // while
        } catch (SQLException ex) {
            System.out.println("DatabaseManager: Error loading pet ");
        }
    } //uploadPetsTable
    
    private void uploadAdoptersTable() {
        adopters = new ArrayList<>();
        try {
            // Reuse the protected variables from AccessDAL
            preparedStatement = con.prepareStatement("SELECT * FROM tblAdopters");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Adopter temp = new Adopter(
                    rs.getString("adopterID"), 
                    rs.getString("fullName"),
                    rs.getString("idNumber"),
                    rs.getString("address"),
                    rs.getInt("contactNumber"),
                    rs.getString("homeType"),
                    rs.getBoolean("hasKids"),
                    rs.getString("activityPreference")
                );
                System.out.println("Adding adopter: " + temp.toString());
                adopters.add(temp);
            }//while
        } catch (SQLException ex) {
            System.out.println("DatabaseManager: Error loading Adopters");
        }
    }//uploadAdoptersTable
    
    public ArrayList<Pet> getAllPets() {
        return pets;
    }//getAllPets
    
     /**
     * Re-queries tblPets (excluding already adopted pets) and refreshes
     * the list. Call this after an adoption so any open screen
     * reflects the change immediately.
     */
    public void refreshPets() {
        uploadPetsTable();
    }//refreshPets
    

    public void processAdoption(String petID, String adopterID, double fee) {
        try {
            // 1. Updated SQL to include the fee column
            String insertSQL = "INSERT INTO tblAdoptions (petID, adopterID, adoptionDate, adoptionFee) VALUES (?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(insertSQL);

            preparedStatement.setString(1, petID);
            preparedStatement.setString(2, adopterID);
            preparedStatement.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setDouble(4, fee); 
            preparedStatement.executeUpdate();
            
            String updateSQL = "UPDATE tblPets SET status = 'Adopted' WHERE petID = ?";
            preparedStatement = con.prepareStatement(updateSQL);
            preparedStatement.setString(1, petID);
            preparedStatement.executeUpdate();
            //Refreshes the internal list so the Dashboard gets the new data
            uploadPetsTable();
            
        } catch (SQLException ex) {
            System.out.println("Error finalizing adoption with fee: " + ex.getMessage());
        }
    }//processAdoption
    
    public DefaultTableModel getArchiveModel() {
    //Create a blank table model with columns
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Pet Name");
    model.addColumn("Adopter Name");
    model.addColumn("Adoption Date");

        try {
            //The JOIN Query
            String sql = "SELECT tblPets.petName, tblAdopters.fullName, tblAdoptions.adoptionDate " +
                         "FROM (tblAdoptions " +
                         "INNER JOIN tblPets ON tblAdoptions.petID = tblPets.petID) " +
                         "INNER JOIN tblAdopters ON tblAdoptions.adopterID = tblAdopters.adopterID";

            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            //Loop through results and add rows to the model
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("petName"),
                    rs.getString("fullName"),
                    rs.getDate("adoptionDate") 
                });
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching archive: " + ex.getMessage());
        }
    return model;
    }//getArchiveModel
    
    public String generateNextPetID() {
        String nextID = "P001"; // Default if the table is empty
        try {
            // Gets the highest ID alphabetically
            String sql = "SELECT MAX(petID) AS lastID FROM tblPets";
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            if (rs.next() && rs.getString("lastID") != null) {
                String lastID = rs.getString("lastID"); // e.g. "P004"

                // Strips the 'P' and convert "004" to the integer 4
                int lastNum = Integer.parseInt(lastID.substring(1));

                // Increment
                int nextNum = lastNum + 1;

                // Format back to P + 3 digits (e.g. P005)
                nextID = String.format("P%03d", nextNum);
            }
        } catch (SQLException e) {
            System.out.println("Error generating ID: " + e.getMessage());
        }
        return nextID;
    }//generateNextPetID
    
    public boolean insertPet(String petID,String petName, int age, String breed, LocalDate intakeDate, String type, String energy, boolean isIndoor, boolean isChildFriendly, double fee) {
        String sql = "INSERT INTO tblPets (petID, petName, ageInMonths, breed, type, intakeDate, childFriendly, energyLevel, indoor, adoptionFee, status) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, petID);
            preparedStatement.setString(2, petName);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, breed);
            preparedStatement.setString(5, type); 
            preparedStatement.setDate(6, java.sql.Date.valueOf(intakeDate));// Converts Localdate to yyyy-mm-dd Access format
            preparedStatement.setBoolean(7, isChildFriendly);
            preparedStatement.setString(8, energy);
            preparedStatement.setBoolean(9, isIndoor);
            preparedStatement.setDouble(10, fee);
            //Set the status to "Available" for every new pet
            preparedStatement.setString(11, "Available");
            preparedStatement.executeUpdate();
            
            uploadPetsTable(); //Refresh internal list so new pet is added to table
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }//insertPet
    
    public String generateNextAdopterID() {
        String nextID = "A001"; // Default if the table is empty
        try {
            // Gets the highest ID alphabetically
            String sql = "SELECT MAX(AdopterID) AS lastID FROM tblAdopters";
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            if (rs.next() && rs.getString("lastID") != null) {
                String lastID = rs.getString("lastID"); // e.g. "A004"

                // Strips the 'A' and convert "004" to the integer 4
                int lastNum = Integer.parseInt(lastID.substring(1));

                // Increment
                int nextNum = lastNum + 1;

                // Format back to A + 3 digits (e.g. A005)
                nextID = String.format("A%03d", nextNum);
            }
        } catch (SQLException e) {
            System.out.println("Error generating ID: " + e.getMessage());
        }
        return nextID;
    }//generateNextAdopterID
    
    public boolean insertAdopter(String adopterID,String fullName, String idNumber,String address, int contactNumber, String homeType,  boolean hasKids, String activityPreference) {
        String sql = "INSERT INTO tblAdopters (adopterID, fullName, idNumber, address, contactNumber, homeType, hasKids, activityPreference) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, adopterID);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, idNumber);
            preparedStatement.setString(4, address); 
            preparedStatement.setInt(5, contactNumber);
            preparedStatement.setString(6, homeType);
            preparedStatement.setBoolean(7, hasKids);
            preparedStatement.setString(8, activityPreference);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }//insertAdopter
    
    
}


