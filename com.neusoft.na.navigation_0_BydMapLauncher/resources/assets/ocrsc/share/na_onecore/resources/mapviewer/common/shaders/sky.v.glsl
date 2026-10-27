uniform mat4 uViewRotationMat;
uniform float uSkyOffsetY;

attribute vec3 aPos;
attribute vec3 aNrm;

varying vec3 vNrm;

void main(void)
{
   vec4 p = uViewRotationMat * vec4(aPos + vec3(0.0, uSkyOffsetY, 0.0), 1.0);

   float tz = 0.9 * p.w;
   gl_Position = vec4(p.xy, tz, p.w);

   vNrm = aNrm;
}

// file:sky.v.glsl
