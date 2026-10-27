#ifdef GL_ES
    precision mediump float;
#endif

varying vec2 oTex;
varying vec4 oCol;

uniform sampler2D flaresMap;

void main(void)
{
   gl_FragColor = vec4(texture2D(flaresMap, oTex).r * oCol.a * oCol.rgb, 1.0);
}
// file:lensflares.f.glsl
