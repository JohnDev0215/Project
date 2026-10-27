#version %GLver%
#ifdef GL_ES
precision mediump float;
#endif

// shader: texstylevs_area_tex.fs

uniform sampler2D areaAtlas;
//uniform sampler2D styleTex;

uniform vec2  uAtlasTexelDim;     // 1 / texture width, 1 / texture hegith
uniform vec2  uAtlasTileSize;     // size of tile in px
uniform vec2  uAtlasGridSize;     // the granularity of the virtual grid in atlas for tile alignment (default 8x8)

in vec2 vTex;
in vec2 vTexTile;
in vec4 vCol;
in vec3 vLit;

out vec4 fragmentColor;

void main(void)
{
   vec4 color;
   if(vTexTile.x < 250.0)
   {
      vec2 ts2 = uAtlasTileSize - vec2(4.0);
      vec2 tc = vec2(fract(vTex.s), fract(vTex.t));
      tc = (vTexTile * uAtlasGridSize + tc * ts2 + 2.0) * uAtlasTexelDim;
      color = texture( areaAtlas, tc);
//      float s = (vTexTile.s * uTileSize + fract(vTex.s) * ts2 + 0.5) * uAtlasTexelDim.x;
//      float t = (vTexTile.t * uTileSize + fract(vTex.t) * ts2 + 0.5) * uAtlasTexelDim.y;
//      color = texture2D( areaAtlas, vec2( s, t));
   }
   else
      color = vCol;

   color.rgb *= vLit;

   fragmentColor = color;
}
// file:es30:texstylevs_area_tex.f.glsl
