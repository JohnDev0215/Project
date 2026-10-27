#ifdef GL_ES
precision mediump float;
#endif

varying vec4  vCol;
varying vec2  vTex;
varying vec3  vTexParams;
varying float vNdotL;

uniform sampler2D windowsMap;
uniform sampler2D lightsMap;

uniform vec2 uLightTexCoordScale;

uniform vec3 uSunLightDirection;
uniform vec3 uSunLightColor;
uniform float uSunLightIntensity;
uniform vec4 uLightingParams;

uniform vec4 uRoofColor;

varying float vHeight;
varying float vOpacity;
varying float vHeightDimGradientFactor;
varying float vPitch;

void main(void)
{
   float op = vOpacity;
   float height = vHeight;
   float pitch = vPitch;

   vec4 color;
   vec2 tc = vTex.xy;

   color = mix(uRoofColor, pow(texture2D(windowsMap, tc), vec4(2.2)), vTexParams.x);

   if(%FAKE_AMBIENT_OCCLUSION%)
   {
      float ambHeightFac = smoothstep(0.0, %FAKE_AMBIENT_OCCLUSION_HEIGHT%, height);
      const float ambFac = %FAKE_AMBIENT_OCCLUSION_FACTOR%;
      color.rgb *= (1.0 - ambFac) + ambHeightFac * ambFac;
   }

   if(uLightingParams.x > 0.0)
   {
      float kA = uLightingParams.y * smoothstep(0.7, 1.0, uSunLightIntensity) + 0.02;
      color.rgb = color.rgb * (kA  + uLightingParams.z * vNdotL * uSunLightColor * uSunLightIntensity);

      float noiseVal = texture2D(lightsMap, (vTex.xy) * uLightTexCoordScale + vTexParams.yz ).a * 160.0;

      float lightVal = texture2D(lightsMap, tc * 0.5).r * 255.0;
      noiseVal = ceil(noiseVal / 10.0);
      lightVal = ceil(lightVal / 10.0);

      if(lightVal > 0.0 && lightVal == noiseVal)
      {
         float lightFac = smoothstep(0.7, 1.0, (1.0 - vNdotL)) * 0.2 * (1.0 - uSunLightDirection.z);
         lightFac += smoothstep(0.8, 1.0, (1.0 - uSunLightIntensity));
         color.rgb += lightFac * vec3(1.0, 0.907, 0.41);
      }
   }

   float uTransparency = %EFB_TRANSPARENCY%;

   gl_FragColor = vec4( pow(color.rgb, vec3(0.45)) * vHeightDimGradientFactor, uTransparency * op);
}
// file:efb_windows.f.glsl
