package org.zapylaev.sandbox;

import com.badlogic.gdx.graphics.Color;

/**
 * @author k.zapylaev <zapylaev@gmail.com>
 */
public class LevelColor {
    public static Color get(int level) {
        switch (level) {
            case 0: return Color.BLUE;
            case 1: return Color.GREEN;
            case 2: return Color.LIGHT_GRAY;
            case 3: return Color.DARK_GRAY;
            default: return Color.RED;
        }
    }
}
