#ifdef GL_ES
precision highp float;
#endif

uniform sampler2D areaAtlas;

//uniform vec3  uSunLightColor;
//uniform float uSunLightIntensity;
//uniform float ambientCoefficient;
uniform vec2 uAtlasTexelDim;
uniform vec2 uLineTileWidth;
uniform float uRouteTransparency;

varying vec4  vCol;
varying vec2  vTex;
varying vec2  vTexTile;
varying vec2  vLineOffset;
varying float vDotLine;
varying float vLineWidth;
varying float vWidthFactor; // pass uniform as varying to allow different precision definitions in vs & fs
varying vec2 vNormal;
varying float vBorderFactor;
varying vec4  vBorderCol;
varying float  vEnableTi;

void main(void)
{
   %FixFarClip%  // force clipping at the far plane (workaround for Viviante GC2000)

   float fx = 1.0 - abs(vTex.x * 2.0 - 1.0);
   float alpha = fx * vTex.y;
   alpha = 1.0 - pow(abs(alpha - 1.0), 16.0);


   float blur = 1. - smoothstep(0.8, 1., length(vNormal));

   float vMixFac = 1. - smoothstep(vBorderFactor, 0.9, length(vNormal));
   
   vec3 vMix = mix(vBorderCol.rgb, vCol.rgb, vMixFac);
   vec4 vMixColor = vec4(vMix, 1.0);

   vec4 vColor = vCol;

   if(vEnableTi > 0.5 && vBorderFactor < 1.0)
   {
      vColor = vMixColor;
   }


   if(vDotLine > 0.5)
   {
      vec4 color = vec4(0.0, 0.0, 0.0, 0.0);

      if(vTexTile.x < 254.5)
      {
         if(alpha > 0.0)
         {
            float lineTileW = uLineTileWidth.x;
            float lineTileH = uLineTileWidth.y > 0.0 ? uLineTileWidth.y : 16.0;
            vec2 texOffsetX = vLineOffset * (0.5 / vWidthFactor * 0.5) / vLineWidth;
            vec2 st = vec2( fract(texOffsetX.x), vTex.x);
            float s = (vTexTile.s * lineTileW + fract(st.s) * (lineTileW - 1.0) + 0.5) * uAtlasTexelDim.x;
            float t = (vTexTile.t * lineTileH + fract(st.t) * (lineTileH - 1.0) + 0.5) * uAtlasTexelDim.y;
            color = texture2D( areaAtlas, vec2(s, t));

            alpha *= color.a;
         }
         if(alpha == 0.0)
         {
            gl_FragColor = vec4( vColor.rgb, blur * 1.0);
         }
         else
         {
            float blurWhite = 1. - smoothstep(0.9, 1., length(vNormal));
            gl_FragColor = vec4( color.rgb, blurWhite * alpha * uRouteTransparency);
         }
      }
      else
      {
         gl_FragColor = vec4( vColor.rgb, blur * alpha * uRouteTransparency);
      }
   }
   else
   {
      gl_FragColor = vec4( vColor.rgb, blur * alpha * uRouteTransparency);
   }
}
// file:texstyle_route_lit.f.glsl

