%GL_VERSION%

uniform mat4 vpMat;

attribute vec3 vPos;
attribute vec3 vNrm;
attribute vec4 vCol;

uniform float uHeightFactor;
uniform float uDtmNrmLength;

%USE_UNIFORM_BUFFERS%

#ifdef USE_UNIFORM_BUFFERS

layout (std140) uniform ubLightingData
{
   vec4        uLightingParamsBMD;
   vec4        uLightingParamsDTM;
   vec4        uLightingParamsWater;
   vec4        uSunLightColor;
   vec4        uSunLightDirection;
};

#else

uniform vec3 uSunLightDirection;

#endif

varying vec4 oCol;

void main(void)
{
   vec3 pos = vPos;
   pos.z *= uHeightFactor;

   vec3 len3 = vec3( length( vpMat[0].xyz),
                     length( vpMat[1].xyz),
                     length( vpMat[2].xyz));
   float scale = length( len3);

   gl_Position = vpMat * vec4(pos + vNrm * scale * uDtmNrmLength, 1.0);

   oCol = vCol;
}
// file:basic_dtm_norms.v.glsl
