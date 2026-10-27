#version %GLver%
// shader: texstyle_area_overdraw_deb

uniform mat4  uVPMat;

in vec4 aPos;         // xy: vertex pos; zw texture coordinates in tile
in vec2 aScaleOffset; // texture coordinates in style-texture

//-- area overdraw debug shader

void main(void)
{
   gl_Position = uVPMat * vec4(aPos.xy, 0.0, 1.0);
}
// file:es30:texstyle_area_overdraw_deb.v.glsl
