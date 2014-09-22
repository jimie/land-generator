package org.zapylaev.sandbox.map;

import com.badlogic.gdx.Gdx;

import java.util.Scanner;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class GenerateFromFile implements MapGenerator {

    @Override
    public int[][] generateMap(int size) {
        Scanner mapScanner = new Scanner(Gdx.files.internal("sample_map").read());
        int[][] mapBuffer = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mapBuffer[i][j] = mapScanner.nextInt();
            }
        }
        return mapBuffer;
    }

    @Override
    public int[] generateLine(int length) {
        Scanner mapScanner = new Scanner(Gdx.files.internal("sample_map").read());
        int[] line = new int[length];
        for (int i = 0; i < length; i++) {
            line[i] = mapScanner.nextInt();
        }
        return line;
    }
}
