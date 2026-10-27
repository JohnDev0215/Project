#ifdef GL_ES
    precision mediump float;
#endif

uniform sampler2D watermarkMap;
uniform float uOpacity;

varying vec2 vTex;

void main(void)
{
   vec4 col = texture2D(watermarkMap, vTex);
   float opacity = col.a * uOpacity;
   gl_FragColor = vec4(col.rgb * opacity, opacity);
}

// file:watermark.f.glsl
