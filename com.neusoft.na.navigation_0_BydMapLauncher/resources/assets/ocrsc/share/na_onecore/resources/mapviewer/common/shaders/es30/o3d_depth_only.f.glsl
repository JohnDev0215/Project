
%GL_VERSION%

#ifdef GL_ES
precision mediump float;
#endif

out vec4 fragmentColor;

void main(void)
{
   fragmentColor = vec4( 1.0, 1.0, 1.0, 1.0);
}

// file:o3d_depth_only.f.glsl
