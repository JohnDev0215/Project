#ifdef GL_ES
precision mediump float;
#endif


varying vec2 vTex;

uniform sampler2D CornerIcon;
uniform float uCompass;

void main(void)
{
   if(uCompass < 0.5)
      discard;

   vec4 col = texture2D(CornerIcon, vTex.xy);
   gl_FragColor = vec4(col.rgb,  col.a);
}
// file:compass_corner.f.glsl
