package br.edu.unoesc.enums;

public enum EducationLevel {
    HIGH_SCHOOL("High School"),
    TECHNICAL("Technical"),
    BACHELORS("Bachelor's Degree"),
    SPECIALIZATION("Specialization"),
    MASTERS("Master's Degree");

    private final String displayName;

    EducationLevel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}