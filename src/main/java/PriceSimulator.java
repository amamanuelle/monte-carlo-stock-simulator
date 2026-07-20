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

    public double simulateDailyReturn() {
        double z = random.nextGaussian(0, 1);

        return stockModel.getExpectedDailyReturn() + stockModel.getDailyVolatility() * z;
    }

    public double simulateNextPrice(double currentPrice) {
        if (currentPrice <= 0) throw new IllegalArgumentException("Current price must be positive");

        return currentPrice * (1 + simulateDailyReturn());
    }

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
}
