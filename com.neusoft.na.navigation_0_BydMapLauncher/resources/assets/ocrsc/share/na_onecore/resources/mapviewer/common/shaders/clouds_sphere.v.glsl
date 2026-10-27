
uniform mat4 globeVpMat;

attribute vec3 vPos;
attribute vec2 vTex;

varying vec2 oTex;

void main(void)
{
   oTex = vTex;
   gl_Position = globeVpMat * vec4(vPos, 1.0);
}
// file:clouds_sphere.v.glsl
