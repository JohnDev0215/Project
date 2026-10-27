#version %GLver%
// shader: texstylevs_line_lit.vs

uniform mat4 uVPMat;

uniform vec4  uStyleOff;  // xy - relative appearence offset, zw - relative width offset in style texture
uniform float uScaleIndex;
//uniform vec2  uStyleTexelDim;
//uniform float uStyleTexScaleOff;
uniform float uWidthFactor;
uniform float uPixelFactor;
uniform float uPitch;
uniform float uFlattenDtm;
uniform float uHeightFactor;

// x: enabled, y: kA, z: kD, w: kS
uniform vec4  uLightingParamsBMD;
uniform float uLightingEnabled;
uniform vec3  uSunLightDirection;
uniform vec3  uSunLightColor;
uniform float uSunLightIntensity;

uniform sampler2D styleTex;


//in vec4 aPos;       // xy = vertex position, zw = vertex offset direction
//in vec4 aStyleMeta; // xy = style-tex offset; z = maximal scale, w = normalized coords for vertex side (for alpha-blending at the border)
//in vec4 aSpecialMeta; // x = line-len-offset, y = rev-line-len-offset, xy = style-tex offset of the master element (for overlays)
// 3*16-> 48 bytes

in vec4  aPos;       // float - xy: vertex pos, z: line-len-offset, w: rev-line-len-offset
in uvec4 aStyle;     // u8    - xy: style-tex offset, zw: style-tex offset of the master element (for overlays)
in vec4  aSide;      // i8    - xy: vertex offset direction, z: normalized side flag, w: side scale factor
in float aMaxScale;  // float - max scale,
// 16+4+4+4 -> 28 bytes

out vec4  vCol;
out vec3  vLit;
out vec2  vSide;

void main(void)
{
   int scaleIdx = int(uScaleIndex) * 4;  // we have 2*2 pixels per style entry along the X axis
   ivec2 styleBase = ivec2(aStyle.xy) + ivec2(uStyleOff.xy) + ivec2(scaleIdx,0);
   vec4 styleAppear= texelFetchOffset( styleTex, styleBase, 0, ivec2(1,0));  // @0: rgba, @1: meta

   float halfWidth= styleAppear.w * %Texel2Float%.0 * 0.5;
   //float halfWidth= 5.0;

   vCol= texelFetch( styleTex, styleBase, 0);
   //gl_Position = uVPMat * vec4(aPos.xy, 0.0, 1.0);

   vSide = vec2(float(aSide.z) * (halfWidth * 2.0 + 1.0), halfWidth * 2.0);

   float fac = (halfWidth + (0.5 * step(0.1, halfWidth))) * uWidthFactor * uPixelFactor;
   vec2 pos = vec2(aPos.xy + vec2(aSide.xy) / aSide.w * fac); // 0.0000000001 *
   gl_Position = uVPMat * vec4(pos, 0.0, 1.0);

   if((uLightingParamsBMD.x * uLightingEnabled) > 0.0)
   {
      float NdotL = max(uSunLightDirection.z, 0.0);
      float coeff = NdotL * (uLightingParamsBMD.z * uSunLightIntensity) + uLightingParamsBMD.y;
      vLit= vCol.rgb * uSunLightColor * coeff;
   }
   else
      vLit= vec3(1.0);
}

// file:es30:texstylevs_line_lit.v.glsl
