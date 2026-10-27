#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D areaAtlas;
uniform sampler2D waterNrm;

uniform vec2 uAtlasTexelDim;     // 1 / texture width, 1 / texture hegith
uniform vec2 uAtlasTileSize;     // size of tile in px
uniform vec2 uAtlasGridSize;     // the granularity of the virtual grid in atlas for tile alignment (default 8x8)

uniform vec3 uReflectiveWaterParams;      // tex scale, normal strength, shininess

uniform float uReflectiveWaterEnabled;

uniform vec3 uSunLightDirection;
uniform vec3 uSunLightColor;
uniform float uSunLightIntensity;

// x: enabled, y: kA, z: kD, w: kS
uniform vec4 uLightingParamsWater;
uniform float uLightingEnabled;

varying vec2 vTex;
varying vec2 vTexTile;
varying vec4 vCol;

varying vec3 vViewDirWS;

void main(void)
{
   %FixFarClip%  // force clipping at the far plane (workaround for Viviante GC2000)

   vec4 color;
   if(vTexTile.x < 254.9)
   {
      vec2 ts2 = uAtlasTileSize - vec2(4.0);
      float s = (vTexTile.s * uAtlasGridSize.x + fract(vTex.s) * ts2.x + 2.0) * uAtlasTexelDim.x;
      float t = (vTexTile.t * uAtlasGridSize.y + fract(vTex.t) * ts2.y + 2.0) * uAtlasTexelDim.y;
      color = texture2D( areaAtlas, vec2( s, t));
   }
   else
      color = vCol;

   if((uLightingParamsWater.x * uLightingEnabled) > 0.0)
   {
      if(uReflectiveWaterEnabled > 0.0)
      {
         vec3 nrm = 2.0 * texture2D(waterNrm, fract(vTex * uReflectiveWaterParams.x)).rgb - vec3(1.0);
         nrm = normalize(nrm * vec3(uReflectiveWaterParams.yy, 1.0));
         vec3 sunLightDir = %SUN_LIGHT_DIRECTION%;
         float nDotL = max(dot(nrm, sunLightDir), 0.0);

         float specCoeff = 0.0;
         float shininess = uReflectiveWaterParams.z;
         if(nDotL > 0.0)
         {
            vec3 lightRefWS = reflect(sunLightDir, nrm);
            specCoeff = pow(max(0.0, dot(normalize(vViewDirWS), normalize(lightRefWS))), shininess);
            const float pi2 = 6.283185307;
            specCoeff *= (2.0 + shininess) / pi2;
         }
         color.rgb *= uLightingParamsWater.y + uSunLightColor * uSunLightIntensity * ((uLightingParamsWater.z + specCoeff * uLightingParamsWater.w) * nDotL);
//         color.rgb = vec3(uSunLightColor * uSunLightIntensity);
      }
      else
      {
         float nDotL = max(uSunLightDirection.z, 0.0);
         color.rgb *= uLightingParamsWater.y + uLightingParamsWater.z * nDotL * uSunLightIntensity;
      }
   }

   gl_FragColor = color;
}
// file:texstylevs_area_water.f.glsl
