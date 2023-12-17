package core.graphics;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL30.*;

/**
 * The VAO class will hold all the VAO related methods. <br>
 * For people who may not know, VAO is Vertex Array Object. <br>
 * VAO is important for rendering as it allows mapping vertices to specific attributes.
 * @author akr_sm64
 */

public class VAO {
	private int vao; // The only useful global variable.
	
	/**
	 * The constructor will generate a vertex array which will be used to create a VAO.
	 */
	
	public VAO() {
		vao = glGenVertexArrays();
	}
	
	/**
	 * The bind method will set up the VAO.
	 */
	
	public void bind() {
		glBindVertexArray(vao);
	}
	
	/**
	 * This method will convert a float array into a FloatBuffer.
	 * @param vertices  an array which stores the vertices.
	 * @return vertexBuffer  contains the vertices arranged in a way OpenGL understands.
	 */
	
	public FloatBuffer createFloatBuffer(float[] vertices) {
		FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
		vertexBuffer.put(vertices).flip();
		return vertexBuffer;
	}
	
	/**
	 * The setAttribPointer method will set up all the vertex attributes and enable them.
	 * @param index  the index of the attribute (has to match in the vertex shader).
	 * @param size  the number of values stored in the vertex.
	 * @param type  the data type of the values.
	 * @param normalized  whether the data is normalized or not.
	 * @param stride  the byte offset between consecutive generic vertex attributes. 
	 * @param pointer  the offset of the 1st component of the vertex (read documentation).
	 */
	
	public void setAttribPointer(int index, int size, int type, boolean normalized, int stride, int pointer) {
		glVertexAttribPointer(index, size, type, normalized, stride, pointer);
		enable(index);
	}
	
	/**
	 * It will enable the vertex attribute array at the given index.
	 * @param index  the index of the specific attribute (must match the one in the shader).
	 */
	
	public void enable(int index) {
		glEnableVertexAttribArray(index);
	}
	
	/**
	 * It will disable the vertex attribute array at the given index.
	 * @param index  the index of the specific attribute (must match the one in the shader).
	 */
	
	public void disable(int index) {
		glDisableVertexAttribArray(index);
	}
	
	/**
	 * It will bind the VAO to 0
	 */
	
	public void unbind() {
		glBindVertexArray(0);
	}
}
