import java.util.random.RandomGenerator;

public class PriceSimulator {

    private final StockModel stockModel;
    private final RandomGenerator random;

    public PriceSimulator(StockModel stockModel, RandomGenerator random) {

        if (stockModel == null) throw new IllegalArgumentException("Model cannot be null");
        if (random == null) throw new IllegalArgumentException("Random generator cannot be null");

        this.stockModel = stockModel;
        this.random = random;
    }

    /**
     * Simulates a single daily stock return using a standard normal distribution.
     *
     * @return simulated daily return
     */
    public double simulateDailyReturn() {
        double z = random.nextGaussian(0, 1);

        return stockModel.getExpectedDailyReturn() + stockModel.getDailyVolatility() * z;
    }

    /**
     * Simulates the next stock price based on the current price.
     *
     * @param currentPrice current stock price
     * @return next simulated stock price
     */
    public double simulateNextPrice(double currentPrice) {
        if (currentPrice <= 0) throw new IllegalArgumentException("Current price must be positive");

        return currentPrice * (1 + simulateDailyReturn());
    }

    /**
     * Simulates a single stock price path over a specified number of trading days.
     *
     * @param initialPrice starting stock price
     * @param numOfDays number of trading days to simulate
     * @return array containing the simulated stock prices
     * @throws IllegalArgumentException if the initial price is not positive
     *                                  or the number of days is negative
     */
    public double[] simulatePricePath(double initialPrice, int numOfDays) {
        if (initialPrice <= 0) throw new IllegalArgumentException("Initial price must be positive");
        if (numOfDays < 0) throw new IllegalArgumentException("Number of days cannot be negative");

        double[] prices = new double[numOfDays + 1];
        prices[0] = initialPrice;

        for (int day = 1; day <= numOfDays; day++) {
            double currentPrice = prices[day - 1];
            prices[day] = simulateNextPrice(currentPrice);
        }

        return prices;
    }
    
    /**
     * Performs a Monte Carlo simulation by generating multiple independent
     * stock price paths.
     *
     * @param initialPrice starting stock price
     * @param numOfDays number of trading days to simulate
     * @param numOfSimulations number of independent simulations to perform
     * @return two-dimensional array containing all simulated price paths
     * @throws IllegalArgumentException if the initial price is not positive,
     *                                  the number of days is negative,
     *                                  or the number of simulations is not positive
     */
    public double[][] simulateMonteCarlo(double initialPrice, int numOfDays, int numOfSimulations) {
        if (numOfSimulations <= 0) throw new IllegalArgumentException("Number of simulations must be positive");
        double[][] result = new double[numOfSimulations][numOfDays + 1];

        for (int simulation = 0; simulation < numOfSimulations; simulation++) {
            result[simulation] = simulatePricePath(initialPrice, numOfDays);
        }

        return result;
    }
}
