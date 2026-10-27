#ifdef GL_ES
precision mediump float;
#endif

// shader: external_texstyle_area_lit.fs

varying vec4 vCol;

//-- area shader for untextured, opaque BMD tiles

void main(void)
{
   %FixFarClip%  // force clipping at the far plane (workaround for Viviante GC2000)

   gl_FragColor = vCol;
}
// file:external_texstyle_area_lit.f.glsl
