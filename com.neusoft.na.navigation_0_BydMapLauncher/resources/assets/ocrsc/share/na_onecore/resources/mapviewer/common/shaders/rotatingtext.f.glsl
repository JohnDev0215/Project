
#ifdef GL_ES
precision highp float;
#endif

varying vec4 oCol;
varying vec4 oOutlineCol;
varying vec2 oTex;
varying vec4 oChannel;

uniform sampler2D fontMap;

void main(void)
{
   const float minThreshold = 0.4;
   const float maxThreshold = 0.6;
   float minOutline = 0.1;
   float maxOutline = 0.3;

   float v = dot( texture2D(fontMap, oTex), oChannel);

   vec4 color;
   if(oOutlineCol.a > 0.0)
   {
      color.rgb = mix(oOutlineCol.rgb, oCol.rgb, smoothstep(minThreshold, maxThreshold, v));
      color.a   = smoothstep(minOutline, maxOutline, v);
   }
   else
   {
      color.rgb = oCol.rgb;
      color.a   = smoothstep(minThreshold, maxThreshold, v);
   }
   gl_FragColor = color;
}
// file:rotatingtext.f.glsl
