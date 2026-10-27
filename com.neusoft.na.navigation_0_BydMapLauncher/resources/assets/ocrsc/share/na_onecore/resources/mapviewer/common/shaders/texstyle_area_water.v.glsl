uniform mat4 uVPMat;

uniform float uStyleTexScaleOff;  // x offset in the style-texture (according to the current scale)
uniform vec2 uStyleTexelDim;        // 1 / texture width, 1 / texture height
uniform vec3 uCameraPosition;

uniform sampler2D styleTex;

attribute vec3 aPos;
attribute vec2 aTex;         // texture coordinates in tile

varying vec2 vTex;
varying vec2 vTexTile;
varying vec4 vCol;

varying vec3 vViewDirWS;

const float add_8bits = 256.0;
const float skip_8bits = 1.0 / add_8bits;

void main(void)
{
   vec2 styleTexData = vec2( aPos.z, floor( aPos.z * skip_8bits));
   styleTexData.x -= styleTexData.y * add_8bits;

   gl_Position = uVPMat * vec4(aPos.xy, 0.0, 1.0);

   vec2 styleTexOffset= styleTexData * uStyleTexelDim + vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);
   vec4 styleExtra= texture2DLod( styleTex, styleTexOffset + vec2( uStyleTexelDim.x, 0), 0.0); // texture2DLod is accepted by iMX6
   if (styleExtra.r == 1.0)
   {
      vCol= texture2DLod( styleTex, styleTexOffset, 0.0); // texture2DLod is accepted by iMX6
   }
   else
   {
      vCol= vec4(1.0, 0.0, 1.0, 1.0); // set to warn color (shall be replaced by texel from fs)
   }

   vTexTile = styleExtra.rg * %Texel2Float%.0;
   vTex = aTex;
   vViewDirWS = normalize(vec3(aPos.xy, 0.0) - uCameraPosition);
}
// file:texstyle_area_water.v.glsl
