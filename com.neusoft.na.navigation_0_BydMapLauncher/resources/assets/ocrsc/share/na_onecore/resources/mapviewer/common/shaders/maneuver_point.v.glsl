uniform mat4 vpMat;
uniform float uWidthFactor;
uniform float uPixelFactor;
uniform float uPitch;
uniform float uFlattenDtm;

attribute vec3 vPos;
attribute vec2 vTex;
attribute vec2 vPosOffset;
attribute vec2 vScale;

varying vec2 oTex;

void main(void)
{
   vec4 pos = vec4(vPos, 1.0);
   pos.z = vPos.z * uFlattenDtm;
   float fac = uWidthFactor * uPixelFactor;
   vec2 maneuverSize = vec2(vScale.xy * fac);
   pos = vpMat * vec4(pos.x + (vPosOffset.x - 0.5) * maneuverSize.x, pos.y + (vPosOffset.y - 0.5) * maneuverSize.y, pos.z, pos.w);
   gl_Position = pos;

   oTex = vTex;
}
// file:maneuver_point.v.glsl
