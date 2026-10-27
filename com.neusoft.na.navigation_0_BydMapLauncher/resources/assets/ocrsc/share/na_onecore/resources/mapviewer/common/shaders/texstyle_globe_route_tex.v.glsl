// file:texstyle_globe_route_lit.v.glsl

attribute vec4 aPosPolar;  //w:R of TI color
attribute vec4 aNrmPolar;  //w:G of TI color
attribute vec4 aParam;     // xy = vertex offset direction, zw = normalilzed coords for vertex (for alpha-blending at the border)
attribute vec4 aStatus;    // routeStatus(bit0=offroad, bit1=odd vertex index, bit2=AD2, bit3=RNC), uLineIndex, uSegmentIndex
attribute vec4 aSpecialMeta; //z:B of TI color w:A of TI color
attribute vec4 aStyleMeta;

// global uniforms
uniform sampler2D styleTex;
uniform mat4      globeVpMat;
uniform vec2      uStyleTexelDim;
uniform vec3      uSunLightDirection;
uniform float     uPixelFactor;
uniform float     globeWidthFactor;
uniform float     uStyleTexScaleOff;

// local uniforms
uniform float uLineIndex;
uniform float uSegmentIndex;
uniform float uRenderState;
uniform vec3  uStartPosPolar;

varying vec4  vCol;
varying vec2  vTex;
varying vec2  vTexTile;
varying vec2  vLineOffset;
varying float vDotLine;
varying float vLineWidth;

void main(void)
{
   vec2 styleTexOffset= aStyleMeta.xy + vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);
   vec4 styleExtra= texture2DLod( styleTex, styleTexOffset + vec2( uStyleTexelDim.x, 0), 0.0); // texture2DLod is not accepted by iMX6
   float width= styleExtra.w * 256.0;
   vec4 pos;

   vDotLine = 0.0;
   vLineWidth = width;
   vTexTile = vec2(0.0,0.0); // FIXME!
   vLineOffset = vec2(0.0,0.0);  // FIXME!

   if (mod(aStatus.x + 0.5, 16.0) > 8.0) //RNC -> RoadNetworkCoverage???
   {
      vDotLine = 1.0;
      vTexTile = 255.0 * styleExtra.xy;
      vLineWidth = width * 2.0;
      vLineOffset = aSpecialMeta.xy;
   }

   float fac = globeWidthFactor * width * 0.5 * uPixelFactor; // use the half width of the offset of the midline
   if (mod(uRenderState + 0.5, 2.0) > 1.0 && (aStatus.z < uSegmentIndex - 0.5 || (aStatus.z < uSegmentIndex + 0.5 && aStatus.z > uSegmentIndex - 0.5 && aStatus.y > uLineIndex + 0.5))) // consumed segment
   {
      pos = vec4(uStartPosPolar + aNrmPolar.xyz * fac, 1.0);
      if(vDotLine > 0.5)
      {
         vLineOffset.x -= distance(aPosPolar.xy,uStartPosPolar.xy);
      }
   }
   else
   {
      pos = vec4(aPosPolar.xyz + aNrmPolar.xyz * fac, 1.0);
   }

   gl_Position = globeVpMat * pos;

   vec2 colorOffset= vec2( uStyleTexelDim.x * 2.0 * aStyleMeta.z, 0);

   if (aStatus.w > 0.5) //enable TI
   {
       vCol=vec4(aPosPolar.w, aNrmPolar.w, aSpecialMeta.z, aSpecialMeta.w);
   }
   else {
      vCol= texture2DLod( styleTex, styleTexOffset + colorOffset, 0.0); // texture2DLod is not accepted by iMX6
   }

   vTex = aParam.zw;
}
// file:texstyle_globe_route_tex.v.glsl
