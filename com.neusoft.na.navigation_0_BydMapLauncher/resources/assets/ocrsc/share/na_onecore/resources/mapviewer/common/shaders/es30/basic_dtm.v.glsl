
%GL_VERSION%

#ifdef GL_ES
precision highp float;
#endif

in vec3 aPos;
in vec3 aNrm;
in vec2 aTex;

uniform mat4 vpMat;
uniform mat4 viewMat;

uniform float uHeightFactor;
uniform vec2 uDtmSubTexScale;
uniform vec2 uDtmSubTexOffset;
uniform float uCameraFar;

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

uniform float uFlattenDtm;

#endif

out vec2 vTex;
out vec3 vNrm;
out vec3 vSun;
//out float vNdotL;
out float vLinearDepth;

void main(void)
{
   vec3 pos = aPos;
   pos.z *= uHeightFactor * uFlattenDtm;

   gl_Position = vpMat * vec4(pos, 1.0);
   vNrm= aNrm;
   vSun= uSunLightDirection.xyz;
   if (vSun.z < 0.0)
      vSun.z= -vSun.z;

   vTex= (aTex - uDtmSubTexOffset) * uDtmSubTexScale;

   vec4 viewPos= viewMat * vec4(pos, 1.0);
   vLinearDepth= -viewPos.z / uCameraFar;
}
// file:basic_dtm.v.glsl
