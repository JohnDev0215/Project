uniform mat4 vpMat;

attribute vec3 vPos;
attribute vec3 vNrm;

uniform vec3 camPos;
uniform float scaleFactor;

varying float l;

void main(void)
{
   vec3 pos = vPos - camPos;
   pos.xy *= scaleFactor;

   gl_Position = vpMat * vec4(pos, 1.0);

   l = max(dot(vNrm, vec3(0.7070, 0, 0.707)), 0.3);

}
// file:terrain.v.glsl
