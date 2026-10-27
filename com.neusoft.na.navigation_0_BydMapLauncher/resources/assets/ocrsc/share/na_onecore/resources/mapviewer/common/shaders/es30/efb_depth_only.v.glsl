
%GL_VERSION%

in vec4 aPos;       // xyz: pos, w: creation time stamp
in vec4 aNrm;       // xyz: nrm, w: has 3d building (yes: 0.0, no: 1.0)

uniform mat4 uVPMat;

uniform float uTimeStamp;
uniform float uGrowthDuration;
uniform float u3DBuildingsMask;     // on: 0.0, off: 1.0

void main(void)
{
   vec3 nrm = aNrm.xyz;

   if(u3DBuildingsMask + aNrm.w > 0.1)
   {
      float dh = aPos.w > 0.0 && uTimeStamp < aPos.w ? 1.0 - (aPos.w - uTimeStamp) / uGrowthDuration : 1.0;

      vec3 pos = aPos.xyz;
      pos.z *= dh;

      vec4 tp = uVPMat * vec4(pos, 1.0);
      gl_Position = tp;

   }
   else
      gl_Position = vec4(vec3(-10000.0), 1.0);

}

// file:efb_depth_only.v.glsl
