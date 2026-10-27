uniform mat4 vpMat;

attribute vec2 aPos;
attribute vec4 aParam;     // xy = vertex offset direction, zw = normalilzed coords for vertex (for alpha-blending at the border)
attribute vec4 aStatus;    // routeStatus(bit0=offroad, bit1=odd vertex index, bit2=AD2, bit3=RNC), uLineIndex, uSegmentIndex
attribute vec2 aSpecialMeta;
attribute vec4 aStyleMeta;
attribute vec4 aTiColor;
attribute vec4 aTiBorderColor;

// global uniforms
uniform vec2      uStyleTexelDim;
uniform float     uStyleTexScaleOff;
uniform float     uWidthFactor;
uniform float     uPixelFactor;
uniform float     uPitch;
uniform vec3      uSunLightDirection;
uniform sampler2D styleTex;


// uniforms
uniform float uLineIndex;
uniform float uSegmentIndex;
uniform float uRenderState;
uniform vec4  uStartPos;
uniform float uBorderFactor;
uniform float uScaleFactor;

// varying
varying vec4  vCol;
varying vec2  vTex;
varying vec2  vTexTile;
varying vec2  vLineOffset;
varying float vDotLine;
varying float  vLineWidth;
varying float  vWidthFactor; // pass uniform as varying to allow different precision definitions in vs & fs
varying vec2 vNormal;  //normal of two points(from point and to point)
varying float  vBorderFactor;
varying vec4  vBorderCol;
varying float  vEnableTi;

void main(void)
{
   vDotLine = 0.0;
   vTexTile = vec2(0.0,0.0);
   vLineOffset = vec2(0.0,0.0);
   vLineWidth = 1.0;
   vWidthFactor = uScaleFactor;
   vBorderFactor = uBorderFactor;

   vec2 styleTexOffset= aStyleMeta.xy + vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);
   vec4 styleExtra= texture2DLod( styleTex, styleTexOffset + vec2( uStyleTexelDim.x, 0), 0.0); // texture2DLod is not accepted by iMX6
   float width= styleExtra.w * 256.0 * 0.5; // use the half width of the offset of the midline

//   When this code is activated a texture with is set in the styles will not be shown
//   So I commented this code out to show only the inner blue/gray color which is actually configured in styles.
//   K.Skibbe, 11th september 2019
   if (mod(aStatus.x + 0.5, 16.0) > 8.0) //RNC (RoadNetworkCoverage...???)
   {
      vDotLine = 1.0;
      vTexTile = 255.0 * styleExtra.xy;
      vLineWidth = width * 2.0;
      vLineOffset = aSpecialMeta;
   }

   vec4 pos;
   float fac = uWidthFactor * width * uPixelFactor;
   if (mod(uRenderState + 0.5, 2.0) > 1.0 && (aStatus.z < (uSegmentIndex - 0.5) || (aStatus.z < (uSegmentIndex + 0.5) && aStatus.z > (uSegmentIndex - 0.5) && aStatus.y > (uLineIndex + 0.5)))) // consumed segment
   {
      vec2 side = vec2(0.0, 0.0);
      if(aParam.z > 0.5)
      {
         side = vec2(-uStartPos.z, -uStartPos.w);
      }
      else // (> 0.5) means ==1.0
      {
        side = vec2(uStartPos.z, uStartPos.w);
      }
      side  = side * (1. + 0.2);
      pos = vec4(uStartPos.xy + side.xy * fac, 0.0, 1.0);
      if(vDotLine > 0.5)
      {
         vLineOffset.x -= distance(aPos.xy,uStartPos.xy);
      }
   }
   else
   {
      pos = vec4(aPos.xy + aParam.xy * fac, 0.0, 1.0);
   }

   gl_Position = vpMat * pos;

   vec2 colorOffset= vec2( uStyleTexelDim.x * 2.0 * aStyleMeta.z, 0);

   if (aStatus.w > 0.5) //enable TI
   {
      vCol=aTiColor;
   }
   else {
      vCol= texture2DLod( styleTex, styleTexOffset + colorOffset, 0.0); // texture2DLod is not accepted by iMX6
   }

   vTex = aParam.zw;
   vNormal = normalize(aParam.xy);
   vBorderCol = vec4(aTiBorderColor.rgb, 1.);
   vEnableTi = aStatus.w;
   if((styleExtra.z * 256.0) > 0.5)
   {
      vLineOffset.x *= 0.5;
   }
}
// file:texstyle_route_tex.v.glsl
