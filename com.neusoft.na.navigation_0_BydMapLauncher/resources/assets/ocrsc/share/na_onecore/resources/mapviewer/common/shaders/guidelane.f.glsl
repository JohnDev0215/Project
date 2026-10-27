#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D areaAtlas;

uniform vec2 uAtlasTexelDim;
uniform vec2 uLineTileWidth;

varying vec4 vCol;
varying vec2 vTexTile;
varying vec2 vLineOffset;
varying vec2 vSide;

varying float vWidthFactor; // pass uniform as varying to allow different precision definitions in vs & fs

void main(void)
{
   %FixFarClip%  // force clipping at the far plane (workaround for Viviante GC2000)
   float alpha = 1.0;//min( min(vSide.x, 1.0 - (vSide.x - vSide.y)), 1.0);

   vec4 color;
   if(vTexTile.x < 254.5)
   {
      float lineTileW = uLineTileWidth.x;
      float lineTileH = uLineTileWidth.y > 0.0 ? uLineTileWidth.y : 16.0;
      vec2 texOffsetX = vLineOffset * (0.5 / vWidthFactor * 0.5) / vSide.y;

      if (alpha > 0.0)
      {
         vec2 st = vec2( fract(texOffsetX.x), vLineOffset.y); // texture coordinates along the line
         float s = (vTexTile.s * lineTileW + fract(st.s) * (lineTileW - 1.0) + 0.5) * uAtlasTexelDim.x;
         float t = (vTexTile.t * lineTileH + fract(st.t) * (lineTileH - 1.0) + 0.5) * uAtlasTexelDim.y;
         color = texture2D( areaAtlas, vec2(s, t));
         alpha *= color.a;
      }
   }
   else
   {
      color = vCol;
      alpha *= vCol.a;
   }
   gl_FragColor = vec4( color.rgb, alpha);
}

