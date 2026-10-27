#ifdef GL_ES
    precision mediump float;
#endif

uniform vec3 uColorGradientFrom;
uniform vec3 uColorGradientTo;

varying vec3 vNrm;

void main(void)
{
   vec3 skyCol = uColorGradientFrom * vNrm.z + uColorGradientTo * (1.0 - vNrm.z);
   gl_FragColor = vec4(skyCol, 1.0);
}

// file:sky.f.glsl
