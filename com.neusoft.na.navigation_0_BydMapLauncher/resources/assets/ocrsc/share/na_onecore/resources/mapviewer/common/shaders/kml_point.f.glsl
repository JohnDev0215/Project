#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;

uniform sampler2D kmlMap;

void main(void)
{
   vec4 col = texture2D(kmlMap, oTex);
   gl_FragColor = col;
}
// file:kml_point.f.glsl
