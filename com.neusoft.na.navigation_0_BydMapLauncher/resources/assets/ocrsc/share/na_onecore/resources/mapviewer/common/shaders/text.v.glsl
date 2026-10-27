uniform mat4 displayMat;

attribute vec4 vPos;
attribute vec2 vTex;
attribute vec4 vCol;
attribute vec4 vOutlineCol;
attribute vec4 vChannel;

varying vec4 oCol;
varying vec4 oOutlineCol;
varying vec2 oTex;
varying vec4 oChannel;
varying float vOpacity;

void main(void)
{
   vec4 tp = displayMat * vec4(vPos.xy, 0.0, 1.0);
   gl_Position = vec4(tp.xy / tp.w, vPos.z, 1.0);

   vOpacity = vPos.w;
   oCol = vCol;
   oOutlineCol = vOutlineCol;
   oTex = vec2(vTex.x, 1.0 - vTex.y);
   oChannel = vChannel;
}
// file:text.v.glsl
