#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D sampler;

varying vec2 uv;

void main(void)
{
    vec4 color = texture2D( sampler, uv);

    gl_FragColor= color;
}
// file:hud_tex.f.glsl
