#ifdef GL_ES
precision highp float;
#endif

varying vec2 vTex;

uniform float uTexStretchT;

uniform sampler2D texMap;


void main(void)
{
   vec3 col = texture2D(texMap, vTex.xy * vec2(1.0, uTexStretchT)).rgb;
   gl_FragColor = vec4(col, 1.0);
}

