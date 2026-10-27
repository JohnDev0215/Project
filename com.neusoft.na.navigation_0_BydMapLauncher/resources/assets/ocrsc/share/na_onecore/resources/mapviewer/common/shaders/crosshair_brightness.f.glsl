#ifdef GL_ES
precision mediump float;
#endif

uniform vec4 uCrosshairColor;
uniform float uCrosshairAlpha;

varying float brightness;

void main(void)
{
   gl_FragColor = vec4(uCrosshairColor.rgb * brightness, uCrosshairColor.a * uCrosshairAlpha);
}
// file:crosshair_brightness.f.glsl
