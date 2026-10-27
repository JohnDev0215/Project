#ifdef GL_ES
   precision mediump float;
#endif

uniform sampler2D sourceMap;
uniform vec2 uDirection;
varying vec2 vTex;

void main(void)
{
   float weight[8];
   weight[0] = 0.0511468586406;
   weight[1] = 0.10046967206560001;
   weight[2] = 0.0934947023238;
   weight[3] = 0.0821347690869;
   weight[4] = 0.0681132737879;
   weight[5] = 0.053317202270999994;
   weight[6] = 0.0393903196376;
   weight[7] = 0.027462700193399997;
   float offset[8];
   offset[0] = 0.0;
   offset[1] = 0.4981949458483896;
   offset[2] = 2.490974729241922;
   offset[3] = 4.4837545126353335;
   offset[4] = 6.47653429602867;
   offset[5] = 8.469314079421896;
   offset[6] = 10.462093862816001;
   offset[7] = 12.454873646209089;
   vec4 col = texture2D( sourceMap, vTex) * weight[0];
   col += texture2D( sourceMap, ( vTex + uDirection * 15.0) ) * 0.0100439313135;
   col += texture2D( sourceMap, ( vTex - uDirection * 15.0) ) * 0.0100439313135;

   for(int i = 1; i < 8; i++)
   {
      col += texture2D( sourceMap, ( vTex + uDirection * offset[i]) ) * weight[i];
      col += texture2D( sourceMap, ( vTex - uDirection * offset[i]) ) * weight[i];
   }

   gl_FragColor = col;
}

// file:convolution15.f.glsl
