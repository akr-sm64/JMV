/**
 * This is the fragment shader, it will set up the colors to be output to the screen
 * Author: akr_sm64 (@author is irrelevant in GLSL)
 */

#version 330 core

// Variables coming from the vertex shader.
in vec2 fragTextCoord;

// Variable being output to the graphics pipeline.
out vec4 color;

struct Material { // Data structure named Material.
    vec4 diffuse; // 4 Float vector for diffuse material.
};

// Uniform variables.
uniform sampler2D TEX_SAMPLER;
uniform Material material;

void main() {
    color = texture(TEX_SAMPLER, fragTextCoord) + material.diffuse;
}