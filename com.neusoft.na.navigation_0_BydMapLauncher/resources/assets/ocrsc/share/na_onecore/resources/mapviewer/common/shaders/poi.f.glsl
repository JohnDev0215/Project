#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;
varying float oOpacity;

uniform sampler2D iconMap;

void main(void)
{
   vec4 col = texture2D(iconMap, oTex);
   gl_FragColor = vec4(col.rgb, oOpacity * col.a);
   //gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);
   //gl_FragColor = vec4(oOpacity, oOpacity, oOpacity, 1.0);
   //gl_FragColor = vec4(gl_FragCoord.zzz, 1.0);
}

// file:poi.f.glsl
