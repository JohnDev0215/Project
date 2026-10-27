#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;
varying float vOpacity;

uniform sampler2D TextureAtlas;

void main(void)
{
   vec4 col = texture2D(TextureAtlas, oTex);
   gl_FragColor = vec4(col.rgb, col.a * vOpacity);
}
// file:labelicon.f.glsl
