
attribute vec2 aCorner;

uniform vec4 uParams;      // xy: screenspace position, zw: screenspace size
uniform float uAspectRatio;

varying vec2 vTex;

void main(void)
{
   gl_Position = vec4(uParams.xy + aCorner * vec2(uParams.z, uParams.w / uAspectRatio), 0.0, 1.0);
   vTex = aCorner;
}

// file:watermark.v.glsl
