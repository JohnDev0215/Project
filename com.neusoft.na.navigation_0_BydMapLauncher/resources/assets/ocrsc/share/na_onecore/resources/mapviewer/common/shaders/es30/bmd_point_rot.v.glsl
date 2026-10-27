#version %GLver%
// #ifndef GL_ES
// #version 330
// #else
// #version 300 es
// #endif

uniform mat4 vpMat;

uniform vec2 uAtlasSize;
uniform vec2 uDisplaySize;
uniform float uHeading;
uniform float uBmdIconScale;

in vec2 vPos;  // xy: geo coordinate in relation to the anchor
in vec2 vPosCenter;  // xy: geo coordinate of center
in uvec4 vIcon; // xy: corner ({-0.5,0.5} => north-west), z: icon rotation
in uvec4 vTex;  // xy: icon position in atlas, zw: icon size

out vec2 oTex;

void main(void)
{
//   gl_Position = vpMat * vec4(vPos.xy, 0.0, 1.0);
   gl_Position = vpMat * vec4(vPosCenter.xy+(vPos.xy-vPosCenter.xy)*uBmdIconScale, 0.0, 1.0);

//   float rot = float(vIcon.z) * (3.14159/128.0);
//   float s = sin(uHeading - rot);
//   float c = cos(uHeading - rot);
//   vec2 side = vec2(vIcon.xy) - 0.5;
//   vec2 sideRotated = vec2( side.x * c + side.y * -s, side.x * s + side.y * c);

//   pos.xy+= (sideRotated * vec2(vTex.zw) / uDisplaySize) * (pos.w * 2.0);  // add offset to the icon corner in screen space
//   gl_Position = pos;

   vec2 tt = vec2(vTex.xy + vTex.zw * vIcon.xy) / uAtlasSize;
   oTex= vec2(tt.x, 1.0 - tt.y);
}
// file:es30:bmd_point_rot.v.glsl


//{
//   vec4 pos = vpMat * vec4(vPos.xy, 0.0, 1.0);

//   float rot = float(vIcon.z) * (3.14159/128.0);
//   float s = sin(uHeading - rot);
//   float c = cos(uHeading - rot);
//   vec2 side = vec2(vIcon.xy) - 0.5;
//   vec2 sideRotated = vec2( side.x * c + side.y * -s, side.x * s + side.y * c);

//   pos.xy+= (sideRotated * vec2(vTex.zw) / uDisplaySize) * (pos.w * 2.0);  // add offset to the icon corner in screen space
//   gl_Position = pos;

//   vec2 tt = vec2(vTex.xy + vTex.zw * vIcon.xy) / uAtlasSize;
//   oTex= vec2(tt.x, 1.0 - tt.y);
//}
