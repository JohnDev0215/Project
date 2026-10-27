#ifdef GL_ES
precision highp float;
#endif

uniform mat4 vpMat;

uniform vec2 uDisplaySize;
uniform float uFlattenDtm;

attribute vec3 vPos;
attribute vec2 vOffset;
attribute vec2 vTex;
attribute vec2 vFlagsize;

varying vec2 oTex;

void main(void)
{
   vec4 posIn = vec4(vPos, 1.0);
   posIn.z = vPos.z * uFlattenDtm;
   vec4 pos = vpMat * posIn;

   vec2 flagSize = vec2(2.0 / uDisplaySize.x * vFlagsize.x, 2.0 / uDisplaySize.y * vFlagsize.y);
   pos.xy += vec2(vOffset.x * pos.w * flagSize.x, vOffset.y * pos.w * flagSize.y);
   gl_Position = pos;

   oTex = vTex;
}
// file:dest_flag.v.glsl
