package jp.co.tdkn.study.layerblend;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import jp.co.tdkn.study.layerblend.blend.Blender;
import jp.co.tdkn.study.layerblend.blend.BlenderFactory;
import jp.co.tdkn.study.layerblend.blend.BlendMode;
import jp.co.tdkn.study.layerblend.blend.ImageBlender;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView canvas;
    @FXML
    private ChoiceBox<BlendMode> blendModeSelector;

    private Blender currentBlender;
    private List<Image> layers = new ArrayList<>();
    private int canvasWidth;
    private int canvasHeight;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeLayers();
        initializeBlendMode();
    }

    private void initializeLayers() {
        layers.clear();
        Image foreImage = createForegroundImage();
        layers.add(foreImage);

        canvasWidth = (int) foreImage.getWidth();
        canvasHeight = (int) foreImage.getHeight();
    }

    private void initializeBlendMode() {
        blendModeSelector.setItems(FXCollections.observableArrayList(BlendMode.values()));
        blendModeSelector.getSelectionModel().selectedItemProperty().addListener(observable -> {
            updateCurrentBlender();
            updateCanvas();
        });
        blendModeSelector.getSelectionModel().select(BlendMode.NORMAL);
    }

    private void updateCurrentBlender() {
        BlendMode blendMode = blendModeSelector.getSelectionModel().getSelectedItem();
        try {
            currentBlender = BlenderFactory.create(blendMode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCanvas() {
        WritableImage canvasImage = createCanvasImage();
        for (int i = 0; i < layers.size(); i++) {
            Image layer = layers.get(i);
            ImageBlender.blend(currentBlender, layer, canvasImage);
        }

        canvas.setImage(canvasImage);
    }

    private WritableImage createCanvasImage() {
        WritableImage image = new WritableImage(canvasWidth, canvasHeight);
        PixelWriter w = image.getPixelWriter();

        int red = Blender.getArgb(255, 255, 0, 0);
        for (int y = 0; y < canvasHeight; y++) {
            for (int x = 0; x < canvasWidth; x++) {
                w.setArgb(x, y, red);
            }
        }

        return image;
    }

    private Image createForegroundImage() {
        return new Image(getClass().getResourceAsStream("/image/foreground.png"));
    }
}
