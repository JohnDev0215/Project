uniform mat4  vpMat;
uniform vec2  uDisplaySize;

attribute vec2 vPos;
attribute vec4 vIcon; // xy: side offset, zw: texture-coordinate

varying vec2 oTex;

void main(void)
{
   vec4 pos = vpMat * vec4( vPos.xy, 0.0, 1.0);
   vec2 toNDC = vec2( 2.0 * pos.w) / uDisplaySize; // fator converting to normalized display coordinates
   pos.xy += vIcon.xy * toNDC;
   gl_Position = pos;

   oTex = vIcon.zw;
}
// file:static_icon.v.glsl
