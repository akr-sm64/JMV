/**
 * This is the fragment shader, it will set up the colors to be output to the screen
 * Author: akr_sm64 (@author is irrelevant in GLSL)
 */

#version 330 core

// Variables coming from the vertex shader.
in vec4 fragColor;
in vec2 fragTexCoords;

// Variable being output to the graphics pipeline.
out vec4 color;

uniform sampler2D TEX_SAMPLER; // Uniform variable for texture

void main() {
    color = texture(TEX_SAMPLER, fragTexCoords); // check https://www.khronos.org/opengl/wiki/Core_Language_(GLSL)
}