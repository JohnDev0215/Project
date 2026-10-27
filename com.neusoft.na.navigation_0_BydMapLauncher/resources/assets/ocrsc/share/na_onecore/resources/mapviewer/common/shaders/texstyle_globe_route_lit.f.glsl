#ifdef GL_ES
precision mediump float;
#endif

varying vec4  vCol;
varying vec2  vTex;
varying float vDotLine;

// local uniforms
uniform float uRouteTransparency;

void main(void)
{
   %FixFarClip%  // force clipping at the far plane (workaround for Viviante GC2000)

   float fx = 1.0 - abs(vTex.x * 2.0 - 1.0);
   float alpha = fx * vTex.y;
   alpha = 1.0 - pow(abs(alpha - 1.0), 16.0);

   //-disable the true sun dependent lighting for now
   //vec3 litCol = vCol.rgb * uSunLightColor;
   //vec3 col = litCol * ambientCoefficient + (1.0 - ambientCoefficient) * litCol * nDotL * uSunLightIntensity;

   // keep the road lighting constant at day and night
   // TODO(lex): use own style for the night (moon-lit-road)
   if(vDotLine > 0.5)
   {
      gl_FragColor = vec4(1.0, 1.0, 1.0, 0.0);
   }
   else
   {
      gl_FragColor = vec4( vCol.rgb, alpha * uRouteTransparency);
   }

   //gl_FragColor = vec4( 1.0, 0.0, 1.0, 1.0);
}
// file:texstyle_globe_route_lit.f.glsl
