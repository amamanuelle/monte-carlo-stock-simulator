import org.junit.jupiter.api.Test;

import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.*;

public class PriceSimulatorTest {

    @Test
    void nullStockModelTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PriceSimulator(null, RandomGenerator.getDefault());
        });
    }

    @Test
    void nullRandom() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PriceSimulator(new StockModel(10, 10, 10), null);
        });
    }

    @Test
    void zeroSimulatePriceTest() {
        StockModel model = new StockModel(0.08, 0.2, 252);
        PriceSimulator simulator = new PriceSimulator(model, RandomGenerator.getDefault());
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.simulateNextPrice(0.0);
        });
    }

    @Test
    void negativeSimulatePriceTest() {
        StockModel model = new StockModel(0.08, 0.2, 252);
        PriceSimulator simulator = new PriceSimulator(model, RandomGenerator.getDefault());
        assertThrows(IllegalArgumentException.class, () -> {
            simulator.simulateNextPrice(-1.0);
        });
    }

    @Test
    void simulateDailyReturnTest() {
        StockModel model = new StockModel(0.252, 0, 252);
        RandomGenerator random = RandomGenerator.getDefault();
        PriceSimulator simulator = new PriceSimulator(model, random);

        double dailyReturn = simulator.simulateDailyReturn();

        assertEquals(0.001, dailyReturn);
    }

    @Test
    void simulateNextPriceTest() {
        StockModel model = new StockModel(0.252, 0, 252);
        RandomGenerator random = RandomGenerator.getDefault();
        PriceSimulator simulator = new PriceSimulator(model, random);

        double nextPrice = simulator.simulateNextPrice(100);

        assertEquals(100.1, nextPrice, 1e-12);
    }
}
