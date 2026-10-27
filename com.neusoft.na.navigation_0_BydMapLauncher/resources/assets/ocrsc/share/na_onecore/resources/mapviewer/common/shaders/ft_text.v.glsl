uniform mat4 displayMat;

attribute vec4 aPos;
attribute vec2 aTex;

varying vec2 vTexCoord;

void main(void)
{
   vec4 tp = displayMat * vec4(aPos.xy, 0.0, 1.0);
   gl_Position = vec4(tp.xy / tp.w, aPos.z, 1.0);

   vTexCoord = aTex;
}
// file:ft_text.v.glsl
