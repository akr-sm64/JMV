package core.graphics.model.texture;

import java.util.*;

/**
 * The TextureCache class will take care of texture alongside the Texture class.
 * @author akr_sm64
 */

public class TextureCache {

    public static final String DEFAULT_TEXTURE = "./resources/textures/brick.png";

    private Map<String, Texture> textureMap;

    /**
     * The costructor will create a HashMap and store the default texture in it.
     */
    
    public TextureCache() {
        textureMap = new HashMap<>();
        textureMap.put(DEFAULT_TEXTURE, new Texture(DEFAULT_TEXTURE));
    }

    /**
     * This method will clean the map.
     */
    
    public void cleanup() {
        textureMap.values().forEach(Texture::cleanup);
    }
    
    /**
     * This method will create a texture if it's not existent.
     * @param texturePath the texture path.
     * @return a new texture if not existent.
     */

    public Texture createTexture(String texturePath) {
        return textureMap.computeIfAbsent(texturePath, Texture::new);
    }
    
    /**
     * Getter for texture.
     * @param texturePath  the path.
     * @return texture at texturePath and the default texture if null.
     */

    public Texture getTexture(String texturePath) {
        Texture texture = null;
        if (texturePath != null) {
            texture = textureMap.get(texturePath);
        }
        if (texture == null) {
            texture = textureMap.get(DEFAULT_TEXTURE);
        }
        return texture;
    }
}