# PAWS-Shelter-Manager
A desktop program built with Java and JavaFX that helps animal shelter staff manage pet intake, track animal statuses, and match pets with suitable adopters

Overview
PAWS Shelter Manager is designed for local animal shelter volunteers and administrators. It manages the full lifecycle of dogs and cats from intake to adoption, using species-specific data and a lifestyle matching algorithm to improve adoption success rates. The program is fully offline, making it ideal for small shelters with limited budgets or unreliable internet.
Key Features

Intake Management - Add pets with dynamic forms that change based on species (Dog or Cat)
Species-Specific Logic - Uses OOP inheritance to handle unique attributes (e.g. energy level for dogs, indoor/outdoor preference for cats)
Smart Matching Algorithm - Calculates a compatibility score (%) by comparing adopter lifestyle (home type, children, activity level) with pet traits
Status Tracking & Filtering - Filter animals by status (Ready for Adoption, Medical Hold, Quarantine) or species
Long-Stay Alerts - Automatically flags animals that have been in the shelter for more than 30 days
Adoption Archive - Maintains a history of adopted pets and their new owners
Data Persistence - All records are stored in a local Microsoft Access database

Technical Details

Language / Framework: Java + JavaFX
Storage: Microsoft Access database (.accdb) with tables for Pets, Archive, and Adopters
Architecture: Object-oriented design with inheritance (Pet → Dog/Cat), a DatabaseManager class, and separate UI screens
Platform: Offline desktop program (Windows-focused due to Access)

Motivation
Inspired by real volunteer experience at a shelter, where staff struggled to remember which animals were child-friendly or suitable for apartments. The goal was to apply Grade 12 Java skills to create a practical tool that helps animals find permanent homes faster by improving matching accuracy and reducing return rates.
