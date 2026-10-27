#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;
varying float opacity;

uniform sampler2D TextureAtlas;

void main(void)
{
   vec4 col = texture2D(TextureAtlas, oTex);
   gl_FragColor = vec4(col.rgb, col.a * opacity);
   //gl_FragColor = vec4(0.9, 0.04, 0.14, 0.6);
}
// file:labelicon.f.glsl
