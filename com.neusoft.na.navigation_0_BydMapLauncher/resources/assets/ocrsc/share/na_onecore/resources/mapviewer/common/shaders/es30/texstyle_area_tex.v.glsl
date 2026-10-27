#version %GLver%
// shader: texstylevs_area_tex.vs

uniform mat4  uVPMat;
uniform float uScaleIndex;

// x: enabled, y: kA, z: kD, w: kS
uniform vec4  uLightingParamsBMD;
uniform float uLightingEnabled;
uniform vec3  uSunLightColor;
uniform vec3  uSunLightDirection;
uniform float uSunLightIntensity;

uniform sampler2D styleTex;

in vec4 aPos;          // xy: vertex pos; zw: texture coordinates in tile
in ivec2 aTex;         // xy: texture coordinates in style-texture

out vec2 vTex;
out vec2 vTexTile;
out vec4 vCol;
out vec3 vLit;

//-- the version below uses vertex texture access (unsupported by iMX6 by driver @2014.09.01) --
//== test this version on non iMX targets ==


void main(void)
{
   gl_Position = uVPMat * vec4(aPos.xy, 0.0, 1.0);

   vec4 styleExtra= texelFetchOffset( styleTex, aTex, 0, ivec2(1,0));
   if (styleExtra.r == 1.0) {
      vCol= texelFetch( styleTex, aTex, 0);
   }
   else {
      vCol= vec4(1.0, 0.0, 1.0, 1.0); // set to warn color (shall be replaced by texel from fs)
   }

   vTexTile = styleExtra.rg * %Texel2Float%.0;
   if (uScaleIndex <= 3.0)
      vTex = (2.0 * (4.0 - uScaleIndex)) * aPos.zw;  // hack: avoid extremely scaled pixels of area-textures
   else
      vTex = aPos.zw;
   if((uLightingParamsBMD.x * uLightingEnabled) > 0.0) {
      float NdotL = max( uSunLightDirection.z, 0.0);
      vLit= uLightingParamsBMD.y + NdotL * (uLightingParamsBMD.z * uSunLightIntensity * uSunLightColor);
   }
   else
      vLit = vec3(1.0);
}
// file:es30:texstylevs_area_tex.v.glsl
