
%GL_VERSION%

uniform mat4 uVPMat;

in vec4  aPos;          // xyz: pos, w: creation time stamp
in vec4  aNrm;          // xyz: nrm, w: has 3d building (yes: 0.0, no: 1.0)
in float aHeightOffset; // x: height offset when terrain data is available
in vec2  aTex;          // xy: tex coord
in vec4  aTexParams;    // xy: tex size, zw light offset

uniform float uTimeStamp;
uniform float uGrowthDuration;
uniform float uFlattenDtm;

void main(void)
{
   float dh = aPos.w > 0.0 && uTimeStamp < aPos.w ? 1.0 - (aPos.w - uTimeStamp) / uGrowthDuration : 1.0;

   vec3 pos = aPos.xyz;
   pos.z *= dh;
   pos.z += aHeightOffset * uFlattenDtm;
   vec4 tp = uVPMat * vec4(pos, 1.0);
   gl_Position = tp;
}
// file:o3d_depth_only.v.glsl
