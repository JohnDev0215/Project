
%GL_VERSION%

#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D diffuseMap;

uniform float uDtmFogOffset;  // offset in NDC, where the fog / background-blending begins

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

uniform vec4 uLightingParamsDTM;
uniform vec3 uSunLightColor;

#endif


in vec2 vTex;
in vec3 vNrm;
in vec3 vSun;
//in float vNdotL;
in float vLinearDepth;

out vec4 fragmentColor;

const float PI = 3.14159265358979;

void main(void)
{
   vec4 texel= texture( diffuseMap, vTex);
   float ambCoeff= 0.1;

   float vNdotL = max( dot( vNrm, vSun) * vNrm.z, 0.0);


   vec3 mixed = texel.rgb * (uSunLightColor.xyz * vNdotL * uLightingParamsDTM.z + vec3(1.0, 0.95, 0.87) * uLightingParamsDTM.y);
   float fogFactor= smoothstep( uDtmFogOffset, 1.0, vLinearDepth);
   vec3 fogColor= vec3(0.5, 0.6, 0.75);
   mixed = mix( mixed, fogColor, fogFactor);
   fragmentColor = vec4( mixed, 1.0 - fogFactor);

   if (false) // colorize surface by normals (HSL-like model)
   {
      vec3 nrm = vNrm;
      nrm.z = nrm.z / 4.0;
      nrm = normalize( nrm);
      fragmentColor = vec4( abs(nrm), 1.0);

      float value = sqrt( nrm.x * nrm.x + nrm.y * nrm.y);
      vec3 rgb = vec3(0.0, 0.0, 0.0);
      if (nrm.z < 0.999)
      {
         vec2 xy = normalize( nrm.xy);

         float angle = atan( xy.y, xy.x) + PI;
         float p3 = 2.0 * PI / 3.0;
         if (angle > 0.0 && angle <= 2.0*p3)
            rgb.r = 1.0 - abs(angle - p3) / p3;
         if (angle > p3 && angle <= 3.0*p3)
            rgb.g = 1.0 - abs(angle - 2.0*p3) / p3;
         if (angle <= p3)
            rgb.b = (p3 - angle) / p3;
         else if (angle > 2.0*p3)
            rgb.b = (angle - 2.0*p3) / p3;
         rgb *= value;
         //rgb = vec3(abs(nrm.xy), 0.0);
      }

      fragmentColor = vec4( rgb, 1.0);
   }
}
