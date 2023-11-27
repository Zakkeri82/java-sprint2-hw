public class Converter {

    int convertToKm(int steps) {
        return (int) (steps * 75 * 0.00001);
    }

    int convertStepsToKilocalories(int steps) {
        return (int) (steps * 50 * 0.0001);
    }
}