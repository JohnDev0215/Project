uniform mat4 vpMat;

uniform vec3 camPos;
uniform float scaleFactor;

attribute vec3 vPos;

void main(void)
{
   vec3 pos = vPos - camPos;
   pos *= scaleFactor;
   gl_Position = vpMat * vec4(pos, 1.0);
}
// file:tileborder.v.glsl
