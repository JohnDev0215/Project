
attribute vec4 vPos;
attribute vec2 vTex;


uniform vec3 uTransparencyCtr;
uniform float uAspectRatio;
uniform float uPitch;
uniform vec2 uDisplaySize;
uniform float uGlobalLabelingOpacity;

varying vec2 oTex;
varying float vOpacity;

void main(void)
{
   vec2 pos = 2.0 * (vPos.xy / uDisplaySize.xy) - vec2(1.0, 1.0);
   pos.y *= -1.0;

   float z = vPos.w;

   if(%O3D_TRANSPARENCY_AROUND_CARSOR%)
   {
      if(uTransparencyCtr.z > -1.0)
      {
         vec2 fragCoord = 2.0 * vPos.xy / uDisplaySize.xy - vec2(1.0);

         if(%O3D_SIGHT_ELLIPSE%)
         {
            vec2 ctr = vec2(uTransparencyCtr.x, -1.0);
            float a = %O3D_SIGHT_ELLIPSE_A%;
            float b = uTransparencyCtr.y + 1.0 + %O3D_SIGHT_ELLIPSE_DELTA_B%;
            b *= b;
            fragCoord -= ctr;
            fragCoord *= fragCoord;
            float fragL = sqrt(fragCoord.x / a + fragCoord.y / b);
            z *= smoothstep(%O3D_SIGHT_ELLIPSE_RANGE%, 1.0, fragL);
         }
         else
         {
            vec2 v2c = uTransparencyCtr.xy - fragCoord;
            v2c.y *= uAspectRatio;
            if(%O3D_SIGHT_TUNNEL%)
               v2c.y *= 1.0 - smoothstep(0.2, 0.6, uPitch);  // scale height of area depending on pitch
            float radiusScale  = pow(length(v2c), %O3D_RADIUS_EXPONENT%);
            z *= smoothstep(0.0, %O3D_TRANSPARENCY_RADIUS%, radiusScale);
         }
      }
   }

   gl_Position = vec4(pos, z, 1.0);
   //oTex = vec2(vTex.x, 1.0 - vTex.y);
   oTex = vTex;
   vOpacity = vPos.z * uGlobalLabelingOpacity;
}
// file:labelicon.v.glsl
