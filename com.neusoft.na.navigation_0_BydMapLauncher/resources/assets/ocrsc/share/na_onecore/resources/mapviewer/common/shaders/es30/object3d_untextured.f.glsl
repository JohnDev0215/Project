
%GL_VERSION%

#ifdef GL_ES
precision mediump float;
#endif

uniform vec3  uTransparencyCtr;
uniform vec4 uLightingParams;

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

uniform float uAspectRatio;
uniform vec2 uDisplaySize;
uniform vec2 uDisplayOffset;

#endif

in float vPitch;
in float vNdotL;
in vec4  vCol;

in float vHeight;
in float vOpacity;

out vec4 fragmentColor;

void main(void)
{
#ifdef USE_UNIFORM_BUFFERS
   float uSunLightIntensity = uSunLightColor.w;

   vec2  uDisplayOffset = uDisplayRect.xy;
   vec2  uDisplaySize = uDisplayRect.zw;
#endif

   float op = vOpacity;

   float l = 1.0;

   vec4 color;
   color = vCol;

   op *= color.a;

   if(%FAKE_AMBIENT_OCCLUSION%)
   {
      float ambHeightFac = smoothstep(0.0, %FAKE_AMBIENT_OCCLUSION_HEIGHT%, vHeight);
      const float ambFac = %FAKE_AMBIENT_OCCLUSION_FACTOR%;
      color.rgb *= (1.0 - ambFac) + ambHeightFac * ambFac;
   }

   if(uLightingParams.x > 0.0)
   {
      float coeff = uLightingParams.z * vNdotL * uSunLightIntensity;
      vec3 litCol = color.rgb * uSunLightColor.xyz * coeff;
      color.rgb *= uLightingParams.y;
      color.rgb += litCol;
   }


   fragmentColor = vec4( pow(color.rgb, vec3(0.45)), op);
}

// file:object3d_untextured.f.glsl
