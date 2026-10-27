#ifdef GL_ES
precision mediump float;
#endif

uniform vec3 uSunLightColor;
uniform float uSunLightIntensity;
uniform vec4 uLightingParams;

varying float vPitch;
varying float vNdotL;
varying vec4  vCol;

varying float vHeight;
varying float vOpacity;

void main(void)
{
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
      vec3 litCol = color.rgb * uSunLightColor * coeff;
      color.rgb *= uLightingParams.y;
      color.rgb += litCol;
   }


   gl_FragColor = vec4( pow(color.rgb, vec3(0.45)), op);
}

// file:object3d_untextured.f.glsl
