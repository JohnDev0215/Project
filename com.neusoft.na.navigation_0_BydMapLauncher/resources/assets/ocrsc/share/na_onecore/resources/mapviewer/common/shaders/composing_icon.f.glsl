#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;

uniform sampler2D composingIconMap;

void main(void)
{
   vec4 col = texture2D(composingIconMap, oTex);
   gl_FragColor = col;
}
// file:composing_icon.f.glsl
