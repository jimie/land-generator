package org.zapylaev.sandbox.map;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class DiamondSquareGenerator implements MapGenerator {

    public static final int LINE_MAX_HEIGHT = 30;
    private Value[] mLine;

    @Override
    public int[][] generateMap(int size) {
        return new int[0][];
    }

    static class Value {
        int i;
        int v;

        int middle(Value other) {
            return Math.abs(other.v + v) / 2;
        }

        @Override
        public String toString() {
            return i + "";
        }
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
            lineInt[i] = mLine[i].v;
        }

        return lineInt;
    }

    private void midPoint(Value l, Value r) {
        if (Math.abs(l.i - r.i) <= 1) {
            return;
        }
        Value c = mLine[(l.i + r.i) / 2];
        c.v = l.middle(r);
        midPoint(l, c);
        midPoint(c, r);
    }

    private int random() {
        return (int) (Math.random() * LINE_MAX_HEIGHT);
    }
}
