#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D diffuseMap;
uniform vec3 uSunLightColor;
uniform float uDtmFogOffset;  // offset in NDC, where the fog / background-blending begins

// x: enabled, y: kA, z: kD, w: kS
uniform vec4 uLightingParamsDTM;

varying vec2 vTex;
varying vec3 vNrm;
varying vec3 vSun;
//varying float vNdotL;
varying float vLinearDepth;

void main(void)
{
   vec4 texel= texture2D( diffuseMap, vTex);
   float ambCoeff= 0.1;
   
   float vNdotL = max( dot( vNrm, vSun) * vNrm.z, 0.0);

   
   vec3 mixed = texel.rgb * (uSunLightColor * vNdotL * uLightingParamsDTM.z + vec3(1.0, 0.95, 0.87) * uLightingParamsDTM.y);
   float fogFactor= smoothstep( uDtmFogOffset, 1.0, vLinearDepth);
   vec3 fogColor= vec3(0.5, 0.6, 0.75);
   mixed = mix( mixed, fogColor, fogFactor);
   gl_FragColor = vec4( mixed, 1.0 - fogFactor);
}
// file:basic_dtm.f.glsl
