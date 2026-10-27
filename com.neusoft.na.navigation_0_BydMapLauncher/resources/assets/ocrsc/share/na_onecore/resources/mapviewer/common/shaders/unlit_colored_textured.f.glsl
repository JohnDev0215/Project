#ifdef GL_ES
precision mediump float;
#endif

varying vec4 oCol;
varying vec2 oTex;

uniform sampler2D textureMap;

void main(void)
{
   vec4 col = texture2D(textureMap, oTex);
   gl_FragColor = col * oCol;
}

// file:unlit_colored_textured.f.glsl
