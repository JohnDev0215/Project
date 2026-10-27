#ifdef GL_ES
precision mediump float;
#endif

varying vec3 color;
varying float nDotL;

// x: enabled, y: kA, z: kD, w: kS
uniform vec4 uLightingParamsBMD;
uniform float uLightingEnabled;


void main(void)
{
    float light = uLightingParamsBMD.y + uLightingParamsBMD.z * nDotL;
    light = (uLightingParamsBMD.x * uLightingEnabled) > 0.0 ? light : 1.0;
    gl_FragColor= vec4(color * light, 1.0);
}
// file:ground_lit.f.glsl
