// shader: packed_line_tex_lit.vs, see BmdLinePackerES20TexPacked for vertex attribute packaging

uniform mat4 uVPMat;

attribute vec4 aPos;
attribute vec4 aTex;

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
uniform float uSkipThinLines;

varying vec4 vCol;
varying vec2 vTexTile;
varying vec2 vLineOffset;
varying vec3 vLit;
varying vec3 vSide;  // x: linewidth extended by 0.5pixel * normal-side-factor; y: line-width; z:normal-side-factor
varying float vLineTexGap;
varying float vWidthFactor;

const float add_8bits = 256.0;
const float add_14bits = 16384.0;
const float add_15bits = 32768.0;
const float skip_8bits = 1.0 / add_8bits;
const float skip_14bits = 1.0 / add_14bits;
const float skip_15bits = 1.0 / add_15bits;

void main(void)
{
   vec2 data = aPos.zw;
   float sideId = floor( data.x * skip_15bits);
   data.x -= sideId * add_15bits;

   float thinLine = floor( data.x * skip_14bits);
   data.x -= thinLine * add_14bits;

   vec2 styleTexOffset = floor( data * vec2(skip_8bits));
   data -= styleTexOffset * (add_8bits);
   styleTexOffset *= uStyleTexelDim;

   vec2 sideNorm = (data - 127.0) / 127.0;

   vec2 offsetToExtra = vec2( uStyleTexelDim.x, 0);
   vec2 offsetByScale = vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);
   vec2 styleBaseAddr = styleTexOffset + offsetByScale;

   vec4 styleAppear= texture2DLod( styleTex, styleBaseAddr + offsetToExtra + uStyleOff.xy, 0.0); // texture2DLod is not accepted by iMX6
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

   vSide = vec3(sideId * (halfWidth * 2.0 + 1.0), halfWidth * 2.0, sideId);
   vLineOffset= aTex.xy;
   vWidthFactor= uWidthFactor;

   float fac = (halfWidth + (0.5 * step(0.1, halfWidth))) * uWidthFactor * uPixelFactor;
   vec2 pos = vec2(aPos.xy + sideNorm * fac);
   vec4 tansformedPos = uVPMat * vec4(pos, 0.0, 1.0);
   gl_Position = mix(tansformedPos,vec4(0.0,0.0,0.0,0.0), thinLine * uSkipThinLines );

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

// file:packed_line_tex_lit.v.glsl
