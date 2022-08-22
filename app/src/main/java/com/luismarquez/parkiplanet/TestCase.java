package com.luismarquez.parkiplanet;

import java.util.HashMap;

public class TestCase {
    private int NumberOfPoints;
    private HashMap<Integer, Island> setOfIslands;


    public void addIslands(Island isla) {
        setOfIslands.put(isla.hashCode(), isla);
    }

    public HashMap<Integer, Island> getSetOfIslands() {
        return setOfIslands;
    }


    public TestCase(int numberOfPoints) {
        NumberOfPoints = numberOfPoints;
        setOfIslands = new HashMap<>();

    }
}