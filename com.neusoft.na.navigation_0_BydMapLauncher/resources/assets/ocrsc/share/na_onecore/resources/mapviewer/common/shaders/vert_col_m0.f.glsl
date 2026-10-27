#ifdef GL_ES
precision mediump float;
#endif

varying vec3 color;

void main(void)
{
    gl_FragColor= vec4(color, 1.0);
}
// file:vert_col_m0.f.glsl
