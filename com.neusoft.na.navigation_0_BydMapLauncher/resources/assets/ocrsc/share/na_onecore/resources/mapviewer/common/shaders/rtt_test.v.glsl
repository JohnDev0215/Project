
uniform mat4 vpMat;

attribute vec3 vPos;
attribute vec2 vTex;

varying vec2 oTex;

void main(void)
{
   oTex = vTex;

   //gl_Position = vpMat * vec4(vPos.xyz, 1.0);
   gl_Position = vec4(vPos.xy * 0.001 - vec2(0.5, 0.5), vPos.z, 1.0);
   gl_Position = vec4(vPos.xy, vPos.z, 1.0);
}
// file:rtt_test.v.glsl
