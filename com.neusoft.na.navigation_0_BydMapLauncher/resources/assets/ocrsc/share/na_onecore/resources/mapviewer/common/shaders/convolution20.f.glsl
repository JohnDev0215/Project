
#ifdef GL_ES
   precision mediump float;
#endif

uniform sampler2D sourceMap;
uniform vec2 uDirection;
varying vec2 vTex;

void main(void)
{
   float weight[10];
   weight[0] = 0.0377581067066;
   weight[1] = 0.0748277016415;
   weight[2] = 0.0721362704758;
   weight[3] = 0.0675330235454;
   weight[4] = 0.0613969585028;
   weight[5] = 0.054205234810599995;
   weight[6] = 0.0464722280129;
   weight[7] = 0.0386897974845;
   weight[8] = 0.0312781848566;
   weight[9] = 0.0245538042964;
   float offset[10];
   offset[0] = 0.0;
   offset[1] = 0.4990825688071691;
   offset[2] = 2.49541284403671;
   offset[3] = 4.49174311926596;
   offset[4] = 6.488073394495571;
   offset[5] = 8.484403669725369;
   offset[6] = 10.48073394495382;
   offset[7] = 12.47706422018405;
   offset[8] = 14.473394495413324;
   offset[9] = 16.46972477064247;
   vec4 col = texture2D( sourceMap, vTex) * weight[0];
   col += texture2D( sourceMap, ( vTex + uDirection * 19.0) ) * 0.0100277430202;
   col += texture2D( sourceMap, ( vTex - uDirection * 19.0) ) * 0.0100277430202;
   for(int i = 1; i < 10; i++)
   {
      col += texture2D( sourceMap, ( vTex + uDirection * offset[i]) ) * weight[i];
      col += texture2D( sourceMap, ( vTex - uDirection * offset[i]) ) * weight[i];
   }

   gl_FragColor = col;
}
// file:convolution20.f.glsl
