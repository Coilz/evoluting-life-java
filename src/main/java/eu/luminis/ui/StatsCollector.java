package eu.luminis.ui;

import eu.luminis.events.Event;
import eu.luminis.events.EventType;
import eu.luminis.robots.sim.SimRobot;
import eu.luminis.robots.sim.SimRobotPopulation;

import java.util.*;

public class StatsCollector implements Observer {

    private boolean deactivated = false;
    private int totalStarved;
    private int totalCollisions;
    private int totalWandered;
    private int totalDiedOfAge;

    private SimRobotPopulation population;
    private List<PeriodicStats> periodicStatsList = new ArrayList<>();

    public StatsCollector(SimRobotPopulation population) {
        this.population = population;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (deactivated) {
            return;
        }

        Event event = (Event) arg;

        if (event.type.equals(EventType.COLLIDE)) {
            totalCollisions++;
        }
        else if (event.type.equals(EventType.STARVED)) {
            totalStarved++;
        }
        else if (event.type.equals(EventType.WANDERED)) {
            totalWandered++;
        }
        else if (event.type.equals(EventType.DIED_OF_AGE)) {
            totalDiedOfAge++;
        }
        else if (event.type.equals(EventType.CYCLE_END)) {
            collectPeriodicStats();
        }
    }

    public Stats getStats() {
        List<PeriodicStats> tempStatsList = periodicStatsList;
        this.periodicStatsList = new ArrayList<>();

        Stats stats = new Stats(totalStarved, totalCollisions, totalWandered, totalDiedOfAge, tempStatsList);

        this.totalCollisions = 0;
        this.totalStarved = 0;
        this.totalWandered = 0;
        this.totalDiedOfAge = 0;

        this.deactivated = false;

        return stats;
    }

    private void collectPeriodicStats() {
        double totalHealth = 0;
        double totalAge = 0;
        double totalDistance = 0;

        List<SimRobot> allRobots = population.getAllRobots();
        for (SimRobot robot : allRobots) {
            totalHealth += robot.health();
            totalAge += robot.getAgeInformation().getAge();
            totalDistance += robot.getTravelledDistance();
        }

        int populationSize = allRobots.size();
        double avgHealth = totalHealth / populationSize;
        double avgAge = totalAge / populationSize;
        double avgDistance = totalDistance / populationSize;
        double bestFitness = population.getWinningEntity().fitness();

        PeriodicStats periodicStats = new PeriodicStats(avgHealth, avgAge, avgDistance, bestFitness);

        periodicStatsList.add(periodicStats);

        if (periodicStatsList.size() > 5000) {
            deactivated = true;
        }
    }
}
