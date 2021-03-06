package eu.luminis.robots.sim;

import eu.luminis.geometry.IBorderDimensions;

import java.util.ArrayList;
import java.util.List;

public class SimWorld implements IBorderDimensions {
    private final SimRobotPopulation robotPopulation;
    private final StationaryObstaclePopulation obstaclePopulation;

    public SimWorld() {
        this.obstaclePopulation = new StationaryObstaclePopulation(this);
        this.robotPopulation = new SimRobotPopulation(this);
    }

    @Override
    public int getMinX() {
        return 0;
    }

    @Override
    public int getMaxX() {
        return 1600;
    }

    @Override
    public int getMinY() {
        return 0;
    }

    @Override
    public int getMaxY() {
        return 900;
    }

    public List<SimObstacle> getAllObstacles() {
        List<SimObstacle> simObstacles = new ArrayList<>();

        simObstacles.addAll(robotPopulation.getAllRobots());
        simObstacles.addAll(obstaclePopulation.getAllRoundObstacles());

        return simObstacles;
    }

    public SimRobotPopulation getRobotPopulation() {
        return robotPopulation;
    }

    public StationaryObstaclePopulation getObstaclePopulation() {
        return obstaclePopulation;
    }
}
