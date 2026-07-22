# Monte Carlo Stock Price Simulator

A Java application that simulates future stock prices using Monte Carlo methods. The project explores quantitative finance concepts by generating thousands of possible stock price paths, analyzing the results with statistical and risk metrics, and visualizing sample simulations using XChart.

## Motivation

I built this project to learn how Monte Carlo simulations are used in quantitative finance while strengthening my understanding of probability, statistics, object-oriented programming, and data visualization in Java.

## Features

- Simulate stock price movements over configurable trading periods
- Generate thousands of independent Monte Carlo simulations
- Compute descriptive statistics:
    - Mean
    - Median
    - Five-number summary
- Calculate financial risk metrics:
    - Probability of Loss
    - Value at Risk (VaR)
- Visualize simulated price paths using XChart
- Unit tested with JUnit 5

## Technologies

- Java
- Maven
- JUnit 5
- XChart

## Sample Simulation

The figure below shows a sample of 10 simulated stock price paths generated from a total of 1,000 Monte Carlo simulations. Each line represents one possible future stock price trajectory. Although all simulations begin at the same initial price, they diverge over time due to random daily returns, illustrating the uncertainty of future market movements.

![Monte Carlo Simulation](images/simulation-graph.png)