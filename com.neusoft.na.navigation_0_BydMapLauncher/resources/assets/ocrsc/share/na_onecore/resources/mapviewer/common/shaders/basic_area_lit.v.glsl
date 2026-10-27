uniform mat4 vpMat;

attribute vec2 aPos;
attribute vec4 aCol;
attribute vec2 aTex;
attribute vec2 aTexTile;

uniform vec3 uSunLightDirection;

varying vec4 vCol;
varying vec2 vTex;
varying vec2 vTexTile;

varying float vNdotL;

void main(void)
{
   gl_Position = vpMat * vec4(aPos.xy, 0.0, 1.0);

   vCol = aCol;
   vTex = aTex;
   vTexTile = aTexTile;
   vNdotL = max(uSunLightDirection.z, 0.0);
}
// file:basic_area_lit.v.glsl
