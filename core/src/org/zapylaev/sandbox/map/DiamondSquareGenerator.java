package org.zapylaev.sandbox.map;

import java.util.Random;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class DiamondSquareGenerator implements MapGenerator {

    public static final int LINE_MAX_HEIGHT = 16;
    public static final double ROUGHNESS = 0.4;
    private static Random sRandom = new Random();
    private double[][] values;
    private int size;

    @Override
    public int[][] generateMap(int size) {
        this.size = size;
        values = new double[size + 1][size + 1];

        setValue(0, 0, random());
        setValue(0, size, random());
        setValue(size, 0, random());
        setValue(size, size, random());

        int stepSize = size;
        while (stepSize > 1) {
            diamondSquare(stepSize);
            stepSize /= 2;
        }

        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = (int) values[i][j];
            }
        }
        return result;
    }

    void diamondSquare(int stepSize) {
        int halfStep = stepSize / 2;

        for (int y = halfStep; y < size + halfStep; y += stepSize) {
            for (int x = halfStep; x < size + halfStep; x += stepSize) {
                sampleSquare(x, y, stepSize, randomExtra(stepSize));
            }
        }

        for (int y = 0; y < size; y += stepSize) {
            for (int x = 0; x < size; x += stepSize) {
                sampleDiamond(x + halfStep, y, stepSize, randomExtra(stepSize));
                sampleDiamond(x, y + halfStep, stepSize, randomExtra(stepSize));
            }
        }
    }

    public void sampleSquare(int x, int y, int size, double value) {
        int hs = size / 2;

        // a     b
        //
        //    x
        //
        // c     d

        double a = getValue(x - hs, y - hs);
        double b = getValue(x + hs, y - hs);
        double c = getValue(x - hs, y + hs);
        double d = getValue(x + hs, y + hs);

        setValue(x, y, ((a + b + c + d) / 4.0) + value);
    }

    public void sampleDiamond(int x, int y, int size, double value) {
        int hs = size / 2;

        //   c
        //
        //a  x  b
        //
        //   d

        double a = getValue(x - hs, y);
        double b = getValue(x + hs, y);
        double c = getValue(x, y - hs);
        double d = getValue(x, y + hs);

        setValue(x, y, ((a + b + c + d) / 4.0) + value);
    }

    public double getValue(int x, int y) {
        if (x > values.length - 1 || x < 0 || y > values.length - 1 || y < 0) {
            return -10;
        }
        return values[x][y];
    }

    public void setValue(int x, int y, double value) {
        if (x > values.length - 1 || x < 0 || y > values.length - 1 || y < 0) {
            return;
        }

        values[x][y] = value;
    }

    private double randomExtra(double l) {
        return random(l * ROUGHNESS);
    }

    private int random() {
        return (int) (Math.random() * LINE_MAX_HEIGHT);
    }

    public static double random(double range) {
        return range - ((2 * range) * sRandom.nextDouble());
    }
}
