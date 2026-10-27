uniform mat4 displayMat;

attribute vec2 vPos;
attribute vec2 vTex;
attribute vec4 vCol;
attribute vec4 vOutlineCol;
attribute vec4 vChannel;

varying vec4 oCol;
varying vec4 oOutlineCol;
varying vec2 oTex;
varying vec4 oChannel;

void main(void)
{
   gl_Position = displayMat * vec4(vPos.xy, 0.0, 1.0);
   oCol = vCol;
   oOutlineCol = vOutlineCol;
   oTex = vec2(vTex.x, 1.0 - vTex.y);
   oChannel = vChannel;
}
// file:rotatingtext.v.glsl
