uniform mat4 vpMat;
uniform vec3 camPos;
uniform float scaleFactor;
uniform float cameraDistance;

uniform float uDisplaySize;

attribute vec3 vPos;
attribute vec4 vParams;

varying vec2 oTex;
varying float oOpacity;

void main(void)
{
   float dist = 1.2 * cameraDistance;
   vec4 pos = vpMat * vec4((vPos - camPos) * scaleFactor, 1.0);
   float scale = pos.z <= dist ? 1.0 : max(0.0, 1.0 - (pos.z - dist) / dist);

   pos.xy += vec2(vParams.z * pos.w * scale, vParams.w  * pos.w * scale/ uDisplaySize);

   gl_Position = pos;

   oTex = vParams.xy;
   oOpacity = scale;//1.0 - pos.z / (1.5 * cameraDistance);
}
// file:poi.v.glsl
