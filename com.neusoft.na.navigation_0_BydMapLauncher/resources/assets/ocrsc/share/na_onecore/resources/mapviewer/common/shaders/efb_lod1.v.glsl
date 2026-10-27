uniform mat4 uVPMat;

attribute vec4 aPos;       // xyz: pos, w: creation time stamp
attribute vec4 aNrm;       // xyz: nrm, w: has 3d building (yes: 0.0, no: 1.0)

uniform vec2 uHeightDimGradientFactors;     //x mean roof dim factor, y mean base dim factor
uniform float uTimeStamp;
uniform float uGrowthDuration;
uniform float u3DBuildingsMask;     // on: 0.0, off: 1.0

varying float vOpacity;
varying float vHeightDimGradientFactor;
varying float vRoof;
varying vec3  vNormal;
varying vec3  vPos;

void main(void)
{
   vOpacity = 1.0;
   if(u3DBuildingsMask + aNrm.w > 0.1)
   {
      float dh = aPos.w > 0.0 && uTimeStamp < aPos.w ? 1.0 - (aPos.w - uTimeStamp) / uGrowthDuration : 1.0;

      vec3 pos = aPos.xyz;
      pos.z *= dh;

      vHeightDimGradientFactor = (aPos.z > 0.0) ? uHeightDimGradientFactors.x : uHeightDimGradientFactors.y;

      vec4 tp = uVPMat * vec4(pos, 1.0);
      gl_Position = tp;

      if(%FADE_OUT_TO_SKYLINE%)
      {
         vOpacity *= 1.0 - smoothstep(%FADE_OUT_TO_SKYLINE_FACTOR%, 1.0, abs(tp.z / tp.w));
      }
   }
   else
      gl_Position = vec4(vec3(-10000.0), 1.0);

   vRoof = (aNrm.x == 0.0 && aNrm.y == 0.0 && aNrm.z == 1.0) ? 1.0 : 0.0;

   vNormal = aNrm.xyz;
   vPos = aPos.xyz;
}

// file:efb_lod1.v.glsl
