
uniform mat4 vpMat;

attribute vec3 vPos;
attribute vec2 vTex;

varying vec2 oTex;

void main(void)
{
   oTex = vTex;

   gl_Position = vpMat * vec4(vPos.xyz, 1.0);
}
// file:weather_overlay.v.glsl
