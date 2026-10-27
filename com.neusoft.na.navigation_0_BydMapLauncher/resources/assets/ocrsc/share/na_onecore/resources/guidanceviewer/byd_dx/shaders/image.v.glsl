
attribute vec2 vPos;
attribute vec2 vTex;

uniform float rotationAngle;
uniform vec2 rotationOffset;
uniform vec2 invScreenSize;

varying vec2 oTex;

void main(void)
{
   mat2 rotMat = mat2(cos(rotationAngle), sin(rotationAngle), -sin(rotationAngle), cos(rotationAngle));
   vec2 pos = rotMat * (vPos - rotationOffset) + rotationOffset;
   pos = vec2(2.0 * pos.x * invScreenSize.x - 1.0, 1.0 - 2.0 * pos.y * invScreenSize.y);
   gl_Position = vec4(pos, 0.0, 1.0);

   oTex = vTex;
}
