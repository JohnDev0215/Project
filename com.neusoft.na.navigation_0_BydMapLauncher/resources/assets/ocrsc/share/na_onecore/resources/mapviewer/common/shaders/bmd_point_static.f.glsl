#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;

uniform sampler2D bmdAtlas;

void main(void)
{
   vec4 col = texture2D(bmdAtlas, oTex);
   // col = vec4(oTex, 1.0, 1.0);
   gl_FragColor = col;
}

// file:bmd_point_static.f.glsl
