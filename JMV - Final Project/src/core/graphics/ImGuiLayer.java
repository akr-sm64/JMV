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

public class ImGuiLayer {
	private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();
    private final String DEFAULT_MODEL_DIR = "./resources/models/Pyramid/pyramid.obj";

    private String glslVersion = "#version 330 core";
    
    public void init(long windowPtr) {
    	initImGui();
    	imGuiGlfw.init(windowPtr, true);
    	imGuiGl3.init(glslVersion);
    }
    
    public void destroy() {
    	imGuiGl3.dispose();
    	imGuiGlfw.dispose();
    	ImGui.destroyContext();
    }
    
    private void initImGui() {
    	ImGui.createContext();
    	ImGuiIO io = ImGui.getIO();
    	io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);
    	initFont(io);
    }
    
    public void update() {
    	imGuiGlfw.newFrame();
    	ImGui.newFrame();
    	
    	imGui();
    	
    	ImGui.render();
    	imGuiGl3.renderDrawData(ImGui.getDrawData());
    	
    	if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final long backupWindowPtr = glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            glfwMakeContextCurrent(backupWindowPtr);
        }
    }
    
    public void imGui() {
    	ImGui.begin("JMV - Java Model Viewer");
    	if (ImGui.beginTabBar("Tabs", ImGuiTabBarFlags.None)) {
            if (ImGui.beginTabItem("Edit")) {
                ImGui.text("You may change some values here.");
                ImGui.endTabItem();
            }

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
    	
    	if (ImGui.button("Falco")) {
    		Graphics.changeModel("./resources/models/Falco/PlyFalco.dae");
    	}
    	if (ImGui.button("Default (pyramid)")) {
    		Graphics.changeModel(DEFAULT_MODEL_DIR);
    	}
    	
    	ImGui.end();
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
}