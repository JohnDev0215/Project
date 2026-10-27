// shader: external_texstyle_area_lit.vs

uniform mat4  uVPMat;
uniform vec2  uStyleTexelDim;        // 1 / texture width, 1 / texture height
uniform float uStyleTexScaleOff;  // x offset in the style-texture (according to the current scale)
uniform float uBaseDepth;

// x: enabled, y: kA, z: kD, w: kS
uniform vec4  uLightingParamsBMD;
uniform float uLightingEnabled;
uniform vec3  uSunLightColor;
uniform vec3  uSunLightDirection;
uniform float uSunLightIntensity;

uniform sampler2D styleTex;

attribute vec4 aPos;

varying vec4 vCol;

const float add_8bits = 256.0;
const float skip_8bits = 1.0 / add_8bits;

void main(void)
{
   vec2 styleTexData = vec2( aPos.w, floor( aPos.w * skip_8bits));
   styleTexData.x -= styleTexData.y * add_8bits;

   vec2 styleTexOffset= styleTexData * uStyleTexelDim + vec2( uStyleTexScaleOff + uStyleTexelDim.x * 0.5, uStyleTexelDim.y * 0.5);
   vCol= texture2DLod( styleTex, styleTexOffset, 0.0);

   if (vCol.a > 0.0)
   {
      gl_Position = uVPMat * vec4(aPos.xy, aPos.z, 1.0);

      vec3 light;
      if((uLightingParamsBMD.x * uLightingEnabled) > 0.0) {
         float NdotL = max( uSunLightDirection.z, 0.0);
         light= uLightingParamsBMD.y + NdotL * (uLightingParamsBMD.z * uSunLightIntensity * uSunLightColor);
      }
      else
         light = vec3(1.0);

      vCol = vec4(vCol.rgb * light, vCol.a);
   }
   else
   {
      gl_Position = vec4( 0.0, 0.0, 0.0, 0.0);  // discard
   }
}
// file:external_texstyle_area_lit.v.glsl
