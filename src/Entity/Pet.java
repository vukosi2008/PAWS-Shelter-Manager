/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author vukos
 */
public class Pet {
    private String petID;
    private String petName;
    private int ageMonths;
    private String breed;
    private String type;
    private LocalDate intakeDate;
    private boolean childFriendly;
    private String energyLevel;
    private boolean indoor;
    private double adoptionFee;
    private double score;

    public Pet() {
    }

    public Pet(String petID, String petName, int ageMonths, String breed, String type, LocalDate intakeDate, boolean childFriendly, String energyLevel, boolean indoor, double adoptionFee) {
        this.petID = petID;
        this.petName = petName;
        this.ageMonths = ageMonths;
        this.breed = breed;
        this.type = type;
        this.intakeDate = intakeDate;
        this.childFriendly = childFriendly;
        this.energyLevel = energyLevel;
        this.indoor = indoor;
        this.adoptionFee = adoptionFee;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getAgeMonths() {
        return ageMonths;
    }

    public void setAgeMonths(int ageMonths) {
        this.ageMonths = ageMonths;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getIntakeDate() {
        return intakeDate;
    }

    public void setIntakeDate(LocalDate intakeDate) {
        this.intakeDate = intakeDate;
    }

    public boolean isChildFriendly() {
        return childFriendly;
    }

    public void setChildFriendly(boolean childFriendly) {
        this.childFriendly = childFriendly;
    }

    public String getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(String energyLevel) {
        this.energyLevel = energyLevel;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }
    
    public double getAdoptionFee() {
        return adoptionFee;
    }

    public void setAdoptionFee(double adoptionFee) {
        this.adoptionFee = adoptionFee;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
    
    public int calculateDateInShelter() {
        return (int) ChronoUnit.DAYS.between(intakeDate, LocalDate.now());
    }

    @Override
    public String toString() {
        return "Pet{" + "petID=" + petID + ", name=" + petName + ", age in months=" + ageMonths + ", breed=" + breed + ", type=" + type + ", intakeDate=" + intakeDate + ", childFriendly=" + childFriendly + ", energyLevel=" + energyLevel + ", indoor=" + indoor + '}';
    }
    
    
    
    
}
