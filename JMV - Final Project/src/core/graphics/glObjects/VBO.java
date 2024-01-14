package core.graphics.glObjects;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL30.*;

/**
 * The VBO class will hold all the VBO related methods. <br>
 * For people who may not know, VBO is Vertex Buffer Object. <br>
 * VBO is important for rendering as it allows sending vertex data to the VAO.
 * @author akr_sm64
 */

public class VBO {
	private int vbo;
	
	/**
	 * This constructor will generate a buffer for the VBO.
	 */
	
	public VBO() {
		vbo = glGenBuffers();
	}
	
	/**
	 * Bind will set up the VBO by binding it to an array buffer.<br>
	 * The vertex buffer will the be written to the VBO.
	 * @param vertexBuffer  contains the vertices the way OpenGL likes them.
	 */
	
	public void bind(FloatBuffer vertexBuffer) {
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
	}
	
	/**
	 * It will bind the VBO to 0.
	 */
	
	public void unbind() {
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
}