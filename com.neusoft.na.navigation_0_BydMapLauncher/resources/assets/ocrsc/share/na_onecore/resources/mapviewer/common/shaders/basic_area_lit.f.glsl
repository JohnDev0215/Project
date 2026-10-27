#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D areaAtlas;

varying vec4 vCol;
varying vec2 vTex;
varying vec2 vTexTile;
varying float vNdotL;

uniform vec2 uAtlasTexelDim;      // 1 / texture width, 1 / texture hegith
uniform float uTileSize;          // size of tile

uniform vec3 uSunLightColor;
uniform vec3 uSunLightDirection;
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
   {
      float coeff = uLightingParamsBMD.z * vNdotL * uSunLightIntensity;
      vec3 litCol = color.rgb * uSunLightColor * coeff;
      color.rgb *= uLightingParamsBMD.y;
      color.rgb += litCol;
   }

   gl_FragColor = color;
}
// file:basic_area_lit.f.glsl
