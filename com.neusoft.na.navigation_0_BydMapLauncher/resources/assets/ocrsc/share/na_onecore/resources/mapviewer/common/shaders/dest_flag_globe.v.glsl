#ifdef GL_ES
precision highp float;
#endif

uniform mat4 globeVpMat;

uniform vec2 uDisplaySize;

attribute vec3 vPos;
attribute vec2 vOffset;
attribute vec2 vTex;
attribute vec2 vFlagsize;

varying vec2 oTex;
varying vec4 oPos;

void main(void)
{
   vec4 pos = globeVpMat * vec4(vPos, 1.0);

      vec2 flagSize = vec2(2.0 / uDisplaySize.x * vFlagsize.x, 2.0 / uDisplaySize.y * vFlagsize.y);
   pos.xy += vec2(vOffset.x * pos.w * flagSize.x, vOffset.y * pos.w * flagSize.y);
   gl_Position = pos;

   oTex = vTex;
   oPos = pos;
}
// file:dest_flag.v.glsl
