
attribute vec4 aPos;
attribute vec4 aEdge;
attribute vec4 aColor;

uniform mat4 uVPMat;
uniform mat4 uPrevVPMat;

uniform vec3 uCameraPosition;
uniform vec3 uCameraForward;

uniform float uBoxSize;
uniform float uOpacity;

uniform float uWidth;
uniform float uVelocityStretch;

uniform vec3 uOffset[8];
uniform vec3 uVelocity[8];

varying vec2 vTex;
varying vec4 vColor;

void main(void)
{
   int layerId = int(aPos.w);
   // calculate current position
   vec3 pos = aPos + uOffset[layerId];
   pos = mod(pos, uBoxSize);
   pos -= 0.5 * uBoxSize;

   pos += uCameraForward * 10.0;
   pos += uCameraPosition;

   vec3 topPosWorld = pos - uVelocity[layerId] * uVelocityStretch;

   vec4 bottomPos = (uVPMat * vec4(pos, 1.0));
   vec4 topPos = (uVPMat * vec4(topPosWorld, 1.0));
   vec4 prevTopPos = (uPrevVPMat * vec4(topPosWorld, 1.0));

   prevTopPos = mix(prevTopPos, topPos, 1.0);

   vec4 transformedPos = mix(bottomPos, prevTopPos, aEdge.y);

   vec2 dir = normalize(topPos.xy / topPos.w - bottomPos.xy / bottomPos.w);
   vec2 prevDir = normalize(prevTopPos.xy / prevTopPos.w - bottomPos.xy / bottomPos.w);
   vec2 nrm = vec2(-prevDir.y, prevDir.x);

   transformedPos.xy += nrm * aEdge.x * uWidth;
   gl_Position = transformedPos;
//   gl_Position = vec4(aEdge.xy * 0.75, 0.0, 1.0);

   vTex = aEdge.zw;
   vColor = vec4(aColor.rgb, aColor.a * uOpacity);
}

// file:precipitation.v.glsl
