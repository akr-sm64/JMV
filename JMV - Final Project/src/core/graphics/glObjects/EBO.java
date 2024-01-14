package core.graphics.glObjects;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL30.*;

/**
 * The EBO class will hold all the EBO related methods. <br>
 * For people who may not know, EBO is Element Buffer Object. <br>
 * EBO is important for reading index array to draw the vertices to the screen with the given order.
 * @author akr_sm64
 */

public class EBO {
	private int ebo;
	
	/**
	 * This EBO constructor will generate a buffer which can the be used to create the EBO.
	 */
	
	public EBO() {
		ebo = glGenBuffers();
	}
	
	/**
	 * This bind method will bind the EBO to an element array buffer. <br>
	 * Then, the index buffer will be written to the bound EBO.
	 * @param indexBuffer  It contains the arranged indices array to be processed by OpenGL.
	 */
	
	public void bind(IntBuffer indexBuffer) {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
	}
	
	/**
	 * The unbind method does exactly what you think: Free the EBO.
	 */
	
	public void unbind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	/**
	 * The draw method will draw the vertices using the GL_TRIANGLES primitive.
	 * @param indicesSize  stores the indices size.
	 */
	
	public void draw(int indicesSize) {
		glDrawElements(GL_TRIANGLES, indicesSize, GL_UNSIGNED_INT, 0);
	}
}