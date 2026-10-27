#ifdef GL_ES
precision mediump float;
#endif

uniform float uGlobalLabelingOpacity;

varying vec4 vColor;
varying float vOpacity;

void main(void)
{
   gl_FragColor= vec4(vColor.rgb, vColor.a * uGlobalLabelingOpacity * vOpacity );
}

