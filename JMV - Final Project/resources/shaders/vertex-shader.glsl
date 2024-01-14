/**
 * This is the vertex shader, it will set up the vertices to be output to the screen
 * Author: akr_sm64 (@author is irrelevant in GLSL)
 */

#version 330 core // Version

// Layout variables are for setting up the vertex attributes.
layout (location=0) in vec3 pos;
layout (location=1) in vec2 texCoord;

// Variables which will be output to the fragment shader.
out vec2 fragTextCoord;

// Uniform variables for projection, view and model matrices.
uniform mat4 proj;
uniform mat4 view;
uniform mat4 model;

void main()
{
    gl_Position = proj * view * model * vec4(pos, 1.0); // check https://www.khronos.org/opengl/wiki/Core_Language_(GLSL)
    fragTextCoord = texCoord;
}