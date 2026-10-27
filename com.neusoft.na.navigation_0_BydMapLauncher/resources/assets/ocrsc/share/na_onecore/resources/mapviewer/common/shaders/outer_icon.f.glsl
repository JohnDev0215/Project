#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;

uniform sampler2D outerIconMap;

void main(void)
{
   vec4 col = texture2D(outerIconMap, oTex);
   gl_FragColor = col;
}
// file:outer_icon.f.glsl
