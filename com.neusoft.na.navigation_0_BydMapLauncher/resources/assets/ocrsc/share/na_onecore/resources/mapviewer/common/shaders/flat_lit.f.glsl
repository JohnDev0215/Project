#ifdef GL_ES
precision mediump float;
#endif

varying float l;

uniform vec4 color;

void main(void)
{
   //gl_FragColor = vec4(0.44, 0.44, 0.44, 1.0);
   gl_FragColor = vec4(color.rgb * l, color.a);
}
// file:flat_lit.f.glsl
