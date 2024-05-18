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
        position = new Vector3f(0.0f, 0.25f, 3.0f);
        rotation = new Vector3f(0.0f, 0.0f, 0.0f);
    }
    
    /**
     * The move method will change the position to new coordinates.
     * @param dx  the distance in the x-axis.
     * @param dy  the distance in the y-axis.
     * @param dz  the distance in the z-axis.
     */

    public void move(float dx, float dy, float dz) {
        if (dz != 0) {
        	position.x += Math.sin(Math.toRadians(rotation.y)) * -1.0f * dz;
        	position.z += Math.cos(Math.toRadians(rotation.y)) * dz;
        }
        
        if (dx != 0) {
        	position.x += Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * dx;
        	position.z += Math.cos(Math.toRadians(rotation.y - 90)) * dx;
        }
        
        position.y += dy;
    }
    
    public void setPosition(float x, float y, float z) {
		this.position.set(x, y, z);
	}

	public void setRotation(float pitch, float yaw, float roll) {
		this.rotation.x = pitch;
		this.rotation.y = yaw;
		this.rotation.z = roll;
	}

	/**
     * The rotate method will rotate the camera using the mouse position.
     * @param pitch  the rotation angle about the x-axis (I believe).
     * @param yaw the rotation angle about the y-axis (I believe).
     */
    
    public void rotate(float pitch, float yaw, float roll) {
    	this.rotation.x += pitch;
    	this.rotation.y += yaw;
    	this.rotation.z += roll;
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
        	rotate(rotVec.x * 0.1f, rotVec.y * 0.1f, 0.0f);
        }
    }
    
    public Matrix4f getView() {
    	view = new Matrix4f().identity();
    	view.rotate(Math.toRadians(rotation.x), new Vector3f(1.0f, 0.0f, 0.0f))
	    	.rotate(Math.toRadians(rotation.y), new Vector3f(0.0f, 1.0f, 0.0f))
	    	.rotate(Math.toRadians(rotation.z), new Vector3f(0.0f, 0.0f, 1.0f));
    	view.translate(-position.x, -position.y, -position.z);
    	return view;
    }
    
    public Matrix4f getProj() {
    	proj = new Matrix4f().identity();
    	proj.perspective(Math.toRadians(70.0f), (float) 800 / 600, 0.1f, 1000.0f);
    	return proj;
    }
}