#ifdef GL_ES
precision mediump float;
#endif

uniform float uSunLightIntensity;
uniform vec3 uRoofColor;
uniform vec3 uWallColor;
uniform vec3 lightDirection;
uniform vec3 lightAmbient;
uniform vec3 lightDiffuse;
uniform vec3 lightSpecular;
uniform vec3 materialAmbient;
uniform vec3 materialDiffuse;
uniform vec3 materialSpecular;
uniform vec3 uCameraCenterPosition;

varying float vOpacity;
varying float vHeightDimGradientFactor;
varying float vRoof;
varying vec3  vNormal;
varying vec3  vPos;

void main(void)
{
   vec3 finalColor = uWallColor;
   if(vRoof > 0.5)
   {
      finalColor = uRoofColor;
   }

   finalColor *= vHeightDimGradientFactor * uSunLightIntensity * uSunLightIntensity;

   float uTransparency = %EFB_TRANSPARENCY%;
   gl_FragColor = vec4( finalColor, uTransparency * vOpacity);
}
// file:efb_lod1.f.glsl
