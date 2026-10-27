#ifdef GL_ES
precision mediump float;
#endif

varying vec3 oCol;
varying vec2 oTex;
varying float nDotL;

uniform vec3 uSunLightColor;
uniform float uSunLightIntensity;
uniform float ambientCoefficient;

//uniform sampler2D diffTexture;

void main(void)
{
   float fx = 1.0 - abs(oTex.x * 2.0 - 1.0);
   //fx = 1.0 - pow(abs(fx - 1.0), 16.0);
   float fy = oTex.y;
   //fy = 1.0 - pow(abs(fy - 1.0), 16.0);
   float i = fx * fy;
   i = 1.0 - pow(abs(i - 1.0), 16.0);
   //gl_FragColor = vec4(i, i, i, 1.0);

   //-disable the true sun dependent lighting for now
   //vec3 litCol = oCol.rgb * uSunLightColor;
   //vec3 col = litCol * ambientCoefficient + (1.0 - ambientCoefficient) * litCol * nDotL * uSunLightIntensity;

   // keep the road lighting constant at day and night
   // TODO(lex): use own style for the night (moon-lit-road)
   gl_FragColor = vec4( oCol.rgb, i);
}
// file:basic_route_lit.f.glsl
