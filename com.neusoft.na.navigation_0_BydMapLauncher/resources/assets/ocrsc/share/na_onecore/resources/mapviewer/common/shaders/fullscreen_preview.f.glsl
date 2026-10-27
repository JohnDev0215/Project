
uniform sampler2D previewMap;

varying vec2 vTexCoord;

void main(void)
{
   gl_FragColor = vec4(texture2D(previewMap, vTexCoord).rgb, 1.0);
}
// file:fullscreen_preview.f.glsl
