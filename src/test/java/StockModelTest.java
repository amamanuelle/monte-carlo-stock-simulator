import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StockModelTest {

    @Test
    void negativeAnnualVolatilityTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StockModel(0, -0.2, 252);
        });
    }

    @Test
    void negativeTradingDaysPerYearTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StockModel(0, 10, -10);
        });
    }

    @Test
    void zeroTradingDaysPerYearTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StockModel(0, 0, 0);
        });
    }

    @Test
    void expectedDailyReturnTest() {
        StockModel model = new StockModel(0.08, 0.20, 252);

        assertEquals(0.08/252, model.getExpectedDailyReturn());
    }

    @Test
    void calculatesDailyVolatility() {
        StockModel model = new StockModel(0.08, 0.20, 252);

        assertEquals(0.20 / Math.sqrt(252), model.getDailyVolatility());
    }
}
