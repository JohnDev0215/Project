uniform mat4 projMat;
uniform mat4 crosshairRotMat;
uniform mat4 crosshairViewMat;

attribute vec3 vPos;
attribute vec3 vNrm;
attribute vec2 vTex;

uniform vec3 crosshairCarPos;

uniform float crosshairScale;

varying vec3 oTex;

void main(void)
{
   vec3 rotatedVert = (crosshairRotMat * vec4(vPos * crosshairScale, 1.0)).xyz;
   vec4 viewVert = crosshairViewMat * vec4(rotatedVert + crosshairCarPos, 1.0);
   //vec4 viewVert = vec4(rotatedVert + carPos + vec3(0.0, 0.0, -12.0), 1.0);
   gl_Position = projMat * vec4( viewVert.xyz, 1.0);

   //oTex = vec3(vTex, max(dot(vNrm, normalize(vec3(-0.5, 1.0, 2.0))), 0.6));
   oTex = vec3(vTex, 1.0);
}

void main_maik(void)
{
    vec3 projVert = (projMat * crosshairRotMat * vec4(vPos * 0.02 + vec3(0.0, 0.0, -2.0), 1.0)).xyz;
    vec3 projCrosshairCtr = (projMat * crosshairViewMat * vec4(vec3(0.0, 0.0, 0.0), 1.0)).xyz;
   gl_Position = vec4( projVert, 1.0);

   oTex = vec3(vTex, max(dot(vNrm, normalize(vec3(0.0, 1.0, 2.0))), 0.6));
}

void main_orig(void)
{
   vec3 rotatedVert = (crosshairRotMat * vec4(vPos * crosshairScale, 1.0)).xyz;
   vec4 viewVert = crosshairViewMat * vec4(rotatedVert + crosshairCarPos, 1.0);
   //vec4 viewVert = vec4(rotatedVert + carPos + vec3(0.0, 0.0, -12.0), 1.0);
   gl_Position = projMat * vec4( viewVert.xyz, 1.0);

   oTex = vec3(vTex, max(dot(vNrm, normalize(vec3(0.0, 1.0, 2.0))), 0.6));
}
// file:crosshair.v.glsl
