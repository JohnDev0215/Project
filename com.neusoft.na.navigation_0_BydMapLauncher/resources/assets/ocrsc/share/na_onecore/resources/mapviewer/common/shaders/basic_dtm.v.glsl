#ifdef GL_ES
precision highp float;
#endif

uniform mat4 vpMat;
uniform mat4 viewMat;

attribute vec3 aPos;
attribute vec3 aNrm;
attribute vec2 aTex;

uniform vec3 uSunLightDirection;
uniform float uHeightFactor;
uniform float uFlattenDtm;
uniform float uCameraFar;
uniform vec2 uDtmSubTexScale;
uniform vec2 uDtmSubTexOffset;

varying vec2 vTex;
varying vec3 vNrm;
varying vec3 vSun;
//varying float vNdotL;
varying float vLinearDepth;

void main(void)
{
   vec3 pos = aPos;
   pos.z *= uHeightFactor * uFlattenDtm;

   gl_Position = vpMat * vec4(pos, 1.0);
   vNrm= aNrm;
   vSun= uSunLightDirection;
   if (vSun.z < 0.0)
      vSun.z= -vSun.z;

   vTex= (aTex - uDtmSubTexOffset) * uDtmSubTexScale;

   vec4 viewPos= viewMat * vec4(pos, 1.0);
   vLinearDepth= -viewPos.z / uCameraFar;
}
// file:basic_dtm.v.glsl
