uniform mat4 vpMat;

uniform vec2 uAtlasSize;
uniform float uBmdIconScale;
uniform float uPitch;
uniform float uScale;

attribute vec4 vPos;  // xy: geo coordinate in relation to the anchor, zw: corner ({-0.5,0.5} => north-west)
attribute vec2 vPosCenter;  // xy: geo coordinate of center
attribute vec4 vTex;  // xy: icon position in atlas, z: icon size (x shifted by 8bit), w: icon rotation
attribute vec2 aScaleRange;

varying vec2 oTex;

void main(void)
{
//   gl_Position = vpMat * vec4(vPos.xy, 0.0, 1.0);
   float pitchFactor = mix(1.0, 1.5, uPitch);
   if (uScale >= aScaleRange.x && uScale <= aScaleRange.y)
   {
      gl_Position = vpMat * vec4(vPosCenter.xy+(vPos.xy-vPosCenter.xy)*uBmdIconScale * pitchFactor, 0.0, 1.0);
   }
   else
   {
      gl_Position = vec4( 0, 0, 0, 1);
   }
   vec2 iconSize= vec2(vTex.z / 256.0, mod(vTex.z, 256.0)); // decode icon size from one float value

   vec2 iconCoord= vPos.zw + vec2(0.5,0.5);
   vec2 tt = (vTex.xy + iconSize * iconCoord) / uAtlasSize;
   oTex= vec2(tt.x, 1.0 - tt.y);
}
// file:bmd_point_rot.v.glsl


//{
//   vec4 pos = vpMat * vec4(vPos.xy, 0.0, 1.0);

//   float s = sin(uHeading - vTex.w);
//   float c = cos(uHeading - vTex.w);
//   vec2 sideRotated = vec2( vPos.z * c + vPos.w * -s, vPos.z * s + vPos.w * c);

//   vec2 iconSize= vec2(vTex.z / 256.0, mod(vTex.z, 256.0)); // decode icon size from one float value

//   pos.xy+= sideRotated * pos.w * iconSize / uDisplaySize * 2.0;  // add offset to the icon corner in screen space
//   gl_Position = pos;

//   vec2 iconCoord= vPos.zw + vec2(0.5,0.5);
//   vec2 tt = (vTex.xy + iconSize * iconCoord) / uAtlasSize;
//   oTex= vec2(tt.x, 1.0 - tt.y);
//}
