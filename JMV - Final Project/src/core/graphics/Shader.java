package core.graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.lwjgl.opengl.GL30.*;

/**
 * The Shader class will manage the shader methods for the program. <br>
 * It will be initializing the vertex shader, fragment shader and the shader program <br>
 * @author akr_sm64
 */

public class Shader {
	private String vertexFile = "./resources/shaders/vertex-shader.glsl";
	private String fragmentFile = "./resources/shaders/fragment-shader.glsl";
	
	private int shaderProgram, vertexShader, fragmentShader; // Useful instance variables
	
	/**
	 * This is an empty Shader constructor
	 */
	
	public Shader() {}
	
	/**
	 * This constructor will set the shader files to whatever is given.
	 * @param vertexFile  the vertex shader
	 * @param fragmentFile  the fragment shader
	 */
	
	public Shader(String vertexFile, String fragmentFile) {
		this.vertexFile = vertexFile;
		this.fragmentFile = fragmentFile;
	}
	
	/**
	 * This method reads the contents of the specified file.
	 * @param filepath  stores the path to the shader file.
	 * @return str  contains the shader source code from the glsl file.
	 */
	
	private String readFile(String filepath) {
		String str = "";
		File file = new File(filepath);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (sc.hasNextLine()) {
			str += sc.nextLine() + "\n";
		}
		
		return str;
	}
	
	/**
	 * Loads a shader using the glsl files created.<br>
	 * Then, it will compile the created shader.
	 * @param type  the shader we want (vertex - fragment - geometry (not doing geometry shader because it's optional)).
	 * @param file  stores the path to the specific glsl file.
	 * @return shader  the target shader we want to create
	 */
	
	private int loadShader(int type, String file) {
		int shader = glCreateShader(type);
		glShaderSource(shader, readFile(file));
		glCompileShader(shader);
		
		if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.out.println("Could Not Compile " + file);
			System.out.println(glGetShaderInfoLog(shader));
		}
		
		return shader;
	}
	
	/**
	 * Creates the shader program, loads the shaders and compiles them.<br>
	 * Then, the compiled shaders will be attached to the program and the whole thing will be linked.
	 */
	
	public void create() {
		shaderProgram = glCreateProgram();
		
		vertexShader = loadShader(GL_VERTEX_SHADER, vertexFile);
		fragmentShader = loadShader(GL_FRAGMENT_SHADER, fragmentFile);
		
		glAttachShader(shaderProgram, vertexShader);
		glAttachShader(shaderProgram, fragmentShader);
		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);
	}
	
	/**
	 * Use the current shader program
	 */
	
	public void use() {
		glUseProgram(shaderProgram);
	}
	
	/**
	 * Set the current shader program to nothing
	 */
	
	public void stop() {
		glUseProgram(0);
	}
	
	/**
	 * This method will stop every operation going on with the shaders
	 */
	
	public void delete() {
		stop();
		glDetachShader(shaderProgram, vertexShader);
		glDetachShader(shaderProgram, fragmentShader);
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
		glDeleteProgram(shaderProgram);
	}
}
