package core.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * The Mouse class will take in charge all mouse events.
 * @author akr_sm64
 */

public class Mouse {
	private static Mouse instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastY, lastX;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging; // Useful variables

    /**
     * This constructor will set all the X and Y values to 0.<br>
     * If it's not done, the mouse won't be registered properly.
     */
    
    private Mouse() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    /**
     * Get will create a Mouse instance only if the instance variable is null because this class can only initialized once.
     * @return instance  the Mouse class instance.
     */
    
    public static Mouse get() {
        if (Mouse.instance == null) {
            Mouse.instance = new Mouse();
        }

        return Mouse.instance;
    }
    
    /**
     * This is the mouse position callback which will check for the mouse position.
     * @param window  handle to window.
     * @param xpos  the X position of the mouse.
     * @param ypos  the Y position of the mouse.
     */

    public static void mousePosCallback(long window, double xpos, double ypos) {
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xpos;
        get().yPos = ypos;
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }
    
    /**
     * This is the mouse button callback which will check for whether a mouse button was pressed or released.
     * @param window  handle to window.
     * @param button  the button of the mouse.
     * @param action  the action (pressed - released - repeated).
     * @param mods  if any modifier keys were pressed (ctrl, alt, shift...).
     */

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }
    
    /**
     * This is the mouse button callback which will check for whether a mouse button was pressed or released.
     * @param window  handle to window.
     * @param xOffset  how much the mouse was scrolled in the x-axis.
     * @param yOffset  how much the mouse was scrolled in the x-axis.
     */

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    /**
     * This methods updates the last coordinates of the mouse and resets the scroll to 0.
     */
    
    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }
    
    /**
     * @return the X position.
     */

    public static float getX() {
        return (float)get().xPos;
    }
    
    /**
     * @return the Y position.
     */

    public static float getY() {
        return (float)get().yPos;
    }
    
    /**
     * @return the distance from the last position to the current position in the x-axis.
     */

    public static float getDx() {
        return (float)(get().lastX - get().xPos);
    }

    /**
     * @return the distance from the last position to the current position in the y-axis.
     */
    
    public static float getDy() {
        return (float)(get().lastY - get().yPos);
    }
    
    /**
     * @return the scroll in the x-axis.
     */

    public static float getScrollX() {
        return (float)get().scrollX;
    }
    
    /**
     * @return the scroll in the y-axis.
     */

    public static float getScrollY() {
        return (float)get().scrollY;
    }
    
    /**
     * Check if the mouse is dragging something.
     */

    public static boolean isDragging() {
        return get().isDragging;
    }
    
    /**
     * Check for if any mouse button 
     * @param button  the specific button.
     */

    public static boolean mouseButtonDown(int button) {
        if (button < get().mouseButtonPressed.length) {
            return get().mouseButtonPressed[button];
        } else {
            return false;
        }
    }
}