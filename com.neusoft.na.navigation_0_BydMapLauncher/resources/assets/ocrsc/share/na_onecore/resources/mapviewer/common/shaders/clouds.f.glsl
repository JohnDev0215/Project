#ifdef GL_ES
    precision mediump float;
#endif

varying vec2 vTex;

uniform sampler2D cloudsMap;

uniform float uCloudCover;

uniform vec4 uWeights;
uniform vec4 uTexScale;
uniform vec4 uTexOffset;

float getCloudVal(vec2 texCoord)
{
   vec2 o01 = vec2(  2.0 * texture2D(cloudsMap, texCoord * uTexScale.x).r - 1.0
                   , 2.0 * texture2D(cloudsMap, texCoord * uTexScale.x).g - 1.0
                  );

   vec2 o23 = vec2(  2.0 * texture2D(cloudsMap, texCoord * uTexScale.z).r - 1.0
                   , 2.0 * texture2D(cloudsMap, texCoord * uTexScale.z).g - 1.0
                  );
   float cCol;
   cCol  = o01.r * uWeights.x;
   cCol += o01.g * uWeights.y;
   cCol += o23.r * uWeights.z;
   cCol += o23.g * uWeights.w;

   return cCol;
}

void main(void)
{
   float cloudsCol = max(0.5 * getCloudVal(vTex) + 0.5 - uCloudCover, 0.0);
   float alpha = cloudsCol;
   cloudsCol = 1.0;
   gl_FragColor = vec4(vec3(cloudsCol, cloudsCol, cloudsCol), alpha);
}
// file:clouds.f.glsl
