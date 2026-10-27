#ifdef GL_ES
precision mediump float;
#endif

uniform mat4  uMatrix;
uniform float uWidthFactor;
uniform vec2  uStyleTexelDim;
uniform float uStyleTexScaleOff;

attribute vec3 aPos;
attribute vec4 aOffset;
attribute vec4 aStyleMeta;

uniform sampler2D styleTex;

varying vec4 vCol;
varying vec2 vTexTile;
varying vec2 vLineOffset;
varying vec3 vSide;
varying float vLineTexGap;

void main(void)
{
   vec2 offsetToExtra = vec2( uStyleTexelDim.x, 0);
   vec2 offsetByScale = vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);
   vec2 styleBaseAddr = aStyleMeta.xy + offsetByScale;

   vec4 styleAppear= texture2DLod( styleTex, styleBaseAddr + offsetToExtra, 0.0);
   vLineTexGap= styleAppear.z * %Texel2Float%.0 / 10.0;  // "/ 10.0" - restore packing trasformation
   vTexTile = %Texel2Float%.0 * styleAppear.xy; // texture2DLod is not accepted by iMX6

   float halfWidth = styleAppear.w * %Texel2Float%.0 * 0.5;

   float OuterWidth= abs(aOffset.w);

   vSide = vec3(aStyleMeta.w * (halfWidth * 2.0 + 1.0), halfWidth * 2.0, aStyleMeta.w);

   vLineOffset= vec2(aPos.z, 0);

   gl_Position= uMatrix * vec4(aPos.xy + aOffset.xy * (OuterWidth * uWidthFactor), 0.0, 1.0);

   vCol= texture2DLod( styleTex, styleBaseAddr, 0.0); // texture2DLod is not accepted by iMX6
}
