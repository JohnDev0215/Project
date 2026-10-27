#ifdef GL_ES
precision mediump float;
#endif

varying vec4 color;

void main(void)
{
    gl_FragColor= color;
}
// file:hud_col.f.glsl
