#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D LabelCacheAtlas;

varying vec2 vTex;
varying float vOpacity;

void main(void)
{
   vec4 texel = texture2D( LabelCacheAtlas, vTex);
   gl_FragColor = vec4( texel.rgb, texel.a * vOpacity);
//      gl_FragColor = mix( vec4( 1.0, 0.0, 1.0, 1.0), vec4( texel.rgb, 1.0), 0.8);
//   gl_FragColor = vec4( 1.0, 0.0, 1.0, 0.5);
}
// file:label_cache.f.glsl
