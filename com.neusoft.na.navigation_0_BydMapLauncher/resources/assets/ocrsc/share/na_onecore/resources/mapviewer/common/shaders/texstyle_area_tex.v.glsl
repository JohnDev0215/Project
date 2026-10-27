// shader: texstyle_area_tex.vs

uniform mat4  uVPMat;
uniform vec2  uStyleTexelDim;        // 1 / texture width, 1 / texture height
uniform float uStyleTexScaleOff;  // x offset in the style-texture (according to the current scale)
uniform float uScaleIndex;

// x: enabled, y: kA, z: kD, w: kS
uniform vec4  uLightingParamsBMD;
uniform float uLightingEnabled;
uniform vec3  uSunLightColor;
uniform vec3  uSunLightDirection;
uniform float uSunLightIntensity;

uniform sampler2D styleTex;

attribute vec3 aPos;
attribute vec2 aTex;         // texture coordinates in tile

varying vec2 vTex;
varying vec2 vTexTile;
varying vec4 vCol;
varying vec3 vLit;

const float add_8bits = 256.0;
const float skip_8bits = 1.0 / add_8bits;

//-- the version below uses vertex texture access (unsupported by iMX6 by driver @2014.09.01) --
//== test this version on non iMX targets ==

void main(void)
{
   vec2 styleTexData = vec2( aPos.z, floor( aPos.z * skip_8bits));
   styleTexData.x -= styleTexData.y * add_8bits;

   gl_Position = uVPMat * vec4(aPos.xy, 0.0, 1.0);

   vec2 styleTexOffset= styleTexData * uStyleTexelDim + vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);
   vec4 styleExtra= texture2DLod( styleTex, styleTexOffset + vec2( uStyleTexelDim.x, 0), 0.0); // texture2DLod is accepted by iMX6
   //vec4 styleExtra= texture2D( styleTex, styleTexOffset + vec2( uStyleTexelDim.x, 0.0));
   if (styleExtra.r == 1.0) {
      vCol= texture2DLod( styleTex, styleTexOffset, 0.0); // texture2DLod is accepted by iMX6
      //oCol= texture2D( styleTex, styleTexOffset);
   }
   else {
      vCol= vec4(1.0, 0.0, 1.0, 1.0); // set to warn color (shall be replaced by texel from fs)
   }

   vTexTile = styleExtra.rg * %Texel2Float%.0;
   if (uScaleIndex <= 3.0)
      vTex = (2.0 * (4.0 - uScaleIndex)) * aTex;  // hack: avoid extremely scaled pixels of area-textures
   else
      vTex = aTex;
   if((uLightingParamsBMD.x * uLightingEnabled) > 0.0) {
      float NdotL = max( uSunLightDirection.z, 0.0);
      vLit= uLightingParamsBMD.y + NdotL * (uLightingParamsBMD.z * uSunLightIntensity * uSunLightColor);
   }
   else
      vLit = vec3(1.0);

}
// file:texstyle_area_tex.v.glsl
