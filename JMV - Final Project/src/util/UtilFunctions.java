package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Scanner;

import org.lwjgl.BufferUtils;

/**
 * The UtilFunctions class will contain some methods that can be helpful.
 * @author akr_sm64
 */

public class UtilFunctions {
	
	/**
	 * The createIntBuffer method will take an integer array and convert it to an IntBuffer.
	 * @param indices  an integer array containing indices.
	 * @return IntBuffer  contains an index buffer properly arranged for OpenGL to understand it.
	 */
	
	public IntBuffer createIntBuffer(int[] indices) {
		IntBuffer indexBuffer = BufferUtils.createIntBuffer(indices.length);
		indexBuffer.put(indices).flip();
		return indexBuffer;
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
	 * This method reads the contents of the specified file.
	 * @param filepath  stores the path to the shader file.
	 * @return str  contains the shader source code from the glsl file.
	 */
	
	public String readFile(String filepath) {
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
}
