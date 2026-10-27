#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;

uniform sampler2D imageMap;

void main(void)
{
   gl_FragColor = texture2D(imageMap, oTex);
   //gl_FragColor = vec4(oTex.yyy, 1.0);
}
