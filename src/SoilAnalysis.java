public class SoilAnalysis {
    // Private instance variables
    private String farmerId;
    private String districtName;
    private double nitrogenLevel;
    private double phosphorusLevel;
    private double potassiumLevel;
    private String cropType;

    // Constructor
    public SoilAnalysis(String farmerId, String districtName, double nitrogenLevel,
                        double phosphorusLevel, double potassiumLevel, String cropType) {
        this.farmerId = farmerId;
        this.districtName = districtName;
        this.nitrogenLevel = nitrogenLevel;
        this.phosphorusLevel = phosphorusLevel;
        this.potassiumLevel = potassiumLevel;
        this.cropType = cropType;
    }

    // Getters
    public String getFarmerId() { return farmerId; }
    public String getDistrictName() { return districtName; }
    public double getNitrogenLevel() { return nitrogenLevel; }
    public double getPhosphorusLevel() { return phosphorusLevel; }
    public double getPotassiumLevel() { return potassiumLevel; }
    public String getCropType() { return cropType; }

    // Method to check if all nutrients are balanced
    public boolean isBalanced() {
        return (nitrogenLevel >= 20 && nitrogenLevel <= 100) &&
                (phosphorusLevel >= 20 && phosphorusLevel <= 100) &&
                (potassiumLevel >= 20 && potassiumLevel <= 100);
    }

    // Method to calculate fertilizer recommendation
    public String calculateFertilizerNeeded() {
        // Handle invalid nutrient readings
        if (nitrogenLevel <= 0 || phosphorusLevel <= 0 || potassiumLevel <= 0) {
            throw new IllegalArgumentException("Invalid nutrient reading");
        }

        StringBuilder result = new StringBuilder();

        if (isBalanced()) {
            return "OPTIMAL - Maintenance fertilizer only";
        }

        // Check deficient nutrients
        if (nitrogenLevel < 20) result.append("DEFICIENT - High application needed for Nitrogen. ");
        if (phosphorusLevel < 20) result.append("DEFICIENT - High application needed for Phosphorus. ");
        if (potassiumLevel < 20) result.append("DEFICIENT - High application needed for Potassium. ");

        // Check excess nutrients
        if (nitrogenLevel > 100) result.append("EXCESS - Reduce Nitrogen application. ");
        if (phosphorusLevel > 100) result.append("EXCESS - Reduce Phosphorus application. ");
        if (potassiumLevel > 100) result.append("EXCESS - Reduce Potassium application. ");

        return result.toString().trim();
    }
}
