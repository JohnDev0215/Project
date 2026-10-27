#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;

uniform sampler2D bmdAtlas;

void main(void)
{
   vec4 col = texture2D(bmdAtlas, oTex);
   //gl_FragColor = vec4(1.0,0.0,1.0,0.6);
   gl_FragColor = col;
}

// file:bmd_point_rot.f.glsl
