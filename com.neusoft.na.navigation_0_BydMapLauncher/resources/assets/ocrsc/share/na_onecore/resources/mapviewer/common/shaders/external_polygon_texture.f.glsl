#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D sTexture;

varying vec4 vTileMeta;  // xy: start offset, zw: effective tile size
varying vec2 vTexUV;

void main(void)
{
   vec2 TexturePosition= vTileMeta.xy + fract(vTexUV) * vTileMeta.zw;

   gl_FragColor= texture2D(sTexture, TexturePosition);
}
