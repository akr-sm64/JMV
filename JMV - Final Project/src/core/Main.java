package core;

import org.lwjgl.Version;
import imgui.ImGui;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello LWJGL: " + Version.getVersion());
		System.out.println("Hello ImGui: " + ImGui.getVersion());
	}

}
