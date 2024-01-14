package core.graphics.model;

import core.graphics.glObjects.EBO;
import core.graphics.glObjects.VAO;
import core.graphics.glObjects.VBO;
import util.UtilFunctions;

import java.nio.*;
import java.util.*;

import static org.lwjgl.opengl.GL30.*;

/**
 * The Mesh class will manage the creation of vertices.
 * @author akr_sm64
 */

public class Mesh {

    private int numVertices;
    private VAO vaoId;
    private List<VBO> vboIdList;
    private EBO eboId;

    /**
     * The constructor will create a VAO, VBO and EBO to store the vertices for rendering.
     * @param positions  the vertex positions.
     * @param texCoords  the texture coordinates.
     * @param indices  the indices.
     */
    
	public Mesh(float[] positions, float[] texCoords, int[] indices) {
		numVertices = indices.length;
		vboIdList = new ArrayList<>();

		vaoId = new VAO();
		vaoId.bind();

		// Positions VBO
		VBO vboId = new VBO();
		vboIdList.add(vboId);
		FloatBuffer positionsBuffer = UtilFunctions.createFloatBuffer(positions);
		vboId.bind(positionsBuffer);
		vaoId.setAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

		// Texture coordinates VBO
		vboId = new VBO();
		vboIdList.add(vboId);
		FloatBuffer texCoordsBuffer = UtilFunctions.createFloatBuffer(texCoords);
		vboId.bind(texCoordsBuffer);
		vaoId.setAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

		// Index VBO
		eboId = new EBO();
		IntBuffer indicesBuffer = UtilFunctions.createIntBuffer(indices);
		indicesBuffer.put(0, indices);
		eboId.bind(indicesBuffer);

		vboId.unbind();
		vaoId.unbind();
	}
	
	/**
     * Getter for numVertices.
     * @return numVertices.
     */

    public int getNumVertices() {
        return numVertices;
    }
    
    /**
     * Getter for VAO.
     * @return vaoId.
     */

    public final VAO getVaoId() {
        return vaoId;
    }
    
    /**
     * Getter for EBO.
     * @return eboId.
     */

	public EBO getEboId() {
		return eboId;
	}
}