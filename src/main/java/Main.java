import java.util.Arrays;
import java.util.random.RandomGenerator;

public class Main {

    public static void main(String[] args) {

        StockModel model = new StockModel(0.10, 0.20, 252);
        PriceSimulator simulator =
                new PriceSimulator(model, RandomGenerator.getDefault());
        SimulationAnalyzer analyzer = new SimulationAnalyzer();

        double initialPrice = 100.0;
        int numberOfDays = 252;
        int numberOfSimulations = 1000;

        double[][] paths = simulator.simulateMonteCarlo(
                initialPrice,
                numberOfDays,
                numberOfSimulations
        );

        double[] finalPrices = analyzer.extractFinalPrices(paths);
        double mean = analyzer.mean(finalPrices);
        double median = analyzer.median(finalPrices);
        double[] summary = analyzer.fiveNumberSummary(finalPrices);

        System.out.println("Monte Carlo Simulation");
        System.out.println("----------------------");
        System.out.println("Initial price: " + initialPrice);
        System.out.println("Number of days: " + numberOfDays);
        System.out.println("Number of simulations: " + numberOfSimulations);
        System.out.println();

        System.out.println("Mean final price: " + mean);
        System.out.println("Median final price: " + median);
        System.out.println();

        System.out.println("Five-number summary");
        System.out.println("Minimum: " + summary[0]);
        System.out.println("Q1: " + summary[1]);
        System.out.println("Median: " + summary[2]);
        System.out.println("Q3: " + summary[3]);
        System.out.println("Maximum: " + summary[4]);

        SimulationGraph graph = new SimulationGraph();
        graph.showPricePaths(paths, 10);
    }
}