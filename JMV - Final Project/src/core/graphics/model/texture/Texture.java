package core.graphics.model.texture;

import org.lwjgl.BufferUtils;

import java.nio.*;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.stb.STBImage.*;

/**
 * The Texture class will take care of loading textures.
 * @author akr_sm64
 */

public class Texture {
    private String filepath;
    private int textureID;

    /**
     * The texture constructor will first generate a texture object and bind it. <br>
     * Next, it will set some parameters for the image. <br>
     * Then, it will set up the dimensions and the color channels to be used with STB image.<br>
     * Afterwards, it checks the first index of the channels. If it's 3, the format is JPG, if it's 4, then the format is PNG.<br>
     * Finally, it will free the image.
     * @param filepath  relative path to the image.
     */
    
    public Texture(String filepath) {
        this.filepath = filepath;

        // Generate texture on GPU
        textureID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureID);

        // Set texture parameters
        // Repeat image in both directions
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        // When stretching the image, pixelate
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        // When shrinking an image, pixelate
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        ByteBuffer image = stbi_load(filepath, width, height, channels, 0);

        if (image != null) {
            if (channels.get(0) == 3) {
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width.get(0), height.get(0),
                        0, GL_RGB, GL_UNSIGNED_BYTE, image);
            } else if (channels.get(0) == 4) {
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0),
                        0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            } else {
                throw new RuntimeException("Error: (Texture) Unknown number of channesl '" + channels.get(0) + "'");
            }
        } else {
            throw new RuntimeException("Error: (Texture) Could not load image '" + filepath + "'");
        }

        stbi_image_free(image);
    }

    /**
     * Binds the texture.
     */
    
    public void bind() {
        glBindTexture(GL_TEXTURE_2D, textureID);
    }
    
    public void cleanup() {
        glDeleteTextures(textureID);
    }

    /**
     * Unbinds the texture.
     */
    
    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
    
    /**
     * Sets the current texture slot as active.
     */
    
    public void setActive() {
    	glActiveTexture(GL_TEXTURE0);
    }
    
    /**
     * Getter for filepath.
     * @return filepath.
     */
    
    public String getFilepath() {
    	return filepath;
    }
}