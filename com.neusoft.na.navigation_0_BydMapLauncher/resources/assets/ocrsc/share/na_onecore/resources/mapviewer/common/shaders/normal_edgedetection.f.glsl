
#ifdef GL_ES
precision mediump float;
#endif


uniform sampler2D normalMap;

uniform vec2 uInvTextureSize;

varying vec2 vTex;

vec4 getNormal(vec2 texCoord, vec2 offset)
{
   return texture2D(normalMap, texCoord + offset * uInvTextureSize);
}

void main(void)
{
   vec4 a = getNormal(vTex, vec2(1.0, 0.0));
   vec4 b = getNormal(vTex, vec2(-1.0, 0.0));
   vec4 c = getNormal(vTex, vec2(0.0, 1.0));
   vec4 d = getNormal(vTex, vec2(0.0, -1.0));

   vec3 e = abs(a.xyz - b.xyz);
   vec3 f = abs(c.xyz - d.xyz);

   float n = 0.0;
   n = max( n, e.x );
   n = max( n, e.y );
   n = max( n, e.z );
   n = max( n, f.x );
   n = max( n, f.y );
   n = max( n, f.z );

   n = float(n > 0.1);

   float dd = max(abs(a.w - b.w), abs(c.w - d.w));
   n = max(n, float(dd > 0.01));

   gl_FragColor = vec4(vec3(1.0 - 0.85 * n), 1.0);
}
// file:normal_edgedetection.f.glsl
