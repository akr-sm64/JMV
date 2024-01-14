package core.graphics;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Math;

import core.input.Keyboard;
import core.input.Mouse;

import static org.lwjgl.glfw.GLFW.*;

/**
 * The Camera class will manage all camera related operations such as the projection... etc.
 * @author akr_sm64
 */

public class Camera {
    private Vector3f position;
    private Vector3f rotation;
    
    private Matrix4f view;
    private Matrix4f proj; // Useful variables.

    /**
     * The constructor will set the camera at a default position and rotation then update the matrices.
     */
    
    public Camera() {
        position = new Vector3f(0.0f, 0.25f, 1.0f);
        rotation = new Vector3f(0.0f, 0.0f, 0.0f);
        updateMatrices();
    }
    
    /**
     * The move method will change the position to new coordinates.
     * @param dx  the distance in the x-axis.
     * @param dy  the distance in the y-axis.
     * @param dz  the distance in the z-axis.
     */

    public void move(float dx, float dy, float dz) {
        position.add(dx, dy, dz);
        updateMatrices();
    }
    
    /**
     * The rotate method will rotate the camera using the mouse position.
     * @param pitch  the rotation angle about the x-axis (I believe).
     * @param yaw the rotation angle about the y-axis (I believe).
     */
    
    public void rotate(float pitch, float yaw) {
    	this.rotation.x += pitch;
    	this.rotation.y += yaw;
    	updateMatrices();
    }

    /**
     * This method will set the view and projection matrices to their default values.
     */
    
    private void updateMatrices() {
        view = new Matrix4f().identity().rotateX(Math.toRadians(rotation.x)).rotateY(Math.toRadians(rotation.y)).rotateZ(Math.toRadians(rotation.z)).translate(-position.x, -position.y, -position.z);
        proj = new Matrix4f().perspective(Math.toRadians(70.0f), (float) 800 / 600, 0.1f, 1000.0f);
    }
    
    /**
     * The input method will allow the user to control the camera using the keyboard for the position and the mouse for the rotation.
     */
    
    public void input() {
    	if (Keyboard.isKeyPressed(GLFW_KEY_W)) {
            move(0.0f, 0.0f, -0.05f);
        }
        if (Keyboard.isKeyPressed(GLFW_KEY_S)) {
            move(0.0f, 0.0f, 0.05f);
        }
        if (Keyboard.isKeyPressed(GLFW_KEY_A)) {
            move(-0.05f, 0.0f, 0.0f);
        }
        if (Keyboard.isKeyPressed(GLFW_KEY_D)) {
            move(0.05f, 0.0f, 0.0f);
        }
        if (Keyboard.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
            move(0.0f, 0.05f, 0.0f);
        }
        if (Keyboard.isKeyPressed(GLFW_KEY_LEFT_CONTROL)) {
            move(0.0f, -0.05f, 0.0f);
        }
        if (Mouse.isLeftButtonPress()) {
        	Vector2f rotVec = Mouse.getDisplVec();
        	rotate(rotVec.x * 0.1f, rotVec.y * 0.1f);
        }
    }

    /**
     * Getter for the view matrix.
     * @return view
     */
    
	public Matrix4f getView() {
        return view;
    }

	/**
	 * Getter for the projection matrix.
	 * @return proj
	 */
	
    public Matrix4f getProj() {
        return proj;
    }
}