package eu.luminis.robots;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SimSensor implements ISensor {
    private SimRobot owner;

    public SimSensor(SimRobot owner) {
        this.owner = owner;
    }

    @Override
    public Double sense() {
        // TODO: find out if there are obstacles in the line of view
        throw new NotImplementedException();
    }

    @Override
    public Double sense(long msTimeout) {
        // TODO: find out if there are obstacles in the line of view
        throw new NotImplementedException();
    }
}