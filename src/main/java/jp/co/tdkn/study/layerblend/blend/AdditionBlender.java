package jp.co.tdkn.study.layerblend.blend;

public class AdditionBlender implements Blender {

    @Override
    public int blend(int srcArgb, int destArgb) {
        int a = computeColor(Blender.getAlpha(srcArgb), Blender.getAlpha(destArgb));
        int r = computeColor(Blender.getRed(srcArgb), Blender.getRed(destArgb));
        int g = computeColor(Blender.getGreen(srcArgb), Blender.getGreen(destArgb));
        int b = computeColor(Blender.getBlue(srcArgb), Blender.getBlue(destArgb));

        return Blender.getArgb(a, r, g, b);
    }

    private static int computeColor(int srcColor, int destColor) {
        return Math.min(255, srcColor + destColor);
    }

}
