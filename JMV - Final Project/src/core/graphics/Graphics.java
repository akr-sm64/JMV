package core.graphics;

import java.util.Collection;
import java.util.List;

import core.graphics.glObjects.Shader;
import core.graphics.model.Entity;
import core.graphics.model.Mesh;
import core.graphics.model.Model;
import core.graphics.model.ModelLoader;
import core.graphics.model.texture.Material;
import core.graphics.model.texture.Texture;
import core.graphics.model.texture.TextureCache;

import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
 * The Graphics class will contain all the graphics related operations.<br>
 * It will set the graphics pipeline up (shaders, VAO, VBO and EBO).
 * @author akr_sm64
 */

public class Graphics {
	private Shader shader;
	private Scene scene;
	private Model model;
	private Entity entity;
	private Camera camera;
	private TextureCache textureCache;
	
	/**
	 * This method will initialize the shaders and shader program. <br>
	 * It will then take care of the VAO, VBO and EBO and gets everything ready.
	 */
	
	public void init() {
		shader = new Shader();
		shader.create();
		scene = new Scene();
		textureCache = scene.getTextureCache();
		model = ModelLoader.loadModel("3d-model", "./resources/models/Falco/PlyFalco.dae", textureCache);
		entity = new Entity("model-ID", model.getId());
		camera = new Camera();
		scene.addModel(model);
		scene.addEntity(entity);
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

        shader.uploadMat4f("proj", camera.getProj());
        shader.uploadMat4f("view", camera.getView());
        shader.uploadTexture("TEX_SAMPLER", 0);
        
        camera.input();
        entity.update();

        Collection<Model> models = scene.getModelMap().values();
        TextureCache textureCache = scene.getTextureCache();
        for (Model model : models) {
            List<Entity> entities = model.getEntitiesList();

            for (Material material : model.getMaterialList()) {
                shader.uploadVec4f("material.diffuse", material.getDiffuseColor());
                Texture texture = textureCache.getTexture(material.getTexturePath());
                texture.setActive();
                texture.bind();

                for (Mesh mesh : material.getMeshList()) {
                    mesh.getVaoId().bind();
                    for (Entity entity : entities) {
                        shader.uploadMat4f("model", entity.getModel());
                        mesh.getEboId().draw(mesh.getNumVertices());
                    }
                }
            }
        }

        glBindVertexArray(0);

        shader.stop();
	}
	
	/**
	 * This method will make the shader visible to other classes.
	 * @return shader.
	 */
	
	public Shader getShader() {
		return shader;
	}
}