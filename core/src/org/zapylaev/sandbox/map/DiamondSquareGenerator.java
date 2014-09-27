package org.zapylaev.sandbox.map;

import java.util.Random;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class DiamondSquareGenerator implements MapGenerator {

    public static final int LINE_MAX_HEIGHT = 64;
    public static final double ROUGHNESS = 0.1;
    private Value[] mLine;
    private static Random sRandom = new Random();

    @Override
    public int[][] generateMap(int size) {
        int[] line = generateLine(size);
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = line[i];
            }
        }
        return result;
    }

    @Override
    public int[] generateLine(int length) {
        mLine = new Value[length];
        for (int i = 0; i < mLine.length; i++) {
            mLine[i] = new Value();
            mLine[i].i = i;
            mLine[i].v = 0;
        }
        mLine[0].v = random();
        mLine[length - 1].v = random();

        Value l = mLine[0];
        Value r = mLine[length - 1];
        midPoint(l, r);

        int[] lineInt = new int[length];

        for (int i = 0; i < lineInt.length; i++) {
            lineInt[i] = (int) mLine[i].v;
        }

        return lineInt;
    }

    private void midPoint(Value l, Value r) {
        if (Math.abs(l.i - r.i) <= 1) {
            return;
        }
        Value c = mLine[(l.i + r.i) / 2];
        c.v = l.middle(r) + random(ROUGHNESS * Math.abs(l.i - r.i));
        midPoint(l, c);
        midPoint(c, r);
    }

    private int random() {
        return (int) (Math.random() * LINE_MAX_HEIGHT);
    }

    public static double random(double range) {
        return range - ((2 * range) * sRandom.nextDouble());
    }

    private static class Value {
        int i;
        double v;

        double middle(Value other) {
            return (other.v + v) / 2.0;
        }

        @Override
        public String toString() {
            return i + "";
        }
    }
}
