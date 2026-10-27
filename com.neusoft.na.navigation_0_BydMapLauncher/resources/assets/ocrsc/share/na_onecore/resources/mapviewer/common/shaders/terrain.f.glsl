
#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D baseMap;

varying float l;

void main(void)
{
   vec3 col = texture2D(baseMap, vec2(0.23, 0.85));
   gl_FragColor = vec4(col * l, 1.0);
}
// file:terrain.f.glsl
