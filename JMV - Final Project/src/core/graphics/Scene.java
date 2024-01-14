package core.graphics;

import java.util.*;

import core.graphics.model.Entity;
import core.graphics.model.Model;
import core.graphics.model.texture.TextureCache;

/**
 * The scene class will handle all elements which will be displayed onto the screen (models in this case, I guess).
 * @author akr_sm64
 */

public class Scene {
    private Map<String, Model> modelMap;
    private TextureCache textureCache;

    /**
     * The constructor will create a HashMap which maps a String to a specific model and a textureCache object.
     */
    
    public Scene() {
        modelMap = new HashMap<>();
        textureCache = new TextureCache();
    }
    
    /**
     * This method will add an entity to the scene.
     * @param entity  the specific entity to add.
     */

    public void addEntity(Entity entity) {
        String modelId = entity.getModelId();
        Model model = modelMap.get(modelId);
        if (model == null) {
            throw new RuntimeException("Could not find model [" + modelId + "]");
        }
        model.getEntitiesList().add(entity);
    }

    /**
     * This method will add a model to the scene.
     * @param model  the specific model to add.
     */
    
    public void addModel(Model model) {
        modelMap.put(model.getId(), model);
    }
    
    /**
     * This method will remove a model from the scene.
     * @param model  the specific model to remove.
     */
    
    public void removeModel(Model model) {
        modelMap.remove(model.getId(), model);
    }
    
    /**
     * Getter for the model map.
     * @return modelMap.
     */

    public Map<String, Model> getModelMap() {
        return modelMap;
    }
    
    /**
     * Getter for the texture cache.
     * @return textureCache.
     */

    public TextureCache getTextureCache() {
        return textureCache;
    }
}