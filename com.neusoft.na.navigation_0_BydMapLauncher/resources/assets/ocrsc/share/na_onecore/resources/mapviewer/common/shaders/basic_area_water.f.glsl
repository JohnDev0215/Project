#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D areaAtlas;
uniform sampler2D waterNrm;

varying vec4 vCol;
varying vec2 vTex;
varying vec3 vTexTile;

varying vec3 vViewDirWS;

uniform vec2 uAtlasTexelDim;      // 1 / texture width, 1 / texture hegith
uniform float uTileSize;          // size of tile

uniform float uReflectiveWaterEnabled;

uniform vec3 uSunLightDirection;
uniform vec3 uSunLightColor;
uniform float uSunLightIntensity;

// x: enabled, y: kA, z: kD, w: kS
uniform vec4 uLightingParamsBMD;
uniform float uLightingEnabled;

void main(void)
{
   vec4 color;
   if(vTexTile.x >= 0.0)
   {
      float ts2 = uTileSize - 1.0;
      float s = (vTexTile.s * uTileSize + fract(vTex.s) * ts2 + 0.5) * uAtlasTexelDim.x;
      float t = (vTexTile.t * uTileSize + fract(vTex.t) * ts2 + 0.5) * uAtlasTexelDim.y;
      color = texture2D( areaAtlas, vec2( s, t));
   }
   else
      color = vCol;

   if((uLightingParamsBMD.x * uLightingEnabled) > 0.0)
   if(uLightingParamsBMD.x > 0.0)
   {
      if(uReflectiveWaterEnabled > 0.0)
      {
         vec3 nrm = 2.0 * texture2D(waterNrm, fract(vTex)).rgb - vec3(1.0);
         float nDotL = max(dot(nrm, uSunLightDirection), 0.0);

         float specCoeff = 0.0;
         const float shininess = 48.0;
         if(nDotL > 0.0)
         {
            vec3 lightRefWS = reflect(uSunLightDirection, nrm);
            specCoeff = pow(max(0.0, dot(normalize(vViewDirWS), normalize(lightRefWS))), shininess);
            const float pi2 = 6.283185307;
            specCoeff *= (2.0 + shininess) / pi2;
         }
         color.rgb *= uLightingParamsBMD.y + uSunLightColor * uSunLightIntensity * ((uLightingParamsBMD.z + specCoeff * uLightingParamsBMD.w) * nDotL);
      }
      else
      {
         float nDotL = max(uSunLightDirection.z, 0.0);
         float coeff = uLightingParamsBMD.z * nDotL * uSunLightIntensity;
         vec3 litCol = color.rgb * uSunLightColor * coeff;
         color.rgb *= uLightingParamsBMD.y;
         color.rgb += litCol;
      }
   }

   gl_FragColor = vec4(color);
}
// file:basic_area_water.f.glsl
