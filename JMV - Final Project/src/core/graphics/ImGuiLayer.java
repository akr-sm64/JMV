package core.graphics;

import imgui.ImFontAtlas;
import imgui.ImFontConfig;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiTabBarFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;

import static org.lwjgl.glfw.GLFW.*;

/**
 * The ImGuiLayer class will implement all requirements for ImGui to work.
 * @author akr_sm64.
 */

public class ImGuiLayer {
	private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();
    private final String DEFAULT_MODEL_DIR = "./resources/models/Pyramid/pyramid.obj";

    private String glslVersion = "#version 330 core";
    
    /**
     * The init method will get ImGui ready to be used.
     * @param windowPtr  the glfwWindow pointer.
     */
    
    public void init(long windowPtr) {
    	initImGui();
    	imGuiGlfw.init(windowPtr, true);
    	imGuiGl3.init(glslVersion);
    }
    
    /**
     * The destroy method will free all memory used by ImGui.
     */
    
    public void destroy() {
    	imGuiGl3.dispose();
    	imGuiGlfw.dispose();
    	ImGui.destroyContext();
    }
    
    /**
     * This method will create a new context (window) as well as fonts.
     */
    
    private void initImGui() {
    	ImGui.createContext();
    	ImGuiIO io = ImGui.getIO();
    	io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);
    	initFont(io);
    }
    
    /**
     * The update method will update the window and allow them to be drawn and interacted with.
     */
    
    public void update(float dt) {
    	imGuiGlfw.newFrame();
    	ImGui.newFrame();
    	
    	imGui(dt);
    	
    	ImGui.render();
    	imGuiGl3.renderDrawData(ImGui.getDrawData());
    	
    	if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final long backupWindowPtr = glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            glfwMakeContextCurrent(backupWindowPtr);
        }
    }
    
    /**
     * This method will doing the rendering and printing to the screen.
     */
    
    public void imGui(float dt) {
    	ImGui.begin("JMV - Final Project");
    	if (ImGui.beginTabBar("Tabs", ImGuiTabBarFlags.None)) {
            if (ImGui.beginTabItem("Help")) {
                ImGui.text("Here is some info that you should keep track of.\n");
                ImGui.text("Input:\n\n"
                		 + "W - Move the camera forward.\n"
                		 + "A - Move the camera to the left.\n"
                		 + "S - Move the camera backward.\n"
                		 + "D - Move the camera to the right.\n"
                		 + "Left Shift - Move the camera upward.\n"
                		 + "Left Control - Move the camera downward.\n");
                ImGui.text("Up Arrow - Move the model forward.\n"
                		 + "Left Arrow - Move the model to the left.\n"
                		 + "Dow Arrow - Move the model backward.\n"
                		 + "Right Arrow - Move the model to the right.\n"
                		 + "Right Shift - Move the model upward.\n"
                		 + "Right Control - Move the model downward.\n");
                ImGui.text("Numeric Keypad 8 - Rotate the model in a negative \ndirection about the x-axis.\n"
                		 + "Numeric Keypad 2 - Rotate the model in a positive \ndirection about the x-axis.\n"
                		 + "Numeric Keypad 4 - Rotate the model in a negative \ndirection about the y-axis.\n"
                		 + "Numeric Keypad 6 - Rotate the model in a positive \ndirection about the y-axis.\n"
                		 + "Numeric Keypad + - Rotate the model in a negative \ndirection about the z-axis.\n"
                		 + "Numeric Keypad - - Rotate the model in a positive \ndirection about the z-axis.\n\n");
                ImGui.text("Note: You may change the camera's orientation by \ndragging the mouse around :)");
    	    	ImGui.text("\n\nFPS: " + 1 / dt);
                ImGui.endTabItem();
            }

            if (ImGui.beginTabItem("About")) {
                ImGui.text("Author: akr_sm64.\n\n"
                		+ "JMV is a Java Model Viewer (it is not JVM, but JMV) \n"
                		+ "which relies on LWJGL 3.3.3 and ImGui 1.86 by SpaiR. \n\n"
                		+ "So basically, JMV is a program which will prompt the user \n"
                		+ "to select a file name among a list (this list actually \n"
                		+ "represents obj files stored within the workspace). Once \n"
                		+ "the user clicks on one of those, the model they asked \n"
                		+ "for will be rendered to the screen. The user will then \n"
                		+ "be able to move the camera around, rotate it along the \n"
                		+ "x, y and z-axis and same for the model using the GUI or \n"
                		+ "preset key bindings. So far, that is what it will do: \n"
                		+ "Render 3D models and allow the user to move them around.\r\n"
                		+ "\r\n"
                		+ "Hope you will enjoy!");
                ImGui.endTabItem();
            }

            ImGui.endTabBar();
        }
    	ImGui.end();
    	
    	ImGui.begin("Models");
        chooseModel();
       	ImGui.end();
       	
    	ImGui.showDemoWindow();
	}
    
    public void initFont(ImGuiIO io) {
    	final ImFontAtlas fontAtlas = io.getFonts();
        final ImFontConfig fontConfig = new ImFontConfig(); // Natively allocated object, should be explicitly destroyed

        // Glyphs could be added per-font as well as per config used globally like here
        fontConfig.setGlyphRanges(fontAtlas.getGlyphRangesDefault());

        // Fonts merge example
        fontConfig.setMergeMode(false); // When enabled, all fonts added with this config would be merged with the previously added font
        fontConfig.setPixelSnapH(false);

        // Fonts from file/memory example
        // We can add new fonts from the file system
        fontAtlas.addFontFromFileTTF("./resources/fonts/ProggyClean.ttf", 15, fontConfig);
        fontAtlas.build();

        fontConfig.destroy(); // After all fonts were added we don't need this config more
    }
    
    /**
     * This method will manage choosing the models.
     */
    
    public void chooseModel() {
    	if (ImGui.button("Arwing")) {
    		Graphics.changeModel("./resources/models/Arwing/Arwing.obj");
    	}
    	if (ImGui.button("Bowser")) {
    		Graphics.changeModel("./resources/models/Bowser/bowser.obj");
    	}
    	if (ImGui.button("Darth Vader")) {
    		Graphics.changeModel("./resources/models/Darth-Vader/DarthVader.fbx");
    	}
    	if (ImGui.button("Donkey Kong")) {
    		Graphics.changeModel("./resources/models/Donkey-Kong/dk.obj");
    	}
    	if (ImGui.button("Falco")) {
    		Graphics.changeModel("./resources/models/Falco/PlyFalco.dae");
    	}
    	if (ImGui.button("Fox")) {
    		Graphics.changeModel("./resources/models/Fox/fox.obj");
    	}
    	if (ImGui.button("Fox Polygon Fighter")) {
    		Graphics.changeModel("./resources/models/Fox-polygon/fox.obj");
    	}
    	if (ImGui.button("James Bond")) {
    		Graphics.changeModel("./resources/models/James-Bond/scene.gltf");
    	}
    	if (ImGui.button("Luigi")) {
    		Graphics.changeModel("./resources/models/Luigi/luigijp.obj");
    	}
    	if (ImGui.button("Mario")) {
    		Graphics.changeModel("./resources/models/Mario/mariojp.obj");
    	}
    	if (ImGui.button("Metal Mario")) {
    		Graphics.changeModel("./resources/models/Mario/mariomet.obj");
    	}
    	if (ImGui.button("Link")) {
    		Graphics.changeModel("./resources/models/Link/link.fbx");
    	}
    	if (ImGui.button("Master Hand")) {
    		Graphics.changeModel("./resources/models/Master-Hand/hand.obj");
    	}
    	if (ImGui.button("Meowth")) {
    		Graphics.changeModel("./resources/models/Meowth/Meowth.obj");
    	}
    	if (ImGui.button("Nintendo 64 Logo")) {
    		Graphics.changeModel("./resources/models/N64-Logo/N square.obj");
    	}
    	if (ImGui.button("Nintendo DS")) {
    		Graphics.changeModel("./resources/models/Nintendo-DS/ds.obj");
    	}
    	if (ImGui.button("Peppy Hare")) {
    		Graphics.changeModel("./resources/models/PeppyHare/PeppyHare.obj");
    	}
    	if (ImGui.button("Rare Logo")) {
    		Graphics.changeModel("./resources/models/Rare-Logo/Rare Logo.obj");
    	}
    	if (ImGui.button("Rayman")) {
    		Graphics.changeModel("./resources/models/Rayman/rayman.obj");
    	}
    	if (ImGui.button("R.O.B")) {
    		Graphics.changeModel("./resources/models/R.O.B/rob.dae");
    	}
    	if (ImGui.button("Scooby-Doo")) {
    		Graphics.changeModel("./resources/models/Scooby-Doo/Scooby-Doo.obj");
    	}
    	if (ImGui.button("Shaggy Rogers")) {
    		Graphics.changeModel("./resources/models/Shaggy-Rogers/Shaggy Rogers.obj");
    	}
    	if (ImGui.button("Skull Kid")) {
    		Graphics.changeModel("./resources/models/Skull-Kid/skull_kid.dae");
    	}
    	if (ImGui.button("Star Destroyer")) {
    		Graphics.changeModel("./resources/models/Star-Destroyer/ImperialStarDestroyer2.obj");
    	}
    	if (ImGui.button("The Moon")) {
    		Graphics.changeModel("./resources/models/The-Moon/moon.obj");
    	}
    	if (ImGui.button("Default (pyramid)")) {
    		Graphics.changeModel(DEFAULT_MODEL_DIR);
    	}
    }
}