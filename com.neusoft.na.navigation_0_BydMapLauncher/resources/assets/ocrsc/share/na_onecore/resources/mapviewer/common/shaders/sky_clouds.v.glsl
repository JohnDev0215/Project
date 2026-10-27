
uniform mat4 projMat;
uniform mat4 skyViewMat;

attribute vec3 vPos;
attribute vec2 vTex;

varying vec2 oTex;

void main(void)
{
   oTex = vec2(8.0 * vTex.s, vTex.t);

   mat4 vpm = projMat * skyViewMat;
   vec4 p = vpm * vec4(vPos.xyz, 1.0);

   gl_Position = p.xyww;
}
// file:sky_clouds.v.glsl
