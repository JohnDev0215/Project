#ifdef GL_ES
precision mediump float;
#endif

// shader: texstyle_area_tex.fs

uniform sampler2D areaAtlas;
//uniform sampler2D styleTex;

uniform vec2  uAtlasTexelDim;     // 1 / texture width, 1 / texture hegith
uniform vec2  uAtlasTileSize;     // size of tile in px
uniform vec2  uAtlasGridSize;     // the granularity of the virtual grid in atlas for tile alignment (default 8x8)

varying vec2 vTex;
varying vec2 vTexTile;
varying vec4 vCol;
varying vec3 vLit;

//-- the version below uses vertex texture access (unsupported by iMX6 by driver @2014.09.01) --


void main(void)
{
   %FixFarClip%  // force clipping at the far plane (workaround for Viviante GC2000)

   vec4 color;
   if(vTexTile.x < 250.0)
   {
      vec2 effectiveTileSize = uAtlasTileSize - vec2(4.0); // consider 2-pixels tiled-atlas wrap-around border
      vec2 st = (vTexTile * uAtlasGridSize + fract(vTex) * effectiveTileSize + 2.0) * uAtlasTexelDim;
      //float s = (vTexTile.s * uAtlasGridSize.x + fract(vTex.s) * effectiveTileSize + 2.0) * uAtlasTexelDim.x;
      //float t = (vTexTile.t * uAtlasGridSize.y + fract(vTex.t) * effectiveTileSize + 2.0) * uAtlasTexelDim.y;
      color = texture2D( areaAtlas, st);
   }
   else
      color = vCol;

   color.rgb *= vLit;

   gl_FragColor = color;
}
// file:texstyle_area_tex.f.glsl
