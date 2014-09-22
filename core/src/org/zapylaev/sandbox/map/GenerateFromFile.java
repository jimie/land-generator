package org.zapylaev.sandbox.map;

import com.badlogic.gdx.Gdx;

import java.util.Scanner;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class GenerateFromFile implements MapGenerator {

    @Override
    public int[][] generateMap(int size) {
        if (size != 10) throw new IllegalArgumentException("must be 10");
        Scanner mapScanner = new Scanner(Gdx.files.internal("sample_map").read());
        int[][] mapBuffer = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mapBuffer[i][j] = mapScanner.nextInt();
            }
        }
        return mapBuffer;
    }
}
