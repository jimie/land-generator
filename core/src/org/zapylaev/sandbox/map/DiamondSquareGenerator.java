package org.zapylaev.sandbox.map;

import java.util.Random;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class DiamondSquareGenerator implements MapGenerator {

    public static final int LINE_MAX_HEIGHT = 64;
    public static final double ROUGHNESS = 0.1;
    private Value[] mLine;
    private Value[][] mMap;
    private static Random sRandom = new Random();

    @Override
    public int[][] generateMap(int size) {
        mMap = new Value[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mMap[i][j] = new Value();
                mMap[i][j].x = i;
                mMap[i][j].y = j;
                mMap[i][j].v = 30;
            }
        }

        mMap[0][0].v = random();
        mMap[size - 1][0].v = random();
        mMap[0][size - 1].v = random();
        mMap[size - 1][size - 1].v = random();

        Value leftTop = mMap[0][0];
        Value rightTop = mMap[size - 1][0];
        Value leftBottom = mMap[0][size - 1];
        Value rightBottom = mMap[size - 1][size - 1];

        midPoint(leftTop, rightTop, leftBottom, rightBottom);

        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = (int) mMap[i][j].v;
            }
        }
        return result;
    }

    private void midPoint(Value leftTop, Value rightTop, Value leftBottom, Value rightBottom) {
        if (Math.abs(leftTop.x - rightTop.x) <= 1) {
            return;
        }
        Value c = mMap[(leftTop.x + rightTop.x) / 2][(leftTop.y + leftBottom.y) / 2];
        c.v = leftTop.middle(rightTop, leftBottom, rightBottom) + randomExtra(leftTop, rightTop);

        Value cTop = mMap[(leftTop.x + rightTop.x) / 2][leftTop.y];
        cTop.v = leftTop.middle(rightTop) + randomExtra(leftTop, rightTop);

        Value cLeft = mMap[leftTop.x][(leftTop.y + leftBottom.y) / 2];
        cLeft.v = leftTop.middle(leftBottom) + randomExtra(leftTop, leftBottom);

        Value cRight = mMap[rightTop.x][(rightTop.y + rightBottom.y) / 2];
        cRight.v = rightTop.middle(rightBottom) + randomExtra(rightTop, rightBottom);

        Value cBottom = mMap[(leftBottom.x + rightBottom.x) / 2][leftBottom.y];
        cBottom.v = leftBottom.middle(rightBottom) + randomExtra(leftBottom, rightBottom);
        midPoint(leftTop, cTop, cLeft, c);
        midPoint(cTop, rightTop, c, cRight);
        midPoint(cLeft, c, leftBottom, cBottom);
        midPoint(c, cRight, cBottom, rightBottom);
    }

    private double randomExtra(Value left, Value right) {
        return random(ROUGHNESS * Math.abs(left.x - right.x));
    }

    @Override
    public int[] generateLine(int length) {
        mLine = new Value[length];
        for (int i = 0; i < mLine.length; i++) {
            mLine[i] = new Value();
            mLine[i].x = i;
            mLine[i].v = 0;
        }
        mLine[0].v = random();
        mLine[length - 1].v = random();

        Value l = mLine[0];
        Value r = mLine[length - 1];
        midPoint(l, r);

        int[] result = new int[length];

        for (int i = 0; i < result.length; i++) {
            result[i] = (int) mLine[i].v;
        }

        return result;
    }

    private void midPoint(Value l, Value r) {
        if (Math.abs(l.x - r.x) <= 1) {
            return;
        }
        Value c = mLine[(l.x + r.x) / 2];
        c.v = l.middle(r) + randomExtra(l, r);
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
        int x;
        int y;
        double v;

        double middle(Value other) {
            return (other.v + v) / 2.0;
        }

        double middle(Value other1, Value other2, Value other3) {
            return (other1.v + other2.v + other3.v + v) / 4.0;
        }

        @Override
        public String toString() {
            return x + "";
        }
    }
}
