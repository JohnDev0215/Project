#ifdef GL_ES
precision mediump float;
#extension GL_OES_standard_derivatives : enable
#endif

varying vec2 oTex;

uniform sampler2D sdfMap;

void outlined(float v)
{
   if(v > 0.45)
      gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);
   else
      gl_FragColor = vec4(1.0, 1.0, 1.0, smoothstep(0.385, 0.4, v));

}

void main(void)
{
   const float minThreshold = 0.45;
   const float maxThreshold = 0.5;

   float v = texture2D(sdfMap, oTex).r;
   if(v <= minThreshold)
      discard;
   gl_FragColor = vec4(1.0, 0.0, 0.0, smoothstep(minThreshold, maxThreshold, v));
   //outlined(v);
}
// file:sdf.f.glsl
