uniform mat4 globeViewMat;
uniform mat4 globeProjMat;

attribute vec3 vPos;

varying vec3 oTex;

void main(void)
{
   vec4 viewPos= globeViewMat * vec4(vPos, 0.0);

   mat4 distortedGlobeProjectionMatrix= globeProjMat;
   distortedGlobeProjectionMatrix[0][0]*= 0.25;
   distortedGlobeProjectionMatrix[1][1]*= 0.25;
   gl_Position= distortedGlobeProjectionMatrix * vec4(viewPos.xyz, 1.0);

   oTex = vPos;
}
