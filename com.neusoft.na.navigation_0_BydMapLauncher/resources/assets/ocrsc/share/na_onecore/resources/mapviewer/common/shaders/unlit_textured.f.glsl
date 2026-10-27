#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;
varying float opacity;

uniform sampler2D diffuseMap;

void main(void)
{
   vec4 col = texture2D(diffuseMap, oTex);
   gl_FragColor = vec4(col.rgb, opacity * col.a);
   //gl_FragColor = vec4(0.9, 0.04, 0.14, 1.0);
}
// file:unlit_textured.f.glsl
