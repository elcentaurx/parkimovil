package com.luismarquez.parkiplanet;

import android.util.Log;

import com.luismarquez.parkiplanet.Island;

public class Line {
    private Island island_one;
    private Island island_two;

    public double getLength() {
        return length;
    }

    private double length;

    public Line(Island island_one, Island island_two) {
        this.island_one = island_one;
        this.island_two = island_two;
        setLength();
    }

    private void setLength() {
        length = Math.sqrt(Math.pow(island_two.getPointX() - island_one.getPointX(), 2) + Math.pow(island_two.getPointY() - island_one.getPointY(), 2));
        Log.i("Longitus linea ", String.valueOf(length));
    }

    public Island getIslandOne() {
        return island_one;
    }

    public Island getIslandTwo() {
        return island_two;
    }

}
