#ifdef GL_ES
    precision mediump float;
#endif

varying vec2 oTex;

uniform sampler2D diffuseMap;

void main(void)
{
   vec4 texCol = texture2D(diffuseMap, oTex);
   gl_FragColor = texCol;
}
// file:weather_overlay.f.glsl
