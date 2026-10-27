#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;
varying float oOpacity;

uniform sampler2D maskMap;

void main(void)
{
   vec4 col = texture2D(maskMap, oTex);
   gl_FragColor = vec4(col.r * vec3(0.0, 0.01, 0.095), oOpacity * col.r   );
   //gl_FragColor = vec4(1.0, 0.9, 0.85, 0.4);
   //gl_FragColor = vec4(gl_FragCoord.zzz, 1.0);
}

// file:poi_selection.f.glsl
