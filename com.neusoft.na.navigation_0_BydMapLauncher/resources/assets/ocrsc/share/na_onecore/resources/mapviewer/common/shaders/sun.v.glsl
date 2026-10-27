attribute vec3 vPos;
attribute vec2 vTex;

uniform vec4 sunPosDev;
uniform float uAspectRatio;

varying vec4 oTex;

void main(void)
{
   oTex = vec4(vTex, (vTex - vec2(0.5, 0.5)) * 2.0);
   gl_Position = vec4(vPos.x * uAspectRatio + sunPosDev.x, vPos.y + sunPosDev.y, sunPosDev.z, 1.0);
}

// file:sun.v.glsl
