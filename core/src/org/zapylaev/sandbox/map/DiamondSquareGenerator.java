package org.zapylaev.sandbox.map;

import java.util.Random;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class DiamondSquareGenerator implements MapGenerator {

    public static final int LINE_MAX_HEIGHT = 30;
    public static final double ROUGHNESS = 0.09;
    private Value[] mLine;
    private static Random sRandom = new Random();

    @Override
    public int[][] generateMap(int size) {
        return new int[0][];
    }

    static class Value {
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

    @Override
    public double[] generateLine(int length) {
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

        double[] lineInt = new double[length];

        for (int i = 0; i < lineInt.length; i++) {
            lineInt[i] = mLine[i].v;
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
}
