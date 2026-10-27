#version %GLver%
#ifdef GL_ES
precision mediump float;
#endif

// shader: external_texstylevs_area_lit.fs

in vec4 vCol;

out vec4 fragmentColor;

//-- area shader for untextured, opaque BMD tiles

void main(void)
{
   fragmentColor = vCol;
}
// file:es30:external_texstylevs_area_lit.f.glsl
