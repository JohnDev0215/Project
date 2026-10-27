uniform mat4 uMatrix;
uniform float uWidthFactor;

uniform vec2  uStyleTexelDim;        // 1 / texture width, 1 / texture height
uniform float uStyleTexScaleOff;  // x offset in the style-texture (according to the current scale)

uniform sampler2D styleTex;

attribute vec4 aPosition;  // xy: pos, zw: style-atlas-base-offset
attribute vec4 aTextureDescription;  // xy: atlas size, zw: tile size
attribute vec2 aTexturePosition;

varying vec4 vTileMeta;  // xy: start offset, zw: effective tile size
varying vec2 vTexUV; // pass through the UV texture coordinates

void main(void)
{
   gl_Position= uMatrix * vec4(aPosition.xy, 0.0, 1.0);

   vec2 styleExtraAddr= aPosition.zw + vec2( uStyleTexScaleOff + uStyleTexelDim.x * 1.5, uStyleTexelDim.y * 0.5);
   vec4 styleExtra= texture2DLod( styleTex, styleExtraAddr, 0.0);

   vec2 tilePos = styleExtra.xy * (8.0 * 255.0); // restore atlas tile coordinates (see styles.cpp:genericTexPowReduction & fillTexStyleEntry())

   // assume tile layout with 2px inset on each side to guarantee safe texture filtering while accessing textures from atlas
   vTileMeta = vec4( (tilePos.x + 2.0) / aTextureDescription.x,
                     (aTextureDescription.y - (tilePos.y + aTextureDescription.w) + 2.0) / aTextureDescription.y,
                     (aTextureDescription.z - 4.0) / aTextureDescription.x,
                     (aTextureDescription.w - 4.0) / aTextureDescription.y);

   vTexUV= aTexturePosition.xy / uWidthFactor;
}
