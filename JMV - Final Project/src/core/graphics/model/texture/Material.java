package core.graphics.model.texture;

import org.joml.Vector4f;

import core.graphics.model.Mesh;

import java.util.*;

/**
 * The Material class will handle all the color related stuff.
 * @author akr_sm64
 */

public class Material {

    public static final Vector4f DEFAULT_COLOR = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);

    private Vector4f diffuseColor;
    private List<Mesh> meshList;
    private String texturePath;

    /**
     * The constructor sets the variables to default values.
     */
    
    public Material() {
        diffuseColor = DEFAULT_COLOR;
        meshList = new ArrayList<>();
    }
    
    /**
     * Getter for diffuse color.
     * @return diffuseColor.
     */

    public Vector4f getDiffuseColor() {
        return diffuseColor;
    }
    
    /**
     * Getter for mesh list.
     * @return meshList.
     */

    public List<Mesh> getMeshList() {
        return meshList;
    }
    
    /**
     * Getter for texture path.
     * @return texturePath.
     */

    public String getTexturePath() {
        return texturePath;
    }
    
    /**
     * Setter for diffuse color.
     * @param diffuseColor  the specific color.
     */

    public void setDiffuseColor(Vector4f diffuseColor) {
        this.diffuseColor = diffuseColor;
    }
    
    /**
     * Setter for texture path.
     * @return texturePath  the texture path.
     */

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }
}