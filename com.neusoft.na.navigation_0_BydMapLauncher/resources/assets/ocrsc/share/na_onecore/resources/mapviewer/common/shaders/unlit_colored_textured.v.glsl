uniform mat4 transform;

attribute vec3 vPos;
attribute vec4 vCol;
attribute vec2 vTex;

varying vec4 oCol;
varying vec2 oTex;

void main(void)
{
   gl_Position = transform * vec4(vPos, 1.0);
   oCol = vCol;
   oTex = vTex;
}
// file:unlit_colored_textured.v.glsl
