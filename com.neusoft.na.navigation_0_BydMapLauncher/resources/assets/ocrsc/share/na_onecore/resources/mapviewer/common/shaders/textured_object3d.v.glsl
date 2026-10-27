uniform mat4 vpMat;

attribute vec3 vPos;
attribute vec3 vNrm;
attribute vec2 vTex;
attribute vec4 vTexParams;

uniform vec3 camPos;
uniform float scaleFactor;

varying vec3 oTex;
varying vec4 oTexParams;

void main(void)
{
   vec3 pos = vPos - camPos;
   pos *= scaleFactor;
   pos.z *= 1.5;

   gl_Position = vpMat * vec4(pos, 1.0);

   float l = max(dot(vNrm, vec3(-0.5773, 0.5773, -0.5773)), 0.7);

   oTex = vec3(vTex, l);
   oTexParams = vTexParams;
}
// file:textured_object3d.v.glsl
