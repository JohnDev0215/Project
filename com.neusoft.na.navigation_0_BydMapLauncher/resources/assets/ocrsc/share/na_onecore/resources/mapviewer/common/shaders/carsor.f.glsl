#ifdef GL_ES
precision mediump float;
#endif

uniform vec2  uDisplaySize;
uniform vec2  uDisplayOffset;
uniform vec3  uTransparencyCtr;
uniform float uAspectRatio;

uniform float uCarsorAlpha;
uniform float uEnable;

varying vec3 vTex;
varying float vPitch;

uniform sampler2D diffuseMap;

void main(void)
{
   if(uEnable < 0.5)
   {
      discard;
   }

   float opacityBlendFac = 1.0;

   if(%O3D_TRANSPARENCY_AROUND_CARSOR%)
   {
      if(uTransparencyCtr.z > -1.0)
      {
         vec2 fragCoord = 2.0 * (gl_FragCoord.xy - uDisplayOffset) / uDisplaySize.xy - vec2(1.0);

         if(%O3D_SIGHT_ELLIPSE%)
         {
            vec2 ctr = vec2(uTransparencyCtr.x, -1.0);
            float a = %O3D_SIGHT_ELLIPSE_A%;
            float b = uTransparencyCtr.y + 1.0 + %O3D_SIGHT_ELLIPSE_DELTA_B%;
            b *= b;
            fragCoord -= ctr;
            fragCoord *= fragCoord;
            float fragL = sqrt(fragCoord.x / a + fragCoord.y / b);
            opacityBlendFac *= smoothstep(%O3D_SIGHT_ELLIPSE_RANGE%, 1.0, fragL);
         }
         else
         {
            vec2 v2c = uTransparencyCtr.xy - fragCoord;
            v2c.y *= uAspectRatio * vPitch; // scale height of area depending on pitch
            float radiusScale  = pow(length(v2c), %O3D_RADIUS_EXPONENT%);
            opacityBlendFac *= smoothstep(0.0, %O3D_TRANSPARENCY_RADIUS%, radiusScale);
         }
      }
   }

   vec4 col = texture2D(diffuseMap, vTex.xy);
   gl_FragColor = vec4(col.rgb, uCarsorAlpha * mix(1.0, col.a, opacityBlendFac));
}
// file:carsor.f.glsl
