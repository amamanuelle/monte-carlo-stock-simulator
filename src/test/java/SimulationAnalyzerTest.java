import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimulationAnalyzerTest {

    @Test
    void extractFinalPricesTest() {
        SimulationAnalyzer analyzer = new SimulationAnalyzer();

        double[][] paths = {
                {100.0, 102.0, 105.0},
                {100.0, 98.0, 97.0},
                {100.0, 101.0, 103.0}
        };

        double[] finalPrices = analyzer.extractFinalPrices(paths);

        assertArrayEquals(
                new double[]{105.0, 97.0, 103.0},
                finalPrices
        );
    }

    @Test
    void meanTest() {
        SimulationAnalyzer analyzer = new SimulationAnalyzer();

        double[] values = {10.0, 20.0, 30.0, 40.0};

        assertEquals(25.0, analyzer.mean(values));
    }

    @Test
    void medianOddLengthTest() {
        SimulationAnalyzer analyzer = new SimulationAnalyzer();

        double[] values = {5.0, 1.0, 3.0};

        assertEquals(3.0, analyzer.median(values));
    }

    @Test
    void medianEvenLengthTest() {
        SimulationAnalyzer analyzer = new SimulationAnalyzer();

        double[] values = {4.0, 1.0, 3.0, 2.0};

        assertEquals(2.5, analyzer.median(values));
    }

    @Test
    void fiveNumberSummaryTest() {
        SimulationAnalyzer analyzer = new SimulationAnalyzer();

        double[] values = {1, 2, 3, 4, 5, 6, 7, 8};

        double[] summary = analyzer.fiveNumberSummary(values);

        assertArrayEquals(
                new double[]{1.0, 2.5, 4.5, 6.5, 8.0},
                summary
        );
    }
}