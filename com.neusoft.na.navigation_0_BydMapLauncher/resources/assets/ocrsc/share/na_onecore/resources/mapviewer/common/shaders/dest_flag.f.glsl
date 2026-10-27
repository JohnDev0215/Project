#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;

uniform sampler2D flagMap;

void main(void)
{
   vec4 col = texture2D(flagMap, oTex);
   gl_FragColor = col;
}
// file:dest_flag.f.glsl
