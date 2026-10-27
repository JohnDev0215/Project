uniform mat4 uMatrix;

uniform vec2  uStyleTexelDim;
uniform float uStyleTexScaleOff;

uniform sampler2D styleTex;

attribute vec4 aPosition;  // xy: pos, zw: style-atlas-base-offset

varying vec4 vColor;

void main(void)
{
   gl_Position= uMatrix * vec4(aPosition.xy, 0.0, 1.0);

   vec2 styleBaseAddr = aPosition.zw + vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);

   vColor= texture2DLod( styleTex, styleBaseAddr, 0.0);
}
