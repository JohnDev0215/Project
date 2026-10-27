
attribute vec3 aPos;
attribute vec3 aTex;

uniform mat4 uViewProjMat;
uniform mat4 uViewMat;

varying vec3 vTex;

void main(void)
{
   gl_Position = uViewProjMat * vec4(aPos, 1.0);
   vTex = aTex;
}

