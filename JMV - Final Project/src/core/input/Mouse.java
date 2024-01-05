package core.input;

import org.joml.Vector2d;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

/**
 * The Mouse class will take in charge all mouse events.
 * @author akr_sm64
 */

public class Mouse {
	private static Vector2d previousPos, currentPos;
	private static Vector2f displVec;
	
	private static boolean inWindow = false,  leftButtonPress = false, rightButtonPress = false; // Useful variables


    /**
     * This constructor will set all vectors to 0.<br>
     * If it's not done, the mouse won't be registered properly.
     */
    
    public Mouse() {
        previousPos = new Vector2d(-1, -1);
        currentPos = new Vector2d(0, 0);
        displVec = new Vector2f();
    }
    
    /**
     * This is the mouse position callback which will check for the mouse position.
     * @param window  handle to window.
     * @param xpos  the X position of the mouse.
     * @param ypos  the Y position of the mouse.
     */

    public static void mousePosCallback(long window, double xpos, double ypos) {
        currentPos.x = xpos;
        currentPos.y = ypos;
    }
    
    /**
     * This is the mouse button callback which will check for whether a mouse button was pressed or released.
     * @param window  handle to window.
     * @param button  the button of the mouse.
     * @param action  the action (pressed - released - repeated).
     * @param mods  if any modifier keys were pressed (ctrl, alt, shift...).
     */

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        leftButtonPress = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS;
        rightButtonPress = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS;
    }
    
    /**
     * Sets the cursor boundary crossing callback of the specified window, which is called when the cursor enters or leaves the content area of the window. (GLFW says so).
     * @param window  the specific window.
     * @param entered  if the cursor is in out out of the window.
     */
    
    public static void mouseCursorEnterCallback(long window, boolean entered) {
    	inWindow = entered;
    }
    
    /**
     * This method will handle the the mouse input (only supports right and left click).
     */
    
    public static void input() {
    	displVec.x = 0;
    	displVec.y = 0;
    	
    	if (previousPos.x > 0 && previousPos.y > 0 && inWindow) {
    		double x = currentPos.x - previousPos.x;
    		double y = currentPos.y - previousPos.y;
    		
    		boolean rotateX = x != 0;
    		boolean rotateY = y != 0;
    		
    		if (rotateX) 
    			displVec.y = (float)x;
    		if (rotateY)
    			displVec.x = (float)y;
    	}
    	
    	previousPos.x = currentPos.x;
    	previousPos.y = currentPos.y;
    }

    /**
     * Getter for DisplVec.
     * @return displVec
     */
    
	public static Vector2f getDisplVec() {
		return displVec;
	}
	
	/**
     * Getter for left click.
     * @return leftButtonPress
     */

	public static boolean isLeftButtonPress() {
		return leftButtonPress;
	}

	/**
     * Getter for right click.
     * @return rightButtonPress
     */
	
	public static boolean isRightButtonPress() {
		return rightButtonPress;
	}
}