#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 oTex;
uniform sampler2D texSun;

void main(void)
{
   float dt = dot(oTex.zw, oTex.zw);
   if( dt > 1.0)
      discard;

   gl_FragColor = texture2D(texSun, oTex.xy);
}

// file:sun.f.glsl
