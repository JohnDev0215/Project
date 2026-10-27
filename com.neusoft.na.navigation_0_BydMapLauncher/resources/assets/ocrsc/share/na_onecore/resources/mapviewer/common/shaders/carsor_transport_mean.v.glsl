
attribute vec3 aPos;
attribute vec3 aNrm;
attribute vec2 aTex;

uniform vec3 uCarsorTransportMeanPos;

uniform float uPitch;
uniform float uAspectRatio;

varying vec3  vTex;
varying float vPitch;

void main(void)
{
   vec4 pos = vec4(aPos * vec3(uAspectRatio, 1.0, 1.0), 1.0);

   gl_Position = vec4(pos.xyz + uCarsorTransportMeanPos, 1.0);

   vTex = vec3(aTex, 1.0);
   vPitch = 1.0;
   if(%O3D_SIGHT_TUNNEL%)
   {
      vPitch = 1.0 - smoothstep(0.2, 0.6, uPitch);
   }
}

// file:carsor_transport_mean.v.glsl
