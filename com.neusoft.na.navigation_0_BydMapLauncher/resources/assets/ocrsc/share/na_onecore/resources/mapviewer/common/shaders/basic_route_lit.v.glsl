uniform mat4 vpMat;
uniform mat4 globeVpMat;

attribute vec3 vPos;
attribute vec4 vParam;     // xy = vertex offset direction, zw = normalilzed coords for vertex (for alpha-blending at the border)
attribute vec4 vStatus;
attribute vec3 vPosPolar;
attribute vec3 vNrmPolar;

uniform float uWidthFactor;
uniform float uPixelFactor;
uniform float uPitch;
uniform float posIndex;
uniform float uRenderState;
uniform vec4  startPos;
uniform vec4  onRoadColor;
uniform vec4  offRoadColor;
uniform float onRoadWidth;
uniform float offRoadWidth;
uniform vec3  startPosPolar;
uniform vec3  startNrmPolar;

uniform vec3 uSunLightDirection;

varying vec3 oCol;
varying vec2 oTex;
varying float nDotL;

void main(void)
{
   vec4 pos;

   if (mod(uRenderState + 0.5, 4.0) > 2.0) // globe view
   {
      float fac = onRoadWidth * uPixelFactor;

      if (mod(uRenderState + 0.5, 2.0) > 1.0 && posIndex + 0.5 > vStatus.y) // clipped segment
      {
         if (mod(vStatus.x + 0.5, 4.0) > 2.0)
         {
            pos = vec4(startPosPolar + startNrmPolar * fac, 1.0);
         }
         else
         {
            pos = vec4(startPosPolar - startNrmPolar * fac, 1.0);
         }
      }
      else
      {
         pos = vec4(vPosPolar + vNrmPolar * fac, 1.0);
      }

      gl_Position = globeVpMat * pos;
   }
   else
   {
      float fac = onRoadWidth * uPixelFactor;

      if (mod(uRenderState + 0.5, 2.0) > 1.0 && posIndex + 0.5 > vStatus.y) // clipped segment
      {
         if (mod(vStatus.x + 0.5, 4.0) > 2.0)
         {
            pos = vec4(startPos.xy + startPos.zw * fac, 0.0, 1.0);
         }
         else
         {
            pos = vec4(startPos.xy - startPos.zw * fac, 0.0, 1.0);
         }
      }
      else
      {
         pos = vec4(vPos.xy + vParam.xy * fac, 0.0, 1.0);
      }

      gl_Position = vpMat * pos;
   }

   nDotL = max(uSunLightDirection.z, 0.0);
   oCol = onRoadColor.xyz;
   oTex = vParam.zw;
}
// file:basic_route_lit.v.glsl
