#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D IconAtlas;

varying vec2 oTex;

void main(void)
{
   vec4 texel = texture2D( IconAtlas, oTex);
   gl_FragColor = texel;
//      gl_FragColor = mix( vec4( 1.0, 0.0, 1.0, 1.0), vec4( texel.rgb, 1.0), 0.8);
//   gl_FragColor = vec4( 1.0, 0.0, 1.0, 0.5);
}
// file:static_icon.f.glsl
