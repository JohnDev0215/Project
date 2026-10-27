// simple shader processing vertex attributes: position & color.
// the anchor point is fixed to the (0,0), so the zeroPosViewMatrix.

uniform mat4 projMat;
uniform mat4 zeroPosViewMat;

attribute vec3 vPos;
attribute vec3 vCol;

varying vec3 color;

void main(void)
{
   vec4 viewVert = zeroPosViewMat * vec4(vPos, 1.0);
   gl_Position = projMat * vec4( viewVert.xyz, 1.0);

   color= vCol;
}
// file:ground.v.glsl
