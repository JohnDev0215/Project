#ifdef GL_ES
    precision mediump float;
#endif

varying vec2 oTex;

uniform sampler2D cloudsMap;
uniform sampler2D octave0Map;
uniform sampler2D octave1Map;

uniform float cloudCover;

uniform vec4 weights;
uniform vec4 texScale;
uniform vec4 texOffset;

float getCloudVal(vec2 texCoord)
{
   vec2 o01 = vec2(  2.0 * texture2D(cloudsMap, texCoord * texScale.x).r - 1.0
                   , 2.0 * texture2D(cloudsMap, texCoord * texScale.x).g - 1.0
                  );

   vec2 o23 = vec2(  2.0 * texture2D(cloudsMap, texCoord * texScale.z).r - 1.0
                   , 2.0 * texture2D(cloudsMap, texCoord * texScale.z).g - 1.0
                  );
   float cCol;
   cCol  = o01.r * weights.x;
   cCol += o01.g * weights.y;
   cCol += o23.r * weights.z;
   cCol += o23.g * weights.w;

   return cCol;
}

float _getCloudVal(vec2 texCoord)
{
   float cCol;
   cCol  = (2.0 * texture2D(cloudsMap, texCoord * texScale.x + texOffset.x).r - 1.0) * weights.x;
   cCol += (2.0 * texture2D(cloudsMap, texCoord * texScale.y + texOffset.y).g - 1.0) * weights.y;
//   cCol += (2.0 * texture2D(cloudsMap, texCoord * texScale.z + texOffset.z).b - 1.0) * weights.z;
//   cCol += (2.0 * texture2D(cloudsMap, texCoord * texScale.w + texOffset.w).a - 1.0) * weights.w;
   return cCol;
}

void main(void)
{
   const float threshold = 0.5;
   const float factor = (1.0 - threshold);

   vec3 from = vec3(0.15, 0.5, 0.8);
   vec3 to = vec3(0.9, 1.0, 1.0);
   vec3 skyCol = from * oTex.t + to * (1.0 - oTex.t);

   float cloudsCol = max(0.5 * getCloudVal(oTex) + 0.5 - cloudCover, 0.0);
   float alpha = cloudsCol;
   if(oTex.t < threshold)
      alpha *= oTex.t / threshold;

   vec2 cc = vec2(0.5, 0.85);
   //cloudsCol = cc.x * cloudsCol + (1.0 - cloudsCol) * cc.y;

   cloudsCol = 1.0;

   vec3 finalCol = skyCol * (1.0 - alpha) + vec3(cloudsCol, cloudsCol, cloudsCol) * alpha;
   //finalCol = vec3(cloudsCol, cloudsCol, cloudsCol);

   gl_FragColor = vec4(finalCol, 1.0);
}

void _main(void)
{
   const float threshold = 0.3;
   const float factor = (1.0 - threshold);

   //gl_FragColor = vec4(normalize(oNrm.xyz), 1.0);
   vec3 from = vec3(0.15, 0.5, 0.8);
   vec3 to = vec3(0.9, 1.0, 1.0);
   vec3 skyCol = from * oTex.t + to * (1.0 - oTex.t);

   float cloudsCol = 0.0;
   float alpha = 0.0;
   //if(oTex.t > threshold)
   {
      //vec2 tc = vec2(oTex.s, (oTex.t - threshold) / factor );
      cloudsCol = max(0.5 * getCloudVal(oTex) + 0.5 - cloudCover, 0.0);

      alpha = cloudsCol;
      //alpha = col == 0.0 ? 0.0 : 1.0;

      vec2 cc = vec2(0.5, 0.85);
      //cloudsCol = cc.x * cloudsCol + (1.0 - cloudsCol) * cc.y;

      cloudsCol = 1.0;

      //cloudsCol = texture2D(cloudsMap, vec2(oTex.s, (oTex.t - threshold) / factor )).r;
   }

   vec3 finalCol = skyCol * (1.0 - alpha) + vec3(cloudsCol, cloudsCol, cloudsCol) * alpha;
   //finalCol = vec3(cloudsCol, cloudsCol, cloudsCol);
   gl_FragColor = vec4(finalCol, 1.0);
}
// file:sky_clouds.f.glsl
