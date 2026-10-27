uniform mat4 vpMat;

attribute vec3 vPos;
attribute vec4 vParam;     // xy = nrm, z = half width
attribute vec4 vCol;

uniform vec3 camPos;
uniform float scaleFactor;
uniform float uWidthFactor;
uniform float width;

varying vec3 oCol;
//varying vec2 oTex;
varying vec2 vSide;  // x: (linewidth + 1) * normal-side-factor; y: line-width

void main(void)
{
   vec3 pos = vPos - camPos;
   pos *= scaleFactor;
   pos += uWidthFactor * vec3(vParam.xy, 0.0);
   //pos += vec3(0.0, 5000.0, 0.0);

   gl_Position = vpMat * vec4(pos, 1.0);
   oCol = vCol.rgb;
//   oTex = vParam.zw;
   vSide = vec2(vParam.z * (width + 1.0), width);
}
// file:route.v.glsl
