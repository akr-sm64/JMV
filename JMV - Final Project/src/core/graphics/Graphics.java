package core.graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL30.*;

/**
 * The Graphics class will contain all the graphics related operations.<br>
 * It will set the graphics pipeline up (shaders, VAO, VBO and EBO).
 * @author akr_sm64
 */

public class Graphics {
	private float[] vertices = {
		-0.5f, -0.5f, 0.0f,		1.0f, 0.0f, 0.0f, 1.0f,
		0.5f, -0.5f, 0.0f,		0.0f, 1.0f, 0.0f, 1.0f,
		0.0f,  0.5f, 0.0f,		0.0f, 0.0f, 1.0f, 1.0f
	}; // Vertices array
	
	private int[] indices = {
		0, 1, 2
	}; // Indices array
	
	private Shader shader;
	private VAO vaoID;
	private VBO vboID;
	private EBO eboID; // Set up some useful variables.
	
	private int posSize = 3;
	private int colorSize = 4;
	private int floatSizeBytes = 4;
	private int vertexSizeBytes = (posSize + colorSize) * floatSizeBytes; // Same as above.
	
	/**
	 * This method will initialize the shaders and shader program. <br>
	 * It will then take care of the VAO, VBO and EBO and gets everything ready.
	 */
	
	public void init() {
		shader = new Shader();
		shader.create();
		
		vaoID = new VAO();
		vaoID.bind();
		FloatBuffer vertexBuffer = vaoID.createFloatBuffer(vertices);
		
		vboID = new VBO();
		vboID.bind(vertexBuffer);
		
		eboID = new EBO();
		IntBuffer indexBuffer = eboID.createIntBuffer(indices);
		eboID.bind(indexBuffer);
		
		vaoID.setAttribPointer(0, posSize, GL_FLOAT, false, vertexSizeBytes, 0);
		vaoID.setAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, posSize * floatSizeBytes);
	}
	
	/**
	 * The update function will be calling the shader program.<br>
	 * It will enable the vertex attribute array for both vertices and colors.<br>
	 * Then, the draw function is called for rendering everything to the screen.<br>
	 * It will finally free all the resources called previously for resource saving and memory economy.
	 */
	
	public void update() {
		shader.use();
		vaoID.bind();
		
		vaoID.enable(0);
		vaoID.enable(1);
		
		eboID.draw(indices);
		
		vaoID.disable(0);
		vaoID.disable(1);
		vaoID.unbind();
		
		shader.stop();
	}
	
	/**
	 * This method will make the shader visible to other classes.
	 * @return shader the instance variable for the Shader class.
	 */
	
	public Shader getShader() {
		return shader;
	}
}
