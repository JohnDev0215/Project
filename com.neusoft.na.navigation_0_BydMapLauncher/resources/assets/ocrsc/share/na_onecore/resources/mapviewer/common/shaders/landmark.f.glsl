#ifdef GL_ES
precision mediump float;
#endif

varying vec3 oTex;

uniform sampler2D diffuseMap;

void main(void)
{
   //gl_FragColor = vec4(0.44, 0.44, 0.44, 1.0);
   gl_FragColor = vec4(texture2D(diffuseMap, oTex.xy).rgb * oTex.z, 1.0);
}
// file:landmark.f.glsl
