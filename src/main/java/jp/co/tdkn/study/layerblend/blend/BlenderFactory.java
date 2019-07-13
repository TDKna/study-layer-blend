package jp.co.tdkn.study.layerblend.blend;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class BlenderFactory {

    private static final Map<BlendMode, Class<? extends Blender>> blenderClassMap = new LinkedHashMap<>();
    static {
        registerBlender(BlendMode.NORMAL, NormalBlender.class);
        registerBlender(BlendMode.ADDITION, AdditionBlender.class);
        registerBlender(BlendMode.MULTIPLY, MultiplyBlender.class);
        registerBlender(BlendMode.SCREEN, ScreenBlender.class);
    }

    public static Blender create(BlendMode blendMode) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<? extends Blender> blenderClass = blenderClassMap.get(blendMode);
        return blenderClass.getDeclaredConstructor().newInstance();
    }

    private static void registerBlender(BlendMode blendMode, Class<? extends Blender> blenderClass) {
        blenderClassMap.put(blendMode, blenderClass);
    }

}
