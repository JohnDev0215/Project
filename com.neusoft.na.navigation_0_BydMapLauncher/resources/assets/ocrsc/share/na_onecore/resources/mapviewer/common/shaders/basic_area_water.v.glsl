attribute vec2 aPos;
attribute vec4 aCol;
attribute vec2 aTex;
attribute vec3 aTexTile;

uniform mat4 vpMat;
uniform vec3 uCameraPosition;

varying vec4 vCol;
varying vec2 vTex;
varying vec3 vTexTile;
varying vec3 vViewDirWS;

void main(void)
{
   gl_Position = vpMat * vec4(aPos.xy, 0.0, 1.0);

   vCol = aCol;
   vTex = aTex;
   vTexTile = aTexTile;
   vViewDirWS = normalize(vec3(aPos.xy, 0.0) - uCameraPosition);
}
// file:basic_area_water.v.glsl
