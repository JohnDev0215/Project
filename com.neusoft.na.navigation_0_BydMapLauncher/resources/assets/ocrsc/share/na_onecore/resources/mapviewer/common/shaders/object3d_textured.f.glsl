#ifdef GL_ES
precision highp float;
#endif

uniform sampler2D diffuseMap;

uniform vec3 uSunLightColor;
uniform float uSunLightIntensity;
uniform vec4 uLightingParams;

varying float vNdotL;

varying vec2 vTex;
varying vec4 vTexParams;

varying float vHeight;
varying float vOpacity;
varying float vPitch;

void main(void)
{
   float op = vOpacity;

   float l = 1.0;

   vec2 tc = abs(fract(vTex.xy));

   tc.x *= vTexParams.x;
   tc.y *= vTexParams.y;
   tc += vTexParams.zw;

   vec4 texCol = texture2D(diffuseMap, tc);
   if (texCol.a < 0.5) discard;
   vec4 color = vec4(pow(texCol.rgb, vec3(2.2)), 1.0);

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
      vec3 litCol = color.rgb * uSunLightColor * coeff;
      color.rgb *= uLightingParams.y;
      color.rgb += litCol;
   }

   float uTransparency = %O3D_TRANSPARENCY%;

   gl_FragColor = vec4( pow(color.rgb, vec3(0.45)), uTransparency * op);
}

// file:object3d_textured.f.glsl
