#ifdef GL_ES
precision mediump float;
#endif

uniform float uCrosshairAlpha;

varying vec3 oTex;

uniform sampler2D diffuseMap;

void main(void)
{
   //gl_FragColor = vec4( vec3(%CROSSHAIR_COLOR%) * oTex.z, 1.0);

   vec4 col = texture2D(diffuseMap, oTex.xy);
   gl_FragColor = vec4(col.rgb, col.a * uCrosshairAlpha);
}
// file:crosshair.f.glsl
