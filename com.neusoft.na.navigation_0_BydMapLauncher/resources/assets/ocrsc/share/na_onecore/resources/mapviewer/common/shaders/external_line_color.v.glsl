uniform mat4 uMatrix;
uniform float uWidthFactor;
uniform float uFlattenDtm;

attribute vec4 aPosition;     // xy= Position (relative to anchor), z= Total offset along all lines (anchor based).
attribute vec4 aOffset;       // xy= Normalized offset direction, z= Signed lateral inner offset (in pixel), w= Signed lateral outer offset (in pixel).
attribute vec4 aInnerColor;
attribute vec4 aOuterColor;

varying vec4 vOffset;         // x= Total offset along all lines (in pixel). y= Signed lateral offset (in pixel), z= Absolute lateral inner offset (in pixel), w= Absolute lateral outer offset (in pixel).
varying vec4 vInnerColor;
varying vec4 vOuterColor;

void main(void)
{
   float OuterWidth= abs(aOffset.w);

   gl_Position= uMatrix * vec4(aPosition.xy + aOffset.xy * (OuterWidth * uWidthFactor), aPosition.w * uFlattenDtm, 1.0);

   vOffset= vec4(aPosition.z / uWidthFactor, aOffset.w, abs(aOffset.z), OuterWidth);

   vInnerColor= aInnerColor;
   vOuterColor= aOuterColor;
}
