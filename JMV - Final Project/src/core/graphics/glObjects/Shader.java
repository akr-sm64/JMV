package core.graphics.glObjects;

import util.UtilFunctions;

import java.nio.FloatBuffer;

import org.joml.*;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL20.*;

/**
 * The Shader class will manage the shader methods for the program. <br>
 * It will be initializing the vertex shader, fragment shader and the shader program. <br>
 * @author akr_sm64
 */

public class Shader {
	private String vertexFile = "./resources/shaders/vertex-shader.glsl";
	private String fragmentFile = "./resources/shaders/fragment-shader.glsl";
	
	private int shaderProgram, vertexShader, fragmentShader; // Useful instance variables.

	/**
	 * This is an empty Shader constructor.
	 */
	
	public Shader() {}
	
	/**
	 * This constructor will set the shader files to whatever is given.
	 * @param vertexFile  the vertex shader.
	 * @param fragmentFile  the fragment shader.
	 */
	
	public Shader(String vertexFile, String fragmentFile) {
		this.vertexFile = vertexFile;
		this.fragmentFile = fragmentFile;
	}
	
	/**
	 * Loads a shader using the glsl files created.<br>
	 * Then, it will compile the created shader.
	 * @param type  the shader we want (vertex - fragment - geometry (not doing geometry shader because it's optional)).
	 * @param file  stores the path to the specific glsl file.
	 * @return shader  the target shader we want to create.
	 */
	
	private int loadShader(int type, String file) {
		int shader = glCreateShader(type);
		glShaderSource(shader, UtilFunctions.readFile(file));
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
	 * Use the current shader program.
	 */
	
	public void use() {
		glUseProgram(shaderProgram);
	}
	
	/**
	 * Set the current shader program to nothing.
	 */
	
	public void stop() {
		glUseProgram(0);
	}
	
	/**
	 * This method will stop every operation going on with the shaders.
	 */
	
	public void delete() {
		stop();
		glDetachShader(shaderProgram, vertexShader);
		glDetachShader(shaderProgram, fragmentShader);
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
		glDeleteProgram(shaderProgram);
	}
	
	/**
	 * Getter for shader program.
	 * @return shaderProgram.
	 */
	
	public int getShaderProgram() {
		return this.shaderProgram;
	}
	
	/**
	 * This method will allow passing a uniform Matrix4f variable.
	 * @param varName  the name of the variable.
	 * @param mat4  the Matrix4f we want to pass in.
	 */
	
	public void uploadMat4f(String varName, Matrix4f mat4) {
        int varLocation = glGetUniformLocation(shaderProgram, varName);
        use();
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
        mat4.get(matBuffer);
        glUniformMatrix4fv(varLocation, false, matBuffer);
    }

	/**
	 * This method will allow passing a uniform Matrix3f variable.
	 * @param varName  the name of the variable.
	 * @param mat3  the Matrix3f we want to pass in.
	 */
	
    public void uploadMat3f(String varName, Matrix3f mat3) {
        int varLocation = glGetUniformLocation(shaderProgram, varName);
        use();
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(9);
        mat3.get(matBuffer);
        glUniformMatrix3fv(varLocation, false, matBuffer);
    }

    /**
	 * This method will allow passing a uniform Vector4f variable.
	 * @param varName  the name of the variable.
	 * @param vec  the Vector4f we want to pass in.
	 */
    
    public void uploadVec4f(String varName, Vector4f vec) {
        int varLocation = glGetUniformLocation(shaderProgram, varName);
        use();
        glUniform4f(varLocation, vec.x, vec.y, vec.z, vec.w);
    }
    
    /**
	 * This method will allow passing a uniform Vector3f variable.
	 * @param varName  the name of the variable.
	 * @param vec  the Vector3f we want to pass in.
	 */

    public void uploadVec3f(String varName, Vector3f vec) {
        int varLocation = glGetUniformLocation(shaderProgram, varName);
        use();
        glUniform3f(varLocation, vec.x, vec.y, vec.z);
    }
    
    /**
	 * This method will allow passing a uniform Vector2f variable.
	 * @param varName  the name of the variable.
	 * @param vec  the Vector2f we want to pass in.
	 */

    public void uploadVec2f(String varName, Vector2f vec) {
        int varLocation = glGetUniformLocation(shaderProgram, varName);
        use();
        glUniform2f(varLocation, vec.x, vec.y);
    }
    
    /**
	 * This method will allow passing a uniform float variable.
	 * @param varName  the name of the variable.
	 * @param val  the float we want to pass in.
	 */

    public void uploadFloat(String varName, float val) {
        int varLocation = glGetUniformLocation(shaderProgram, varName);
        use();
        glUniform1f(varLocation, val);
    }
    
    /**
	 * This method will allow passing a uniform integer variable.
	 * @param varName  the name of the variable.
	 * @param val  the integer we want to pass in.
	 */

    public void uploadInt(String varName, int val) {
        int varLocation = glGetUniformLocation(shaderProgram, varName);
        use();
        glUniform1i(varLocation, val);
    }
    
    /**
	 * This method will allow passing a uniform sampler2D variable for textures.
	 * @param varName  the name of the variable.
	 * @param slot  the texture slot we want to pass in.
	 */

    public void uploadTexture(String varName, int slot) {
        int varLocation = glGetUniformLocation(shaderProgram, varName);
        use();
        glUniform1i(varLocation, slot);
    }
}