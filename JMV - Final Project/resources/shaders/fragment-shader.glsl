/**
 * This is the fragment shader, it will set up the colors to be output to the screen
 * Author: akr_sm64 (@author is irrelevant in GLSL)
 */

#version 330 core

// Variable coming from the vertex shader.
in vec4 fragColor;

// Variable being output to the graphics pipeline.
out vec4 color;

void main() {
    color = fragColor;
}