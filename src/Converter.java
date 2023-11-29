public class Converter {

    private static final double RATIO_KM = 0.00001;

    private static final double RATIO_CALORIES = 0.0001;

    static int convertToKm(int steps) {
        return (int) (steps * 75 * RATIO_KM);
    }

    static int convertStepsToKilocalories(int steps) {
        return (int) (steps * 50 * RATIO_CALORIES);
    }
}