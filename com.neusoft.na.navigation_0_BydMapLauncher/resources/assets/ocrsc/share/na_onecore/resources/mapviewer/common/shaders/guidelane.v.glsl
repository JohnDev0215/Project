uniform mat4 uVPMat;

attribute vec4 aPos;       // xyz = vertex position, w = 0
attribute vec4 aParam;     // xy = normalized vertex direction, z = texture-offset
attribute vec4 aStatus;    //
attribute vec2 aStyleMeta; // xy = style-tex offset;

//globe uniform
uniform vec2  uStyleTexelDim;
uniform float uStyleTexScaleOff;
uniform float uWidthFactor;
uniform float uPixelFactor;
// uniform float uPitch;
//uniform
uniform vec2  uStyleOffset;
uniform vec4  uRenderStatus;
uniform vec4  uCarsorPos;
uniform vec2  uCarsorPosHigh;

uniform vec2  uStyleAddr;
//sampler
uniform sampler2D styleTex;

varying vec4 vCol;
varying vec2 vTexTile;
varying vec2 vLineOffset;
varying vec2 vSide;

varying float vWidthFactor; // pass uniform as varying to allow different precision definitions in vs & fs

void main(void)
{
   vWidthFactor = uWidthFactor;
   vec2 offsetToExtra = vec2( uStyleTexelDim.x, 0);
   vec2 offsetByScale = vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);
   //vec2 styleBaseAddr = aStyleMeta.xy + offsetByScale;
   vec2 styleBaseAddr = uStyleAddr.xy + offsetByScale;

   vec4 styleAppear= texture2DLod( styleTex, styleBaseAddr + offsetToExtra + uStyleOffset.xy, 0.0);
   // vTexTile = %Texel2Float%.0 * styleAppear.xy;
   vTexTile = vec2(256.0, 0.0);//cancel texture sample

   float halfWidth;
   halfWidth= styleAppear.w * %Texel2Float%.0 * 0.5;

   vSide = vec2((halfWidth * 2.0 + 1.0), halfWidth * 2.0);
   vLineOffset= aParam.zw;

   float fac ;
   vec2  pos ;
   if(uRenderStatus.x > (0.5) && (( aStatus.y < (uRenderStatus.y - 0.5)) || ( (aStatus.y < (uRenderStatus.y + 0.5)) && (aStatus.y > (uRenderStatus.y - 0.5)) && aStatus.w < (uRenderStatus.w + 0.5))))                           
   {
      fac = (halfWidth + (0.5 * step(0.1, halfWidth))) * uWidthFactor * uPixelFactor;
      if(aStatus.x < 0.5)
         pos  = vec2(uCarsorPos.xy + -uCarsorPos.zw * fac);
      else
         pos  = vec2(uCarsorPos.xy + uCarsorPos.zw * fac);

      gl_Position = uVPMat * vec4(pos, uCarsorPosHigh.x, 1.0);
   }
   else
   {
      fac = (halfWidth + (0.5 * step(0.1, halfWidth))) * uWidthFactor * uPixelFactor;
      pos  = vec2(aPos.xy + aParam.xy * fac);
      gl_Position = uVPMat * vec4(pos, aPos.z, 1.0);
   }



   vCol= texture2DLod( styleTex, styleBaseAddr + uStyleOffset.xy, 0.0); // texture2DLod is not accepted by iMX6
}
