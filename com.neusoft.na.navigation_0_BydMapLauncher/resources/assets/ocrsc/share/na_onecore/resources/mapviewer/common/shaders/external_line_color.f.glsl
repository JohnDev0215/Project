#ifdef GL_ES
precision mediump float;
#endif

varying vec4 vOffset;       // x= Total offset along all lines (in pixel). y= Signed lateral offset (in pixel), z= Absolute lateral inner offset (in pixel), w= Absolute lateral outer offset (in pixel).
varying vec4 vInnerColor;
varying vec4 vOuterColor;

void main(void)
{
   float LateralOffset= abs(vOffset.y);

   float InnerOuterFraction= clamp(vOffset.z - LateralOffset, 0.0, 1.0);

   vec4 Color= mix(vOuterColor, vInnerColor, InnerOuterFraction);

   float AntialiasingFraction= min(vOffset.w - LateralOffset, 1.0);

   gl_FragColor= vec4(Color.rgb, Color.a * AntialiasingFraction);
}
