#version %GLver%
#ifdef GL_ES
precision mediump float;
#endif

// shader: texstylevs_line_lit.fs

uniform sampler2D areaAtlas;

in vec4  vCol;
in vec3  vLit;
in vec2  vSide;

out vec4 fragmentColor;

void main(void)
{
   float alpha = min( min(vSide.x, 1.0 - (vSide.x - vSide.y)), 1.0);
   fragmentColor = vec4( vCol.rgb * vLit, vCol.a * alpha);
   //fragmentColor = vec4( 1.0, 0.0, 0.0, 1.0);
}
// file:es30:texstylevs_line_lit.f.glsl
