uniform float uSkyOffsetY;
uniform vec2 uSkyParameters;        // x: relative heading, y: relative field of view

attribute vec3 aPos;
attribute vec2 aTex;

varying vec2 vTex;

void main(void)
{
   gl_Position = vec4(aPos.x, aPos.y + uSkyOffsetY, 0.5, 1.0);

   vTex = vec2( aTex.x + uSkyParameters.x / uSkyParameters.y, aTex.y );
}
// file:clouds.v.glsl
