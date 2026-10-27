
%GL_VERSION%

#ifdef GL_ES
precision mediump float;
#endif

in vec4  vCol;
in float vNdotL;
in float vHeight;
in float vOpacity;
in float vHeightDimGradientFactor;
in float vPitch;
in vec3  vNormal;
in vec3  vPos;

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
uniform vec3 uSunLightColor;
uniform float uSunLightIntensity;

uniform vec3 lightDirection;
uniform vec3 lightAmbient;
uniform vec3 lightDiffuse;
uniform vec3 lightSpecular;
uniform vec3 materialAmbient;
uniform vec3 materialDiffuse;
uniform vec3 materialSpecular;
uniform vec3 uCameraCenterPosition;

uniform float uAspectRatio;

#endif

uniform vec4  uLightingParams;

void main(void)
{
#ifdef USE_UNIFORM_BUFFERS
   float uSunLightIntensity = uSunLightColor.w;
#endif

   vec4 color = vec4(pow(vCol.rgb, vec3(2.2)), 0.0);

   if(%FAKE_AMBIENT_OCCLUSION%)
   {
      float ambHeightFac = smoothstep(0.0, %FAKE_AMBIENT_OCCLUSION_HEIGHT%, vHeight);
      const float ambFac = %FAKE_AMBIENT_OCCLUSION_FACTOR%;
      color.rgb *= (1.0 - ambFac) + ambHeightFac * ambFac;
   }

   if(uLightingParams.x > 0.0)
   {
      float kA = uLightingParams.y * smoothstep(0.7, 1.0, uSunLightIntensity) + 0.02;
      vec3 litCol = color.rgb * uSunLightColor.rgb;

      color.rgb *= kA;
      color.rgb += uLightingParams.z * vNdotL * litCol * uSunLightIntensity;
      color.rgb *= vHeightDimGradientFactor;
   }

   float uTransparency = %EFB_TRANSPARENCY%;
   gl_FragColor = vec4( color.rgb, uTransparency * vOpacity);
}

// file:efb_lod1.f.glsl
