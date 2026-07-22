import java.util.Arrays;

public class SimulationAnalyzer {

    /**
     * Extracts the final price from each simulated price path.
     *
     * @param paths simulated stock price paths
     * @return an array containing the final price of each path
     * @throws IllegalArgumentException if the paths are null, empty,
     *                                  or contain invalid paths
     */
    public double[] extractFinalPrices(double[][] paths) {
        if (paths == null || paths.length == 0) throw new IllegalArgumentException("Paths must not be empty or null");

        double[] finalPrices = new double[paths.length];
        for (int simulation = 0; simulation < paths.length; simulation++) {
            double[] path = paths[simulation];
            validateValues(path);
            finalPrices[simulation] = path[path.length - 1];
        }

        return finalPrices;
    }
    /**
     * Calculates the arithmetic mean of an array of values.
     *
     * @param values values to analyze
     * @return arithmetic mean
     */
    public double mean(double[] values) {
        validateValues(values);
        double sum = 0;

        for (double value : values) {
            sum += value;
        }

        return (double) sum / values.length;
    }

    /**
     * Calculates the median of an array of values
     * @param values values to analyze
     * @return median value
     */
    public double median(double[] values) {
        validateValues(values);

        double[] sortedValues = Arrays.copyOf(values, values.length);
        Arrays.sort(sortedValues);

        int mid = sortedValues.length / 2;
        if (sortedValues.length % 2 != 0) {
            return sortedValues[mid];
        }

        return (sortedValues[mid - 1] + sortedValues[mid]) / 2;
    }


    /**
     * Calculates the Five Number Summary: minimum, Q1, median, Q3, maximum
     * @param values values to analyze
     * @return five number summary
     */
    public double[] fiveNumberSummary(double[] values) {
        validateValues(values);

        double[] sortedValues = Arrays.copyOf(values, values.length);
        Arrays.sort(sortedValues);

        double minimum = sortedValues[0];
        double maximum = sortedValues[sortedValues.length - 1];
        double median = median(sortedValues);

        int mid = sortedValues.length / 2;

        double[] lower = Arrays.copyOfRange(sortedValues, 0, mid);
        double[] upper;

        if (sortedValues.length % 2 == 0) {
            upper = Arrays.copyOfRange(sortedValues, mid, sortedValues.length);
        } else {
            upper = Arrays.copyOfRange(sortedValues, mid + 1, sortedValues.length);
        }

        double q1 = median(lower);
        double q3 = median(upper);

        return new double[]{minimum, q1, median, q3, maximum};
    }

    /**
     * Helper method to validate an array of values
     * @param values values to validate
     */
    private void validateValues(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(
                    "Values must not be null or empty"
            );
        }
    }

    /**
     * Calculates the probability of loss (final prices less than start price)
     * @param finalPrices simulated final prices
     * @param initialPrice starting stock price
     * @return probability of loss
     */
    public double probabilityOfLoss(double[] finalPrices, double initialPrice) {
        validateValues(finalPrices);

        if (initialPrice <= 0) throw new IllegalArgumentException("Initial price must be positive");
        int lossCount = 0;

        for (double price : finalPrices) {
            if (price < initialPrice) {
                lossCount++;
            }
        }

        return (double) lossCount / finalPrices.length;
    }

    /**
     * Calculates the Value at Risk (VaR) at a specified confidence level
     * @param finalPrices simulated ending prices
     * @param initialPrice starting stock price
     * @param confidenceLevel confidence level
     * @return estimated maximum loss
     */
    public double valueAtRisk(double[] finalPrices, double initialPrice, double confidenceLevel) {
        validateValues(finalPrices);

        if (confidenceLevel <= 0 || confidenceLevel >= 1) {
            throw new IllegalArgumentException(
                    "Confidence level must be between 0 and 1."
            );
        }

        double[] sortedPrices = finalPrices.clone();
        Arrays.sort(sortedPrices);

        double tailP = 1 - confidenceLevel;

        int index = (int) Math.floor(
                (tailP * sortedPrices.length) + 1e-10 //floating-point precision
        );
        double percentilePrice = sortedPrices[index];
        return Math.max(0, initialPrice - percentilePrice);
    }
}
