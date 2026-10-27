#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D sourceMap;

varying vec2 vTex;

void main(void)
{
   gl_FragColor = vec4(texture2D( sourceMap, vTex).rgb, 1.0);
//   gl_FragColor = vec4(vTex.xy, 0.0, 1.0);
}

// file:contour_debug.f.glsl
