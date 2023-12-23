package core;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import org.lwjgl.Version;

import core.graphics.Graphics;
import core.input.*;

import imgui.ImGui;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * The Window class will do everything the window needs to load properly.
 * @author akr_sm64
 */

public class Window {
	private long glfwWindow;
	private Graphics gfx;
	
	private int width;
	private int height;
	private String title; // Set up instance variables
	
	/**
	 * This is the default Window constructor. <br>
	 * It sets the window width to a default value of 800. <br>
	 * It sets the window height to a default value of 600. <br>
	 * It sets the window title to a string "JMV - Java Model Viewer".
	 */
	
	public Window() {
		this.width = 800;
		this.height = 600;
		this.title = "JMV - Java Model Viewer";
		this.gfx = new Graphics();
	}
	
	/**
	 * This is the Window constructor that the user can modify and it takes the following parameters: <br>
	 * @param width  sets the window width to the given value.
	 * @param height  sets the window height to the given value.
	 * @param title  sets the window title to the given string.
	 */
	
	public Window(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	/**
	 * The run method will take care of creating the window using GLFW. <br>
	 * It will have the main loop which will do all the rendering. <br>
	 * And finally clean everything up when the program is closed.
	 */
	
	public void run() {
		System.out.println("Hello LWJGL: " + Version.getVersion());
		System.out.println("Hello ImGui: " + ImGui.getVersion());
		init();
		loop();
		
		gfx.getShader().delete();
		
		glfwFreeCallbacks(glfwWindow);
		glfwDestroyWindow(glfwWindow);
		
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	/**
	 * The init method will get GLFW ready to use for the user (it helps a lot).
	 */
	
	public void init() {
		GLFWErrorCallback.createPrint(System.err).set(); // Set error callback to System.err

		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");

		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // Set up some GLFW flags

		// Create the window
		glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
		if ( glfwWindow == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		glfwSetCursorPosCallback(glfwWindow, Mouse::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, Mouse::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, Mouse::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, Keyboard::keyCallback);
		
		glfwMakeContextCurrent(glfwWindow);
		glfwSwapInterval(1);

		glfwShowWindow(glfwWindow);
		
		GL.createCapabilities();
		
		gfx.init();
	}
	
	/**
	 * The loop method will be in charge of updating and rendering to the screen.
	 */

	public void loop() {
		float beginTime = (float)glfwGetTime();
        float endTime;
        float dt = -1.0f;
		
		while ( !glfwWindowShouldClose(glfwWindow) ) {
			glfwPollEvents();
			
			glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			if (dt >= 0) {
                System.out.println("FPS: " + 1 / dt);
                gfx.update();
            }
			
			glfwSwapBuffers(glfwWindow);
			
			endTime = (float)glfwGetTime();
            dt = endTime - beginTime;
            beginTime = endTime;
		}
	}
}