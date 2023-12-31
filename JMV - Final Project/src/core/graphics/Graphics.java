package core.graphics;

import core.graphics.glObjects.Shader;

/**
 * The Graphics class will contain all the graphics related operations.<br>
 * It will set the graphics pipeline up (shaders, VAO, VBO and EBO).
 * @author akr_sm64
 */

public class Graphics {
	private Shader shader;
	private Mesh mesh;
	private Camera camera;
	private Texture texture;
	
	/**
	 * This method will initialize the shaders and shader program. <br>
	 * It will then take care of the VAO, VBO and EBO and gets everything ready.
	 */
	
	public void init() {
		shader = new Shader();
		shader.create();
		mesh = new Mesh();
		camera = new Camera();
		texture = new Texture("./resources/textures/brick.png");
	}
	
	/**
	 * The update function will be calling the shader program.<br>
	 * It will enable the vertex attribute array for both vertices and colors + textures.<br>
	 * Then, the draw function is called for rendering everything to the screen.<br>
	 * It also sends the projection and view matrices to the vertex shader to get the camera working. <br>
	 * It will finally free all the resources called previously for resource saving and memory economy.
	 */
	
	public void update() {
		shader.use();
		shader.uploadTexture("TEX_SAMPLER", 0);

		shader.uploadMat4f("view", camera.getView());
		shader.uploadMat4f("proj", camera.getProj());

		texture.setActive();
		texture.bind();
		
		mesh.update();
		camera.input();
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