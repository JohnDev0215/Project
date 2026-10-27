attribute vec4 aPos;

varying vec2 vTex;

void main(void)
{
   gl_Position = vec4(aPos.xy, 0.0, 1.0);
   vTex = aPos.zw;;
}
// file:convolution.v.glsl
