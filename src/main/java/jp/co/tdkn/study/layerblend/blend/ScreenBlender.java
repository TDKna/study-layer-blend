package jp.co.tdkn.study.layerblend.blend;

/**
 * 明るくする
 */
public class ScreenBlender implements Blender {

    @Override
    public int blend(int srcArgb, int destArgb) {
        if (Blender.getAlpha(srcArgb) == 0) {
            return destArgb;
        }

        int a = Blender.blendAlpha(Blender.getAlpha(srcArgb), Blender.getAlpha(destArgb));
        int r = computeColor(Blender.getRed(srcArgb), Blender.getRed(destArgb));
        int g = computeColor(Blender.getGreen(srcArgb), Blender.getGreen(destArgb));
        int b = computeColor(Blender.getBlue(srcArgb), Blender.getBlue(destArgb));

        return Blender.getArgb(a, r, g, b);
    }

    private static int computeColor(int srcColor, int destColor) {
        return 255 - ((255 - destColor) * (255 - srcColor) >> 8);
    }

}
