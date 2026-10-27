
attribute vec4 aPos;

varying vec2 vTexCoord;

void main(void)
{
   gl_Position = vec4(aPos.xy, 0.0, 1.0);
   vTexCoord = aPos.zw;
}
// file:fullscreen_preview.v.glsl
