#ifdef GL_ES
precision mediump float;
#endif

varying vec3 oTex;
varying vec4 oTexParams;   // offset, size

uniform sampler2D diffuseMap;

void main(void)
{
   vec2 tc = abs(fract(oTex.xy));

   tc.x *= oTexParams.z;
   tc.y *= oTexParams.w;
   tc += oTexParams.xy;

   vec4 texCol = texture2D(diffuseMap, tc);
   if(texCol.a == 0.0)
      discard;
   gl_FragColor = vec4(texCol.rgb * oTex.z, texCol.a);
   //gl_FragColor = vec4(texture2D(diffuseMap, tc).rgb * oTex.z, 1.0);
   //gl_FragColor = vec4(0.44, 0.44, 0.44, 1.0);
}
// file:textured_object3d.f.glsl
