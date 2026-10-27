#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D areaAtlas;

uniform vec2  uAtlasTexelDim;
uniform float uLineTileWidth;
uniform float uWidthFactor;

varying vec4 vCol;
varying vec2 vTexTile;
varying vec2 vLineOffset;
varying vec3 vSide;
varying float vLineTexGap;

void main(void)
{
   float alpha = min( min(vSide.x, 1.0 - (vSide.x - vSide.y)), 1.0);

   vec4 color = vec4(0.0, 1.0, 0.0, 1.0);
   float lineTileW = uLineTileWidth;
   float lineTileH = uLineTileWidth / 2.0;
   vec2 texOffsetX = vLineOffset * (0.5 / uWidthFactor) / vSide.y;

   if (vLineTexGap > 0.0)
   {
    texOffsetX.x += vLineTexGap * 0.5;                 // skip 1/2 of texture-spacing for the first element
    float m = mod( texOffsetX.x, vLineTexGap + 1.0);   // normalize the tex-coord to [gap + tex] space
    alpha *= step( vLineTexGap, m);                    // map the ranges as gap:0, tex:1
    alpha *= step( (vLineTexGap + 1.0 - m), texOffsetX.y); // avoid patrly drawn texture tiles at the end
   }
   if (alpha > 0.0)
   {
    vec2 st = vec2( fract(texOffsetX.x), vSide.z); // texture coordinates along the line
    float s = (vTexTile.s * lineTileW + fract(st.s) * (lineTileW - 1.0) + 0.5) * uAtlasTexelDim.x;
    float t = (vTexTile.t * lineTileH + fract(st.t) * (lineTileH - 1.0) + 0.5) * uAtlasTexelDim.y;
    color = texture2D( areaAtlas, vec2(s, t)) * vCol;

    alpha *= color.a;
   }

   gl_FragColor = vec4( color.rgb, alpha);
}
