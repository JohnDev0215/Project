
attribute vec2 vPos;
attribute vec4 vCol;

uniform vec2 invScreenSize;

varying vec4 oCol;

void main(void)
{
   vec2 pos = vec2(2.0 * vPos.x * invScreenSize.x - 1.0, 1.0 - 2.0 * vPos.y * invScreenSize.y);
   gl_Position = vec4(pos, 0.0, 1.0);

   oCol = vCol;
}
