#ifdef GL_ES
    precision mediump float;
#endif

uniform mat4 uViewRotationMat;
uniform float uSkyOffsetY;
uniform float uDtmActive;
uniform float uRotWithVehicle;

uniform vec2 uSkyParameters;        // x: relative heading, y: relative field of view

attribute vec3 aPos;
attribute vec3 aTex;

varying vec2 vTex;

void main(void)
{
   gl_Position = vec4(aPos.x, mix(aPos.y, aPos.z, uDtmActive) + mix(uSkyOffsetY, 0.0, uDtmActive), 0.5, 1.0);

   float s0 = %TEX_REPEAT% * uSkyParameters.x;
   float s1 = s0 + uSkyParameters.y * %TEX_REPEAT_SQUARED%;

   float curS = mix(s0, s1, aTex.x);
   vec2 texCoordOff = vec2( curS, aTex.y );
   vec2 texCoordOn = vec2( curS, aTex.z ) - vec2(0.0, uSkyOffsetY + 1.0);

   if(uRotWithVehicle > 0.5)
   {
      vTex = mix(texCoordOff, texCoordOn, uDtmActive);
   }
   else
   {
      vTex.x = aTex.x;
      vTex.y = aTex.y;
   }
}

// file:sky_textured.v.glsl
