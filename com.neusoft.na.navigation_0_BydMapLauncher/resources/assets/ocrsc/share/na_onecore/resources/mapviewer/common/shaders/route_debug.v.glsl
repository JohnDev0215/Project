uniform mat4 vpMat;

attribute vec3 vPos;
attribute vec4 vCol;

varying vec4 oCol;

void main(void)
{
   gl_Position = vpMat * vec4(vPos, 1.0);

   oCol = vCol;
}
