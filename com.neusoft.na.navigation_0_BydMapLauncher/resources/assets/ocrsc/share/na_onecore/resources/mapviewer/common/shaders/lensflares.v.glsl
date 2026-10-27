attribute vec4 vPos;
attribute vec2 vTex;
attribute vec4 vCol;

uniform float unblocked;
uniform vec4 sunPosDev;

uniform float uAspectRatio;

varying vec2 oTex;
varying vec4 oCol;

void main(void)
{
   vec4 start = sunPosDev;

   if (  abs(start.x) < 1.1
      && abs(start.y) < 1.1
      && unblocked > 0.0
      )
   {
      start.xyz -= vPos.w * start.xyz;

      vec2 size = vec2(vPos.x * vPos.z * uAspectRatio, vPos.y * vPos.z);

      gl_Position = vec4(size + start.xy, start.z, 1.0);
      //gl_Position = vec4(size, 0.0, 1.0);

      oCol = vec4(vCol.rgb, unblocked * vCol.a);
      oTex = vTex;
   }
   else
      gl_Position = vec4(-10.0, -10.0, -10.0, 1.0);

}
// file:lensflares.v.glsl
