# Development Notes
## 20 July 2026
### Repository created
Completed:
- Created personal GitHub account
- Created repository
- Connected IntelliJ
- Configured Git
- Created Java project

## Simulating Stock Prices
- Created `StockModel` to store:
  - Expected Annual return
  - Annual Volatility
  - Trading Days Per Year
- Converted annual return into daily values
- Implemented `simulateDailyReturn`
- Implemented `simulateNextPrice`
- Implemented `simulatePricePath` to simulate multiple trading days
- Added unit tests for the simulator

## Key formulas

Daily expected return

$$
\mu_{day} = \frac{\mu_{year}}{252}
$$

Daily volatility

$$
\sigma_{day} = \frac{\sigma_{year}}{\sqrt{252}}
$$

Daily return

$$
R = \mu_d + \sigma_d Z,\qquad Z \sim N(0,1)
$$

Next day's price

$$
S_{t+1} = S_t(1+R)
$$
***

# 21 July 2026
## Monte Carlo Simulation
- Added a `simulateMonteCarlo` method that generates multiple independent stock price paths.
- Created a `SimulationAnalyzer` class to analyze the results of Monte Carlo simulations. The class extracts the final stock price from each simulated path and computes descriptive statistics, including the mean, median, and five-number summary (minimum, Q1, median, Q3, and maximum).
- Added Maven support to manage project dependencies
- Integrated XChart to visualize simulated stock price paths
- Created a `SimulationGraph` class to generate line charts for Monte Carlo simulations.

## Risk Analysis
- Extended `SimulationAnalyzer` with additional risk metrics
- Implemented `probabilityOfLoss()`, which calculates the percentage of simulations ending below the initial stock price
- Implemented `valueAtRisk()` to estimate the **maximum** expected loss at a chosen confidence level
- Added unit tests for the new risk analysis methods

## Challenges
- Learned how percentile-based metrics such as VaR are computed from sorted simulation results.
- Encountered floating-point precision issues when converting confidence levels into array indices and adjusted the implementation accordingly.