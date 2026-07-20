
public class StockModel {

    private final double expectedAnnualReturn;
    private final double annualVolatility;
    private final int tradingDaysPerYear;

    public StockModel(double expectedAnnualReturn, double annualVolatility, int tradingDaysPerYear) {
        if (annualVolatility < 0) {
            throw new IllegalArgumentException(
                    "Annual volatility cannot be negative."
            );
        }
        if (tradingDaysPerYear <= 0) {
            throw new IllegalArgumentException(
                    "Trading days per year must be positive."
            );
        }

        this.expectedAnnualReturn = expectedAnnualReturn;
        this.annualVolatility = annualVolatility;
        this.tradingDaysPerYear = tradingDaysPerYear;
    }

    public double getExpectedDailyReturn() {
        return expectedAnnualReturn / tradingDaysPerYear;
    }

    public double getDailyVolatility() {
        return annualVolatility / Math.sqrt(tradingDaysPerYear);
    }
}
