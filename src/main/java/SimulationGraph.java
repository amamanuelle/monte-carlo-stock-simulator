import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class SimulationGraph {

    public void showPricePaths(double[][] paths, int numberOfPaths) {
        if (paths == null || paths.length == 0) throw new IllegalArgumentException("Paths must not be empty or null");

        int pathsToDisplay = Math.min(numberOfPaths, paths.length);

        XYChart chart = new XYChartBuilder()
                .width(900)
                .height(600)
                .title("Monte Carlo Stock Price Simulation")
                .xAxisTitle("Day")
                .yAxisTitle("Stock Price")
                .build();

        for (int simulation = 0; simulation < pathsToDisplay; simulation++) {

            double[] path = paths[simulation];
            double[] days = new double[path.length];

            for (int day = 0; day < path.length; day++) {
                days[day] = day;
            }

            chart.addSeries("Simulation " + (simulation + 1),
                    days,
                    path);
        }

        new SwingWrapper<>(chart).displayChart();
    }
}
