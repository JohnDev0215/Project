// simple shader processing vertex attributes: position & color.

attribute vec4 vPos;
attribute vec2 vTex;

uniform vec2 uDisplaySize;

varying vec2 uv;

void main(void)
{
   gl_Position = vec4( (vPos.x / uDisplaySize.x) * 2.0 - 1.0, (vPos.y / uDisplaySize.y) * 2.0 - 1.0, 0.0, 1.0);

   uv = vTex;
}
// file:hud_tex.v.glsl
