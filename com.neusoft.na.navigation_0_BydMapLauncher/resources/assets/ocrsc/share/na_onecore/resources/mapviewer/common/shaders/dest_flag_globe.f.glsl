#ifdef GL_ES
precision mediump float;
#endif

varying vec2 oTex;
varying vec4 oPos;

uniform float visibleDepth;
uniform float distanceSurfacePosition;
uniform float uAspectRatio;

uniform sampler2D flagMap;

void main(void)
{
   vec3 pos = oPos.xyz / oPos.w;

   //if (pos.z > visibleDepth && length(vec2(pos.x, pos.y * uAspectRatio)) < distanceSurfacePosition)
   if (pos.z > visibleDepth)
      discard;

   gl_FragColor = texture2D(flagMap, oTex);
}
// file:dest_flag.f.glsl
