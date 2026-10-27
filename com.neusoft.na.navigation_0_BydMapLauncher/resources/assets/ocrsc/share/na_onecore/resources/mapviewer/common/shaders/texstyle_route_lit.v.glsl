uniform mat4 vpMat;

attribute vec2    aPos;
attribute vec4    aParam;     // xy = vertex offset direction, zw = normalilzed coords for vertex (for alpha-blending at the border)
attribute vec3    aStatus;    // routeStatus(bit0=offroad, bit1=odd vertex index, bit2=AD2, bit3=RNC), uLineIndex, uSegmentIndex
attribute vec2    aSpecialMeta;
attribute vec4    aStyleMeta;

// global uniforms
uniform float     uPitch;
uniform float     uWidthFactor;
uniform float     uPixelFactor;
uniform float     uStyleTexScaleOff;
uniform vec2      uStyleTexelDim;
uniform sampler2D styleTex;

// uniforms
uniform float     uLineIndex;
uniform vec4      uStartPos;
uniform float     uRenderState;
uniform float     uSegmentIndex;

// varying
varying vec4      vCol;
varying vec2      vTex;
varying float     vDotLine;

void main(void)
{
   vDotLine = 0.0;

   vec2 styleTexOffset= aStyleMeta.xy + vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);
   vec4 styleExtra= texture2DLod( styleTex, styleTexOffset + vec2( uStyleTexelDim.x, 0), 0.0); // texture2DLod is not accepted by iMX6
   float width= styleExtra.w * 256.0 * 0.5; // use the half width of the offset of the midline

   if (mod(aStatus.x + 0.5, 16.0) > 8.0) //RNC (RoadNetworkCoverage...???)
   {
      vDotLine = 1.0;
   }

   vec4 pos;
   float fac = uWidthFactor * width * uPixelFactor;
   if (mod(uRenderState + 0.5, 2.0) > 1.0 && (aStatus.z < (uSegmentIndex - 0.5) || (aStatus.z < (uSegmentIndex + 0.5) && aStatus.z > (uSegmentIndex - 0.5) && aStatus.y > (uLineIndex + 0.5)))) // consumed segment
   {
      pos = vec4(uStartPos.xy + aParam.xy * fac, 0.0, 1.0);
   }
   else
   {
      pos = vec4(aPos.xy + aParam.xy * fac, 0.0, 1.0);
   }

   gl_Position = vpMat * pos;

   vec2 colorOffset= vec2( uStyleTexelDim.x * 2.0 * aStyleMeta.z, 0);
   vCol= texture2DLod( styleTex, styleTexOffset + colorOffset, 0.0); // texture2DLod is not accepted by iMX6
   vTex = aParam.zw;
}
// file:texstyle_route_lit.v.glsl
