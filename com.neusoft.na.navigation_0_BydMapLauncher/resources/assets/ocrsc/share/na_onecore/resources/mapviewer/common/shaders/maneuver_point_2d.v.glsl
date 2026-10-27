uniform mat4 vpMat;
uniform float uFlattenDtm;
uniform vec2 uDisplaySize;

attribute vec3 vPos;
attribute vec2 vTex;
attribute vec2 vPosOffset;
attribute vec2 vScale;

varying vec2 oTex;

void main(void)
{
   vec4 posIn = vec4(vPos, 1.0);
   posIn.z = vPos.z * uFlattenDtm;
   vec4 pos = vpMat * posIn;
   vec2 maneuverSize = vec2(2.0 / uDisplaySize.x * vScale.x, 2.0 / uDisplaySize.y * vScale.y);
   pos.xy += vec2((vPosOffset.x - 0.5) * pos.w * maneuverSize.x, (vPosOffset.y - 0.5) * pos.w * maneuverSize.y);
   gl_Position = pos;

   oTex = vTex;
}
// file:maneuver_point_2d.v.glsl
