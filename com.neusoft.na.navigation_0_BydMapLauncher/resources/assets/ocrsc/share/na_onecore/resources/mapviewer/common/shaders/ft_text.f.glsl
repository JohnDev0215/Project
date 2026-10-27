
#ifdef GL_ES
precision mediump float;
#endif

varying vec2 vTexCoord;

uniform vec4 uTextColor;
uniform float uGlobalLabelingOpacity;

uniform sampler2D sGlyphMap;

void main()
{
   float sampledAlpha = texture2D(sGlyphMap, vTexCoord).r * uGlobalLabelingOpacity;
   gl_FragColor = vec4(uTextColor.rgb, uTextColor.a * sampledAlpha);
}

// file:ft_text.f.glsl
