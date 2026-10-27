#ifdef GL_ES
precision mediump float;
#endif

varying vec3 vCol;
varying vec2 vTex;
varying float vNdotL;

uniform vec3 uSunLightDirection;
uniform vec3 uSunLightColor;
uniform float uSunLightIntensity;

// x: enabled, y: kA, z: kD, w: kS
uniform vec4 uLightingParamsBMD;
uniform float uLightingEnabled;

//uniform sampler2D diffTexture;

void main(void)
{
   float fx = 1.0 - abs(vTex.x * 2.0 - 1.0);
   //fx = 1.0 - pow(abs(fx - 1.0), 16.0);
   float fy = vTex.y;
   //fy = 1.0 - pow(abs(fy - 1.0), 16.0);
   float i = fx * fy;
   i = 1.0 - pow(abs(i - 1.0), 16.0);
   //gl_FragColor = vec4(i, i, i, 1.0);

   vec3 color = vCol.rgb;
   if((uLightingParamsBMD.x * uLightingEnabled) > 0.0)
   {
      float coeff = uLightingParamsBMD.z * vNdotL * uSunLightIntensity;
      vec3 litCol = color.rgb * uSunLightColor * coeff;
      color.rgb *= uLightingParamsBMD.y;
      color.rgb += litCol;
   }

   gl_FragColor = vec4( color, i);
}
// file:basic_line_lit.f.glsl
