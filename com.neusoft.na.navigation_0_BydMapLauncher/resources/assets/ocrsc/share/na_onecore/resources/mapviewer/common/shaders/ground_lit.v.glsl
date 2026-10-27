// simple shader processing vertex attributes: position & color.
// the anchor point is fixed to the (0,0), so the zeroPosViewMatrix.

uniform mat4 projMat;
uniform mat4 zeroPosViewMat;

uniform vec3 uSunLightDirection;

attribute vec3 vPos;
attribute vec3 vCol;

varying vec3 color;
varying float nDotL;

void main(void)
{
   vec4 viewVert = zeroPosViewMat * vec4(vPos.xy, 0.0, 1.0);
   // expand "lower" corners of trapezoid in view space to overcome precision issues
   viewVert.xy *= vPos.z > 0.0 ? 1.5 : 1.0;
   gl_Position = projMat * vec4( viewVert.xyz, 1.0);


   nDotL = max( uSunLightDirection.z, 0.0);

   color= vCol;
}
// file:ground_lit.v.glsl
