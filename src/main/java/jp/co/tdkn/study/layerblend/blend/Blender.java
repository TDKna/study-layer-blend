package jp.co.tdkn.study.layerblend.blend;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public interface Blender {

    int blend(int srcArgb, int destArgb);

    static int getAlpha(int argb) {
        return (argb >> 24) & 0xFF;
    }

    static int getRed(int argb) {
        return (argb >> 16) & 0xFF;
    }

    static int getGreen(int argb) {
        return (argb >> 8) & 0xFF;
    }

    static int getBlue(int argb) {
        return argb & 0xFF;
    }

    static int getArgb(int a, int r, int g, int b) {
        return (a << 24) + (r << 16) + (g << 8) + b;
    }

    static int blendAlpha(int srcAlpha, int destAlpha) {
        return Math.min(255, srcAlpha + destAlpha - (srcAlpha * destAlpha) / 255);
    }

}
