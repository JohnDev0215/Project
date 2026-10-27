
uniform mat4 vpMat;

uniform vec3 camPos;
uniform float scaleFactor;

attribute vec3 vPos;
attribute vec2 vTex;

varying vec2 oTex;

void main(void)
{
   oTex = vTex;

   gl_Position = vpMat * vec4(vPos.xyz, 1.0);
   //gl_Position = vec4(vPos, 1.0);
}
// file:rtt.v.glsl
