
attribute vec3 aPos;
attribute vec2 aTex;

uniform mat4 uProjMat;
uniform mat4 uCarsorRotMat;
uniform mat4 uCarsorViewMat;

uniform vec3 uCarsorCarPos;

uniform float uCarsorScale;
uniform float uScaleFactor;

varying vec3 vTex;

void main(void)
{
   vec3 pos2 = aPos;
   vec3 rotatedVert = (uCarsorRotMat * vec4(pos2 * uCarsorScale * uScaleFactor, 1.0)).xyz;
   vec4 viewVert = uCarsorViewMat * vec4(rotatedVert + uCarsorCarPos, 1.0);

   vec4 tp = uProjMat * vec4( viewVert.xyz, 1.0);
   gl_Position = tp;

   vTex = vec3(aTex, 1.0);


}

// file:compass.v.glsl
