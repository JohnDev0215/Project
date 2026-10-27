#version %GLver%
#ifdef GL_ES
precision mediump float;
#endif

// shader: texstyle_area_overdraw_deb.fs

out vec4 vertexColor;

void main(void)
{
   vertexColor = vec4(1.0, 0.0, 0.0, 0.2);
}
// file:es30:texstyle_area_overdraw_deb.f.glsl
