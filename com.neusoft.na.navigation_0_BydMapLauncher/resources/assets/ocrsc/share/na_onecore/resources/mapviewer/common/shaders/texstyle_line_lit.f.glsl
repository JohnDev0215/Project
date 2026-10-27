#ifdef GL_ES
precision mediump float;
#endif

// shader: texstyle_line_lit.fs

uniform sampler2D areaAtlas;

varying vec4  vCol;
varying vec3  vLit;
varying vec2 vSide;  // x: (linewidth + 1) * normalized-side-factor; y: linewidth

void main(void)
{
   %FixFarClip%  // force clipping at the far plane (workaround for Viviante GC2000)

   float alpha = 1.0;//min( min(vSide.x, 1.0 - (vSide.x - vSide.y)), 1.0);
   gl_FragColor = vec4( vCol.rgb * vLit, vCol.a * alpha);
   //gl_FragColor = vec4( 1.0,0.0,0.0, vCol.a * alpha);
}
// file:texstyle_line_lit.f.glsl
