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
uniform float uScale;

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
in vec4  aSide;      // i8    - xy: vertex offset direction, z: normalized side flag, w: <unused>
in float aMaxScale;  // float - max scale,
// 16+4+4+4 -> 28 bytes

out vec4  vCol;
out vec3  vLit;
out vec3 vSide;  // x: linewidth extended by 0.5pixel * normal-side-factor; y: line-width
out vec2 vTexTile;
out vec2 vLineOffset;
out float vLineTexGap;

void main(void)
{
   if (uScale <= aMaxScale)
   {
      int scaleIdx = int(uScaleIndex) * 4;  // we have 2*2 pixels per style entry along the X axis
      ivec2 styleBase =  ivec2(aStyle.xy) + ivec2(scaleIdx, 0);
      vec4 styleAppear= texelFetchOffset( styleTex, styleBase + ivec2(uStyleOff.xy), 0, ivec2(1,0));
      vTexTile = %Texel2Float%.0 * styleAppear.xy;
      vLineTexGap= styleAppear.z * (%Texel2Float%.0 / 10.0);  // "/ 10.0" - restore packing trasformation
      vLineOffset= aPos.zw;

      // resolving line width in vertex shader:
      // 1. normal case                                       -> use v.attrib1.xy as style-entry-base + uStyleOff.xy as offset
      // 2. width from parent     if   v.attrib1.zw != (0,0)  -> use v.attrib1.zw as style-entry-base + uStyleOff.zw as offset

      float halfWidth= styleAppear.w * %Texel2Float%.0 * 0.5;
      if (aStyle.zw != uvec2( 0, 0))
      {
         ivec2 masterBase =  ivec2(aStyle.zw) + ivec2(scaleIdx, 0) + ivec2(uStyleOff.zw);
         vec4 widthMeta= texelFetchOffset( styleTex, masterBase, 0, ivec2(1,0));  // @0: rgba, @1: meta
         halfWidth= widthMeta.w * %Texel2Float%.0 * 0.5;
      }

      vSide = vec3(float(aSide.z) * (halfWidth * 2.0 + 1.0), halfWidth * 2.0, aSide.z);

      float fac = (halfWidth + (0.5 * step(0.1, halfWidth))) * uWidthFactor * uPixelFactor;
      vec2 pos = vec2(aPos.xy + vec2(aSide.xy) / aSide.w * fac);

      gl_Position = uVPMat * vec4(pos, 0.0, 1.0);

      vCol= texelFetch( styleTex, styleBase + ivec2(uStyleOff.xy), 0);

      if((uLightingParamsBMD.x * uLightingEnabled) > 0.0)
      {
         float NdotL = max(uSunLightDirection.z, 0.0);
         float coeff = NdotL * (uLightingParamsBMD.z * uSunLightIntensity) + uLightingParamsBMD.y;
         vLit= vCol.rgb * uSunLightColor * coeff;
      }
      else
         vLit= vec3(1.0);
   }
   else
   {
      gl_Position = vec4(0,0,0,0);
   }
}

// file:es30:texstylevs_line_lit.v.glsl
