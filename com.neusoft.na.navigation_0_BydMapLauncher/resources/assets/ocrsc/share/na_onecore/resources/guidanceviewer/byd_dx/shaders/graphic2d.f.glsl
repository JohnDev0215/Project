#ifdef GL_ES
precision mediump float;
#endif

varying vec4 oCol;

void main(void)
{
   gl_FragColor = oCol;
}
