/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author vukos
 */
public class Adopter {
    private String adopterID;    
    private String fullName;
    private String idNumber;
    private String address;
    private int contactNumber;
    private String homeType;
    private boolean hasChildren;
    private String activityPreference;

    public Adopter() {
    }

    public Adopter(String adopterID, String fullName, String idNumber, String address, int contactNumber, String homeType, boolean hasChildren, String activityPreference) {
        this.adopterID = adopterID;
        this.fullName = fullName;
        this.idNumber = idNumber;
        this.address = address;
        this.contactNumber = contactNumber;
        this.homeType = homeType;
        this.hasChildren = hasChildren;
        this.activityPreference = activityPreference;
    }

    public String getAdopterID() {
        return adopterID;
    }

    public void setAdopterID(String adopterID) {
        this.adopterID = adopterID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getHomeType() {
        return homeType;
    }

    public void setHomeType(String homeType) {
        this.homeType = homeType;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getActivityPreference() {
        return activityPreference;
    }

    public void setActivityPreference(String activityPreference) {
        this.activityPreference = activityPreference;
    }

    @Override
    public String toString() {
        return "Adopter{" + "adopterID=" + adopterID + ", fullName=" + fullName + ", idNumber=" + idNumber + ", address=" + address + ", contactNumber=" + contactNumber + ", homeType=" + homeType + ", hasChildren=" + hasChildren + ", activityPreference=" + activityPreference + '}';
    }
    
    
    
            
    
}
