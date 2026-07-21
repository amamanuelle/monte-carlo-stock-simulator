import java.util.Arrays;
import java.util.random.RandomGenerator;

public class Main {

    public static void main(String[] args) {

        StockModel model = new StockModel(0.10, 0.20, 252);
        PriceSimulator simulator = new PriceSimulator(model, RandomGenerator.getDefault());

        double[][] paths = simulator.simulateMonteCarlo(100.0, 100, 100);

        for (int simulation = 0; simulation < paths.length; simulation++) {
            System.out.println("Simulation " + (simulation + 1) + ":");
            System.out.println(Arrays.toString(paths[simulation]));
            System.out.println();
        }
    }
}