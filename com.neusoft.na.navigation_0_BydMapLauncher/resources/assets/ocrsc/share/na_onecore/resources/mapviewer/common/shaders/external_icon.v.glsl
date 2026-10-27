
attribute vec3 vPos;
attribute vec2 vTex;

uniform vec2 uDisplaySize;

varying vec2 oTex;
varying float opacity;

void main(void)
{
   vec2 pos = 2.0 * (vPos.xy / uDisplaySize.xy) - vec2(1.0, 1.0);
   pos.y *= -1.0;
   gl_Position = vec4(pos, 0.5, 1.0);
   //oTex = vec2(vTex.x, 1.0 - vTex.y);
   oTex = vTex;
   opacity = vPos.z;
}
// file:labelicon.v.glsl
