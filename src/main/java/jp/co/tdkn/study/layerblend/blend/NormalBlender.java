package jp.co.tdkn.study.layerblend.blend;

/**
 * 前面優先
 */
public class NormalBlender implements Blender {

    @Override
    public int blend(int srcArgb, int destArgb) {
        int srcAlpha = Blender.getAlpha(srcArgb);
        if (srcAlpha == 0) {
            return destArgb;
        }

        int a = Blender.blendAlpha(srcAlpha, Blender.getAlpha(destArgb));
        int r = Blender.getRed(srcArgb);
        int g = Blender.getGreen(srcArgb);
        int b = Blender.getBlue(srcArgb);

        return Blender.getArgb(a, r, g, b);
    }

}
