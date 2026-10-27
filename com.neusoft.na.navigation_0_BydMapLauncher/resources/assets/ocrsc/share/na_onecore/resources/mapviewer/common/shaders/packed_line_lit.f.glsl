// shader: packed_line_lit.fs

#ifdef GL_ES
precision mediump float;
#endif


varying vec4  vCol;
varying vec2  vSide;
varying vec2  vNormal;
varying float vHighAliasing;
varying float vBorder;

void main(void)
{

   vec2 side = vSide;
   float alpha = 1.0;//min( min(side.x, 1.0 - (side.x - side.y)), 1.0);

   float flurAlpha = 1.0;
   float flurAlpha2 = 1.0;
   if((vHighAliasing > 0.5) && (vBorder < 0.5))
   {
      flurAlpha = 1.0 - smoothstep(0.92, 1., length(vNormal));
      flurAlpha2 = smoothstep(0., 0.1, length(vNormal));
   }

   gl_FragColor = vec4( vCol.rgb, vCol.a * alpha * flurAlpha);
   //gl_FragColor = vec4( 1.0,0.0,0.0, vCol.a * alpha);
}
// file:packed_line_lit.f.glsl
