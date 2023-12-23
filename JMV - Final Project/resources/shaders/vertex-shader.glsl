/**
 * This is the vertex shader, it will set up the vertices to be output to the screen
 * Author: akr_sm64 (@author is irrelevant in GLSL)
 */

#version 330 core // Version

// Layout variables are for setting up the vertex attributes.
layout (location = 0) in vec3 pos;
layout (location = 1) in vec4 aColor;
layout (location = 2) in vec2 aTexCoords;

// Variables which will be output to the fragment shader.
out vec4 fragColor;
out vec2 fragTexCoords;

void main() {
	fragColor = aColor;
	fragTexCoords = aTexCoords;
    gl_Position = vec4(pos, 1.0); // check https://www.khronos.org/opengl/wiki/Core_Language_(GLSL)
}