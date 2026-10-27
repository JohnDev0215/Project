#ifdef GL_ES
    precision mediump float;
#endif

uniform vec3 haloColor;

uniform sampler2D haloMap;

varying vec4 oTex;
//varying vec3 sunDirView;

void _main(void)
{
   float dt = dot(oTex.xy, oTex.xy);
   if( dt > 1.0)      discard;

   gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);
}

void main(void)
{
   float dt = dot(oTex.zw, oTex.zw);
   if( dt > 1.0)
      discard;

   vec4 texel= texture2D(haloMap, oTex.xy);
   vec3 colorize= vec3(0.784, 0.9, 1.0);
   gl_FragColor = vec4( texel.rgb * 1.4 * colorize, texel.a);
}

// file:halo.f.glsl
