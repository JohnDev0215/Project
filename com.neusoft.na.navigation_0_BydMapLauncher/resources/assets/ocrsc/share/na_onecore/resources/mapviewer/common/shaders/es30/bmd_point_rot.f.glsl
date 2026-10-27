#version %GLver%
// #ifndef GL_ES
// #version 330
// #else
// #version 300 es
// #endif
precision mediump float;

in vec2 oTex;

uniform sampler2D bmdAtlas;

out vec4 fragmentColor;

void main(void)
{
   vec4 col = texture(bmdAtlas, oTex);
   //gl_FragColor = vec4(oTex,0.0,1.0);
   fragmentColor = col;
}

// file:es30:bmd_point_rot.f.glsl
