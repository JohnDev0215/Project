#ifdef GL_ES
precision highp float;
#endif

varying vec3 vTex;

uniform sampler2D texMap0;
uniform sampler2D texMap1;
uniform sampler2D texMap2;
uniform sampler2D texMap3;
uniform sampler2D texMap4;
uniform sampler2D texMap5;
uniform sampler2D texMap6;
uniform sampler2D texMap7;

uniform vec4 uOffset0;
uniform vec4 uOffset1;
uniform float uGlobalOffset;
uniform float uGlobalOffsetMask;
uniform float uOneByTexHeight;

void main(void)
{
   vec4 col = vec4(1.0, 0.0, 1.0, 1.0);

   float globalOffset = uGlobalOffset * uGlobalOffsetMask;

   if(vTex.z >= 0.0 && vTex.z < 0.1)
   {
      col = texture2D(texMap0, vTex.xy - vec2(0.0, globalOffset) * uOneByTexHeight);
   }
   else if(vTex.z >= 0.9 && vTex.z < 1.1)
   {
      col = texture2D(texMap1, vTex.xy - vec2(0.0, uOffset0.y + globalOffset) * uOneByTexHeight);
   }
   else if(vTex.z >= 1.9 && vTex.z < 2.1)
   {
      col = texture2D(texMap2, vTex.xy - vec2(0.0, uOffset0.z + globalOffset) * uOneByTexHeight);
   }
   else if(vTex.z >= 2.9 && vTex.z < 3.1)
   {
      col = texture2D(texMap3, vTex.xy - vec2(0.0, uOffset0.w) * uOneByTexHeight);
   }
   else if(vTex.z >= 3.9 && vTex.z < 4.1)
   {
      col = texture2D(texMap4, vTex.xy - vec2(0.0, uOffset1.x) * uOneByTexHeight);
   }
   else if(vTex.z >= 4.9 && vTex.z < 5.1)
   {
      col = texture2D(texMap5, vTex.xy - vec2(0.0, uOffset1.y) * uOneByTexHeight);
   }
   else if(vTex.z >= 5.9 && vTex.z < 6.1)
   {
      col = texture2D(texMap6, vTex.xy - vec2(0.0, uOffset1.z) * uOneByTexHeight);
   }
   else if(vTex.z >= 6.9 && vTex.z < 7.1)
   {
      col = texture2D(texMap7, vTex.xy - vec2(0.0, (1.0 - uGlobalOffsetMask) * uOffset1.w) * uOneByTexHeight);
   }

   col.w = 1.0; // we do not expect an alpha channel, but just to be sure
   gl_FragColor = col;
}

