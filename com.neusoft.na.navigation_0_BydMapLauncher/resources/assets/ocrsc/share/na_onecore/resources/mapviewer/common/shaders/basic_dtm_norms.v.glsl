uniform mat4 vpMat;

attribute vec3 vPos;
attribute vec3 vNrm;
attribute vec4 vCol;

uniform float uHeightFactor;
uniform float uDtmNrmLength;

varying vec4 oCol;

void main(void)
{
   vec3 pos = vPos;
   pos.z *= uHeightFactor;

   vec3 nrm= vNrm * uDtmNrmLength;
   //nrm.xy *= uHeightFactor;

   gl_Position = vpMat * vec4(pos + nrm, 1.0);

   oCol = vCol;
}
// file:basic_dtm_norms.v.glsl
