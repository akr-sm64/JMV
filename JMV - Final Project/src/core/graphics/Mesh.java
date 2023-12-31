package core.graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import core.graphics.glObjects.EBO;
import core.graphics.glObjects.VAO;
import core.graphics.glObjects.VBO;
import util.UtilFunctions;

import static org.lwjgl.opengl.GL11.GL_FLOAT;

/**
 * The Mesh class will manage the creation of vertices.
 * @author akr_sm64
 */

public class Mesh {
	private float[] vertices = {
		-0.5f, -0.5f,  0.5f,     0.83f, 0.70f, 0.44f, 1.0f,		0.0f, 0.0f,
		-0.5f, -0.5f, -0.5f,     0.83f, 0.70f, 0.44f, 1.0f,		5.0f, 0.0f,
		 0.5f, -0.5f, -0.5f,     0.83f, 0.70f, 0.44f, 1.0f,		0.0f, 0.0f,
		 0.5f, -0.5f,  0.5f,     0.83f, 0.70f, 0.44f, 1.0f,		5.0f, 0.0f,
		 0.0f,  0.5f,  0.0f,     0.92f, 0.86f, 0.76f, 1.0f,		2.5f, 5.0f
	}; // Vertices array
		
	private int[] indices = {
		0, 1, 2,
		0, 2, 3,
		0, 1, 4,
		1, 2, 4,
		2, 3, 4,
		3, 0, 4
	}; // Indices array
	
	private VAO vaoID;
	private VBO vboID;
	private EBO eboID; // Set up some useful variables.
	
	private int posSize = 3;
	private int colorSize = 4;
	private int uvSize = 2;
	private int vertexSizeBytes = (posSize + colorSize + uvSize) * Float.BYTES; // Same as above.
	
	/**
	 * The mesh constructor will create the VAO, VBO and EBO. <br>
	 * Then, it will bind them and set attribute pointers for the position, color and texture.
	 */
	
	public Mesh() {
		vaoID = new VAO();
		vaoID.bind();
		FloatBuffer vertexBuffer = UtilFunctions.createFloatBuffer(vertices);
		
		vboID = new VBO();
		vboID.bind(vertexBuffer);
		
		eboID = new EBO();
		IntBuffer indexBuffer = UtilFunctions.createIntBuffer(indices);
		eboID.bind(indexBuffer);
		
		vaoID.setAttribPointer(0, posSize, GL_FLOAT, false, vertexSizeBytes, 0);
		vaoID.setAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, posSize * Float.BYTES);
		vaoID.setAttribPointer(2, uvSize, GL_FLOAT, false, vertexSizeBytes, (posSize + colorSize) * Float.BYTES);
	}
		
	/**
	 * The update method will update the openGL objects.
	 */
	
	public void update() {
		vaoID.bind();
		
		vaoID.enable(0);
		vaoID.enable(1);
		vaoID.enable(2);
		
		eboID.draw(indices);
		
		vaoID.disable(0);
		vaoID.disable(1);
		vaoID.disable(2);
		
		vaoID.unbind();
	}
}