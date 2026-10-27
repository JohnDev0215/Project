#ifdef GL_ES
    precision mediump float;
#endif

uniform float fogAlpha;
varying vec2 oTex;
uniform sampler2D texFog;

void main(void)
{
   gl_FragColor = vec4(texture2D(texFog, oTex).rgb, fogAlpha);
}

// file:fog.f.glsl
