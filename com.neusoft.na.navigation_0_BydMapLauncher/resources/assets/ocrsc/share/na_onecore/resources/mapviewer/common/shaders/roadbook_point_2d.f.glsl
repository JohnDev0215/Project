#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;

uniform sampler2D roadbookMap;

void main(void)
{
   vec4 col = texture2D(roadbookMap, oTex);
   gl_FragColor = col;
}
// file:roadbook_point.f.glsl
