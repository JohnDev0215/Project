// shader: texstyle_line_lit.vs

uniform mat4 uVPMat;

attribute vec4 aPos;       // xy = vertex position, zw = vertex offset direction
attribute vec4 aStyleMeta; // xy = style-tex offset; z = maximal scale, w = normalized coords for vertex side (for alpha-blending at the border)
attribute vec4 aSpecialMeta; // x = line-len-offset, y = rev-line-len-offset, xy = style-tex offset of the master element (for overlays)
attribute vec4 aOffsetMeta; // x: offsetType, y: offsetValue/factor, zw: offsetDirection
attribute vec2 aOffsetStyleMeta; // xy = OffsetRoad style-tex offset;

uniform vec4  uStyleOff;  // xy - relative appearence offset, zw - relative width offset in style texture
uniform vec2  uStyleTexelDim;
uniform float uStyleTexScaleOff;
uniform float uWidthFactor;
uniform float uPixelFactor;
uniform float uPitch;

// x: enabled, y: kA, z: kD, w: kS
uniform vec4  uLightingParamsBMD;
uniform float uLightingEnabled;
uniform vec3  uSunLightDirection;
uniform vec3  uSunLightColor;
uniform float uSunLightIntensity;

uniform sampler2D styleTex;

varying vec4  vCol;
varying vec3  vLit;
varying vec2  vSide;  // x: (linewidth + 1) * normalized-side-factor; y: linewidth


void main(void)
{
   vec2 offsetToExtra = vec2( uStyleTexelDim.x, 0);
   vec2 styleBaseAddr = aStyleMeta.xy + vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);

   vec4 styleAppear= texture2DLod( styleTex, styleBaseAddr + offsetToExtra + uStyleOff.xy, 0.0);
//   vTexTile = %Texel2Float%.0 * styleAppear.xy; // texture2DLod is not accepted by iMX6
//   vLineTexGap= styleAppear.z * %Texel2Float%.0 / 10.0;  // "/ 10.0" - restore packing trasformation

   float halfWidth= styleAppear.w * %Texel2Float%.0 * 0.5;

   vSide = vec2(aStyleMeta.w * (halfWidth * 2.0 + 1.0), halfWidth * 2.0);

   vec2 lineOffset = vec2(0.0, 0.0);
   if (aOffsetMeta.x == 1.0)
   {
      lineOffset = aOffsetMeta.zw * (aOffsetMeta.y * uWidthFactor * uPixelFactor);
   }
   if (aOffsetMeta.x == 2.0)
   {
      vec2 offsetToExtra = vec2( uStyleTexelDim.x, 0);
      vec2 styleBaseAddr = aOffsetStyleMeta.xy + vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);
      vec4 styleAppear= texture2DLod( styleTex, styleBaseAddr + offsetToExtra, 0.0);

      float halfWidth= styleAppear.w * %Texel2Float%.0 * 0.5 * aOffsetMeta.y;
      float fac = (halfWidth + (0.5 * step(0.1, halfWidth))) * uWidthFactor * uPixelFactor;
      lineOffset = aOffsetMeta.zw * fac;
   }

   float fac = (halfWidth + (0.5 * step(0.1, halfWidth))) * uWidthFactor * uPixelFactor;
   vec2 pos = vec2(aPos.xy + aPos.zw * fac + lineOffset);
   gl_Position = uVPMat * vec4(pos, 0.0, 1.0);

   vCol= texture2DLod( styleTex, styleBaseAddr + uStyleOff.xy, 0.0); // texture2DLod is not accepted by iMX6

   if((uLightingParamsBMD.x * uLightingEnabled) > 0.0)
   {
      float NdotL = max(uSunLightDirection.z, 0.0);
      float coeff = NdotL * (uLightingParamsBMD.z * uSunLightIntensity) + uLightingParamsBMD.y;
      vLit= vCol.rgb * uSunLightColor * coeff;
   }
   else
      vLit= vec3(1.0);
}
// file:texstyle_line_lit.v.glsl
