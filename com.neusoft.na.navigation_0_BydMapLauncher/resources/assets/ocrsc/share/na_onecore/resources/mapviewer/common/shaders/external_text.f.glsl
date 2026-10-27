#ifdef GL_ES
precision highp float;
#endif

// Configuration for distance-field font parameters
uniform vec4 uFontSetup; // x: inline end offset, y: outline end offset, z: min value of outline alpha, w: max value of outline alpha

varying vec4 oCol;
varying vec4 oOutlineCol;
varying vec2 oTex;
varying vec4 oChannel;
varying float vOpacity;

uniform sampler2D fontMap;
uniform float uGlobalLabelingOpacity;

void main(void)
{
   float minThreshold = uFontSetup.y;
   float maxThreshold = uFontSetup.x;
   float minOutline = uFontSetup.z;
   float maxOutline = uFontSetup.w;

   float v = dot( texture2D(fontMap, oTex), oChannel);

   vec4 color;
   if(oOutlineCol.a > 0.0)
   {
      color.rgb = mix(oOutlineCol.rgb, oCol.rgb, smoothstep(minThreshold, maxThreshold, v));
      color.a   = vOpacity * smoothstep(minOutline, maxOutline, v);
   }
   else
   {
      color.rgb = oCol.rgb;
      color.a   = vOpacity * smoothstep(minThreshold, maxThreshold, v);
   }
   color.a *= uGlobalLabelingOpacity;
   gl_FragColor = color;
}
// file:text.f.glsl
