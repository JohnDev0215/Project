#ifdef GL_ES
    precision mediump float;
#endif

uniform sampler2D skyMap;
uniform float uDtmActive;

varying vec2 vTex;

void main(void)
{
   vec2 texCoord = vTex;
   if(uDtmActive > 0.5)
   {
      texCoord.y = clamp(vTex.y, 0.05, 0.95);
   }
   vec3 skyCol = texture2D(skyMap, texCoord).rgb;
   gl_FragColor = vec4(skyCol, 1.0);
}

// file:sky_textured.f.glsl
