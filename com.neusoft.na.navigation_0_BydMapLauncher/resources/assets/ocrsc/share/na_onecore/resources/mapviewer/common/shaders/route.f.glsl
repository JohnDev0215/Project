#ifdef GL_ES
precision mediump float;
#endif

varying vec3 oCol;
//varying vec2 oTex;
varying vec2 vSide;  // x: (linewidth + 1) * normal-side-factor; y: line-width

//uniform sampler2D diffTexture;

void main(void)
{
   float alpha = min( min(vSide.x, 1.0 - (vSide.x - vSide.y)), 1.0);
   gl_FragColor = vec4(oCol, alpha);
}

//void _main(void)
//{
//   float fx = oTex.x * 2.0 - 1.0;
//   float fy = oTex.y;

//   float intensity = fx * fx;
//   intensity = 1.0 - (abs(intensity));

//   intensity *= fy;
//   //gl_FragColor = vec4(intensity, intensity, intensity, 1.0);
//   gl_FragColor = vec4(oCol, intensity);
//}
// file:route.f.glsl
