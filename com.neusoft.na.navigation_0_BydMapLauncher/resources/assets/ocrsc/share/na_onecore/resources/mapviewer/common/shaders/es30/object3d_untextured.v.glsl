
%GL_VERSION%

in vec4  aPos;       // xyz: pos, w: creation time stamp
in vec4  aNrm;       // xyz: nrm, w: has 3d building (yes: 0.0, no: 1.0)
in float aHeightOffset; // x: height offset when terrain data is available
in vec4  aCol;

uniform mat4 uVPMat;

uniform float uGrowthDuration;

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

uniform vec3  uSunLightDirection;

uniform float uTimeStamp;
uniform float uFlattenDtm;
uniform float uPitch;

#endif

out float vNdotL;
out vec4 vCol;
out float vPitch;
out float vHeight;
out float vOpacity;

void main(void)
{
   vec3 nrm = aNrm.xyz;

   float dh = aPos.w > 0.0 && uTimeStamp < aPos.w ? 1.0 - (aPos.w - uTimeStamp) / uGrowthDuration : 1.0;

   vec3 pos = aPos.xyz;
   pos.z *= dh;
   pos.z += aHeightOffset * uFlattenDtm;
   vec4 tp = uVPMat * vec4(pos, 1.0);
   gl_Position = tp;
   vHeight = aPos.z;

   vOpacity = 1.0;
   if(%FADE_OUT_TO_SKYLINE%)
   {
      vec4 tp = uVPMat * vec4(pos.xy, 0.0, 1.0);
      tp.xyz /= tp.w;
      vOpacity *= 1.0 - smoothstep(%FADE_OUT_TO_SKYLINE_FACTOR%, 1.0, abs(tp.z));
   }

   vPitch = 1.0;
   if(%O3D_SIGHT_TUNNEL%)
   {
      vPitch = 1.0 - smoothstep(0.2, 0.6, uPitch);
   }
   vNdotL = max(dot(nrm, uSunLightDirection.xyz), 0.0);
   vCol = vec4(pow(aCol.rgb, vec3(2.2)), aCol.a);
}
// file:object3d_untextured.v.glsl
