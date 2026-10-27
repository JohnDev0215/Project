#ifdef GL_ES
precision mediump float;
#endif

varying vec4  vCol;
varying vec2  vTex;
varying vec2  vTexTile;
varying vec2  vLineOffset;
varying float vDotLine;
varying float vLineWidth;

// global uniforms
uniform sampler2D areaAtlas;
uniform vec3      uSunLightColor;
uniform vec2      uAtlasTexelDim;
uniform float     uSunLightIntensity;
uniform float     uLineTileWidth;
//uniform float     ambientCoefficient;

// local uniforms
uniform float uWidthFactor;
uniform float uRouteTransparency;

void main(void)
{
   %FixFarClip%  // force clipping at the far plane (workaround for Viviante GC2000)

   float fx = 1.0 - abs(vTex.x * 2.0 - 1.0);
   float alpha = fx * vTex.y;
   alpha = 1.0 - pow(abs(alpha - 1.0), 16.0);

   //-disable the true sun dependent lighting for now
   //vec3 litCol = vCol.rgb * uSunLightColor;
   //vec3 col = litCol * ambientCoefficient + (1.0 - ambientCoefficient) * litCol * nDotL * uSunLightIntensity;

   // keep the road lighting constant at day and night
   // TODO(lex): use own style for the night (moon-lit-road)
   if(vDotLine > 0.5)
   {
      vec4 color;

      if(vTexTile.x < 254.5)
      {
         if(alpha > 0.0)
         {
            float lineTileW = uLineTileWidth;
            float lineTileH = uLineTileWidth / 2.0;
            vec2 texOffsetX = vLineOffset * (0.5 / uWidthFactor) / vLineWidth;
            vec2 st = vec2( fract(texOffsetX.x), vTex.x);
            float s = (vTexTile.s * lineTileW + fract(st.s) * (lineTileW - 1.0) + 0.5) * uAtlasTexelDim.x;
            float t = (vTexTile.t * lineTileH + fract(st.t) * (lineTileH - 1.0) + 0.5) * uAtlasTexelDim.y;
            color = texture2D( areaAtlas, vec2(s, t));
            alpha *= color.a;
            //only blend white color
            if(color.r > 0.99 && color.g > 0.99 && color.b > 0.99)
            {
               color *= vCol;
            }
         }
      }
      else
      {
         color = vec4(1.0, 1.0, 1.0, 0.0);
         alpha = 0.0;
      }

      gl_FragColor = vec4( color.rgb, alpha * uRouteTransparency);
   }
   else
   {
      gl_FragColor = vec4( vCol.rgb, alpha * uRouteTransparency);
   }

   //gl_FragColor = vec4( 1.0, 0.0, 1.0, 1.0);
}
// file:texstyle_globe_route_tex.f.glsl
