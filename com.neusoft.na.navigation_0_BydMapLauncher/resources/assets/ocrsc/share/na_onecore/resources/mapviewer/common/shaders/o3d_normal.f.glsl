#ifdef GL_ES
precision mediump float;
#endif

varying vec3 vNrm;

void main(void)
{
   vec3 nrm = 0.5 * normalize(vNrm) + 0.5;
   gl_FragColor = vec4(nrm, gl_FragCoord.z);
}
// file:object3d_normal.f.glsl
