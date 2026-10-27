uniform mat4 vpMat;
uniform float uFlattenDtm;
uniform vec2 uDisplaySize;

attribute vec3 vPos;
attribute vec2 vTex;
attribute vec2 vPosOffset;


varying vec2 oTex;

void main(void)
{
   vec4 posIn = vec4(vPos, 1.0);
   posIn.z = vPos.z * uFlattenDtm;
   vec4 pos = vpMat * posIn;
   vec2 kmliconSize = vec2(2.0 / uDisplaySize.x, 2.0 / uDisplaySize.y);
   pos.xy += vec2((vPosOffset.x) * pos.w * kmliconSize.x, (vPosOffset.y) * pos.w * kmliconSize.y);
   gl_Position = pos;

   oTex = vTex;
}
// file:kml_point_2d.v.glsl
