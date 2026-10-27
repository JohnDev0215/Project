
uniform mat4 globeVpMat;
uniform mat4 globeViewMat;

uniform vec3 lightPos;

attribute vec3 vPos;
attribute vec2 vTex;

varying vec3 oNrm;
varying vec2 oTex;
varying vec3 vsLightDir;
varying vec3 vsHalfVec;

void main(void)
{
   oTex = vTex;
   oNrm = (globeViewMat * vec4(vPos, 0.0)).xyz;

   vec3 vsPos = (globeViewMat * vec4(vPos, 1.0)).xyz;
   vsLightDir = lightPos - vsPos;
   vsHalfVec = normalize(-vsPos) + normalize(vsLightDir);
   gl_Position =globeVpMat * vec4(vPos, 1.0);
}
// file:globe.v.glsl
