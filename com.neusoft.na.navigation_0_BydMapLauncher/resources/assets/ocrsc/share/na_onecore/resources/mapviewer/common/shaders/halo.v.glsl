//uniform mat4 globeViewMat;
uniform mat4 globeProjMat;

uniform vec3 camPosWorld;
//uniform vec3 sunDirWorld;
uniform float sizeFac;

attribute vec3 vPos;
attribute vec2 vTex;

varying vec4 oTex;
//varying vec3 sunDirView;

void main(void)
{
   oTex = vec4(vTex, (vTex - vec2(0.5, 0.5)) * 2.0);

   // construct plane in view space and transform using projection matrix only
   gl_Position = globeProjMat * vec4(vPos.xy * sizeFac, -length(camPosWorld), 1.0);

   //sunDirView = vec4(globeViewMat * vec4(sunDirWorld, 0.0)).xyz;
}
// file:halo.v.glsl
