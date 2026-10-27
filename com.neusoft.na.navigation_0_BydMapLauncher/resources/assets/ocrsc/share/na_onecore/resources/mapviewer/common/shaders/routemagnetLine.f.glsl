#ifdef GL_ES
precision mediump float;
#endif

varying vec4 outcolor;

void main(void)
{
   gl_FragColor = outcolor;
}
// file:labelicon.f.glsl
