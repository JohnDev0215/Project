#ifdef GL_ES
    precision mediump float;
#endif

varying vec2 oTex;

void main(void)
{
   gl_FragColor = vec4(oTex.xy, 1.0, 1.0);
}
// file:rtt_test.f.glsl
