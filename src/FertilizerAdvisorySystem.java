public class FertilizerAdvisorySystem {

    public void processSamples(SoilAnalysis[] samples) {
        int balancedCount = 0;
        int deficientCount = 0;

        for (SoilAnalysis s : samples) {
            try {
                String recommendation = s.calculateFertilizerNeeded();
                System.out.println("Farmer ID: " + s.getFarmerId());
                System.out.println("District: " + s.getDistrictName());
                System.out.println("Crop Type: " + s.getCropType());
                System.out.println("Recommendation: " + recommendation);
                System.out.println("--------------------------------------");

                if (s.isBalanced())
                    balancedCount++;
                else
                    deficientCount++;

            } catch (IllegalArgumentException e) {
                System.out.println("Error for Farmer " + s.getFarmerId() + ": " + e.getMessage());
                System.out.println("--------------------------------------");
            }
        }

        System.out.println("SUMMARY REPORT:");
        System.out.println("Balanced Samples: " + balancedCount);
        System.out.println("Deficient Samples: " + deficientCount);
    }

    // Main method
    public static void main(String[] args) {
        SoilAnalysis[] samples = new SoilAnalysis[5];

        samples[0] = new SoilAnalysis("F001", "Kirehe", 50, 70, 80, "Maize"); // balanced
        samples[1] = new SoilAnalysis("F002", "Bugesera", 10, 45, 60, "Rice"); // deficient
        samples[2] = new SoilAnalysis("F003", "Nyagatare", 110, 90, 85, "Beans"); // excess
        samples[3] = new SoilAnalysis("F004", "Gicumbi", -5, 40, 60, "Maize"); // invalid
        samples[4] = new SoilAnalysis("F005", "Huye", 15, 15, 18, "Rice"); // deficient

        FertilizerAdvisorySystem system = new FertilizerAdvisorySystem();
        system.processSamples(samples);
    }
}