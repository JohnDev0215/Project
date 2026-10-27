#ifdef GL_ES
    precision mediump float;
#endif

varying vec3 oTex;

uniform samplerCube texSkyBox;

void main(void)
{
   gl_FragColor = vec4(textureCube(texSkyBox, oTex).rgb, 1.0);
}

// file:sky_box.f.glsl
