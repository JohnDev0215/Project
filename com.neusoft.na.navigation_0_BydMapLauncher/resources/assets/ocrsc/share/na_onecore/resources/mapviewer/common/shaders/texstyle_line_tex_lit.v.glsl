// shader: texstyle_line_tex.vs

uniform mat4 uVPMat;

attribute vec4 aPos;       // xy = vertex position, zw = vertex offset direction
attribute vec4 aStyleMeta; // xy = style-tex offset; z = maximal scale, w = normalized coordinates for vertex side (for alpha-blending at the border)
attribute vec4 aSpecialMeta; // x = line-len-offset, y = rev-line-len-offset, xy = style-tex offset of the master element (for overlays)

uniform vec4  uStyleOff;  // xy - relative appearence offset, zw - relative width offset in style texture
uniform vec2  uStyleTexelDim;
uniform float uStyleTexScaleOff;
uniform float uWidthFactor;
uniform float uPixelFactor;
uniform float uPitch;
uniform float uHighAliasing;

// x: enabled, y: kA, z: kD, w: kS
uniform vec4  uLightingParamsBMD;
uniform float uLightingEnabled;
uniform vec3  uSunLightDirection;
uniform vec3  uSunLightColor;
uniform float uSunLightIntensity;

uniform sampler2D styleTex;

varying vec4  vCol;
varying vec2  vTexTile;
varying vec2  vLineOffset;
varying vec3  vLit;
varying vec3  vSide;  // x: (linewidth + 1) * normalized-side-factor; y: linewidth; z:normal-side-factor
varying vec2  vNormal;
varying float vLineTexGap;
varying float vWidthFactor; // pass uniform as varying to allow different precision definitions in vs & fs
varying float vLineWidth;
varying float vHighAliasing;

void main(void)
{
   vWidthFactor = uWidthFactor;
   vHighAliasing = uHighAliasing;
   vec2 offsetToExtra = vec2( uStyleTexelDim.x, 0);
   vec2 offsetByScale = vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);
   vec2 styleBaseAddr = aStyleMeta.xy + offsetByScale;

   vec4 styleAppear= texture2DLod( styleTex, styleBaseAddr + offsetToExtra + uStyleOff.xy, 0.0);
   vLineTexGap= styleAppear.z * %Texel2Float%.0 / 10.0;  // "/ 10.0" - restore packing trasformation
   vTexTile = %Texel2Float%.0 * styleAppear.xy; // texture2DLod is not accepted by iMX6

   float halfWidth;
   if (uStyleOff.xy != uStyleOff.zw) {  // we have an overlay -> get the width from the master element
      vec4 widthMeta= texture2DLod( styleTex, styleBaseAddr + offsetToExtra + uStyleOff.zw, 0.0); // texture2DLod is not accepted by iMX6
      halfWidth= widthMeta.w * %Texel2Float%.0 * 0.5;
   }
   else {
      halfWidth= styleAppear.w * %Texel2Float%.0 * 0.5;
   }

   vSide = vec3(aStyleMeta.w * (halfWidth * 2.0 + 1.0), halfWidth * 2.0, aStyleMeta.w);
   vLineOffset= aSpecialMeta.xy;
   vLineWidth= styleAppear.w * 255.0;

   float fac = (halfWidth + (0.5 * step(0.1, halfWidth))) * uWidthFactor * uPixelFactor;
   float scale = 1.;
   if (uHighAliasing > 0.5)
   {
      scale = clamp(1.1 * uPitch, 0.9, 1.1);
   }
   else
   {
      scale = 1.3;
   }

   vec2 pos = vec2(aPos.xy + aPos.zw * fac * scale);
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
   vNormal = normalize(vSide.xy);
}

// file:texstyle_line_tex_lit.v.glsl
