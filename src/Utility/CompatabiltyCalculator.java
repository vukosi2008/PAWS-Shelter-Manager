/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;
import Entity.Adopter;
import Entity.Pet;

/**
 * Calculates a Lifestyle Compatibility Score (0-100%) between an Adopter and a Pet
 *
 * The score is split across three weighted categories:
 *  - Home Type vs the pet's energy level / indoor needs   (40 points)
 *  - Kids vs the pet's child-friendly flag                 (30 points)
 *  - Preferred activity level vs the pet's energy level    (30 points)
 *
 * @author vukos
 */
public class CompatabiltyCalculator {
      private static final double HOME_WEIGHT = 40;
    private static final double KIDS_WEIGHT = 30;
    private static final double ACTIVITY_WEIGHT = 30;
 
    // Calculates the overall compatibility score (0-100) between the given adopter and pet.

    public static double calculateScore(Adopter adopter, Pet pet) {
        if (adopter == null || pet == null) {
            return 0;
        }
 
        double score = 0;
        score += scoreHomeCompatibility(adopter, pet);
        score += scoreKidsCompatibility(adopter, pet);
        score += scoreActivityCompatibility(adopter, pet);
 
        // Round to whole percent, never above 100
        return Math.min(100, Math.round(score));
    }//calculateScore
 
    /**
     * Compares the adopter's home type to the pet's space requirements.
     * Cats are judged on whether they are an indoor/outdoor cat; dogs are
     * judged on their energy level relative to the space available.
     */
    private static double scoreHomeCompatibility(Adopter adopter, Pet pet) {
        String homeType = adopter.getHomeType();
        String energy = pet.getEnergyLevel();
        boolean isCat = pet.getType() != null && pet.getType().equalsIgnoreCase("Cat");
 
        if (homeType == null) {
            return 0;
        }
 
        if (isCat) {
            // Indoor cats are comfortable anywhere; outdoor cats need a yard.
            switch (homeType) {
                case "Apartment":
                    return pet.isIndoor() ? HOME_WEIGHT : HOME_WEIGHT * 0.4;
                case "House with small yard":
                    return HOME_WEIGHT * 0.9;
                case "House with large yard":
                    return HOME_WEIGHT;
                default:
                    return HOME_WEIGHT * 0.5;
            }
        } else {
            // Dogs: match energy level against available space.
            if (energy == null) {
                return HOME_WEIGHT * 0.5;
            }
            switch (homeType) {
                case "Apartment":
                    if (energy.equalsIgnoreCase("Low")) return HOME_WEIGHT;
                    if (energy.equalsIgnoreCase("Medium")) return HOME_WEIGHT * 0.5;
                    return HOME_WEIGHT * 0.1; // High energy dog in an apartment - poor fit
                case "House with small yard":
                    if (energy.equalsIgnoreCase("Medium")) return HOME_WEIGHT;
                    if (energy.equalsIgnoreCase("Low")) return HOME_WEIGHT * 0.75;
                    return HOME_WEIGHT * 0.6; // High energy, small yard - workable
                case "House with large yard":
                    return HOME_WEIGHT; // Suits every energy level
                default:
                    return HOME_WEIGHT * 0.5;
            }
        }
    }//scoreHomeCompatibility
 
    /**
     * Compares whether the adopter has kids against the pet's
     * child-friendly flag. If the adopter has no kids this category is
     * not a concern, so it is awarded in full.
     */
    private static double scoreKidsCompatibility(Adopter adopter, Pet pet) {
        if (!adopter.isHasChildren()) {
            return KIDS_WEIGHT;
        }
        return pet.isChildFriendly() ? KIDS_WEIGHT : 0;
    }//scoreKidsCompatibility
 
    /**
     * Compares the adopter's preferred activity level against the pet's
     * energy level. Exact matches score full marks, adjacent levels(e.g Medium vs High) score partially, 
     * and opposite matches (Low vs High) score the lowest.
     */
    private static double scoreActivityCompatibility(Adopter adopter, Pet pet) {
        String preferred = adopter.getActivityPreference();
        String petEnergy = pet.getEnergyLevel();
 
        if (preferred == null || petEnergy == null || preferred.isBlank() || petEnergy.isBlank()) {
            return ACTIVITY_WEIGHT * 0.5;
        }
 
        if (preferred.equalsIgnoreCase(petEnergy)) {
            return ACTIVITY_WEIGHT; // Perfect match
        }
 
        boolean oppositeExtremes =
                (preferred.equalsIgnoreCase("Low") && petEnergy.equalsIgnoreCase("High"))
                || (preferred.equalsIgnoreCase("High") && petEnergy.equalsIgnoreCase("Low"));
 
        if (oppositeExtremes) {
            return ACTIVITY_WEIGHT * 0.15;
        }
 
        // One level apart (e.g. Medium vs Low, or Medium vs High)
        return ACTIVITY_WEIGHT * 0.6;
    }//scoreActivityCompatibility
    
}
