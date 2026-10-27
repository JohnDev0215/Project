#ifdef GL_ES
precision highp float;
#endif

uniform mat4 projMat;
uniform mat4 crosshairRotMat;
uniform mat4 crosshairViewMat;
uniform vec3 crosshairCarPos;
uniform float crosshairScale;
uniform vec3 uSunLightDirection;

attribute vec3 vPos;
attribute vec3 vNrm;

varying float brightness;

void main(void)
{
   vec3 rotatedVert = (crosshairRotMat * vec4(vPos * crosshairScale, 1.0)).xyz;
   vec3 rotatedNrm = (crosshairRotMat * vec4(vNrm, 0.0)).xyz;
   vec4 viewVert = crosshairViewMat * vec4(rotatedVert + crosshairCarPos, 1.0);
   vec3 normal = normalize((crosshairViewMat * vec4(rotatedNrm, 0.0)).xyz);
   brightness = max(0.0, dot(normal, uSunLightDirection));

   gl_Position = projMat * vec4( viewVert.xyz, 1.0);
}
// file:crosshair_brightness.v.glsl
