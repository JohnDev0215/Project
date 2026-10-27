uniform mat4 fogMvpMat;

attribute vec3 vPos;
attribute vec2 vTex;

varying vec2 oTex;

void main(void)
{
   oTex = vTex;
   gl_Position = fogMvpMat * vec4(vPos, 1.0);
}
// file:fog.v.glsl
