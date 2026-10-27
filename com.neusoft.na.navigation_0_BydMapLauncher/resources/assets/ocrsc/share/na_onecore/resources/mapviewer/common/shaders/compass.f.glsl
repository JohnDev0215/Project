#ifdef GL_ES
precision mediump float;
#endif


varying vec3 vTex;

uniform sampler2D diffuseMap;
uniform float uCompass;

void main(void)
{
   if(uCompass < 0.5)
      discard;

   vec4 col = texture2D(diffuseMap, vTex.xy);
   gl_FragColor = vec4(col.rgb,  col.a);
}
// file:compass.f.glsl
