
attribute vec3 vPos;
attribute vec4 aParam;     // xy = nrm

uniform vec2 uDisplaySize;
uniform vec4 linecolor;
uniform float linewidth;

varying vec4 outcolor;

void main(void)
{
   vec2 pos = vPos.xy + aParam.xy*linewidth;
   pos = 2.0 * (pos.xy / uDisplaySize.xy) - vec2(1.0, 1.0);
   pos.y *= -1.0;
   gl_Position = vec4(pos, 0.5, 1.0);

   outcolor = linecolor;
}
// file:labelicon.v.glsl
