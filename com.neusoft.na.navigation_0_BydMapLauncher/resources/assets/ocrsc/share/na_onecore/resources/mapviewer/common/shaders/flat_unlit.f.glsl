#ifdef GL_ES
precision mediump float;
#endif

varying vec3 oCol;

//uniform sampler2D diffTexture;

void main(void)
{
    gl_FragColor = vec4(oCol, 1.0);//texture2D(diffTexture, oTex);
}
// file:flat_unlit.f.glsl
