// simple shader processing vertex attributes: position & color.

uniform mat4 mvpMat;

attribute vec3 vPos;
attribute vec4 vCol;

varying vec4 color;

void main(void)
{
   gl_Position = mvpMat * vec4( vPos, 1.0);

   color= vCol;
}
// file:vert_col.v.glsl
