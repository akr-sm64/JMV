package core.graphics.model;

import org.joml.*;

import core.input.Keyboard;
import static org.lwjgl.glfw.GLFW.*;

/**
 * The Entity class will be in charge of handling the model as a whole. <br>
 * This means that it will keep track of the position, rotation, scale and model matrix.
 * @author akr_sm64
 */

public class Entity {

    private final String id;
    private final String modelId;
    private Vector3f position, rotation, scale;

    /**
     * The constructor will set the IDs to whatever is passed in. <br>
     * And then, it will set the position, rotation and scale to their default values.
     * @param id  The entity's ID.
     * @param modelId  the model's ID.
     */
    
    public Entity(String id, String modelId) {
        this.id = id;
        this.modelId = modelId;
        position = new Vector3f(0.0f, 0.0f, 0.0f);
        rotation = new Vector3f(0.0f, 0.0f, 0.0f);
        scale = new Vector3f(1.0f, 1.0f, 1.0f);
    }
    
    /**
     * This method will handle input for the entity.
     */
    
    public void update() {
    	if (Keyboard.isKeyPressed(GLFW_KEY_DOWN)) {
    		position.add(new Vector3f(0.0f, 0.0f, 0.05f));
    	}
    	if (Keyboard.isKeyPressed(GLFW_KEY_UP)) {
    		position.add(new Vector3f(0.0f, 0.0f, -0.05f));
    	}
    	if (Keyboard.isKeyPressed(GLFW_KEY_LEFT)) {
    		position.add(new Vector3f(-0.05f, 0.0f, 0.0f));
    	}
    	if (Keyboard.isKeyPressed(GLFW_KEY_RIGHT)) {
    		position.add(new Vector3f(0.05f, 0.0f, 0.0f));
    	}
    	if (Keyboard.isKeyPressed(GLFW_KEY_RIGHT_SHIFT)) {
    		position.add(new Vector3f(0.0f, 0.05f, 0.0f));
    	}
    	if (Keyboard.isKeyPressed(GLFW_KEY_RIGHT_CONTROL)) {
    		position.add(new Vector3f(0.0f, -0.05f, 0.0f));
    	}
    	
    	if (Keyboard.isKeyPressed(GLFW_KEY_KP_8)) {
    		rotation.add(new Vector3f(-0.05f, 0.0f, 0.0f));
    	}
    	if (Keyboard.isKeyPressed(GLFW_KEY_KP_2)) {
    		rotation.add(new Vector3f(0.05f, 0.0f, 0.0f));
    	}
    	if (Keyboard.isKeyPressed(GLFW_KEY_KP_4)) {
    		rotation.add(new Vector3f(0.0f, -0.05f, 0.0f));
    	}
    	if (Keyboard.isKeyPressed(GLFW_KEY_KP_6)) {
    		rotation.add(new Vector3f(0.0f, 0.05f, 0.0f));
    	}
    	if (Keyboard.isKeyPressed(GLFW_KEY_KP_ADD)) {
    		rotation.add(new Vector3f(0.0f, 0.0f, -0.05f));
    	}
    	if (Keyboard.isKeyPressed(GLFW_KEY_KP_SUBTRACT)) {
    		rotation.add(new Vector3f(0.0f, 0.0f, 0.05f));
    	}
    }

    /**
     * Getter for id.
     * @return id.
     */
    
    public String getId() {
        return id;
    }
    
    /**
     * Getter for modelId.
     * @return modelId.
     */

    public String getModelId() {
        return modelId;
    }
    
    /**
     * Getter for position.
     * @return position.
     */
    
    public Vector3f getPosition() {
		return position;
	}
    
    /**
     * Getter for rotation.
     * @return rotation.
     */

	public Vector3f getRotation() {
		return rotation;
	}
	
	public void setPosition(float x, float y, float z) {
		this.position.x = x;
		this.position.y = y;
		this.position.z = z;
	}

	public void setRotation(float pitch, float yaw, float roll) {
		this.rotation.x = pitch;
		this.rotation.y = yaw;
		this.rotation.z = roll;
	}

	/**
     * Getter for scale.
     * @return scale.
     */

	public Vector3f getScale() {
		return scale;
	}
	
	/**
     * Getter for model matrix which is calculated by translating a matrix by the position vector, rotating it by the rotation vector and scaling it by the scale vector.
     * @return modelMatrix.
     */

    public Matrix4f getModel() {
    	Matrix4f modelMatrix = new Matrix4f()
                .translate(position)
                .rotateXYZ(rotation.x, rotation.y, rotation.z)
                .scale(scale);
    	return modelMatrix;
    }
}