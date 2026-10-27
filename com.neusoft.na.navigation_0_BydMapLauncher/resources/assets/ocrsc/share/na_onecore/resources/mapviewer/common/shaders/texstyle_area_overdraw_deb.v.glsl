// shader: texstyle_area_overdraw_deb

uniform mat4  uVPMat;

attribute vec2 aPos;
attribute vec2 aScaleOffset; // texture coordinates in style-texture
attribute vec2 aTex;         // texture coordinates in tile

//-- area overdraw debug shader

void main(void)
{
   gl_Position = uVPMat * vec4(aPos.xy, 0.0, 1.0);
}
// file:texstyle_area_overdraw_deb.v.glsl
