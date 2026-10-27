// simple shader processing vertex attributes: position & color.

attribute vec4 vPos;
attribute vec4 vCol;

uniform vec2 uDisplaySize;

varying vec4 color;

void main(void)
{
   gl_Position = vec4( (vPos.x / uDisplaySize.x) * 2.0 - 1.0, (vPos.y / uDisplaySize.y) * 2.0 - 1.0, 0.0, 1.0);

   color= vCol;
}
// file:hud_col.v.glsl
