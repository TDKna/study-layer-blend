package jp.co.tdkn.study.layerblend.blend;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public final class ImageBlender {

    public static void blend(Blender blender, Image srcImage, WritableImage destImage) {
        PixelReader srcReader = srcImage.getPixelReader();
        PixelReader destReader = destImage.getPixelReader();
        PixelWriter destWriter = destImage.getPixelWriter();

        for (int y = 0; y < srcImage.getHeight(); y++) {
            for (int x = 0; x < srcImage.getWidth(); x++) {
                int srcArgb = srcReader.getArgb(x, y);
                int destArgb = destReader.getArgb(x, y);
                destArgb = blender.blend(srcArgb, destArgb);

                destWriter.setArgb(x, y, destArgb);
            }
        }
    }

    private ImageBlender() {
    }

}
