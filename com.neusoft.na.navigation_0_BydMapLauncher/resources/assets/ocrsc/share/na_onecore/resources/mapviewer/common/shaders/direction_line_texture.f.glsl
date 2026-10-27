#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D areaAtlas;

uniform vec2  uAtlasTexelDim;
uniform vec2 uLineTileWidth;
uniform float uWidthFactor;

varying vec4 vCol;
varying vec2 vTexTile;
varying vec2 vLineOffset;
varying float vLineTexGap;
varying float vWidthFactor;
varying vec2  vTex;
varying float  vLineWidth;

void main(void)
{
   float fx = 1.0 - abs(vTex.x * 2.0 - 1.0);
   float alpha = fx * vTex.y;
   alpha = 1.0 - pow(abs(alpha - 1.0), 16.0);

   vec4 color = vec4(0.0, 1.0, 0.0, 1.0);
   float lineTileW = uLineTileWidth.x;
   float lineTileH = uLineTileWidth.y > 0.0 ? uLineTileWidth.y : 16.0;
   vec2 texOffsetX = vLineOffset * (0.5 / vWidthFactor * 1.0) / vLineWidth;

   if (alpha > 0.0)
   {
    vec2 st = vec2( fract(texOffsetX.x), vTex.x); // texture coordinates along the line
    float s = (vTexTile.s * lineTileW + fract(st.s) * (lineTileW - 1.0) + 0.5) * uAtlasTexelDim.x;
    float t = (vTexTile.t * lineTileH + fract(st.t) * (lineTileH - 1.0) + 0.5) * uAtlasTexelDim.y;
    color = texture2D( areaAtlas, vec2(s, t));

    alpha *= color.a;
   }

   gl_FragColor = vec4( color.rgb, alpha);
}
