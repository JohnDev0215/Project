#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;

uniform sampler2D srcTexMap;

void main(void)
{
   gl_FragColor = texture2D(srcTexMap, oTex);
   //gl_FragColor = vec4(0.0, 1.0, 0.0, 1.0);
}

// file:textureatlas.f.glsl
