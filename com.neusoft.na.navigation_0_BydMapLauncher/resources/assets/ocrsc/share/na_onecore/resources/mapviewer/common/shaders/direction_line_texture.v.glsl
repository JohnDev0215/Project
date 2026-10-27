#ifdef GL_ES
precision mediump float;
#endif

uniform mat4  uMatrix;
uniform float uWidthFactor;
uniform vec2  uStyleTexelDim;
uniform float uStyleTexScaleOff;
uniform vec2 uDisplaySize;
uniform float uPixelFactor;

attribute vec3 aPos;
attribute vec4 aOffset;
attribute vec4 aStyleMeta;

uniform sampler2D styleTex;

varying vec4 vCol;
varying vec2 vTexTile;
varying vec2 vLineOffset;
varying float vLineTexGap;
varying float vWidthFactor;
varying vec2  vTex;
varying float vLineWidth;

void main(void)
{
   vec2 offsetToExtra = vec2( uStyleTexelDim.x, 0);
   vec2 offsetByScale = vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);
   vec2 styleBaseAddr = aStyleMeta.xy + offsetByScale;

   vec4 styleAppear= texture2DLod( styleTex, styleBaseAddr + offsetToExtra, 0.0);
   vLineTexGap= styleAppear.z * 255.0 / 10.0;  // "/ 10.0" - restore packing trasformation
   vTexTile = 255.0 * styleAppear.xy; // texture2DLod is not accepted by iMX6

   float halfWidth = styleAppear.w * 255.0 * 0.5;

   float OuterWidth= abs(aOffset.w);

   vLineOffset= vec2(aPos.z, 0);

   vWidthFactor = uWidthFactor;
   vTex = aStyleMeta.zw;
   vLineWidth = halfWidth * 2.0;
   float fac = uWidthFactor * halfWidth * 2.0 * uPixelFactor;
   vec4 pos = vec4(aPos.xy + aOffset.xy * fac, 0.0, 1.0);
   gl_Position = uMatrix * pos;

   vCol= texture2DLod( styleTex, styleBaseAddr, 0.0); // texture2DLod is not accepted by iMX6
}
