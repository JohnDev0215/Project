
%GL_VERSION%

#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D diffuseMap;

uniform vec4 uLightingParams;

uniform vec3 uTransparencyCtr;

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

#endif

in float vNdotL;
in vec2 vTex;
in vec4 vTexParams;

in float vHeight;
in float vOpacity;
in float vPitch;

out vec4 fragmentColor;

void main(void)
{
#ifdef USE_UNIFORM_BUFFERS
   float uSunLightIntensity = uSunLightColor.w;

#endif

   float op = vOpacity;

   float l = 1.0;

   vec2 tc = abs(fract(vTex.xy));

   tc.x *= vTexParams.x;
   tc.y *= vTexParams.y;
   tc += vTexParams.zw;

   vec4 texCol = texture(diffuseMap, tc);
   if (texCol.a < 0.5) discard;
   vec4 color = vec4(pow(texCol.rgb, vec3(2.2)), texCol.a);

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
      vec3 litCol = color.rgb * uSunLightColor.rgb * coeff;
      color.rgb *= uLightingParams.y;
      color.rgb += litCol;
   }

   float uTransparency = %O3D_TRANSPARENCY%;

   fragmentColor = vec4( pow(color.rgb, vec3(0.45)), uTransparency * op);
}

// file:object3d_textured.f.glsl
