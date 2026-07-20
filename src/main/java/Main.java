import java.util.random.RandomGenerator;

public class Main {

    public static void main(String[] args) {
        StockModel model = new StockModel(0.08, 0.20, 252);
        RandomGenerator random = RandomGenerator.getDefault();
        PriceSimulator simulator = new PriceSimulator(model, random);
        double[] result = simulator.simulatePricePath(1, 10);
        for (double x : result) {
            System.out.println(x);
        }
        System.out.println("Monte Carlo Stock Simulator");
    }
}