// shader: packed_line_lit.vs, see BmdLinePackerES20Packed for vertex attribute packaging

uniform mat4 uVPMat;

attribute vec4 aPos;       // xy = vertex position, zw = vertex offset direction

uniform vec4  uStyleOff;  // xy - relative appearence offset, zw - relative width offset in style texture
uniform vec2  uStyleTexelDim;
uniform float uStyleTexScaleOff;
uniform float uWidthFactor;
uniform float uPixelFactor;
uniform float uPitch;
uniform float uHighAliasing;

uniform sampler2D styleTex;
uniform float uSkipThinLines;

varying vec4  vCol;
varying vec2  vSide;
varying vec2  vNormal;
varying float vHighAliasing;
varying float vBorder;

const float add_8bits = 256.0;
const float add_14bits = 16384.0;
const float add_15bits = 32768.0;
const float skip_8bits = 1.0 / add_8bits;
const float skip_14bits = 1.0 / add_14bits;
const float skip_15bits = 1.0 / add_15bits;

void main(void)
{
   vHighAliasing = uHighAliasing;
   if(uPitch != 0.0)
   {
      vHighAliasing = 0.0;
   }
   vBorder = 1.0;
   if(uStyleOff.x < (2.0 * uStyleTexelDim.x))
   {
      vBorder = 0.0;
   }
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
   vec2 styleBaseAddr = styleTexOffset + vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);

   vec4 styleAppear= texture2DLod( styleTex, styleBaseAddr + offsetToExtra + uStyleOff.xy, 0.0); // texture2DLod is not accepted by iMX6
   float halfWidth= styleAppear.w * %Texel2Float%.0 * 0.5;

   vSide = vec2(sideId * (halfWidth * 2.0 + 1.0), halfWidth * 2.0);

   float fac = (halfWidth + (0.5 * step(0.1, halfWidth))) * uWidthFactor * uPixelFactor;
   vec2 pos = vec2(aPos.xy + sideNorm * fac);
   vec4 tansformedPos = uVPMat * vec4(pos, 0.0, 1.0);
   gl_Position = mix(tansformedPos,vec4(0.0,0.0,0.0,0.0), thinLine * uSkipThinLines);

   vCol= texture2DLod( styleTex, styleBaseAddr + uStyleOff.xy, 0.0); // texture2DLod is not accepted by iMX6
   //vCol = vec4(1.0,0.0,1.0,1.0);
   vNormal = normalize(vSide.xy);

}
// file:packed_line_lit.v.glsl
