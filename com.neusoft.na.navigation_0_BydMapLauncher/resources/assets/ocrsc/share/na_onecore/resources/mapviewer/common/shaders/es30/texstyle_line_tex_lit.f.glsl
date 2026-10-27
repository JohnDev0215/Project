#version %GLver%
#ifdef GL_ES
precision mediump float;
#endif

// shader: texstylevs_line_tex.fs

uniform sampler2D areaAtlas;

uniform vec2 uAtlasTexelDim;
uniform vec2 uLineTileWidth;
uniform float uWidthFactor;

in vec4 vCol;
in vec3 vLit;
in vec3 vSide;  // x: linewidth extended by 0.5pixel * normal-side-factor; y: line-width
in vec2 vTexTile;
in vec2 vLineOffset;
in float vLineTexGap;

out vec4 fragmentColor;

void main(void)
{
   float alpha = min( min(vSide.x, 1.0 - (vSide.x - vSide.y)), 1.0);

   vec4 color;
   if(vTexTile.x < 254.5)
   {
      float lineTileW = uLineTileWidth.x;
      float lineTileH = uLineTileWidth.y > 0.0 ? uLineTileWidth.y : 16.0;
      vec2 texOffsetX = vLineOffset * (0.5 / uWidthFactor) / vSide.y;
      if (vLineTexGap > 0.0)
      {
         texOffsetX.x += vLineTexGap * 0.5;                 // skip 1/2 of texture-spacing for the first element
         float m = mod( texOffsetX.x, vLineTexGap + 1.0);   // normalize the tex-coord to [gap + tex] space
         alpha *= step( vLineTexGap, m);                    // map the ranges as gap:0, tex:1
         alpha *= step( (vLineTexGap + 1.0 - m), texOffsetX.y); // avoid partly drawn texture tiles at the end
      }
      if (alpha > 0.0)
      {
         vec2 st = vec2( fract(texOffsetX.x), vSide.z); // texture coordinates along the line
         float s = (vTexTile.s * lineTileW + fract(st.s) * (lineTileW - 1.0) + 0.5) * uAtlasTexelDim.x;
         float t = (vTexTile.t * lineTileH + fract(st.t) * (lineTileH - 1.0) + 0.5) * uAtlasTexelDim.y;
         color = texture( areaAtlas, vec2(s, t)) * vCol;
         alpha *= color.a;
      }
   }
   else
   {
      color = vCol;
      alpha *= vCol.a;
   }

   fragmentColor = vec4( color.rgb * vLit, alpha);
}
// file:texstylevs_line_tex_lit.f.glsl
