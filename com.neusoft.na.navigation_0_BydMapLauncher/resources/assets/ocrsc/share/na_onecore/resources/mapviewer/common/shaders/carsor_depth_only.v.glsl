
uniform mat4 uProjMat;
uniform mat4 uCarsorRotMat;
uniform mat4 uCarsorViewMat;

attribute vec3 aPos;

uniform vec3  uCarsorCarPos;

uniform float uCarsorScale;

void main(void)
{
   vec3 rotatedVert = (uCarsorRotMat * vec4(aPos * uCarsorScale, 1.0)).xyz;
   vec4 viewVert = uCarsorViewMat * vec4(rotatedVert + uCarsorCarPos, 1.0);
   vec4 tp = uProjMat * vec4( viewVert.xyz, 1.0);
   gl_Position = tp;
}

// file:carsor_depth_only.v.glsl
