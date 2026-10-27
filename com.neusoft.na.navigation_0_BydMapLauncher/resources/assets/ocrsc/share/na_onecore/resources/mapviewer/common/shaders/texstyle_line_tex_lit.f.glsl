#ifdef GL_ES
precision mediump float;
#endif

// shader: texstyle_line_tex.fs

uniform sampler2D areaAtlas;

uniform vec2  uAtlasTexelDim;
uniform vec2  uLineTileWidth;

varying vec4  vCol;
varying vec2  vTexTile;
varying vec2  vLineOffset;
varying vec3  vLit;
varying vec3  vSide;  // x: linewidth extended by 0.5pixel * normal-side-factor; y: line-width
varying vec2  vNormal;
varying float vLineTexGap;
varying float vWidthFactor; // pass uniform as varying to allow different precision definitions in vs & fs
varying float vLineWidth;
varying float vHighAliasing;

void main(void)
{
   %FixFarClip%  // force clipping at the far plane (workaround for Viviante GC2000)
   
   float alpha = min( min(vSide.x, 1.0 - (vSide.x - vSide.y)), 1.0);

   vec4 color;
   if(vTexTile.x < 254.5)
   {
      float lineTileW = uLineTileWidth.x;
      float lineTileH = uLineTileWidth.y > 0.0 ? uLineTileWidth.y : 16.0;
      vec2 texOffsetX = vLineOffset * (0.5 / vWidthFactor) / vSide.y;
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
   }
   else
   {
      color = vCol;
      alpha *= vCol.a;
   }

   float flurAlpha = 1.0 - smoothstep(0.8, 1., length(vNormal));
   float flurAlpha2 = 1.0;

   if(vHighAliasing < 0.5)
   {
      flurAlpha = 1.0 - smoothstep(0.92, 1., length(vNormal));
      flurAlpha2 = smoothstep(0., 0.08, length(vNormal));
   }

   gl_FragColor = vec4(color.rgb * vLit, alpha * flurAlpha * flurAlpha2);
}
// file:texstyle_line_tex_lit.f.glsl
