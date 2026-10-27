#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;

uniform sampler2D maneuverMap;

void main(void)
{
   vec4 col = texture2D(maneuverMap, oTex);
   gl_FragColor = col;
}
// file:maneuver_point.f.glsl
