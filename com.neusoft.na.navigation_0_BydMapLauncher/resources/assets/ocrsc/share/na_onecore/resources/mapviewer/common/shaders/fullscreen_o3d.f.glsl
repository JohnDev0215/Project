#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D o3dMap;

uniform vec3 uTransparencyCtr;
uniform vec2 uDisplaySize;
uniform vec2 uDisplayOffset;
uniform float uAspectRatio;
uniform float uPitch;

varying vec2 vTex;

void main(void)
{
   float opacity = 1.0;

   if(%O3D_TRANSPARENCY_AROUND_CARSOR%)
   {
      if(uTransparencyCtr.z > -1.0)
      {
         vec2 fragCoord = 2.0 * (gl_FragCoord.xy - uDisplayOffset) / uDisplaySize.xy - vec2(1.0);

         if(%O3D_SIGHT_ELLIPSE%)
         {
            vec2 ctr = vec2(uTransparencyCtr.x, -1.0);
            float a = float(%O3D_SIGHT_ELLIPSE_A%);
            float b = uTransparencyCtr.y + 1.0 + float(%O3D_SIGHT_ELLIPSE_DELTA_B%);
            b *= b;
            fragCoord -= ctr;
            fragCoord *= fragCoord;
            float fragL = sqrt(fragCoord.x / a + fragCoord.y / b);
            opacity = smoothstep(float(%O3D_SIGHT_ELLIPSE_RANGE%), 1.0, fragL);
         }
         else
         {
            vec2 v2c = uTransparencyCtr.xy - fragCoord;
            v2c.y *= uAspectRatio;
            if(%O3D_SIGHT_TUNNEL%)
               v2c.y *= 1.0 - smoothstep(0.2, 0.6, uPitch);  // scale height of area depending on pitch
            float radiusScale  = pow(length(v2c), float(%O3D_RADIUS_EXPONENT%));
            opacity = smoothstep(0.0, float(%O3D_TRANSPARENCY_RADIUS%), radiusScale);
         }
      }
   }

   vec4 col = texture2D( o3dMap, vTex);
   gl_FragColor = vec4(col.rgb, col.a * opacity);
}

// file:fullscreen_o3d.f.glsl
