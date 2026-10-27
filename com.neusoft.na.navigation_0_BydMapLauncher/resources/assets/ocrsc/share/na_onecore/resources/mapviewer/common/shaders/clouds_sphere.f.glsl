#ifdef GL_ES
    precision mediump float;
#endif

uniform sampler2D texClouds;

varying vec2 oTex;

void main(void)
{
   vec3 earthCol = texture2D(texClouds, oTex).rgb;

   gl_FragColor = vec4(earthCol,  1.0);
   //gl_FragColor = vec4(1.0, 1.0, 1.0, 1.0);
}
// file:clouds_sphere.f.glsl
