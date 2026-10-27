uniform mat4 uVPMat;

attribute vec4 aPos;       // xyz: pos, w: creation time stamp
attribute vec4 aNrm;       // xyz: nrm, w: has 3d building (yes: 0.0, no: 1.0)
attribute vec3 aTex;       // xy: tex coord, z: base height

uniform float uTimeStamp;
uniform float uGrowthDuration;
uniform float uFlattenDtm;
uniform float u3DBuildingsMask;     // on: 0.0, off: 1.0

varying vec3 vNrm;

void main(void)
{
   vec3 nrm = aNrm.xyz;

   if(u3DBuildingsMask + aNrm.w > 0.0)
   {
      float dh = aPos.w > 0.0 && uTimeStamp < aPos.w ? 1.0 - (aPos.w - uTimeStamp) / uGrowthDuration : 1.0;

      vec3 pos = aPos.xyz;
      pos.z *= dh;
      pos.z += aTex.z * uFlattenDtm;

      gl_Position = uVPMat * vec4(pos, 1.0);

      vNrm = nrm;
   }
   else
      gl_Position = vec4(vec3(-10000.0), 1.0);

}
// file:object3d_normal.v.glsl
