// simple shader processing vertex attributes: position & color.
// the position is given in screen pixel space

attribute vec3 vPos;
attribute vec3 vCol;

uniform vec2 uDisplaySize;

varying vec3 color;

void main(void)
{
   gl_Position = vec4( (vPos.x / uDisplaySize.x) * 2.0 - 1.0, ((uDisplaySize.y - vPos.y) / uDisplaySize.y) * 2.0 - 1.0, 0.0, 1.0);

   color= vCol;
}
// file:scr_col.v.glsl
