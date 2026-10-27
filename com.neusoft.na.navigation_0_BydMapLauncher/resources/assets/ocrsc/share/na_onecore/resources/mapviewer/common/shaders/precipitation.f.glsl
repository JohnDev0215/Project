#ifdef GL_ES
precision mediump float;
#endif

varying vec2 vTex;
varying vec4 vColor;

uniform sampler2D textureMap;
uniform vec4 uTypeMask;

void main(void)
{
   vec4 col = texture2D(textureMap, vTex);
   float val = dot(col, uTypeMask);
   gl_FragColor = val * vColor;
//   gl_FragColor = vec4(vColor.aaa, 1.0);
//   gl_FragColor = vec4(1.0);
}
// file:precipitation.f.glsl
