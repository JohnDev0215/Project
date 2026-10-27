uniform mat4 uVPMat;

attribute vec3 aPos;
attribute vec4 aParam;     // xy = nrm, z = half width
attribute vec4 aCol;

uniform float uWidthFactor;

uniform float uPixelFactor;
uniform float uPitch;

uniform vec3 uSunLightDirection;

varying vec3 vCol;
varying vec2 vTex;
varying float vNdotL;

void main(void)
{
   vec3 pos = aPos + uPixelFactor * uWidthFactor * vec3(aParam.xy, 0.0);
   gl_Position = uVPMat * vec4(pos, 1.0);

   vNdotL = max(uSunLightDirection.z, 0.0);
   vCol = aCol.rgb;
   vTex = aParam.zw;
}
// file:basic_line_lit.v.glsl
