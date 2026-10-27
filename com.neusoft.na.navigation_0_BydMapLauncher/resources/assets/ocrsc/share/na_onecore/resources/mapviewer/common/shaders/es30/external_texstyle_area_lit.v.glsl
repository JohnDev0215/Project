#version %GLver%
// shader: external_texstylevs_area_lit.vs

uniform mat4  uVPMat;

uniform float uLightingEnabled;

%USE_UNIFORM_BUFFERS%

#ifdef USE_UNIFORM_BUFFERS

layout (std140) uniform ubLightingData
{
   vec4        uLightingParamsBMD;
   vec4        uLightingParamsDTM;
   vec4        uLightingParamsWater;
   vec4        uSunLightColor;
   vec4        uSunLightDirection;
};

#else

// x: enabled, y: kA, z: kD, w: kS
uniform vec4  uLightingParamsBMD;
uniform vec3  uSunLightColor;
uniform vec3  uSunLightDirection;
uniform float uSunLightIntensity;

#endif


uniform sampler2D styleTex;

in vec4  aPos;          // xy: vertex pos; zw: texture coordinates in tile
in ivec2 aTex;         // xy: texture coordinates in style-texture

out vec4 vCol;

//-- the version below uses vertex texture access (unsupported by iMX6 by driver @2014.09.01) --
//== test this version on non iMX targets ==


void main(void)
{
#ifdef USE_UNIFORM_BUFFERS
   float uSunLightIntensity = uSunLightColor.w;
#endif

   vCol= texelFetch( styleTex, aTex, 0);
   if (vCol.a > 0.0 )
   {
      gl_Position = uVPMat * vec4(aPos.xy, aPos.z, 1.0);

      vec3 light;
      if((uLightingParamsBMD.x * uLightingEnabled) > 0.0)
      {
         float NdotL = max( uSunLightDirection.z, 0.0);
         light= uLightingParamsBMD.y + NdotL * (uLightingParamsBMD.z * uSunLightIntensity * uSunLightColor.xyz);
      }
      else
         light = vec3(1.0);

      vCol = vec4(vCol.rgb * light, vCol.a);
      //vCol = vec4(uSunLightDirection.xyz, 1.0);
      //vCol = vec4(vec3(uLightingEnabled), 1.0);
   }
   else
   {
      gl_Position = vec4( 0.0, 0.0, 0.0, 0.0);  // discard
   }

}
// file:es30:external_texstylevs_area_lit.v.glsl
