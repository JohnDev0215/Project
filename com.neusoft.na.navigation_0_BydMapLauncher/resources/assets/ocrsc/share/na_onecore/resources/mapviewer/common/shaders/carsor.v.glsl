
attribute vec3 aPos;
attribute vec3 aNrm;
attribute vec2 aTex;

uniform mat4 uProjMat;
uniform mat4 uCarsorRotMat;
uniform mat4 uCarsorViewMat;

uniform vec3 uCarsorCarPos;
uniform float uPitch;

uniform float uCarsorScale;

varying vec3 vTex;
varying float vPitch;

void main(void)
{
   vec3 rotatedVert = (uCarsorRotMat * vec4(aPos * uCarsorScale, 1.0)).xyz;
   vec4 viewVert = uCarsorViewMat * vec4(rotatedVert + uCarsorCarPos, 1.0);

   vec4 tp = uProjMat * vec4( viewVert.xyz, 1.0);
   gl_Position = tp;

   vTex = vec3(aTex, 1.0);

   vPitch = 1.0;
   if(%O3D_SIGHT_TUNNEL%)
   {
      vPitch = 1.0 - smoothstep(0.2, 0.6, uPitch);
   }
}

// file:carsor.v.glsl
