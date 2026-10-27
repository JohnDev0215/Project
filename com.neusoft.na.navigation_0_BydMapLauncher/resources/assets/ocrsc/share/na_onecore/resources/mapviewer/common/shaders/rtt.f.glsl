#ifdef GL_ES
    precision mediump float;
#endif

varying vec2 oTex;

uniform sampler2D diffuseMap;

void main(void)
{
   gl_FragColor = vec4(1.0, 0.0, 1.0, 1.0);
   gl_FragColor = vec4(texture2D(diffuseMap, oTex).rgb, 1.0);
}
// file:rtt.f.glsl
