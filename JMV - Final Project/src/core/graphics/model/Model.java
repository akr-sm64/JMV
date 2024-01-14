package core.graphics.model;

import java.util.*;

import core.graphics.model.texture.Material;

/**
 * The Model class will handle the models.
 * @author akr_sm64
 */

public class Model {

    private final String id;
    private List<Entity> entitiesList;
    private List<Material> materialList;
    private Map<String, Model> modelMap;

    /**
     * The constructor will set some variables to given values.
     * @param id  the ID.
     * @param materialList  the list of materials.
     */
    
    public Model(String id, List<Material> materialList) {
        this.id = id;
        entitiesList = new ArrayList<>();
        modelMap = new HashMap<>();
        this.materialList = materialList;
    }
    
    /**
     * This method adds an entity.
     * @param entity  the entity to add.
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
     * This method removes an entity.
     * @param entity  the entity to remove.
     */
    
    public void removeEntity(Entity entity) {
        String modelId = entity.getModelId();
        Model model = modelMap.get(modelId);
        if (model == null) {
            throw new RuntimeException("Could not find model [" + modelId + "]");
        }
        model.getEntitiesList().remove(entity);
    }

    /**
     * Getter for entitiesList.
     * @return entitiesList.
     */
    
    public List<Entity> getEntitiesList() {
        return entitiesList;
    }
    
    /**
     * Getter for id.
     * @return id.
     */

    public String getId() {
        return id;
    }
    
    /**
     * Getter for materialList.
     * @return materialList.
     */

    public List<Material> getMaterialList() {
        return materialList;
    }
}