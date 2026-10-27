#ifdef GL_ES
precision mediump float;
#endif

varying vec4 oCol;
varying vec4 oOutlineCol;
varying vec2 oTex;
varying vec4 oChannel;

uniform sampler2D fontMap;

void main(void)
{
   const float minOutline = 0.1;

   const float minThreshold = 0.3;
   const float maxThreshold = 0.6;

   float v = dot( texture2D(fontMap, oTex), oChannel) ;
   if(oOutlineCol.a > 0.0)
   {
      if(v < minOutline)
         discard;
      if(v >= minOutline && v <= minThreshold)
         gl_FragColor = vec4(oOutlineCol.rgb, smoothstep(minOutline, minThreshold, v));
      else if(v >= minThreshold && v <= maxThreshold)
         gl_FragColor = vec4( mix(oOutlineCol.rgb, oCol.rgb, smoothstep(minThreshold, maxThreshold, v)), 1.0);
      else
         gl_FragColor = vec4(oCol.rgb, 1.0);
   }
   else
   {
      if(v < minThreshold)
         discard;
      if(v >= minThreshold && v <= maxThreshold)
         gl_FragColor = vec4( oCol.rgb, smoothstep(minThreshold, maxThreshold, v));
      else
         gl_FragColor = vec4(oCol.rgb, 1.0);
   }
}
