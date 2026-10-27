uniform mat4  vpMat;
uniform vec2  uDisplaySize;
uniform float uFlattenDtm;
uniform float uExtIconOpacity;  // controls the opacity of the second pass, which renders 3D interfering part (z-fail)
uniform float uScale;

attribute vec3 aPos;
attribute vec4 aIcon; // xy: side offset, zw: texture-coordinate
attribute vec2 aScaleRange;

varying vec2 vTex;
varying float vOpacity;

void main(void)
{
   vec4 pos = vpMat * vec4( aPos.xy, aPos.z * uFlattenDtm, 1.0);
   vec2 toNDC = vec2( 2.0 * pos.w) / uDisplaySize; // fator converting to normalized display coordinates
   pos.xy += aIcon.xy * toNDC;
   if (uScale >= aScaleRange.x && uScale <= aScaleRange.y)
   {
      gl_Position = pos;
   }
   else
   {
      gl_Position = vec4( 0, 0, 0, 1);
   }

   vTex = aIcon.zw;
   vOpacity = 1.0;
}
// file:label_cache.v.glsl
