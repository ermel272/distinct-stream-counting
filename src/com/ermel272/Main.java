package com.ermel272;

import com.ermel272.controllers.StreamController;

public class Main {

    public static void main(String[] args) {
        // Instantiate and start the simulation
        StreamController simulationController = new StreamController();
        simulationController.startSimulation();
    }
}
