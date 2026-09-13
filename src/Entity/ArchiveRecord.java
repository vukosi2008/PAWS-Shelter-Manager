/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.time.LocalDate;

/**
 *
 * @author vukos
 */
public class ArchiveRecord {
    private String adopterName;
    private String petName;
    private LocalDate adoptionDate;
    private Double adoptionFee;

    public ArchiveRecord(String adopterName, String petName, LocalDate adoptionDate, Double adoptionFee) {
        this.adopterName = adopterName;
        this.petName = petName;
        this.adoptionDate = adoptionDate;
        this.adoptionFee = adoptionFee;
    }

    public String getAdopterName() {
        return adopterName;
    }

    public void setAdopterName(String adopterName) {
        this.adopterName = adopterName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetname(String petName) {
        this.petName = petName;
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate = adoptionDate;
    }

    public Double getAdoptionFee() {
        return adoptionFee;
    }

    public void setAdoptionFee(Double adoptionFee) {
        this.adoptionFee = adoptionFee;
    }

    @Override
    public String toString() {
        return petName + " adopted by " + adopterName + " on " + adoptionDate;
    }
    
    
    
    
}
