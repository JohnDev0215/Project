
%GL_VERSION%

#ifdef GL_ES
precision mediump float;
#endif

in vec4 aPos;       // xyz: pos, w: creation time stamp
in vec4 aNrm;       // xyz: nrm, w: has 3d building (yes: 0.0, no: 1.0)
in vec2 aTex;       // xy: tex coord, z: base height
in vec3 aTexParams;
in vec4 aCol;

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

layout (std140) uniform ubFrameContext
{
   vec4     uDisplayRect;
   float    uTimeStamp;
   float    uAspectRatio;
   float    uScale;
   float    uHeading;
   float    uPitch;
   float    uFlattenDtm;
};


#else

uniform vec3 uSunLightDirection;

uniform float uTimeStamp;
uniform float uPitch;

#endif

uniform mat4 uVPMat;

uniform vec2 uHeightDimGradientFactors;     //x mean roof dim factor, y mean base dim factor
uniform float u3DBuildingsMask;     // on: 0.0, off: 1.0

uniform float uGrowthDuration;

out vec2  vTex;
out vec3  vTexParams;
out float vNdotL;
out float vHeight;
out float vOpacity;
out float vHeightDimGradientFactor;
out float vPitch;

void main(void)
{
   vec3 nrm = aNrm.xyz;
   vOpacity = 1.0;
   vHeightDimGradientFactor = 1.0;

   if(u3DBuildingsMask + aNrm.w > 0.1)
   {
      float dh = aPos.w > 0.0 && uTimeStamp < aPos.w ? 1.0 - (aPos.w - uTimeStamp) / uGrowthDuration : 1.0;

      vec3 pos = aPos.xyz;
      pos.z *= dh;

      vHeight = aPos.z;
      vHeightDimGradientFactor = (vHeight > 0.0) ? uHeightDimGradientFactors.x : uHeightDimGradientFactors.y;

      vec4 tp = uVPMat * vec4(pos, 1.0);
      gl_Position = tp;

      if(%FADE_OUT_TO_SKYLINE%)
      {
         vOpacity *= 1.0 - smoothstep(%FADE_OUT_TO_SKYLINE_FACTOR%, 1.0, abs(tp.z / tp.w));
      }

   }
   else
   {
      gl_Position = vec4(vec3(-10000.0), 1.0);
   }

   vPitch = 1.0;
   if(%O3D_SIGHT_TUNNEL%)
   {
      vPitch = 1.0 - smoothstep(0.2, 0.6, uPitch);
   }
   vNdotL = max(dot(nrm, uSunLightDirection.xyz), 0.0);
   vTex = aTex.xy;
   vTexParams = aTexParams;
}
// file:efb_windows.v.glsl
